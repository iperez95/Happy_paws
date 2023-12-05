import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Animal } from 'src/app/entidades/animal';
import { AnimalService } from 'src/app/service/animal/animal.service';

@Component({
  selector: 'app-animalgestion',
  templateUrl: './animalgestion.component.html',
  styleUrls: ['./animalgestion.component.css']
})
export class AnimalgestionComponent {
  
    // Atributos

    @Input() public animal: Animal;
    @Input() public fotos: any;
    
    // Constructor
  
    constructor(private _animalService : AnimalService, private router :Router,) {}

    //Métodos de acciones

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
