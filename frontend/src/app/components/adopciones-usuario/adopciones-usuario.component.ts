import { Component } from '@angular/core';
import { Adopcion } from 'src/app/entidades/adopcion';
import { AxiosService } from 'src/app/service/axios/axios.service';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';

@Component({
  selector: 'app-adopciones-usuario',
  templateUrl: './adopciones-usuario.component.html',
  styleUrls: ['./adopciones-usuario.component.css']
})
export class AdopcionesUsuarioComponent {
  adopciones: Adopcion[];
  constructor(
    private axiosService: AxiosService,
    private userService: UsuarioService,
  ) {  }

  ngOnInit(): void {
    const idUsuario = this.userService.getUserData()?.id;
    this.axiosService.request('GET',"adopcion/adoptante/" + idUsuario, null).then((response) => {
      this.adopciones = [];
      response.data.forEach((element: { idAdopcion: number; idUsuario: number; nombreAnimal: string; nombreUsuario: string; fechaAdopcion: string; idAnimal: number; idEstadoAdopcion: number; }) => {
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
}
