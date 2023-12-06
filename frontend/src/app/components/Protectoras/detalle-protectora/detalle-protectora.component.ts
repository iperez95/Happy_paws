import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { switchMap } from 'rxjs';
import { Animal } from 'src/app/entidades/animal';
import { AnimalDto } from 'src/app/entidades/animalDto';
import { Multimedia } from 'src/app/entidades/multimedia';
import { Protectora } from 'src/app/entidades/protectora';
import { Provincia } from 'src/app/entidades/provincia';
import { AnimalService } from 'src/app/service/animal/animal.service';
import { LocationService } from 'src/app/service/localizacion/location.service';
import { MultimediaService } from 'src/app/service/multimedia/multimedia.service';
import { ProtectoraService } from 'src/app/service/protectora/protectora.service';

@Component({
  selector: 'app-detalle-protectora',
  templateUrl: './detalle-protectora.component.html',
  styleUrls: ['./detalle-protectora.component.css']
})
export class DetalleProtectoraComponent {

  public protectora: Protectora;  
  public animales: Animal[];
  public listaAnimales : Animal[] = [];
  public idProtectora : number;
  public listaFotos: { [idAnimal: number]: Array<{ enlace: string }> } = {};
  public provincias: Provincia[]=[];




  constructor(private _protectoraService: ProtectoraService, private _animalService:AnimalService, private _multimediaService:MultimediaService, private _locationService :LocationService, private route: ActivatedRoute, private router: Router) {
    this.route = route;
  }

  ngOnInit() {
    this.listadoProvincias();
    this.obtenerProtectoraPorId(this.idProtectora);
    this.obtenerIdProtectora();
    
  }

  public obtenerProtectoraPorId(idProtectora:number):void{
    this.idProtectora = this.route.snapshot.params['id'];
    this._protectoraService.obtenerProtectoraPorId(this.idProtectora).subscribe( (data: Protectora) => {
      this.protectora = data;  
      console.log("Protectora: "+this.protectora);
    });
  }

  actualizarProtectora(idProtectora:number) {
   this.router.navigate(['protectora/gestion/modificar/', idProtectora]);
    }

    private listadoProvincias() {
      this._locationService.listarProvincias()
        .subscribe(data => {
          this.provincias = data;
          
          console.log(this.provincias);
        });
    }

    public enviarMensaje (){
      this.router.navigate(['protectora/contacto/', this.idProtectora]);
    }
 

   
   
  

  // Métodos de Datos

  
  public obtenerIdProtectora(): void {
    this._protectoraService.obtenerProtectoraPorId(this.idProtectora).pipe(
      switchMap(data => {
        this.idProtectora = data.idprotectora;
        return this._animalService.listarAnimalPorIdProtectora(this.idProtectora);
      })
    ).subscribe({
      next: (res) => { this.listaAnimales = res; },
      error: (err) => { console.error(err); },
      complete: () => { this.obtenerFotosAnimales(); }
    });
  }



  public obtenerFotosAnimales(): void {
    let idsAnimales: number[] = [];
    for (let animal of this.listaAnimales) { idsAnimales.push(animal.idanimal); }
    this._multimediaService.recuperarFotosAnimales(idsAnimales).subscribe({
      next: (res) => { this.listaFotos = res.fotosAnimales; },
      error: (err) => { console.log(err); }
    });
  }


  }

