import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Animal } from 'src/app/entidades/animal';
import { AnimalService } from 'src/app/service/animal/animal.service';

@Component({
  selector: 'app-soloperros',
  templateUrl: './soloperros.component.html',
  styleUrls: ['./soloperros.component.css']
})
export class SoloperrosComponent {
    
  public listaPerros : Animal[] = []


  constructor(private _animalService : AnimalService, private router :Router, ) { 
  }

  ngOnInit():void {
    this.listar();

   }

  public listar(){
    this._animalService.listarPerros().subscribe(dato => {
      this.listaPerros = dato;
    });    
  }

}

