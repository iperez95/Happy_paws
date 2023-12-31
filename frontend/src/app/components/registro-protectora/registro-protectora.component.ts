import { Component } from '@angular/core';
import { Protectora } from 'src/app/entidades/protectora';
import { Usuario } from 'src/app/entidades/usuario';
import { AuthService } from 'src/app/service/auth/auth.service';
import { AxiosService } from 'src/app/service/axios/axios.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2'
import { Municipio } from 'src/app/entidades/municipio';
import { Provincia } from 'src/app/entidades/provincia';
import { LocationService } from 'src/app/service/localizacion/location.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-registro-protectora',
  templateUrl: './registro-protectora.component.html',
  styleUrls: ['./registro-protectora.component.css']
})
export class RegistroProtectoraComponent {
    isSubmitted = false;
    usuario: Usuario = new Usuario();
    protectora: Protectora = new Protectora();
    municipio : Municipio = new Municipio();
    provincia : Provincia =  new Provincia();
    provincias: Provincia [] = [];
    municipios: Municipio [] = [];
    idMunicipio: number = 0;
    registerForm: FormGroup;
    submitted = false;

    constructor(
      private axiosService: AxiosService,
      private authService: AuthService,
      private _router: Router,
      private _locationService: LocationService,
      private fb: FormBuilder
    ) {}
  
    ngOnInit(): void {
      this.listadoProvincias();
      this.listadoMunicipiosProvincia(null);
      this.registerForm = this.fb.group({
        nombre: ['', Validators.required],
        apellidos: ['', Validators.required],
        email: ['', Validators.required],
        password: ['', Validators.required],
        telefono: ['', Validators.required],
        dni: ['', Validators.required],
        direccion: ['', Validators.required],
        nombreProtectora: ['', Validators.required],
        descripcion: ['', Validators.required],
        direccionProtectora: ['', Validators.required],
        emailProtectora: ['', Validators.required],
        telefonoProtectora: ['', Validators.required],
        urlLogo: [''],
        municipio: ['', Validators.required],
        provincia: ['', Validators.required],
      });
    }
  
    convertirUsuario(values: any) {
      this.usuario.nombre = values.nombre;
      this.usuario.apellidos = values.apellidos;
      this.usuario.email = values.email;
      this.usuario.password = values.password;
      this.usuario.telefono = values.telefono;
      this.usuario.dni = values.dni;
      this.usuario.direccion = values.direccion;
    }
    convertirProtectora(values: any) {
      this.protectora.nombre = values.nombreProtectora;
      this.protectora.descripcion = values.descripcion;
      this.protectora.direccion = values.direccionProtectora;
      this.protectora.email = values.emailProtectora;
      this.protectora.telefono = values.telefonoProtectora;
      this.protectora.urlLogo = values.urlLogo;
      this.idMunicipio = values.municipio;
    }

    onSubmit(values: any) {
      this.convertirUsuario(values);
      this.convertirProtectora(values);
      if (this.registerForm.valid) {
        this.axiosService.request("POST", '/api/usuarioProtectoras', {
          "usuario": this.usuario,
          "protectora": this.protectora,
          "idMunicipio": values.municipio,
        }).then(response => {
          this.authService.setLoggedIn(true); 
          this.axiosService.setAuthToken(response.data.token);
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
            text: error.response.data,
            icon: 'error',
          })
        });
      }
    }

    private listadoProvincias() {
      this._locationService.listarProvincias()
        .subscribe(data => {
          this.provincias = data;          
        });
    }

    listadoMunicipiosProvincia(event: any) {
      //console.log(event.target.value);
      let idProvincia: number = 1;
      if (event != null) {
        console.log(event.target.value);
        idProvincia = event.target.value;
      }

      this._locationService.listarMunicipiosDeUnaProvincia(idProvincia).subscribe(municipios => {
        this.municipios = municipios;
      });
    }
}

