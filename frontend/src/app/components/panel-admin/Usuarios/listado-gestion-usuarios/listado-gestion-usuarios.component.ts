import { Component } from '@angular/core';
import { Router } from '@angular/router';
import axios from 'axios';
import { Usuario } from 'src/app/entidades/usuario';
import { AxiosService } from 'src/app/service/axios/axios.service';

@Component({
  selector: 'app-listado-gestion-usuarios',
  templateUrl: './listado-gestion-usuarios.component.html',
  styleUrls: ['./listado-gestion-usuarios.component.css']
})
export class ListadoGestionUsuariosComponent {
  usuarios: Usuario[];
  ngOnInit() {
    this.getUsuarios();
  }

  constructor(
    private router: Router,
    private axiosService: AxiosService
  ) { }

  getUsuarios(): void {
    this.axiosService.request('GET', 'api/admin/usuarios/todos', null).then((response) => {
      this.usuarios = [];
        response.data.forEach((element: {
          id: string;
          apellidos: string;
          nombre: string;
          email: any;
          dni: string;
          telefono: string;
          direccion: string;
          rol: string;
          enabled: number;
        }) => {
          const usuario = new Usuario();
          usuario.id = parseInt(element.id);
          usuario.apellidos = element.apellidos;
          usuario.nombre = element.nombre;
          usuario.email = element.email;
          usuario.dni = element.dni;
          usuario.telefono = element.telefono;
          usuario.direccion = element.direccion;
          usuario.rol = element.rol;
          usuario.enabled = element.enabled;
          this.usuarios.push(usuario);
        });
    }).catch((error: any) => {
      console.error('There was an error!', error);
    });
  }

  public busquedaPorEmail(email: string): void {
    this.axiosService.request('GET', `api/admin/usuarios/busquedaporemail/${email}`, null).then((response) => {
      this.usuarios = [];
        response.data.forEach((element: {
          id: string;
          apellidos: string;
          nombre: string;
          email: any;
          dni: string;
          telefono: string;
          direccion: string;
          rol: string;
          enabled: number;
        }) => {
          const usuario = new Usuario();
          usuario.id = parseInt(element.id);
          usuario.apellidos = element.apellidos;
          usuario.nombre = element.nombre;
          usuario.email = element.email;
          usuario.dni = element.dni;
          usuario.telefono = element.telefono;
          usuario.direccion = element.direccion;
          usuario.rol = element.rol;
          usuario.enabled = element.enabled;
          this.usuarios.push(usuario);
        });
    }).catch((error: any) => {
      console.error('There was an error!', error);
    });
  }

  getColor(estado: string): string {
    // return color based on estado
    if (estado === 'Inactivo') {
      return 'red';
    } else if (estado === 'Activo') {
      return 'green';
    } else {
      return 'black';
    }
  }

  public cambiarEstadoUsuario(idusuario: number): void {
    this.axiosService.request('PUT', `api/admin/usuario/cambiarestado/${idusuario}`, null).then((response) => {
      const index = this.usuarios.findIndex(usuario => usuario.id === idusuario);
      this.usuarios[index] = Object.assign(new Usuario, response.data);
    }).catch((error: any) => {
      console.error('There was an error!', error);
    });
  }

  editarUsuario(idUsuario: number) {
    this.router.navigate(['admin/gestion/usuario/editar/', idUsuario]);
  }
} 
