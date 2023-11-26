import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { VistaFormulario } from 'src/app/entidades/VistaFormulario';
import { PreguntasAdoptante } from 'src/app/entidades/preguntasAdoptante';
import { RespuestasAdoptante } from 'src/app/entidades/respuestasAdoptante';
import { Usuario } from 'src/app/entidades/usuario';
import { AxiosService } from 'src/app/service/axios/axios.service';
import { CuestionarioService } from 'src/app/service/cuestionario/cuestionario.service';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-cuestionario-adopcion',
  templateUrl: './cuestionario-adopcion.component.html',
  styleUrls: ['./cuestionario-adopcion.component.css']
})


export class CuestionarioAdopcionComponent {

  respuestasForm: FormGroup;
  preguntasContestadas: VistaFormulario[];
  preguntas: PreguntasAdoptante[] = [];
  usuario: Usuario;
 
  constructor(
    private fb: FormBuilder,
    private _cuestionarioService: CuestionarioService,
    private _usuarioService : UsuarioService,
    private axiosService : AxiosService,
    private router: Router,
    private route: ActivatedRoute
  ) {}
  
  ngOnInit() {
    if (this._usuarioService.getUserData() == null) {
    } else {
      this.listarPreguntas();
      this.respuestasForm = this.fb.group({
        respuestasArray: this.fb.array([])
      });

      this.usuario = this._usuarioService.getUserData() || {} as Usuario;
      Promise.all([
        this.axiosService.request('GET', "cuestionario/verRespuestas/" + this.usuario.id, null).catch(error => {
          return { respuestas: [] };
        }),
        this._cuestionarioService.obtenerPreguntas().toPromise()
      ]).then(([respuestas, preguntas]) => {
        this.preguntasContestadas = respuestas.data ?? [];
        preguntas.forEach((pregunta :PreguntasAdoptante, index:number) => {
          this.respuestasArray.push(this.fb.group({
            preguntaid: [pregunta.idpregunta],
            respuesta: [this.preguntasContestadas[index] ? this.preguntasContestadas[index].respuesta : ''],
            preguntasAdoptante: this.fb.group({
              pregunta: [pregunta.pregunta]
            })
          }));
        });
      })
    }
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



  //version 2 intentando enviar el token logueado
  onSubmit() { 
    if (!this.respuestasForm.valid) {
      return;
    }
    const respuestas = this.respuestasForm.value.respuestasArray.map((respuesta: { preguntaid: number, respuesta: string, preguntasAdoptante: { pregunta: string } }) => ({  
      // idpregunta: respuesta.preguntaid,
      respuesta: respuesta.respuesta,
      preguntasAdoptante: {
        idpregunta: respuesta.preguntaid,
      },      
    }));

    console.log(respuestas);  

    this._cuestionarioService.enviarRespuestas(respuestas).subscribe({
      next: response => {
        console.log('Respuestas enviadas con éxito', response);
        Swal.fire({
          title: 'Genial!',
          text: 'Respuestas enviadas con éxito',
          icon: 'success',
          showConfirmButton: false,
          timer: 2000
        }).then(() => {
          this.router.navigate(['/adoptante/gestion']);
        })
      },
      error: error => {
        console.error('Error al enviar respuestas', error);
        Swal.fire({
          title: 'Error al enviar respuestas',
          text: error,
          icon: 'error',
        })
      }
    });
  }

  //ONSUBMIT FUNCIONANDO PERO SIN USUARIO EN SESIÓN
  // onSubmit() {
  //   const usuario = this._usuarioService.getUserData();
  //   if (usuario) {
  //     const emailUsuario = usuario.email;
  //     console.log("Email del usuario logueado: " + emailUsuario);
  //   } else {
  //     console.log('No user is logged in');
  //   }

  //   const respuestas = this.respuestasForm.value.respuestasArray.map((respuesta: { preguntaid: number, respuesta: string, preguntasAdoptante: { pregunta: string }, usuario:{idusuario:number} }) => ({  
      
  //     idpregunta: respuesta.preguntaid,
  //     respuesta: respuesta.respuesta,
  //     preguntasAdoptante: {
  //       idpregunta: respuesta.preguntaid,
  //       pregunta: respuesta.preguntasAdoptante.pregunta,
  //     },
  //     //TODODM cambiar el idusuario por el usuario logueado
  //     usuario:{
  //       idusuario: 1,
       
  //     },
  //   }));
  //   console.log(respuestas);
  //   this._cuestionarioService.enviarRespuestas(respuestas).subscribe(
  //     response => {
  //       console.log('Respuestas enviadas con éxito', response);
  //       // Puedes manejar la respuesta del backend según tus necesidades
  //     },
  //     error => {
  //       console.error('Error al enviar respuestas', error);
  //       // Puedes manejar los errores según tus necesidades
  //     }
  //   );
  // }

}
