import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Animal } from 'src/app/entidades/animal';
import { AnimalService } from 'src/app/service/animal/animal.service';
import Swal from 'sweetalert2';

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
      Swal.fire({
        title: '¿Estás seguro?',
        text: "¡No podrás revertir esto!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '¡Sí, bórralo!'
      }).then((result) => {
        if (result.isConfirmed) {
          this._animalService.eliminarAnimal(idanimal).subscribe({
            next: (res) => {
              Swal.fire(
                '¡Eliminado!',
                'El animal ha sido eliminado.',
                'success'
              );
              setTimeout(() => {
                Swal.close();
                location.reload();
              }, 2000);
            },
            error: (err) => {
              console.error(err);
            }
          });
        }
      })
    }
}
