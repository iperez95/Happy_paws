import { Component } from '@angular/core';
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

  constructor(private _animalService : AnimalService) { 
    this._animalService = _animalService
    //Cargamos la lista de Animales al inicializar el componente
    this.listar()
  }

  public listar(){
    let obs : Observable<any> = this._animalService.listarAnimales();
      
    //Cuando invocamos el método subscribe es cuando ejecutamos la petición 
    //HTTP al servidor. Dentro de método ponemos 2 funciones lambda, next
    //se ejecutará si todo ha ido bien, error se ejecutará si ha habido
    //algún problema
    obs.subscribe({
        next:  respuesta => {
          this.listaAnimales = respuesta;
          console.log(`listar -> ${JSON.stringify(respuesta)}`)
        },
        error: e => {
          this.listaAnimales = []
          console.log(`listar -> No se han podido listar las personas, ${e}`)
          alert(e)
        }
      });
  }

}
