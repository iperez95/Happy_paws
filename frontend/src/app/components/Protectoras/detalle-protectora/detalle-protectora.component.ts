import { Component } from '@angular/core';
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
  id: number;
  protectora: Protectora;
  provincias: Provincia[]=[];
  public listaAnimales : Animal[] = [];
  public listaFotos: { [idAnimal: number]: Array<{ enlace: string }> } = {};


  constructor(private _protectoraService: ProtectoraService, private _animalService:AnimalService, private _multimediaService:MultimediaService, private _locationService :LocationService, private route: ActivatedRoute, private router: Router) {
    this.route = route;
  }

  ngOnInit() {
    this.listadoProvincias();    
    this.id = this.route.snapshot.params['id'];
    this.protectora = new Protectora();
    this._protectoraService.obtenerProtectoraPorId(this.id).subscribe( (data: Protectora) => {
    this.protectora = data;
    this.obtenerAnimalesIdProtectora(this.id);
    });
  }

  actualizarProtectora(id:number) {
   this.router.navigate(['protectora/gestion/modificar/', id]);
    }

    private listadoProvincias() {
      this._locationService.listarProvincias()
        .subscribe(data => {
          this.provincias = data;
          
          console.log(this.provincias);
        });
    }

    public enviarMensaje (){
      this.router.navigate(['protectora/contacto/', this.id]);
    }
 

    public obtenerAnimalesIdProtectora(id:number):void{
      this._animalService.listarAnimalPorIdProtectora(id).subscribe(dato => {
        this.listaAnimales = dato;
        console.log("Lista de obtener animales por id protectora: "+this.listaAnimales);
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

