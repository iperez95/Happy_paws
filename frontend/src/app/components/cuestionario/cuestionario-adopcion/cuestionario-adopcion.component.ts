import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Respuestas } from 'src/app/entidades/respuestas';


@Component({
  selector: 'app-cuestionario-adopcion',
  templateUrl: './cuestionario-adopcion.component.html',
  styleUrls: ['./cuestionario-adopcion.component.css']
})
export class CuestionarioAdopcionComponent {

  adopcionForm: FormGroup;
  respuestas: Respuestas = new Respuestas();

  // constructor(private fb: FormBuilder, private protectoraContactService: ProtectoraContactService,private _protectoraService: ProtectoraService, private router: Router, private route: ActivatedRoute) {
  //   this.contactForm = this.fb.group({
  //     name: ['', Validators.required],
  //     email: ['', [Validators.required, Validators.email]],
  //     message: ['', Validators.required],
  //   });
  // }

}
