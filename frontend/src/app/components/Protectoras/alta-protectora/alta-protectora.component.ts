

  import { Component } from '@angular/core';
  import { Protectora } from 'src/app/entidades/protectora';
  import { ProtectoraService } from 'src/app/servicios/protectora.service';
  import { Router } from '@angular/router';

  @Component({
    selector: 'app-alta-protectora',
    templateUrl: './alta-protectora.component.html',
    styleUrls: ['./alta-protectora.component.css']
  })
  export class AltaProtectoraComponent {

    protectora : Protectora = new Protectora();

    constructor(private _protectoraService : ProtectoraService, private router: Router) { }

    IrListadoProtectoras(){
      this.router.navigate(['/protectoras/listado']);
    }

    guardarProtectra(){
      this._protectoraService.altaProtectora(this.protectora)
      .subscribe(
        response => {
          console.log(response);
          this.IrListadoProtectoras();
        },
        error => {
          console.log(error);
          // Do something with the error, such as showing an error message
        }
      );
    }
    
    onSubmit(){
     this.guardarProtectra();
    }
    
  }

