import { NumberSymbol } from '@angular/common';
import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { Animal } from 'src/app/entidades/animal';
import { Protectora } from 'src/app/entidades/protectora';
import { AnimalService } from 'src/app/service/animal/animal.service';
import { ProtectoraService } from 'src/app/service/protectora/protectora.service';
import {UsuarioService} from "../../../../service/usuario/usuario.service";
import {Usuario} from "../../../../entidades/usuario";
import {Multimedia} from "../../../../entidades/multimedia";
import {MultimediaService} from "../../../../service/multimedia/multimedia.service";

@Component({
  selector: 'app-gestion-animal',
  templateUrl: './gestion-animal.component.html',
  styleUrls: ['./gestion-animal.component.css']
})
export class GestionAnimalComponent {

  // Atributos

  public protectora: Protectora;
  public animales: Animal[];
  public listaAnimales : Animal[] = [];
  public idUsuario : number;
  public idProtectora : number;
  public listaFotos: { [idAnimal: number]: Array<{ enlace: string }> } = {};
  public nombreBuscado: string = '';

  // Constructor

  constructor(
    private _animalService : AnimalService,
    private router :Router,
    private _protectoraService: ProtectoraService,
    private _usuarioService: UsuarioService,
    private _multimediaService: MultimediaService
  ) {}

  // Init

  ngOnInit():void {
    this.obtenerIdUsuario();
    this.obtenerIdProtectora(this.idUsuario);
  }

  // Métodos de Datos

  public obtenerIdUsuario():void{
    const user: any = this._usuarioService.getUserData();
    this.idUsuario = user.id;
  }

  public obtenerIdProtectora(idUsuario: number): void {
    this._protectoraService.obtenerProtectoraPorIdUsuario(idUsuario).pipe(
      switchMap(data => {
        this.idProtectora = data.idprotectora;
        return this._animalService.listarAnimalPorIdProtectora(this.idProtectora);
      })
    ).subscribe({
      next: (res) => { this.listaAnimales = res; },
      error: (err) => { console.error(err); },
      complete: () => { this.obtenerFotosAnimales(); }
    });
  }

  public obtenerAnimalesIdProtectora(idProtectora:number):void{
    this._animalService.listarAnimalPorIdProtectora(idProtectora).subscribe(dato => {
      this.listaAnimales = dato;
    });
 }

  public obtenerFotosAnimales(): void {
    let idsAnimales: number[] = [];
    for (let animal of this.listaAnimales) { idsAnimales.push(animal.idanimal); }
    this._multimediaService.recuperarFotosAnimales(idsAnimales).subscribe({
      next: (res) => { this.listaFotos = res.fotosAnimales; },
      error: (err) => { console.log(err); }
    });
  }

  // Método de filtro

  buscarAnimal() {
    if (this.nombreBuscado) {
      let nombreBuscadoLower = this.nombreBuscado.toLowerCase();
      this.listaAnimales = this.listaAnimales.filter(animal => animal.nombre.toLowerCase().includes(nombreBuscadoLower));
    }
  }

  public listar(){
    this._animalService.listarAnimalPorIdProtectora(this.idProtectora).subscribe(dato => {
      this.listaAnimales = dato;
      console.log(this.listaAnimales);
    });
 }

 // Métodos de Acciones

  public irAltaAnimal() {
    this.router.navigate(['/animales/gestion/alta']);
   }

  public irModificalAnimal(idanimal: number) {
    this.router.navigate(['/animales/gestion/modificar', idanimal]);
   }

   public borrarAnimal(idanimal: number){
    this._animalService.eliminarAnimal(idanimal).subscribe({
      next: (res) => {
        location.reload();
      },
      error: (err) => {
        console.error(err);
      }
    });

   }

}
