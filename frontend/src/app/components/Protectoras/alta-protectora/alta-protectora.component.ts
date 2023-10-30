

  import { Component } from '@angular/core';
  import { Protectora } from 'src/app/entidades/protectora';
  import { ProtectoraService } from 'src/app/service/protectora/protectora.service';
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
      .subscribe({
        next: response => console.log(response),
        error: error => console.log(error),
        complete: () => console.log('Alta Realizada')
      })  
    }
    
    onSubmit(){
     this.guardarProtectra();
    }
    
  }

