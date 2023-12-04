import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {Animal} from 'src/app/entidades/animal';
import {AnimalService} from 'src/app/service/animal/animal.service';
import {Multimedia} from "../../../entidades/multimedia";
import {MultimediaService} from "../../../service/multimedia/multimedia.service";

@Component({
  selector: 'app-soloperros',
  templateUrl: './soloperros.component.html',
  styleUrls: ['./soloperros.component.css']
})
export class SoloperrosComponent {

  // Atributos

  public listaPerros: Animal[];
  public listaFotos: Multimedia [];

  // Constructor

  constructor(
    private _animalService: AnimalService,
    private _multimediaSerive: MultimediaService,
    private _router: Router) {
  }

  // Init

  ngOnInit(): void {
    this.listar();
  }

  // Métodos

  private listar(): void {
    this._animalService.listarPerros().subscribe({
      next: (res) => { this.listaPerros = res; },
      error: (err) => { console.error(err); },
      complete: () => { this.obtenerFotosAnimales(); }
    });
  }

  irPrincipal() {
    this._router.navigate(['/']);
}

  public obtenerFotosAnimales(): void {
    let idsAnimales: number[] = [];
    for (let perro of this.listaPerros) { idsAnimales.push(perro.idanimal); }
    this._multimediaSerive.recuperarFotosAnimales(idsAnimales).subscribe({
      next: (res) => { this.listaFotos = res.fotosAnimales; },
      error: (err) => { console.log(err); }
    });
  }

}

