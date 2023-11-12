import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PreguntasAdoptante } from 'src/app/entidades/preguntasAdoptante';
import { RespuestasAdoptante } from 'src/app/entidades/respuestasAdoptante';
import { CuestionarioService } from 'src/app/service/cuestionario/cuestionario.service';


@Component({
  selector: 'app-cuestionario-adopcion',
  templateUrl: './cuestionario-adopcion.component.html',
  styleUrls: ['./cuestionario-adopcion.component.css']
})


export class CuestionarioAdopcionComponent {

  respuestasForm: FormGroup;

  // respuestas: RespuestasAdoptante[] = [];
  preguntas: PreguntasAdoptante[] = [];
 
  constructor(private fb: FormBuilder, private _cuestionarioService: CuestionarioService,  private router: Router, private route: ActivatedRoute) {}
  
  ngOnInit() {
    // this.initializeForm();
    this.listarPreguntas();
    this.respuestasForm = this.fb.group({
      respuestasArray: this.fb.array([])
    });

    this._cuestionarioService.obtenerPreguntas().subscribe(preguntas => {
      preguntas.forEach((pregunta :PreguntasAdoptante, index:number) => {
        this.respuestasArray.push(this.fb.group({
          preguntaid: [pregunta.idpregunta],
          respuesta: [''],
          preguntasAdoptante: this.fb.group({
            pregunta: [pregunta.pregunta]
          })
        }));
      });
    });
  };

  listarPreguntas() {
    this._cuestionarioService.obtenerPreguntas()
    .subscribe( data => {
      this.preguntas = data;
          })
  }

  get respuestasArray():FormArray {
    return this.respuestasForm.get('respuestasArray') as FormArray;
  }

  onSubmit() {
    const respuestas = this.respuestasForm.value.respuestasArray.map((respuesta: { preguntaid: number, respuesta: string, preguntasAdoptante: { pregunta: string }, usuario:{idusuario:number} }) => ({  
      idpregunta: respuesta.preguntaid,
      respuesta: respuesta.respuesta,
      preguntasAdoptante: {
        idpregunta: respuesta.preguntaid,
        pregunta: respuesta.preguntasAdoptante.pregunta,
      },
      //TODODM cambiar el idusuario por el usuario logueado
      usuario:{
        idusuario: 1
      }
    }));
    console.log(respuestas);
    this._cuestionarioService.enviarRespuestas(respuestas).subscribe(
      response => {
        console.log('Respuestas enviadas con éxito', response);
        // Puedes manejar la respuesta del backend según tus necesidades
      },
      error => {
        console.error('Error al enviar respuestas', error);
        // Puedes manejar los errores según tus necesidades
      }
    );
  }
}
