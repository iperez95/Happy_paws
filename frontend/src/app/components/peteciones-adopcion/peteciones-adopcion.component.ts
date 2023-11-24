import { Component } from '@angular/core';
import { Adopcion } from 'src/app/entidades/adopcion';
import { AxiosService } from 'src/app/service/axios/axios.service';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';

@Component({
  selector: 'app-peteciones-adopcion',
  templateUrl: './peteciones-adopcion.component.html',
  styleUrls: ['./peteciones-adopcion.component.css']
})
export class PetecionesAdopcionComponent {
  idProtectora: number;
  adopciones: Adopcion[];
  constructor(
    private axiosService: AxiosService,
    private userService: UsuarioService,
  ) {  }

  ngOnInit(): void {
    const idProtectora = this.userService.getUserData()?.idProtectora;
    this.axiosService.request('GET',"adopcion/protectora/" + idProtectora, null).then((response) => {
      this.adopciones = response.data;
      console.log(this.adopciones);
    }).catch((error: any) => {
    });
  }
}
