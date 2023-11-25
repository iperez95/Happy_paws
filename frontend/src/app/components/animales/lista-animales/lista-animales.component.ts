import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Animal } from 'src/app/entidades/animal';
import { Raza } from 'src/app/entidades/raza';
import { AnimalService } from 'src/app/service/animal/animal.service';
import { RazaService } from 'src/app/service/raza/raza.service';

@Component({
  selector: 'app-lista-animales',
  templateUrl: './lista-animales.component.html',
  styleUrls: ['./lista-animales.component.css']
})
export class ListaAnimalesComponent {

  public listaAnimales : Animal[] = []
  public listaRazas : Raza[]=[]

  constructor(private _animalService : AnimalService, private router :Router, private _razaService: RazaService) { 
  }

  ngOnInit():void {
    this.listar();
    this.getRazas();
   }

  public listar(){
    this._animalService.listarAnimales().subscribe(dato => {
      this.listaAnimales = dato;
      console.log(this.listaAnimales);
    });    
  }
   public irAltaAnimal() {
    this.router.navigate(['/animales/gestion/alta']);
   }

   private getRazas():void {
    this._razaService.listarRazas().subscribe({
      next: data => {
        this.listaRazas= data;
      },
      error: error => {
        console.error(error);
      }
    });
    }

          
    public buscar(event:any):void{
      const idRaza = event.target.value;
      this._animalService.listarAnimalPorRaza(idRaza).subscribe({
        next: data => {
          this.listaAnimales = data;
         },
        error: error => {
          this.listaAnimales=[]; 
        }

      });

   }


  }
