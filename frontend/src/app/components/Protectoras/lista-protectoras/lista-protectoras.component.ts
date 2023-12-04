import { Component, HostListener, NgModule, Pipe } from '@angular/core';
import { Router } from '@angular/router';

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

   constructor (private _protectoraService:ProtectoraService, _provinciaService:ProvinciaService, private router :Router ) { } 
  
   ngOnInit():void {
    this.listar();
    this.listadoProvincias();
   }

   public listar(){
      this._protectoraService.listarProtectora().subscribe(dato => {
        this.listaProtectoras = dato;
        console.log(this.listaProtectoras);
      });
   }

   public verDetallesProtectora(id: number) {
    this.router.navigate(['/protectora/detalle/' + id]);
   }

   public contactarProtectora(id: number) {
    this.router.navigate(['/protectora/contacto/' + id]);
   }

   public irAltaProtectora() {
    this.router.navigate(['/protectora/alta']);
   }

  public listarProtectorasPorIdProvincia(idProvincia: number) {
    this._protectoraService.listarProtectoraPorIdProvincia(idProvincia)
      .subscribe(data => {
        this.listaProtectoras = data;
        console.log(this.listaProtectoras);
      });
  }

   private listadoProvincias() {
    this._protectoraService.listarProvincias()
      .subscribe(data => {
        this.listaProvincias = data;
        console.log(this.listaProvincias);
      });
  }







   
}
