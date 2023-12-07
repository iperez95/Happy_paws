import { Component } from '@angular/core';
import { Adopcion } from 'src/app/entidades/adopcion';
import { AxiosService } from 'src/app/service/axios/axios.service';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';
import { MatDialog } from '@angular/material/dialog';
import { ModalCuestionarioComponent } from '../modal-cuestionario/modal-cuestionario.component';
import Swal from 'sweetalert2';
import { AnimalService } from 'src/app/service/animal/animal.service';
import { Animal } from 'src/app/entidades/animal';

@Component({
  selector: 'app-peteciones-adopcion',
  templateUrl: './peteciones-adopcion.component.html',
  styleUrls: ['./peteciones-adopcion.component.css']
})
export class PetecionesAdopcionComponent {
  idProtectora: number;
  adopciones: Adopcion[];
  idAdopcion: number;
  constructor(
    private axiosService: AxiosService,
    private userService: UsuarioService,
    public dialog: MatDialog,
    private _animal: AnimalService
  ) {}

  openDialog(idUsuario: number): void {
    const dialogRef = this.dialog.open(ModalCuestionarioComponent, {
      data: idUsuario,
    });
    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
    });
  }

  ngOnInit(): void {
    const idProtectora = this.userService.getUserData()?.idProtectora;
    this.axiosService
      .request('GET', 'adopcion/encurso/protectora/' + idProtectora, null)
      .then((response) => {
        this.adopciones = response.data;
      })
      .catch((error: any) => {
        console.log(error);
      });
  }

  approveAdoption(adopcion: Adopcion) {
    this.axiosService
      .request('GET', 'adopcion/aprobar/' + adopcion.idAdopcion, null)
      .then((response) => {
        Swal.fire({
          title: 'Genial!',
          text: 'La adopcion ha sido aprobada!',
          icon: 'success',
          showConfirmButton: false,
          timer: 2000,
        }).then(() => {
          this.editarEstadoAnimal(adopcion.idAnimal);
        });
      })
      .catch((error) => {
        Swal.fire({
          title: 'Ha ocurrido un error!',
          text: 'Por favor, intentelo de nuevo mas tarde',
          icon: 'error',
          showConfirmButton: false,
          timer: 2000,
        }).then(() => {
          window.location.reload();
        });
      });
  }

  // Método que camgia el estado de animal a false
  public editarEstadoAnimal(idAnimal: number): void {
    this._animal.verAnimal(idAnimal).subscribe({
      next: (animal) => {
        animal.enabled = false;
        this._animal.modificarAnimal(animal.idanimal, animal).subscribe({
          next: () => {
            window.location.reload();
          },
          error: (error) => {
            console.log(error);
          },
        });
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  rejectAdoption(idAdopcion: number) {
    this.axiosService
      .request('GET', 'adopcion/rechazar/' + idAdopcion, null)
      .then((response) => {
        Swal.fire({
          title: 'Correcto!',
          text: 'La adopcion ha sido rechazada!',
          icon: 'success',
          showConfirmButton: false,
          timer: 2000,
        }).then(() => {
          window.location.reload();
        });
      })
      .catch((error) => {
        Swal.fire({
          title: 'Ha ocurrido un error!',
          text: 'Por favor, intentelo de nuevo mas tarde',
          icon: 'error',
          showConfirmButton: false,
          timer: 2000,
        }).then(() => {
          window.location.reload();
        });
      });
  }
}
