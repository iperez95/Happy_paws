import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/entidades/usuario';
import { AxiosService } from 'src/app/service/axios/axios.service';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';

@Component({
  selector: 'app-perfil-usuario',
  templateUrl: './perfil-usuario.component.html',
  styleUrls: ['./perfil-usuario.component.css']
})
export class PerfilUsuarioComponent {
  usuario: Usuario;
  constructor(
    private usuarioService: UsuarioService,
    private axiosService: AxiosService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    const user = this.usuarioService.getUserData();
    if (user) {
      this.axiosService.request('GET', "/api/usuarios/"+user.id, null).then((response: any) => {
        console.log(response);
        const usuario = new Usuario();
        usuario.id = response.data.idusuaario;
        usuario.nombre = response.data.nombre;
        usuario.apellidos = response.data.apellidos;
        usuario.email = response.data.email;
        usuario.telefono = response.data.telefono;
        usuario.direccion = response.data.direccion;
        usuario.dni = response.data.dni;
        
        this.usuario = usuario;
      }).catch((error: any) => {
      });
    } else {
      this.router.navigate(['/']);
    }
  }
}
