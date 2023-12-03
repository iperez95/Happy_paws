import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {Animal} from 'src/app/entidades/animal';
import {Raza} from 'src/app/entidades/raza';
import {AnimalService} from 'src/app/service/animal/animal.service';
import {RazaService} from 'src/app/service/raza/raza.service';
import {MultimediaService} from "../../../service/multimedia/multimedia.service";
import {Multimedia} from "../../../entidades/multimedia";

@Component({
  selector: 'app-lista-animales',
  templateUrl: './lista-animales.component.html',
  styleUrls: ['./lista-animales.component.css']
})
export class ListaAnimalesComponent {

  // Atributos

  public listaAnimales: Animal[] = []
  public listaRazas: Raza[] = []
  public listaFotos: Multimedia [];

  // Constructor

  constructor(
    private _animalService: AnimalService,
    private _router: Router,
    private _razaService: RazaService,
    private _multimediaSerive: MultimediaService
  ) {}

  // Init

  ngOnInit(): void {
    this.listar();
    this.getRazas();
  }

  // Métodos

  public listar() {
    this._animalService.listarAnimales().subscribe({
      next: (res) => { this.listaAnimales = res; },
      error: (err) => { console.log(err); },
      complete: () => { this.obtenerFotosAnimales(); }
    });
  }

  public obtenerFotosAnimales(): void {
    let idsAnimales: number[] = [];
    for (let animal of this.listaAnimales) { idsAnimales.push(animal.idanimal); }
    this._multimediaSerive.recuperarFotosAnimales(idsAnimales).subscribe({
      next: (res) => { this.listaFotos = res.fotosAnimales; },
      error: (err) => { console.log(err); }
    });
  }

  // Métodos de Filtro

  private getRazas(): void {
    this._razaService.listarRazas().subscribe({
      next: data => {
        this.listaRazas = data;
      },
      error: error => {
        console.error(error);
      }
    });
  }

  public buscar(event: any): void {
    const idRaza = event.target.value;
    this._animalService.listarAnimalPorRaza(idRaza).subscribe({
      next: data => {
        this.listaAnimales = data;
      },
      error: error => {
        this.listaAnimales = [];
      }
    });
  }

}
