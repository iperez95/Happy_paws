import { Component } from '@angular/core';
import { Usuario } from 'src/app/entidades/usuario';
import { AuthService } from 'src/app/service/auth/auth.service';
import { AxiosService } from 'src/app/service/axios/axios.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2'
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent {
  isSubmitted = false;
  usuario: Usuario = new Usuario();
  registerForm: FormGroup;
  submitted = false;

  constructor(
    private axiosService: AxiosService,
    private authService: AuthService,
    private _router: Router,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      nombre: ['', Validators.required],
      apellidos: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      telefono: ['', Validators.required],
      dni: ['', Validators.required],
      direccion: ['', Validators.required],
    });
  }

  onSubmit(values: any) {
    this.submitted = true;
    if (this.registerForm.valid) {
      this.axiosService.request("POST", '/api/usuarios', values).then(response => {
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
    } else {
      Swal.fire({
        title: 'Algo ha salido mal',
        text: 'Debes rellenar todos los campos',
        icon: 'error',
      })

    }
  }
}
