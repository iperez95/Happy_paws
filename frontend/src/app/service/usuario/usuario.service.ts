import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AxiosService } from '../axios/axios.service';
import { Usuario } from 'src/app/entidades/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private usuario = new Usuario();
  constructor(private axiosService: AxiosService) { }

  getUserData(): Usuario | null | undefined {
    const tokenData = this.axiosService.readToken();

    if (!tokenData) {
        this.usuario = new Usuario();
        return null;
    }

    this.usuario.email = tokenData.iss;
    this.usuario.nombre = tokenData.nombre;
    this.usuario.apellidos = tokenData.apellidos;
    this.usuario.rol = tokenData.rol;
    this.usuario.id = tokenData.id;

    return this.usuario;
  }
}

export class UsuarioProtectoraService {
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