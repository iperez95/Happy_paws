import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AxiosService } from '../axios/axios.service';
import { Usuario } from 'src/app/entidades/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  constructor(private axiosService: AxiosService) { }

  getUserData(): Usuario | null | undefined {
    const tokenData = this.axiosService.readToken();

    if (!tokenData) {
        return null;
    }
    console.log(tokenData);
    const usuario = new Usuario();
    usuario.email = tokenData.iss;
    usuario.nombre = tokenData.nombre;
    usuario.apellidos = tokenData.apellidos;

    return usuario;
  }
}