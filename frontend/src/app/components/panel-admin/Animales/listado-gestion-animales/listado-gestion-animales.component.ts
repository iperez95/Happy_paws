import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Animal } from 'src/app/entidades/animal';
import { AnimalAdminService } from 'src/app/service/panel-admin/animal-admin.service';

@Component({
  selector: 'app-listado-gestion-animales',
  templateUrl: './listado-gestion-animales.component.html',
  styleUrls: ['./listado-gestion-animales.component.css']
})
export class ListadoGestionAnimalesComponent {

  listaAnimales: Animal[]=[];

  constructor(private _animalAdminService:AnimalAdminService, private router:Router) { }

  ngOnInit():void{
    this.listar();

  }

  public listar(){
    this._protectoraAdminService.listaProtectoras().subscribe(dato => { 
      this.listaProtectoras = dato;
      console.log(this.listaProtectoras);
    });
  }

}
