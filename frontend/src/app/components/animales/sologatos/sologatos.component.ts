import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Animal } from 'src/app/entidades/animal';
import { AnimalService } from 'src/app/service/animal/animal.service';
import {Multimedia} from "../../../entidades/multimedia";
import {MultimediaService} from "../../../service/multimedia/multimedia.service";

@Component({
  selector: 'app-sologatos',
  templateUrl: './sologatos.component.html',
  styleUrls: ['./sologatos.component.css']
})
export class SologatosComponent {

  // Atributos

  public listaGatos : Animal[];
  public listaGatosHabilitados : Animal[];
  public listaFotos: Multimedia [];

  // Constructor

  constructor(
    private _animalService : AnimalService,
    private _multimediaSerive: MultimediaService,
    private _router: Router
  ) {}

  // Init

  ngOnInit():void {
    this.listar();

   }

  private listar(): void {
    this._animalService.listarGatos().subscribe({
      next: (res) => { 
        this.listaGatos = res; 
        this.listaGatosHabilitados = this.listaGatos.filter(gato => gato.enabled);
      },
      error: (err) => { console.error(err); },
      complete: () => { this.obtenerFotosAnimales(); }
    });
  }

  irPrincipal() {
      this._router.navigate(['/']);
  }

  public obtenerFotosAnimales(): void {
    let idsAnimales: number[] = [];
    for (let gato of this.listaGatosHabilitados) { idsAnimales.push(gato.idanimal); }
    this._multimediaSerive.recuperarFotosAnimales(idsAnimales).subscribe({
      next: (res) => { this.listaFotos = res.fotosAnimales; },
      error: (err) => { console.log(err); }
    });
  }

}
