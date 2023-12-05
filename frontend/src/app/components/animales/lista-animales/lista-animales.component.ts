import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {Animal} from 'src/app/entidades/animal';
import {Raza} from 'src/app/entidades/raza';
import {AnimalService} from 'src/app/service/animal/animal.service';
import {RazaService} from 'src/app/service/raza/raza.service';
import {MultimediaService} from "../../../service/multimedia/multimedia.service";
import {Multimedia} from "../../../entidades/multimedia";
import { Especie } from 'src/app/entidades/especie';
import { Sexo } from 'src/app/entidades/sexo';
import { Tamano } from 'src/app/entidades/tamano';
import { Provincia } from 'src/app/entidades/provincia';
import { EspecieService } from 'src/app/service/especie/especie.service';
import { SexoService } from 'src/app/service/sexo/sexo.service';
import { TamanosService } from 'src/app/service/tamano/tamano.service';
import { ProvinciaService } from 'src/app/service/provincia/provincia.service';

@Component({
  selector: 'app-lista-animales',
  templateUrl: './lista-animales.component.html',
  styleUrls: ['./lista-animales.component.css']
})
export class ListaAnimalesComponent {

  // Atributos de obtención de datos

  public listaAnimales: Animal[] = []
  public listaRazas: Raza[] = []
  public listaFotos: Multimedia [];
  public listEspecies: Especie [];
  public listaSexos: Sexo[] = [];
  public listaTamanyos: Tamano[] = [];
  public listaProvincias: Provincia[] = [];
  public listaAnimalesHabilitados = this.listaAnimales.filter(animal => animal.enabled);

    // Atributos para filtro.

    public especie: string = '';
    public raza: string = '';
    public sexo: string = '';
    public tamanyo: string = '';
    public provincia: string = '';
    public envio: boolean;

  // Constructor

  constructor(
    private _animalService: AnimalService,
    private _router: Router,
    private _especieService: EspecieService,
    private _razaService: RazaService,
    private _sexoService: SexoService,
    private _tamanyoService: TamanosService,
    private _provinciaService: ProvinciaService,
    private _multimediaSerive: MultimediaService
  ) {}


  ngOnInit(): void {
    this.listar();
    this.getEspecies();
    this.getRazas();
    this.getSexo();
    this.getTamanyo();
    this.getProvincia();
  }

  // Métodos



  public listar() {
    this._animalService.listarAnimales().subscribe({
      next: (animales) => { 
        this.listaAnimales = animales; 
        this.actualizarListaAnimales();
      },
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

 private getEspecies(): void {
    this._especieService.listarEspecies().subscribe({
      next: especie => this.listEspecies = especie,
      error: err => console.error(err)
    });
  }

  private getRazas(): void {
    this._razaService.listarRazas().subscribe({
      next: raza => this.listaRazas = raza,
      error: error => console.error(error)
    });
  }

  private getSexo(): void {
    this._sexoService.listarSexos().subscribe({
      next: sexo => this.listaSexos = sexo,
      error: error => console.error(error)
    });
  }

  private getTamanyo(): void {
    this._tamanyoService.listarTamanos().subscribe({
      next: tamanyo => this.listaTamanyos = tamanyo,
      error: error => console.error(error)
    });
  }

  private getProvincia(): void {
    this._provinciaService.listarProvincias().subscribe({
      next: provincia => this.listaProvincias = provincia,
      error: error => console.error(error)
    });
  }

  // Método del filtro

  public buscar(): void {
    this._animalService
      .filtrarAnimales(this.especie, this.raza, this.sexo, this.tamanyo, this.provincia, this.envio)
      .subscribe({
        next: (animales) => {
          this.listaAnimales = animales,
          this.actualizarListaAnimales();
        },
        error: (err) => console.error(err)
      });
  }
  
  // Métodos de setteo

  public actualizarEspecie(event: any) {
    this.especie = event.target.value;
  }

  public actualizarRaza(event: any) {
    this.raza = event.target.value;
  }

  public actualizarSexo(event: any) {
    this.sexo = event.target.value;
  }

  public actualizarTamanyo(event: any) {
    this.tamanyo = event.target.value;
  }

  public actualizarProvincia(event: any) {
    this.provincia = event.target.value;
  }

  public actualizarEnvio(event: any) {
    this.envio = event.target.value === 'true';
  }

  public actualizarListaAnimales(){
    this.listaAnimalesHabilitados = this.listaAnimales.filter(animal => animal.enabled);
  }

  // Métodos de navegación

  public irPrincipal() {
    this._router.navigate(['/']);
  }

  public reload(): void {
    window.location.reload();
  }
}
