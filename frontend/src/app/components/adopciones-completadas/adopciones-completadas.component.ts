import { Component } from '@angular/core';
import { Adopcion } from 'src/app/entidades/adopcion';
import { AxiosService } from 'src/app/service/axios/axios.service';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';

@Component({
  selector: 'app-adopciones-completadas',
  templateUrl: './adopciones-completadas.component.html',
  styleUrls: ['./adopciones-completadas.component.css']
})
export class AdopcionesCompletadasComponent {

  idProtectora: number;
  adopciones: Adopcion[];
  idAdopcion: number;
  constructor(
    private axiosService: AxiosService,
    private userService: UsuarioService,
  ) {  }

  ngOnInit(): void {
    const idProtectora = this.userService.getUserData()?.idProtectora;
    this.axiosService.request('GET',"adopcion/completadas/protectora/" + idProtectora, null).then((response) => {
      this.adopciones = [];
      response.data.forEach((element: { idAdopcion: number; idUsuario: number; nombreAnimal: string; nombreUsuario: string; fechaAdopcion: string; idAnimal: number; idEstadoAdopcion: number;  emailUsuario: string}) => {
        const adopcion = new Adopcion();
        adopcion.idAdopcion = element.idAdopcion;
        adopcion.idUsuario = element.idUsuario;
        adopcion.nombreAnimal = element.nombreAnimal;
        adopcion.nombreUsuario = element.nombreUsuario;
        adopcion.fechaAdopcion = element.fechaAdopcion;
        adopcion.idAnimal = element.idAnimal;
        adopcion.emailUsuario=element.emailUsuario;
        adopcion.idEstadoAdopcion = element.idEstadoAdopcion;
        this.adopciones.push(adopcion);
      });
    }).catch((error: any) => {
      console.log(error);
    });
  }
}