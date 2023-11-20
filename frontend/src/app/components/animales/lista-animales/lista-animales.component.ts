import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Animal } from 'src/app/entidades/animal';
import { AnimalService } from 'src/app/service/Animal/animal.service';

@Component({
  selector: 'app-lista-animales',
  templateUrl: './lista-animales.component.html',
  styleUrls: ['./lista-animales.component.css']
})
export class ListaAnimalesComponent {

  listaAnimales : Animal[] = []

  constructor(private _animalService : AnimalService, private router :Router) { 
    //this._animalService = _animalService
    //Cargamos la lista de Animales al inicializar el componente
    //this.listar()
  }

  ngOnInit():void {
    this.listar();
   }

  public listar(){
    this._animalService.listarAnimales().subscribe(dato => {
      this.listaAnimales = dato;
      console.log(this.listaAnimales);
    });    
  }

  public verDetallesAnimal(id: number) {
    this.router.navigate(['animal/verUno/', id]);
   }

   public irAltaAnimal() {
    this.router.navigate(['/animal/gestion/alta']);
   }

  // public listar(){
  //   let obs : Observable<any> = this._animalService.listarAnimales();
  //   //Cuando invocamos el método subscribe es cuando ejecutamos la petición 
  //   //HTTP al servidor. Dentro de método ponemos 2 funciones lambda, next
  //   //se ejecutará si todo ha ido bien, error se ejecutará si ha habido
  //   //algún problema
  //   obs.subscribe({
  //       next:  respuesta => {
  //         this.listaAnimales = respuesta;
  //         console.log(`listar -> ${JSON.stringify(respuesta)}`)
  //       },
  //       error: e => {
  //         this.listaAnimales = []
  //         console.log(`listar -> No se han podido listar las personas, ${e}`)
  //         alert(e)
  //       }
  //     });
  // }

}
