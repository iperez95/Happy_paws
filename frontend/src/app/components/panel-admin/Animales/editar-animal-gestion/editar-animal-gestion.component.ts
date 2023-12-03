import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Animal } from 'src/app/entidades/animal';
import { AnimalDto } from 'src/app/entidades/animalDto';
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
import { AnimalAdminService } from 'src/app/service/panel-admin/animal-admin.service';
import { ProtectoraService } from 'src/app/service/protectora/protectora.service';
import { RazaService } from 'src/app/service/raza/raza.service';
import { SexoService } from 'src/app/service/sexo/sexo.service';
import { TamanosService } from 'src/app/service/tamano/tamano.service';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';

@Component({
  selector: 'app-editar-animal-gestion',
  templateUrl: './editar-animal-gestion.component.html',
  styleUrls: ['./editar-animal-gestion.component.css']
})
export class EditarAnimalGestionComponent {

  animal:Animal = new Animal();

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
  public protectora: Protectora;

  constructor(
    private fb: FormBuilder, private _animalAdminService: AnimalAdminService, private router: Router,
    private _locationService: LocationService, private _especieService: EspecieService,
    private _razaService: RazaService, private _sexoService: SexoService, private _tamanoService: TamanosService,
    private _usuarioService: UsuarioService, private _protectoraService: ProtectoraService,
    private route:ActivatedRoute
  ) {}

  public ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.obtenerAnimal(id);

    this.altaForm = this.fb.group({
      nombre: [''],
      descripcion: [''],
      // fechaNacimiento: [''],
      // fechaEnabled: [new Date, ],
      // municipio: ['', Validators.required],
      // provincia: ['', Validators.required],
      raza: [''],
      especie: [''],
      tamano: [''],
      sexo: [''],
      envio: ['']
    });

    this.listadoProvincias();
    this.listadoMunicipiosProvincia();
    this.listadoEspecies();
    this.listadoSexos();
    this.listadoTamanos();
    this.listadoRazasPorIdEspecie();
    
  
    // this.obtenerProtectora();

  }
  // ngOnInit(){
  //   const id = Number(this.route.snapshot.paramMap.get('id'));
  //   this.obtenerAnimal(id);
  //   this.listadoEspecies();
   
  //   // this.listadoRazasDeUnaEspecie();
  //   this.listadoSexos();
  //   console.log("Listado Sexos: "+this.sexos);
  //   this.listadoTamanos();  
  // }
  
  onSubmit() {
  this.actualizarAnimal();
  this.irGestion();    
  }

  private actualizarAnimal(): void {
    if (this.altaForm.valid) {
      // Crear instancia de Animal y asignar valores
      const animal: Animal = {
        idanimal: this.animal.idanimal,
        descripcion: this.altaForm.get('descripcion')?.value,
        enabled: true,
        envio: this.altaForm.get('envio')?.value,
        fechaAlta: new Date,
        fechaNacimiento: this.altaForm.get('fechaNacimiento')?.value,
        municipio: this.obtenerMunicipio(this.altaForm.get('municipio')?.value),
        protectora: this.protectora,
        nombre: this.altaForm.get('nombre')?.value,
        raza: this.obtenerRaza(this.altaForm.get('raza')?.value),
        sexo: this.obtenerSexo(this.altaForm.get('sexo')?.value),
        tamano: this.obtenerTanyo(this.altaForm.get('tamano')?.value),
        fecha_enabled: new Date,
      };

      this.guardarAnimal(animal);
    }
  }

  guardarAnimal(animal:Animal) {
    this._animalAdminService.actualizarAnimal(animal.idanimal, animal)
      .subscribe({
        next: dato => console.log(dato),
        error: error => console.log(error),
        complete: () => {
          console.log(animal);
          console.log('Modificación realizada');
        }
      })
  }


  


  private obtenerAnimal(id: number) {
    this._animalAdminService.obtenerAnimalPorId(id)
      .subscribe({
        next: animal => this.animal = animal,
        error: error => console.log(error),
        complete: () => console.log('Obtención de animal realizada')
      })
      console.log("Animal Almacenado :" + this.animal)
  }

  // actualizarAnimal() {
  //   this._animalAdminService.actualizarAnimal(this.animal.idanimal, this.animal)
  //     .subscribe({
  //       next: dato => console.log(dato),
  //       error: error => console.log(error),
  //       complete: () => {
  //         console.log(this.animal);
  //         console.log('Modificación realizada');
  //       }
  //     })
  // }

  // actualizarAnimalDto() {
  //   this._animalAdminService.actualizarAnimalDto(this.animal.idanimal, this.animalDto)
  //     .subscribe({
  //       next: dato => console.log(dato),
  //       error: error => console.log(error),
  //       complete: () => {
  //         console.log(this.animalDto);
  //         console.log('Modificación realizada');
  //       }
  //     })
  // }

  actualizarRazas(event: Event) {
    const idEspecieSeleccionada = (event.target as HTMLSelectElement).value;
    // Puedes convertir el idEspecieSeleccionada a un número si es necesario
    const idEspecieNumero = parseInt(idEspecieSeleccionada, 10);

    this._razaService.listarRazasDeUnaEspecie(idEspecieNumero)
      .subscribe(data => {
        this.razas = data;
      });
  }

  irGestion() {
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this.router.navigate(['/admin/gestion']);
    }); 
  }

  // private obtenerProtectora(): void {
  //   const user: Usuario | null | undefined = this._usuarioService.getUserData();
  //   if (user) {
  //     this._protectoraService.obtenerProtectoraPorIdUsuario(user.id).subscribe({
  //       next: (res) => {
  //         this.protectora = res;
  //       },
  //       error: (err) => {
  //         console.error(err)
  //       }
  //     });
  //   }
  // }

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
  

 
      

}
