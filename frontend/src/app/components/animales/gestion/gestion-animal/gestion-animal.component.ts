import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Animal } from 'src/app/entidades/animal';
import { Protectora } from 'src/app/entidades/protectora';
import { AnimalService } from 'src/app/service/animal/animal.service';
import { ProtectoraService } from 'src/app/service/protectora/protectora.service';

@Component({
  selector: 'app-gestion-animal',
  templateUrl: './gestion-animal.component.html',
  styleUrls: ['./gestion-animal.component.css']
})
export class GestionAnimalComponent {

  @Input() protectora: Protectora;
  @Input() animales: Animal[];
  public listaAnimales : Animal[] = []
  idprotectora : number = 1;

  constructor(private _animalService : AnimalService, private router :Router, private _protrectoraService: ProtectoraService) { 
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

  // public listar(){
  //   this._animalService.listarAnimalPorIdProtectora(this.protectora.idprotectora).subscribe(data => {
  //     this.listaAnimales = data;
  //     console.log(this.listaAnimales);
  //   });
  // }
  public irAltaAnimal() {
    this.router.navigate(['/animales/gestion/alta']);
   }

  //  private animalesPorProtectora() {
  //   this.listaAnimales = this.animales.filter(animal => animal.protectora.idprotectora === this.protectora.idprotectora);
  // }

  public irModificalAnimal(idanimal: number) {
    this.router.navigate(['/animales/gestion/modificar', idanimal]);
   }

   public BorrarAnimal(idanimal: number){
    this._animalService.eliminarAnimal(idanimal).subscribe(() => {
      this.router.navigate(['/animales/gestion']);
    },
    (error) => {
      console.error("Error al eliminar el animal:", error);
    });
    

   }
}
