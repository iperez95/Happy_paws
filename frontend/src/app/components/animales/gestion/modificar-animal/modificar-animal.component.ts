import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
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

  // Atributos
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

  // Constructor
constructor(private _animalService: AnimalService, private router: Router,
  private _locationService: LocationService, private _especieService: EspecieService,
  private _razaService: RazaService, private _sexoService: SexoService, 
  private _tamanoService: TamanosService, private fb: FormBuilder,
  private _protectoraService: ProtectoraService,private route:ActivatedRoute){}



ngOnInit(): void {

  const idAnimal: number = Number(this.route.snapshot.paramMap.get('id'));
  this.obtenerAnimal(idAnimal);
  this.altaForm = this.fb.group({
    nombre: [''],
    especie: [''],
    raza: [''],
    sexo: [''],
    tamano: [''],
    descripcion: [''],
    provincia: [''],
    municipio: [''],
    enabled: [''],
    envio: ['']
  });
  this.listadoProvincias();
  this.listadoMunicipiosProvincia();
  this.listadoEspecies();
  this.listadoSexos();
  this.listadoTamanos();
  this.listadoRazasPorIdEspecie();  
}

  // Métodos

  onSubmit() {
    this.actualizarAnimal();
  }

private actualizarAnimal(): void {
  if (this.altaForm.valid) {
    // Crear instancia de Animal y asignar valores
    const animal: Animal = {
      idanimal: this.animal.idanimal,
      descripcion: this.altaForm.get('descripcion')?.value,
      enabled: this.altaForm.get('enabled')?.value,
      envio: this.altaForm.get('envio')?.value,
      fechaAlta: new Date,
      fechaNacimiento: this.animal.fechaNacimiento,
      municipio: this.obtenerMunicipio(this.altaForm.get('municipio')?.value),
      protectora: this.animal.protectora,
      nombre: this.altaForm.get('nombre')?.value,
      raza: this.obtenerRaza(this.altaForm.get('raza')?.value),
      sexo: this.obtenerSexo(this.altaForm.get('sexo')?.value),
      tamano: this.obtenerTanyo(this.altaForm.get('tamano')?.value),
      fecha_enabled: new Date,
    };
    this.guardarAnimal(animal);
  }
}

private guardarAnimal(animal: Animal): void {
  this._animalService.modificarAnimal(animal.idanimal, animal).subscribe({
    error: error => console.log(error),
    complete: () => this.irGestionAnimal()
  });
}

private obtenerAnimal(id: number) {
  this._animalService.verAnimal(id).subscribe({
    next: animal => this.animal = animal,
    error: error => console.log(error)
  });
}

private obtenerSexo(id: number): Sexo | null {
  let valor = null;
  for (let sexo of this.sexos) {
    if (sexo.idsexo == id) {
      valor = sexo;
      break;
    }
  }
  return valor;
}

private obtenerTanyo(id: number): Tamano | null {
  let valor = null;
  for (let tamanyo of this.tamanos) {
    if (tamanyo.idtamano == id) {
      valor = tamanyo;
      break;
    }
  }
  return valor;
}

private obtenerRaza(id: number): Raza | null {
  let valor = null;
  for (let raza of this.razas) {
    if (raza.idraza == id) {
      valor = raza;
      break;
    }
  }
  return valor;
}

private obtenerMunicipio(id: number): Municipio | null {
  let valor = null;
  for (let municipio of this.municipios) {
    if (municipio.idmunicipio == id) {
      valor = municipio;
      break;
    }
  }
  return valor;
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
  
  public irGestionAnimal(): void {
    this.router.navigate(['/protectora/gestion']);
  }
  public irFotosAnimal(id: number) {
    this.router.navigate(['/animales/gestion/subirfotoanimal/' + id]);
   }

}
