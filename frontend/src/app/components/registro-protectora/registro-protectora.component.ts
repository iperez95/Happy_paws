import { Component } from '@angular/core';
import { Protectora } from 'src/app/entidades/protectora';
import { Usuario } from 'src/app/entidades/usuario';
import { AuthService } from 'src/app/service/auth/auth.service';
import { AxiosService } from 'src/app/service/axios/axios.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-registro-protectora',
  templateUrl: './registro-protectora.component.html',
  styleUrls: ['./registro-protectora.component.css']
})
export class RegistroProtectoraComponent {
    isSubmitted = false;
    usuario: Usuario = new Usuario();
    protectora: Protectora = new Protectora();

    constructor(
      private axiosService: AxiosService,
      private authService: AuthService,
      private _router: Router,
    ) {}
  
    ngOnInit(): void {
    }
  
    onSubmit(values: any) {
      this.axiosService.request("POST", '/api/usuarioProtectoras', {
        "usuario": this.usuario,
        "protectora": this.protectora
      }).then(response => {
        this.authService.setLoggedIn(true); // Update the loggedIn property
        this.axiosService.setAuthToken(response.data.token); // Save the token in the local storage
        this.isSubmitted = true;
        Swal.fire({
          title: 'Genial!',
          text: 'Te has registrado correctamente, bienvenido a Happy Paws!',
          icon: 'success',
          showConfirmButton: false,
          timer: 2000
        }).then(() => {
          this._router.navigate(['/']);
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

