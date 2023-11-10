   import { Component } from '@angular/core';
  import { Protectora } from 'src/app/entidades/protectora';
  import { ProtectoraService } from 'src/app/service/protectora/protectora.service';
  import { Router } from '@angular/router';
import { Form, FormBuilder, FormGroup, Validators } from '@angular/forms';

  @Component({
    selector: 'app-alta-protectora',
    templateUrl: './alta-protectora.component.html',
    styleUrls: ['./alta-protectora.component.css']
  })
  export class AltaProtectoraComponent {

    altaForm: FormGroup;
    protectora : Protectora = new Protectora();

    constructor(private fb: FormBuilder, private _protectoraService : ProtectoraService, private router: Router) {
      this.altaForm = this.fb.group({
        nombre: ['', Validators.required],
        direccion: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        descripcion: ['', Validators.required],
      });
     }

    IrListadoProtectoras(){
      this.router.navigate(['/protectora/todas']);
    }

    guardarProtectora(){
      this._protectoraService.altaProtectora(this.protectora)
      .subscribe({
        next: response => console.log(response),
        error: error => console.log(error),
        complete: () => console.log('Alta Realizada')
      })  
    }
    
    onSubmit(){
      if (this.altaForm.valid) {        
        this.protectora.nombre = this.altaForm.get('nombre')?.value;
        this.protectora.direccion = this.altaForm.get('direccion')?.value;
        this.protectora.email = this.altaForm.get('email')?.value;
        this.protectora.descripcion = this.altaForm.get('descripcion')?.value;
        this.guardarProtectora();
        this.IrListadoProtectoras();
      }
    }  

}

