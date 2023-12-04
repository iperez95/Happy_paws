import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Animal } from 'src/app/entidades/animal';
import { Multimedia } from 'src/app/entidades/multimedia';
import { AnimalService } from 'src/app/service/animal/animal.service';
import swal from 'sweetalert2';
import {MultimediaService} from "src/app/service/multimedia/multimedia.service";

@Component({
  selector: 'app-subirfoto-animal',
  templateUrl: './subirfoto-animal.component.html',
  styleUrls: ['./subirfoto-animal.component.css']
})
export class SubirfotoAnimalComponent {

  multimedia: Multimedia = new Multimedia();
  id: number;
  animal: Animal = new Animal();
  fotosMultimedia: Multimedia[] = [];
  public fotos: FileList;

  constructor  (
    private _animalService: AnimalService,
    private _router: Router,
    private activateRouter: ActivatedRoute,
    private _multimediaService: MultimediaService    
  ) {}

  ngOnInit() {
    this.activateRouter.params.subscribe((params) => {
      this.id = parseInt(params['id']);
      this.getAnimal();
      this.listaFotosAnimal(this.id);
    })
  }

  //Métodos
  private getAnimal(): void {
    this._animalService.verAnimal(this.id).subscribe({
      next: animal => {
        this.animal = animal;
      },
      error: err => console.log(err)
    });
  }

  public listaFotosAnimal(idAnimal: number): void {
    this._animalService.fotosAnimal(idAnimal)
      .subscribe(data => {
        this.fotosMultimedia = data;
      });
  }

  seleccionarFotos(event: any): void {
    this.fotos = event.target.files;
  }

  public guardarFotos(): void {
    if (this.fotos) {
      const formData = new FormData();
      // añado las fotos.
      for (let i = 0; i < this.fotos.length; i++) {
        formData.append('files', this.fotos[i]);
      }
      // Añado el ID del animal.
      formData.append("id", this.id.toString());
      // Guardo las fotos en la bbdd.
      this._multimediaService.subirFotosAnimal(formData).subscribe({
        error: (err) => {
          console.error('error:' + err);
        },
        complete: () => {
          swal.fire('Foto subida', `La foto se ha subido con éxito`, 'success');
          window.location.reload();
        }
      });
    }
  }

  public borrarFoto(id: number): void {
    swal.fire({
      title: '¿Estás seguro?',
      text: "No podrás revertir esto!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, borrarlo!'
    }).then((result) => {
      if (result.isConfirmed) {
        this._animalService.borrarFoto(id).subscribe({
          error: error => console.log(error),
          complete: () => {
            swal.fire('Realizado', `La protectora ha sido dada de baja correctamente`, 'success');
            window.location.reload();
          }
        })
      } else if (result.isDenied) {
        swal.fire("Los cambios no se han guardado", "", "info");
      }
    });
  }

  irAtras(idanimal: number) {
    this._router.navigate(['/animales/gestion/modificar/', idanimal]);
}
  

  // borrarFoto(id: number) {
  //   this._animalService.borrarFoto(id).subscribe(
  //     (response) => {
  //       console.log(response);
  //       swal.fire('Foto borrada', `La foto se ha borrado con éxito`, 'success');
  //     },
  //     (error) => {
  //       console.error(error);
  //       swal.fire('Error', `Hubo un error al borrar la foto`, 'error');
  //     }
  //   );
  // }
}
