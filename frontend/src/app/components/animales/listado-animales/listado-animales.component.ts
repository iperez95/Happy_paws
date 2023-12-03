import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Animal } from 'src/app/entidades/animal';
import { AnimalDto } from 'src/app/entidades/animalDto';
import { Provincia } from 'src/app/entidades/provincia';
import { AnimalService } from 'src/app/service/animal/animal.service';
import { ProvinciaService } from 'src/app/service/provincia/provincia.service';

@Component({
  selector: 'app-listado-animales',
  templateUrl: './listado-animales.component.html',
  styleUrls: ['./listado-animales.component.css']
})
export class ListadoAnimalesComponent {

  listaAnimales: AnimalDto[] = [];
  listaProvincias: Provincia[]=[];
  provinciaSeleccionada: string;
  provinciaSeleccionadaId: number;   


  constructor (private _animalService:AnimalService, _provinciaService:ProvinciaService, private router :Router ) { } 
  
  ngOnInit():void {
   this.listarDto();
   this.listadoProvincias();
  }

  public listarDto(){
     this._animalService.listarAnimalesDto().subscribe(dato => {
       this.listaAnimales = dato;
       console.log(this.listaAnimales);
     });
  }


  public verDetallesAnimal(id: number) {
   this.router.navigate(['/animales/verUno/' + id]);
  }

  // public irAltaProtectora() {
  //  this.router.navigate(['/protectora/alta']);
  // }

 public listarAnimalesPorIdProvincia(idProvincia: number) {
   this._animalService.listarAnimalPorIdProvincia(idProvincia)
     .subscribe(data => {
       this.listaAnimales = data;
       console.log(this.listaAnimales);
     });
 }

  private listadoProvincias() {
   this._animalService.listarProvincias()
     .subscribe(data => {
       this.listaProvincias = data;
       console.log(this.listaProvincias);
     });
 }





}
