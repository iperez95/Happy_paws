import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Animal } from 'src/app/entidades/animal';
import { AnimalService } from 'src/app/service/animal/animal.service';
import { Municipio } from 'src/app/entidades/municipio';
import { Provincia } from 'src/app/entidades/provincia';
import { Router } from '@angular/router';
import { LocationService } from 'src/app/service/localizacion/location.service';
import { Raza } from 'src/app/entidades/raza';
import { Tamano } from 'src/app/entidades/tamano';
import { Especie } from 'src/app/entidades/especie';
import { Protectora } from 'src/app/entidades/protectora';
import { Sexo } from 'src/app/entidades/sexo';
import { EspecieService } from 'src/app/service/especie/especie.service';
import { RazaService } from 'src/app/service/raza/raza.service';
import { SexoService } from 'src/app/service/sexo/sexo.service';
import { TamanosService } from 'src/app/service/tamano/tamano.service';


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

  municipio : Municipio = new Municipio();
  provincia : Provincia =  new Provincia();
  raza : Raza = new Raza();
  especie : Especie = new Especie();
  tamano : Tamano = new Tamano();
  sexo: Sexo = new Sexo;

  provinciaSeleccionadaId: number;
  especieSeleccionadaId: number;


  constructor(private fb: FormBuilder, private _animalService : AnimalService, private router: Router, private _locationService: LocationService, private _especieService: EspecieService, private _razaService: RazaService, private _sexoService: SexoService, private _tamanoService: TamanosService) {  
    this.altaForm = this.fb.group({
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
      fechaNacimiento: ['', Validators.required],
      fechaAlta: ['', Validators.required],
      enabled: ['', Validators.required],
      fechaEnabled: ['', Validators.required],
      municipio: ['', Validators.required],
      provincia: ['', Validators.required], 
      //protectora: ['', Validators.required],
      raza: ['', Validators.required],
      especie: ['', Validators.required],
      tamano: ['', Validators.required],
      sexo: ['', Validators.required],
      envio: ['', Validators.required]
    });
   }

   ngOnInit() {
    this.listadoProvincias();
    this.listadoMunicipiosProvincia();
    this.listadoEspecies();
    this.listadoSexos();
    this.listadoTamanos();
    this.listadoRazasPorIdEspecie();
  }

  enabled: boolean = true;

    onSubmit() {
      if (this.altaForm.valid) {
        // Crear instancia de Animal y asignar valores
        const animal: Animal = {
          idanimal: 0,
          nombre: this.altaForm.get('nombre')?.value,
          descripcion: this.altaForm.get('descripcion')?.value,
          fechaNacimiento: this.altaForm.get('fechaNacimiento')?.value,
          fechaAlta: new Date(),
          enabled: this.altaForm.get('enabled')?.value,  // aqui seria true
          fechaEnabled: new Date(),
          municipio: {
            idmunicipio: this.altaForm.get('municipio')?.value,
            municipio: '',
            provincia: {
              idprovincia: this.altaForm.get('provincia')?.value,
              provincia: '', // No es necesario enviar este valor al backend
            },
          },
          protectora: {
            idprotectora: 0, // la tendra que recoger del usuario
            nombre: ''
          },
          raza: {
            idraza: this.altaForm.get('raza')?.value,
            raza: '',
            especie: {
              idespecie: this.altaForm.get('especie')?.value,
              especie: ''
            }
          },
          sexo: {
            idsexo: this.altaForm.get('sexo')?.value,
            sexo: ''
          },
          tamano: {
            idtamano: this.altaForm.get('tamano')?.value,
            tamano: ''
          },
          envio: this.altaForm.get('envio')?.value,
        };
        console.log(animal);
        this.guardarAnimal(animal);
        //this.IrListadoAnimales();
      }
    }


    // IrListadoAnimales(){
    //   this.router.navigate(['/animales/listado']);
    // }

    guardarAnimal(animal: Animal){
      this._animalService.altaAnimal(animal)
      .subscribe({
        next: response => console.log(response),
        error: error => console.log(error),
        complete: () => {
          this.router.navigate(['/animales/listado']),
          console.log('Alta de Animal Realizada')
          }
        })
      }  
    
    private listadoProvincias() {
      this._locationService.listarProvincias()
        .subscribe((data: any[]) => {
          this.provincias = data;
        });
    }

    private listadoMunicipiosProvincia() {
      this.altaForm.get('provincia')?.valueChanges.subscribe(idProvincia => {
        this._locationService.listarMunicipiosDeUnaProvincia(idProvincia).subscribe((municipios: any[]) => {
          this.municipios = municipios;
         
        });
      });
    }  
    
    private listadoEspecies(){
      this._especieService.listarEspecies()
       .subscribe((data: any[]) => {
          this.especies = data;
        });  
    }

    private listadoRazasPorIdEspecie(){
      this.altaForm.get('especie')?.valueChanges.subscribe(idEspecie => {
        this._razaService.listarRazasDeUnaEspecie(idEspecie).subscribe((razas: any[]) => {
          this.razas = razas;
        });
      });
    }

    private listadoSexos(){
      this._sexoService.listarSexos()
      .subscribe((data: any[]) => {
          this.sexos = data;
        });
    }

    private listadoTamanos(){
      this._tamanoService.listarTamanos()
     .subscribe((data: any[]) => {
      this.tamanos = data;
    });
  }

}
