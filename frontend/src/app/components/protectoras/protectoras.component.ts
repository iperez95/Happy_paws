import { Component } from '@angular/core';
import { Protectora } from 'src/app/entidades/protectora';
import { ProtectoraService } from 'src/app/servicios/protectora.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-protectoras',
  templateUrl: './protectoras.component.html',
  styleUrls: ['./protectoras.component.css']
})
export class ProtectorasComponent {

  // //Listado de protectoras
  listaProtectoras: Protectora[]=[];
  
  //Datos que recogemos del formulario
    idProtectora: number = -1 ;
    idEstadoProtectora: number=0;
    nombre: string ="";
    direccion: string="";
    urlLogo: string="";
    email: string="";

    constructor(private _protectoraService : ProtectoraService) { 
      this._protectoraService= _protectoraService
    }

    public listar(){
      let obs : Observable<any> = this._protectoraService.listar();
        
      //Cuando invocamos el método subscribe es cuando ejecutamos la petición 
      //HTTP al servidor. Dentro de método ponemos 2 funciones lambda, next
      //se ejecutará si todo ha ido bien, error se ejecutará si ha habido
      //algún problema
      obs.subscribe({
          next:  respuesta => {
            this._protectoraService = respuesta;
            console.log(`listar -> ${JSON.stringify(respuesta)}`)
          },
          error: e => {
            this.listaProtectoras = []
            console.log(`listar -> No se han podido listar las personas, ${e}`)
            alert(e)
          }
        });
    }
    // public listar(){
    //   this._protectoraService.listar().subscribe({
    //     next: respuesta => {
    //       this.listaProtectoras = respuesta;
    //       console.log(`listar -> ${JSON.stringify(respuesta)}`)
    //     },
    //     error: e =>{
    //       this.listaProtectoras =[]
    //       console.log (`listar -> Nose han podido listar las protectoras: ${e}`)
    //       alert (e)
    //     }
    //   });
    // }

    // public insertar(){
    //   let protectora = new Protectora();
    //   protectora.idProtectora = this.idProtectora;
    //   protectora.idEstadoProtectora = this.idEstadoProtectora;
    //   protectora.nombre = this.nombre;
    //   protectora.direccion = this.direccion;
    //   protectora.urlLogo = this.urlLogo;
    //   protectora.email = this.email;
    //   this._protectoraService.insertar(protectora).subscribe({
    //     next: respuesta => {
    //       this.listar();
    //       console.log(`insertar -> ${JSON.stringify(respuesta)}`)
    //     },
    //     error: e =>{
    //       console.log (`insertar -> Nose ha podido insertar la protectora: ${e}`)
    //       alert (e)
    //     }
    //   });
    // }

}
