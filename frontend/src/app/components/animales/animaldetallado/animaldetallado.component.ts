import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Animal } from 'src/app/entidades/animal';
import { AnimalService } from 'src/app/service/animal/animal.service';
import { Multimedia } from 'src/app/entidades/multimedia';
import axios from 'axios';
import { Adopcion } from 'src/app/entidades/adopcion';
import Swal from 'sweetalert2'
import { AxiosService } from 'src/app/service/axios/axios.service';
import { AuthService } from 'src/app/service/auth/auth.service';
import  {UsuarioService} from 'src/app/service/usuario/usuario.service';
import { Usuario } from 'src/app/entidades/usuario';

@Component({
  selector: 'app-animaldetallado',
  templateUrl: './animaldetallado.component.html',
  styleUrls: ['./animaldetallado.component.css']
})
export class AnimaldetalladoComponent {


animal: Animal;

id: number;
usuario: Usuario = new Usuario();


fotosMultimedia: Multimedia[] = [];
  adopcion: Adopcion[];

  constructor(private router: Router, 
    private activateRouter: ActivatedRoute, 
    private _animalService: AnimalService,   
    private axiosService: AxiosService,
    private usuarioService: UsuarioService,
    ) {}

  ngOnInit() {
    this.activateRouter.params.subscribe((params) => {
      this.id = parseInt(params['id']);
      this.getAnimal();
      this.listaFotosAnimal(this.id);
    })

    this.usuario = this.usuarioService.getUserData() || {} as Usuario;
  }

  private getAnimal(): void {
    this._animalService.verAnimal(this.id).subscribe({
      next: animal => {
        this.animal = animal;
      },
      error: err => console.log(err)
    });
  }

  getAnimalAge(fechaNacimiento: Date): number {
    const birthDate = new Date(fechaNacimiento);
    const today = new Date();
    let age = today.getFullYear() - birthDate.getFullYear();
    const m = today.getMonth() - birthDate.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
      age--;
    }
    return age;
  }

  public listaFotosAnimal(idAnimal: number) {
    console.log("Id Animal:" + idAnimal);
    this._animalService.fotosAnimal(idAnimal)
      .subscribe(data => {
        this.fotosMultimedia = data;
        console.log(this.fotosMultimedia);
      });
  }
  
  //Solicitar adopcion
  altaAdopcion(): void {
    const requestBody = { idAnimal: this.id };

    this.axiosService.request("POST", '/adopcion/adoptar', requestBody)
      .then(response => {
        Swal.fire({
          title: 'Genial!',
          text: 'La adopción se ha registrado correctamente!',
          icon: 'success',
          showConfirmButton: false,
          timer: 2000
        }).then(() => {
          this.router.navigate(['/']);
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
