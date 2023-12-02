import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Animal } from 'src/app/entidades/animal';
import { Especie } from 'src/app/entidades/especie';

import { Raza } from 'src/app/entidades/raza';
import { Sexo } from 'src/app/entidades/sexo';
import { Tamano } from 'src/app/entidades/tamano';
import { EspecieService } from 'src/app/service/especie/especie.service';
import { AnimalAdminService } from 'src/app/service/panel-admin/animal-admin.service';
import { RazaService } from 'src/app/service/raza/raza.service';
import { SexoService } from 'src/app/service/sexo/sexo.service';
import { TamanosService } from 'src/app/service/tamano/tamano.service';

@Component({
  selector: 'app-editar-animal-gestion',
  templateUrl: './editar-animal-gestion.component.html',
  styleUrls: ['./editar-animal-gestion.component.css']
})
export class EditarAnimalGestionComponent {

  animal: Animal = new Animal();
 

  // modificarForm: FormGroup;

  especies:Especie[] = [];
  razas: Raza[] = [];
  sexos:Sexo[] = [];
  tamanos:Tamano[] = [];

  constructor(private _animalAdminService: AnimalAdminService,
    // private fb: FormBuilder,
    private _razaService: RazaService,
    private _especieService: EspecieService,
    private _sexoService: SexoService,
    private _tamanoService: TamanosService, 
    private router: Router, 
    private route: ActivatedRoute) {
     }

  onSubmit() {
    // if (this.modificarForm.valid) {
    //   const animal: Animal = {
    //     idanimal: this.modificarForm.get('idanimal')?.value,
    //     descripcion: this.modificarForm.get('descripcion')?.value,
    //     enabled: false,
    //     envio: false,
    //     fechaAlta: new Date(),
    //     fechaNacimiento: new Date(),
    //     nombre: this.modificarForm.get('nombre')?.value,
    //     raza: {
    //       idraza: this.modificarForm.get('raza')?.value,
          
    //       especie: {
    //         idespecie: this.modificarForm.get('especie')?.value,
            
    //       }
    //     },
    //     sexo: {
    //       idsexo: this.modificarForm.get('sexo')?.value,
          
    //     },
    //     tamano: {
    //       idtamano: this.modificarForm.get('tamano')?.value,
        
    //     },
    //     municipio: '', 
    //     protectora:this.protectora,
    //     fecha_enabled: '', 
    //   };
      this.actualizarAnimal();
      this.irGestion();
    // 
  }

  ngOnInit(){
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.obtenerAnimal(id);
    this.listadoEspecies();
   
    // this.listadoRazasDeUnaEspecie();
    this.listadoSexos();
    console.log("Listado Sexos: "+this.sexos);
    this.listadoTamanos();


    
    
  }


  private obtenerAnimal(id: number) {
    this._animalAdminService.obtenerAnimalPorId(id)
    .subscribe(data =>{
      this.animal = data;
      // this.modificarForm.get('nombre')?.setValue(this.animal.nombre);
      // this.modificarForm.get('especie')?.setValue(this.animal.raza.especie.especie);
      // this.modificarForm.get('raza')?.setValue(this.animal.raza.raza);
      // this.modificarForm.get('sexo')?.setValue(this.animal.sexo.sexo);
      // this.modificarForm.get('tamano')?.setValue(this.animal.tamano.tamano);
      // this.modificarForm.get('descripcion')?.setValue(this.animal.descripcion);
      console.log("Listado Especies: "+this.razas);
    });
    }

  actualizarAnimal() {
    this._animalAdminService.actualizarAnimal(this.animal.idanimal, this.animal)
      .subscribe({
        next: dato => console.log(dato),
        error: error => console.log(error),
        complete: () => {
          console.log(this.animal);
          console.log('Modificación realizada');
        }
      })
  }

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

 
  

  private listadoEspecies() {
    this._especieService.listarEspecies()
    .subscribe(data =>{
      this.especies = data;
      console.log("Listado Especies: "+this.especies);
    });
  }

 

  // private listadoRazasDeUnaEspecie() {
  //   this.modificarForm.get('especie')?.valueChanges.subscribe(idEspecie => {
  //     console.log(idEspecie);
  //     this._razaService.listarRazasDeUnaEspecie(idEspecie).subscribe(razas => {
  //       this.razas = razas;
  //       console.log("Listado Razas: "+this.razas);
  //     });
  //   });
  // }



    private listadoSexos() {
      this._sexoService.listarSexos()
      .subscribe(data =>{
        this.sexos = data;
        console.log("Listado Sexos: "+this.sexos);
      });
      }

      private listadoTamanos() {
        this._tamanoService.listarTamanos()
        .subscribe(data =>{
          this.tamanos = data;
          console.log("Listado Tamaños: "+this.tamanos);  
        });
        }

}
