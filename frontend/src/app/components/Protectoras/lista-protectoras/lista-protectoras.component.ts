import { Component } from '@angular/core';
import { Protectora } from 'src/app/entidades/protectora';
import { ProtectoraService } from 'src/app/service/protectora/protectora.service';

@Component({
  selector: 'app-lista-protectoras',
  templateUrl: './lista-protectoras.component.html',
  styleUrls: ['./lista-protectoras.component.css']
})
export class ListaProtectorasComponent {

   // //Listado de protectoras
   listaProtectoras: Protectora[]=[];

   

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
   
}
