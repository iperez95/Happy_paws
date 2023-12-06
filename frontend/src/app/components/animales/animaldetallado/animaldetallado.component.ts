import {Component, Input} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Animal} from 'src/app/entidades/animal';
import {AnimalService} from 'src/app/service/animal/animal.service';
import {Multimedia} from 'src/app/entidades/multimedia';
import axios from 'axios';
import { Adopcion } from 'src/app/entidades/adopcion';
import Swal from 'sweetalert2'
import { AxiosService } from 'src/app/service/axios/axios.service';
import { AuthService } from 'src/app/service/auth/auth.service';
import  {UsuarioService} from 'src/app/service/usuario/usuario.service';
import { Usuario } from 'src/app/entidades/usuario';
import {MultimediaService} from "../../../service/multimedia/multimedia.service";

@Component({
  selector: 'app-animaldetallado',
  templateUrl: './animaldetallado.component.html',
  styleUrls: ['./animaldetallado.component.css']
})
export class AnimaldetalladoComponent {

  // Atributos

  public animal: Animal;
  public id: number;
  public usuario: Usuario = new Usuario();
  public listaFotos: any;

  // Constructor

  constructor(
    private _router:Router,
    private _activateRouter: ActivatedRoute,
    private _animalService: AnimalService,
    private _axiosService: AxiosService,
    private _usuarioService: UsuarioService,
    private _multimediaService: MultimediaService
  ) {}

  //Init

  ngOnInit() {
    this._activateRouter.params.subscribe((params) => {
      this.id = parseInt(params['id']);
      this.getAnimal();
      this.obtenerFotosAnimales();
    })

    const userData = this._usuarioService.getUserData();
      if (userData) {
    this.usuario = userData;
}
  }

  // Métodos

  private getAnimal(): void {
    this._animalService.verAnimal(this.id).subscribe({
      next: animal => {
        this.animal = animal;
      },
      error: err => console.log(err)
    });
  }

  public getAnimalAge(fechaNacimiento: Date): number {
    const birthDate = new Date(fechaNacimiento);
    const today = new Date();
    let age = today.getFullYear() - birthDate.getFullYear();
    const m = today.getMonth() - birthDate.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
      age--;
    }
    return age;
  }

  public obtenerFotosAnimales(): void {
    let idsAnimales: number[] = [this.id];
    this._multimediaService.recuperarFotosAnimales(idsAnimales).subscribe({
      next: (res) => { this.listaFotos = res.fotosAnimales[this.id]; },
      error: (err) => { console.log(err); }
    });
  }

    //Solicitar adopcion
    altaAdopcion(): void {
      const requestBody = { idAnimal: this.id };
  
      this._axiosService.request("POST", '/adopcion/adoptar', requestBody)
        .then(response => {
          Swal.fire({
            title: 'Genial!',
            text: 'La adopción se ha registrado correctamente!',
            icon: 'success',
            showConfirmButton: false,
            timer: 2000
          }).then(() => {
            this._router.navigate(['/']);
          })
        }).catch(error => {
          console.log(error);
          Swal.fire({
            title: 'Algo ha salido mal',
            text: error.response.data,
            icon: 'error',
          })
        });
  
    }

}
