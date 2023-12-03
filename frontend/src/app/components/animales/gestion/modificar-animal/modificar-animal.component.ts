import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Animal } from 'src/app/entidades/animal';
import { Especie } from 'src/app/entidades/especie';
import { Municipio } from 'src/app/entidades/municipio';
import { Protectora } from 'src/app/entidades/protectora';
import { Provincia } from 'src/app/entidades/provincia';
import { Raza } from 'src/app/entidades/raza';
import { Sexo } from 'src/app/entidades/sexo';
import { Tamano } from 'src/app/entidades/tamano';
import { AnimalService } from 'src/app/service/animal/animal.service';
import { EspecieService } from 'src/app/service/especie/especie.service';
import { LocationService } from 'src/app/service/localizacion/location.service';
import { ProtectoraService } from 'src/app/service/protectora/protectora.service';
import { RazaService } from 'src/app/service/raza/raza.service';
import { SexoService } from 'src/app/service/sexo/sexo.service';
import { TamanosService } from 'src/app/service/tamano/tamano.service';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';

@Component({
  selector: 'app-modificar-animal',
  templateUrl: './modificar-animal.component.html',
  styleUrls: ['./modificar-animal.component.css']
})
export class ModificarAnimalComponent {

animal: Animal = new Animal();


altaForm: FormGroup;
provincias: Provincia [] = [];
municipios: Municipio [] = [];
razas: Raza [] = [];
especies: Especie [] = [];
tamanos: Tamano [] = [];
sexos: Sexo [] = [];
municipio: Municipio = new Municipio();
provincia: Provincia = new Provincia();
raza: Raza = new Raza();
especie: Especie = new Especie();
tamano: Tamano = new Tamano();
sexo: Sexo = new Sexo;
enabled: boolean = true;
envio: boolean;
public protectora: Protectora;

constructor(private _animalService: AnimalService, private router: Router,
  private _locationService: LocationService, private _especieService: EspecieService,
  private _razaService: RazaService, private _sexoService: SexoService, 
  private _tamanoService: TamanosService, private _usuarioService: UsuarioService,
  private _protectoraService: ProtectoraService,private route:ActivatedRoute){}

onSubmit(){
  this.actualizarAnimal();
  this.irGestionAnimal();
}

ngOnInit(): void {
  const id = Number(this.route.snapshot.paramMap.get('id'));
  this.obtenerAnimal(id);

  

  

  this.listadoProvincias();
  this.listadoMunicipiosProvincia();
  this.listadoEspecies();
  this.listadoSexos();
  this.listadoTamanos();
  this.listadoRazasPorIdEspecie();
}

 private obtenerAnimal(id: number) {
  this._animalService.verAnimal(id)
    .subscribe({
      next: animal => this.animal = animal,
      error: error => console.log(error),
      complete: () => console.log('Obtención de animal realizada')
    })
    console.log("Animal almacenado :" + this.animal)
}

  actualizarAnimal( ) {
    this._animalService.modificarAnimal(this.animal.idanimal, this.animal)
      .subscribe({
        next: dato => console.log(dato),
        error: error => console.log(error),
        complete: () => {
          console.log('Animal modificado correctamente');
          
        }
      })
  }

  irGestionAnimal() {
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this.router.navigate(['/protectora/gestion']);
    }); 
  }

  private listadoProvincias() {
    this
      ._locationService
      .listarProvincias()
      .subscribe((data: any[]) => {
        this.provincias = data;
      });
  }

  private listadoMunicipiosProvincia() {
    this
      .altaForm
      .get('provincia')
      ?.valueChanges
      .subscribe(idProvincia => {
        this
          ._locationService
          .listarMunicipiosDeUnaProvincia(idProvincia)
          .subscribe((municipios: any[]) => {
            this.municipios = municipios;
          });
      });
  }

  private listadoEspecies() {
    this
      ._especieService
      .listarEspecies()
      .subscribe((data: any[]) => {
        this.especies = data;
      });
  }

  private listadoRazasPorIdEspecie() {
    this
      .altaForm
      .get('especie')
      ?.valueChanges
      .subscribe(idEspecie => {
        this
          ._razaService
          .listarRazasDeUnaEspecie(idEspecie)
          .subscribe((razas: any[]) => {
            this.razas = razas;
          });
      });
  }

  private listadoSexos() {
    this
      ._sexoService
      .listarSexos()
      .subscribe((data: any[]) => {
        this.sexos = data;
      });
  }

  private listadoTamanos() {
    this._tamanoService.listarTamanos()
      .subscribe((data: any[]) => {
        this.tamanos = data;
      });
  }

  public irFotosAnimal(id: number) {
    this.router.navigate(['/animales/gestion/subirfotoanimal/' + id]);
   }

}
