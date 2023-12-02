import { Component } from '@angular/core';
import { Adopcion } from 'src/app/entidades/adopcion';
import { AxiosService } from 'src/app/service/axios/axios.service';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';

@Component({
  selector: 'app-listado-adopciones',
  templateUrl: './listado-adopciones.component.html',
  styleUrls: ['./listado-adopciones.component.css']
})
export class ListadoAdopcionesComponent {
  adopciones: Adopcion[];
  idAdopcion: number;
constructor(
  private axiosService: AxiosService,
  private userService: UsuarioService,
) { }

ngOnInit(): void {
this.getAdopciones();
}

getAdopciones(): void {
    this.axiosService.request('GET', "adopcion/todas", {}).then((response) => {
    this.adopciones = [];
    response.data.forEach((element: any) => {
      const adopcion = new Adopcion();
      adopcion.idAdopcion = element.idAdopcion;
      adopcion.idUsuario = element.idUsuario;
      adopcion.nombreAnimal = element.nombreAnimal;
      adopcion.nombreUsuario = element.nombreUsuario;
      adopcion.fechaAdopcion = element.fechaAdopcion;
      adopcion.idAnimal = element.idAnimal;
      adopcion.idEstadoAdopcion = element.idEstadoAdopcion;
      this.adopciones.push(adopcion);
    });
  }).catch((error: any) => {
    console.log(error);
  });

  
  }

  public adopcionPorID(idAdopcion: string): void {
    const id = Number(idAdopcion);
    if (!isNaN(id)) {
      this.axiosService.request('GET', `/adopcion/${id}`, null).then((response) => {
        this.adopciones = [];
        const adopcion = new Adopcion();
        adopcion.idAdopcion = response.data.idAdopcion;
        adopcion.idUsuario = response.data.idUsuario;
        adopcion.nombreAnimal = response.data.nombreAnimal;
        adopcion.nombreUsuario = response.data.nombreUsuario;
        adopcion.fechaAdopcion = response.data.fechaAdopcion;
        adopcion.idAnimal = response.data.idAnimal;
        adopcion.idEstadoAdopcion = response.data.idEstadoAdopcion;
        this.adopciones.push(adopcion);
      }).catch((error: any) => {
        console.error('There was an error!', error);
      });
    } else {
      console.error('Invalid ID');
    }
  }

}
