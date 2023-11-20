import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Animal } from 'src/app/entidades/animal';
import { AnimalService } from 'src/app/service/Animal/animal.service';
import { Municipio } from 'src/app/entidades/municipio';
import { Provincia } from 'src/app/entidades/provincia';
import { Router } from '@angular/router';
import { LocationService } from 'src/app/service/localizacion/location.service';

@Component({
  selector: 'app-alta-animal',
  templateUrl: './alta-animal.component.html',
  styleUrls: ['./alta-animal.component.css']
})
export class AltaAnimalComponent {

  altaForm: FormGroup;

  provincias: Provincia [] = [];
  municipios: Municipio [] = [];

  municipio : Municipio = new Municipio();
  provincia : Provincia =  new Provincia();

  provinciaSeleccionadaId: number;

  constructor(private fb: FormBuilder, private _animalService : AnimalService, private router: Router, private _locationService: LocationService) {  
    this.altaForm = this.fb.group({
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
      provincia: ['', Validators.required], 
      municipio: ['', Validators.required] 
    });
   }

   ngOnInit() {
    this.listadoProvincias();
    this.listadoMunicipiosProvincia();
  }



    onSubmit() {
      if (this.altaForm.valid) {
        // Crear instancia de Animal y asignar valores
        const animal: Animal = {
          idanimal: 0,
          nombre: this.altaForm.get('nombre')?.value,
          descripcion: this.altaForm.get('descripcion')?.value,
          fechaNacimiento: this.altaForm.get('fechaNacimiento')?.value,
          fechaAlta: this.altaForm.get('fechaAlta')?.value,
          enabled: this.altaForm.get('enabled')?.value,
          fechaEnabled: this.altaForm.get('fechaEnabled')?.value,
          municipio: {
            idmunicipio: this.altaForm.get('municipio')?.value,
            municipio: '',
            provincia: {
              idprovincia: this.altaForm.get('provincia')?.value,
              provincia: '', // No es necesario enviar este valor al backend
            },
          },

        };
        console.log(animal);
        this.guardarAnimal(animal);
        this.IrListadoAnimales();
      }
    }
  

    IrListadoAnimales(){
      this.router.navigate(['/animales/listado']);
    }

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
          
          console.log(this.provincias);
        });
    }

    private listadoMunicipiosProvincia() {
      this.altaForm.get('provincia')?.valueChanges.subscribe(idProvincia => {
        console.log(idProvincia);
        this._locationService.listarMunicipiosDeUnaProvincia(idProvincia).subscribe((municipios: any[]) => {
          this.municipios = municipios;
         
        });
      });
    }     
  }


