import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Animal } from 'src/app/entidades/animal';
import { Multimedia } from 'src/app/entidades/multimedia';
import { AnimalService } from 'src/app/service/animal/animal.service';
import swal from 'sweetalert2';

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

  private fotoSeleccionada:File;

  

  constructor  (
    private _animalService: AnimalService,
    private router: Router,
    private activateRouter: ActivatedRoute,
    
  ) { }

  ngOnInit() {
    this.activateRouter.params.subscribe((params) => {
      this.id = parseInt(params['id']);
      this.getAnimal();
      this.listaFotosAnimal(this.id);
    })
  }

  private getAnimal(): void {
    this._animalService.verAnimal(this.id).subscribe({
      next: animal => {
        this.animal = animal;
      },
      error: err => console.log(err)
    });
  }

  public listaFotosAnimal(idAnimal: number) {
    console.log("Id Animal:" + idAnimal);
    this._animalService.fotosAnimal(idAnimal)
      .subscribe(data => {
        this.fotosMultimedia = data;
        console.log("lista de fotos " + this.fotosMultimedia);
      });
  }

  seleccionarFoto(event: Event) {
    const target = event.target as HTMLInputElement;
    const files = target.files as FileList;
    this.fotoSeleccionada = files[0];
    console.log(this.fotoSeleccionada);
  }

  subirFoto(){
    this._animalService.subirFoto(this.fotoSeleccionada, this.id)
      .subscribe({
        next: multimedia => {
          this.multimedia = multimedia;
          console.log(this.multimedia);
        },
        error: error => console.log(error),
        complete: () => {
          swal.fire('Foto subida', `La foto se ha subido con éxito`, 'success');
          this.irSubirFotoAnimal(); 
        }
      })
  }

  borrarFoto(id: number) {
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
        this._animalService.borrarFoto(id)
          .subscribe({
            next: dato => console.log(dato),
            error: error => console.log(error),
            complete: () => {
              console.log('Baja realizada');
              swal.fire('Realizado', `La protectora ha sido dada de baja correctamente`, 'success');
              this.irSubirFotoAnimal();
            }
          })
      } else if (result.isDenied) {
        swal.fire("Los cambios no se han guardado", "", "info");
      }
    });
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


  irSubirFotoAnimal() {
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this.router.navigate(['/animales/gestion/subirfotoanimal/', this.id]);
    }); 
  }

}
