import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Protectora } from 'src/app/entidades/protectora';
import { ProtectoraAdminService } from 'src/app/service/panel-admin/protectora-admin.service';
import { ProtectoraService } from 'src/app/service/protectora/protectora.service';

@Component({
  selector: 'app-listado-gestion-protectoras',
  templateUrl: './listado-gestion-protectoras.component.html',
  styleUrls: ['./listado-gestion-protectoras.component.css']
})
export class ListadoGestionProtectorasComponent {

  listaProtectoras: Protectora[]=[];


  constructor(private _protectoraAdminService:ProtectoraAdminService, private router:Router) { }

  ngOnInit():void{
    this.listar();

  }

  public listar(){
    this._protectoraAdminService.listaProtectoras().subscribe(dato => { 
      this.listaProtectoras = dato;
      console.log(this.listaProtectoras);
    });
  }

  public busquedaPorNombre(nombre:string){
    this._protectoraAdminService.busquedaPorNombre(nombre).subscribe(dato => {
      this.listaProtectoras = dato;
      console.log("Este el nombre buscado: "+nombre)
      console.log("Esta es la lista de protectoras devuelta: "+this.listaProtectoras);
    });
  }

  public editarProtectora(id:number){
    this.router.navigate(['admin/gestion/protectoras/editar/', id]);
  }

  public inactivarProtectora(id:number){
    this._protectoraAdminService.inactivarProtectora(id, this.listaProtectoras[id]).subscribe(dato => {
      this.listar();
    });
  }

  public activarProtectora(id:number){
    this._protectoraAdminService.activarProtectora(id, this.listaProtectoras[id]).subscribe(dato => {
      this.listar();
    });
  }

  public pendienteProtectora(id:number){
    this._protectoraAdminService.pendienteProtectora(id, this.listaProtectoras[id]).subscribe(dato => {
      this.listar();
    });
  }

 
}
