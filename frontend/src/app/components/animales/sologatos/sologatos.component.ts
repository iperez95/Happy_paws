import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Animal } from 'src/app/entidades/animal';
import { AnimalService } from 'src/app/service/animal/animal.service';

@Component({
  selector: 'app-sologatos',
  templateUrl: './sologatos.component.html',
  styleUrls: ['./sologatos.component.css']
})
export class SologatosComponent {

  public listaGatos : Animal[] = []


  constructor(private _animalService : AnimalService, private router :Router, ) { 
  }

  ngOnInit():void {
    this.listar();

   }

  public listar(){
    this._animalService.listarGatos().subscribe(dato => {
      this.listaGatos = dato;
    });    
  }


}
