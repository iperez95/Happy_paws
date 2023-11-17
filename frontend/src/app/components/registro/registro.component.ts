import { Component } from '@angular/core';
import { Usuario } from 'src/app/entidades/usuario';
import { AuthService } from 'src/app/service/auth/auth.service';
import { AxiosService } from 'src/app/service/axios/axios.service';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent {
  isSubmitted = false;
  usuario: Usuario = new Usuario();
  constructor(
    private axiosService: AxiosService,
    private authService: AuthService,
  ) {}

  ngOnInit(): void {
  }

  onSubmit(values: any) {
    console.log(values);
    this.axiosService.request("POST", '/api/usuarios', values).then(response => {
      this.authService.setLoggedIn(true); // Update the loggedIn property
      this.axiosService.setAuthToken(response.data.token); // Save the token in the local storage
      this.isSubmitted = true;
      Swal.fire({
        title: 'Genial!',
        text: 'Te has registrado correctamente, bienvenido a Happy Paws!',
        icon: 'success',
      })
    }).catch(error => {
      Swal.fire({
        title: 'Algo ha salido mal',
        text: error.response.data.message,
        icon: 'error',
      })
    });
  }
}
