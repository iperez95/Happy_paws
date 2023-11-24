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

    constructor(
      private axiosService: AxiosService,
      private authService: AuthService,
      private _router: Router,
      private _locationService: LocationService
    ) {}
  
    ngOnInit(): void {
      this.listadoProvincias();
      this.listadoMunicipiosProvincia(null);
    }
  
    onSubmit(values: any) {
      console.log(values);
      console.log(this.idMunicipio);
      this.axiosService.request("POST", '/api/usuarioProtectoras', {
        "usuario": this.usuario,
        "protectora": this.protectora,
        "idMunicipio": this.idMunicipio,
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
          text: error.response.data,
          icon: 'error',
        })
      });
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

