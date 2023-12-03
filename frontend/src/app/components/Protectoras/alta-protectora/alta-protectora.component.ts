  /**
  import { Component } from '@angular/core';
  import { Protectora } from 'src/app/entidades/protectora';
  import { ProtectoraService } from 'src/app/service/protectora/protectora.service';
  import { Router } from '@angular/router';
  import { Form, FormBuilder, FormGroup, Validators } from '@angular/forms';
  import { LocationService } from 'src/app/service/localizacion/location.service';
import { Municipio } from 'src/app/entidades/municipio';
import { Provincia } from 'src/app/entidades/provincia';

  @Component({
    selector: 'app-alta-protectora',
    templateUrl: './alta-protectora.component.html',
    styleUrls: ['./alta-protectora.component.css']
  })
  export class AltaProtectoraComponent {ra

    altaForm: FormGroup;
   
    
    provincias: Provincia [] = [];
    municipios: Municipio [] = [];

    municipio : Municipio = new Municipio();
    provincia : Provincia =  new Provincia();
    
    provinciaSeleccionadaId: number;

    constructor(private fb: FormBuilder, private _protectoraService : ProtectoraService, private router: Router, private _locationService: LocationService) {  
      this.altaForm = this.fb.group({
        nombre: ['', Validators.required],
        direccion: ['', Validators.required],
        telefono: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
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
        // Crear instancia de Protectora y asignar valores
        const protectora: Protectora = {
          idprotectora: 0,
          nombre: this.altaForm.get('nombre')?.value,
          direccion: this.altaForm.get('direccion')?.value,
          email: this.altaForm.get('email')?.value,
          descripcion: this.altaForm.get('descripcion')?.value,
          municipio: {
            idmunicipio: this.altaForm.get('municipio')?.value,
            municipio: '',
            provincia: {
              idprovincia: this.altaForm.get('provincia')?.value,
              provincia: '', // No es necesario enviar este valor al backend
            },
          },
          provincia: {
            idprovincia: this.altaForm.get('provincia')?.value,
            provincia: '', // No es necesario enviar este valor al backend
          },
          urlLogo: '',
          telefono: this.altaForm.get('telefono')?.value,
        };
        console.log(protectora);
        this.guardarProtectora(protectora);
        this.IrListadoProtectoras();
      }
    }  

    IrListadoProtectoras(){
      this.router.navigate(['/protectora/todas']);
    }

    guardarProtectora(protectora: Protectora){
      this._protectoraService.altaProtectora(protectora)
      .subscribe({
        next: response => console.log(response),
        error: error => console.log(error),
        complete: () => console.log('Alta Realizada')
      })  
    }

    private listadoProvincias() {
      this._locationService.listarProvincias()
        .subscribe(data => {
          this.provincias = data;
          
          console.log(this.provincias);
        });
    }

    private listadoMunicipiosProvincia() {
      this.altaForm.get('provincia')?.valueChanges.subscribe(idProvincia => {
        console.log(idProvincia);
        this._locationService.listarMunicipiosDeUnaProvincia(idProvincia).subscribe(municipios => {
          this.municipios = municipios;
         
        });
      });
      
    }
    

}
*/
