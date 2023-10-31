import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Protectora } from 'src/app/entidades/protectora';
import { Provincia } from 'src/app/entidades/provincia';
import { ProtectoraService } from 'src/app/service/protectora/protectora.service';

@Component({
  selector: 'app-lista-protectoras',
  templateUrl: './lista-protectoras.component.html',
  styleUrls: ['./lista-protectoras.component.css']
})
export class ListaProtectorasComponent {

  // //Listado de protectoras
  listaProtectoras: Protectora[]=[];
  provinciasSeleccionadas: Provincia[] = [];
  provinciaId: string;
  



   

   constructor (private _protectoraService:ProtectoraService ) { } 
  
   ngOnInit():void {
    this.listar();
   }

   private listar(){
      this._protectoraService.listarProtectora().subscribe(dato => {
        this.listaProtectoras = dato;
        console.log(this.listaProtectoras);
      });
   }

   buscarProtectorasPorProvincia() {
    this._protectoraService.listarProtectoraPorProvincia(this.provinciaId)
      .subscribe(data => {
        Object.assign(this.listaProtectoras, data);
        this.listaProtectoras = this.listaProtectoras.filter(protectora => protectora.municipio.provincia.provincia === this.provinciaId);
      });
  }

  //  public listadoProvincias() {
  //   this._protectoraService.listarProvincias()
  //     .subscribe(data => {
  //       this.listaProvincias = data;
  //       console.log(this.listaProvincias);
  //     });
  // }

 

  // provinciaSeleccionada(provincia) {
  //   this.provinciaId = provincia;
  //   this.buscarProtectorasPorProvincia(provincia);
  // } 

 
   
}
