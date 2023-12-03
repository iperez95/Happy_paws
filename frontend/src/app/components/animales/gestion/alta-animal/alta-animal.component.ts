import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Animal} from 'src/app/entidades/animal';
import {AnimalService} from 'src/app/service/animal/animal.service';
import {Municipio} from 'src/app/entidades/municipio';
import {Provincia} from 'src/app/entidades/provincia';
import {Router} from '@angular/router';
import {LocationService} from 'src/app/service/localizacion/location.service';
import {Raza} from 'src/app/entidades/raza';
import {Tamano} from 'src/app/entidades/tamano';
import {Especie} from 'src/app/entidades/especie';
import {Protectora} from 'src/app/entidades/protectora';
import {Sexo} from 'src/app/entidades/sexo';
import {EspecieService} from 'src/app/service/especie/especie.service';
import {RazaService} from 'src/app/service/raza/raza.service';
import {SexoService} from 'src/app/service/sexo/sexo.service';
import {TamanosService} from 'src/app/service/tamano/tamano.service';
import {UsuarioService} from "../../../../service/usuario/usuario.service";
import {ProtectoraService} from "../../../../service/protectora/protectora.service";
import {Usuario} from "../../../../entidades/usuario";
import {formatDate} from "@angular/common";
import {MultimediaService} from "../../../../service/multimedia/multimedia.service";
import Swal from 'sweetalert2';


@Component({
  selector: 'app-alta-animal',
  templateUrl: './alta-animal.component.html',
  styleUrls: ['./alta-animal.component.css']
})

export class AltaAnimalComponent {

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
  public fotos: FileList;


  constructor(
    private fb: FormBuilder, private _animalService: AnimalService, private router: Router,
    private _locationService: LocationService, private _especieService: EspecieService,
    private _razaService: RazaService, private _sexoService: SexoService, private _tamanoService: TamanosService,
    private _usuarioService: UsuarioService, private _protectoraService: ProtectoraService,
    private _multimediaService: MultimediaService
  ) {}


  public ngOnInit(): void {

    this.altaForm = this.fb.group({
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
      fechaNacimiento: ['', Validators.required],
      fechaEnabled: [new Date, Validators.required],
      municipio: ['', Validators.required],
      provincia: ['', Validators.required],
      raza: ['', Validators.required],
      especie: ['', Validators.required],
      tamano: ['', Validators.required],
      sexo: ['', Validators.required],
      envio: ['', Validators.required],
      fotos: ['', Validators.required]
    });

    this.listadoProvincias();
    this.listadoMunicipiosProvincia();
    this.listadoEspecies();
    this.listadoSexos();
    this.listadoTamanos();
    this.listadoRazasPorIdEspecie();
    this.obtenerProtectora();

  }

  public onSubmit(): void {
    this.crearAnimal();
    
  }

  private crearAnimal(): void {
    if (this.altaForm.valid) {
      // Crear instancia de Animal y asignar valores
      const animal: Animal = {
        idanimal: 0,
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
        tamano: this.obtenerTamanyo(this.altaForm.get('tamano')?.value),
        fecha_enabled: new Date,
      };

      this.guardarAnimal(animal);
      
    }
  }

  private obtenerProtectora(): void {
    const user: Usuario | null | undefined = this._usuarioService.getUserData();
    if (user) {
      this._protectoraService.obtenerProtectoraPorIdUsuario(user.id).subscribe({
        next: (res) => {
          this.protectora = res;
        },
        error: (err) => {
          console.error(err)
        }
      });
    }
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

  private obtenerTamanyo(id: number): Tamano | null {
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

  private guardarAnimal(animal: Animal): void {
    this._animalService.altaAnimal(animal).subscribe({
      next: (res) => {
        const idAnimal: string = res.idAnimal;
        this.guardarFotos(idAnimal);
      },
      error: (err) => {
        console.error(err);
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

  public seleccionarFotos(event: any): void {
    this.fotos = event.target.files;
  }

  private guardarFotos(idAnimal: string): void {
    if (this.fotos) {
      const formData = new FormData();
      // añado las fotos.
      for (let i = 0; i < this.fotos.length; i++) { formData.append('files', this.fotos[i]); }
      // Añado el ID del animal.
      formData.append("id", idAnimal);
      // Guardo las fotos en la bbdd.
      this._multimediaService.subirFotosAnimal(formData).subscribe({
        error: (err) => { console.error('error:'+err); },
        complete: () => {
          Swal.fire({
            icon: 'success',
            title: '¡¡ Perfecto !!',
            text: 'La mascota ha sido creada satisfactoriamente',
            showConfirmButton: false,
            timer:2000
          })
          .then(()=> {
            this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
              this.router.navigate(['/protectora/gestion']);
          })
        }).catch(error =>
          Swal.fire({
            icon: 'error',
            title: 'Algo ha salido mal',
            text: error.response.data.message,            
          })
        )}
      });
      } else {            
        Swal.fire({
          icon: 'error',
          title: 'Algo ha salido mal',
          text: 'Comprueba que has rellanado todos los datos por favor',            
        })
        
    
    }
  }
}
