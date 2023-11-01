import { Component, NgModule, Pipe } from '@angular/core';

import { Protectora } from 'src/app/entidades/protectora';
import { Provincia } from 'src/app/entidades/provincia';
import { ProtectoraService } from 'src/app/service/protectora/protectora.service';
import { ProvinciaService } from 'src/app/service/provincia/provincia.service';





@Component({
  selector: 'app-lista-protectoras',
  templateUrl: './lista-protectoras.component.html',
  styleUrls: ['./lista-protectoras.component.css'],
 
})


export class ListaProtectorasComponent {

  // //Listado de protectoras
  listaProtectoras: Protectora[]=[];
  listaProvincias: Provincia[]=[];
  provinciaSeleccionada: string;
  provinciaSeleccionadaId: number;
  



   

   constructor (private _protectoraService:ProtectoraService, _provinciaService:ProvinciaService ) { } 
  
   ngOnInit():void {
    this.listar();
    this.listadoProvincias();
   }

   listar(){
      this._protectoraService.listarProtectora().subscribe(dato => {
        this.listaProtectoras = dato;
        console.log(this.listaProtectoras);
      });
   }

   buscarProtectorasPorProvincia(provinciaSeleccionada: string) {
    this._protectoraService.listarProtectoraPorProvincia(this.provinciaSeleccionada)
      .subscribe(data => {
        Object.assign(this.listaProtectoras, data);
        this.listaProtectoras = this.listaProtectoras.filter(protectora => protectora.municipio.provincia.provincia === this.provinciaSeleccionada);
      });
  }

  // buscarProtectorasPorIdProvincia() {
  //   this._protectoraService.listarProtectoraPorIdProvincia(this.provinciaSeleccionadaId)
  //     .subscribe(data => {
  //       Object.assign(this.listaProtectoras, data);
  //       this.listaProtectoras = this.listaProtectoras.filter(protectora => protectora.municipio.provincia.provincia === this.provinciaSeleccionada);
  //     });
  // }

  public listarProtectorasPorIdProvincia(idProvincia: number) {
    this._protectoraService.listarProtectoraPorIdProvincia(idProvincia)
      .subscribe(data => {
        this.listaProtectoras = data;
        console.log(this.listaProtectoras);
      });
  }

   public listadoProvincias() {
    this._protectoraService.listarProvincias()
      .subscribe(data => {
        this.listaProvincias = data;
        console.log(this.listaProvincias);
      });
  }


   
}
