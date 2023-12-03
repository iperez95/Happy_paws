import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Animal } from 'src/app/entidades/animal';
import { AnimalDto } from 'src/app/entidades/animalDto';
import { AxiosService } from 'src/app/service/axios/axios.service';
import { AnimalAdminService } from 'src/app/service/panel-admin/animal-admin.service';

@Component({
  selector: 'app-listado-gestion-animales',
  templateUrl: './listado-gestion-animales.component.html',
  styleUrls: ['./listado-gestion-animales.component.css']
})
export class ListadoGestionAnimalesComponent {

  listaAnimalesDto: AnimalDto[]=[];

  constructor(private _animalAdminService:AnimalAdminService, private router:Router, private axiosService: AxiosService) { }

  ngOnInit():void{
    this.listar();

  }

  public listar(){
    this._animalAdminService.listaAnimalesDto().subscribe(dato => { 
      this.listaAnimalesDto = dato;
      console.log(this.listaAnimalesDto);
    });
  }

  public editarAnimal(id:number){
    this.router.navigate(['admin/gestion/animales/editar/', id]);
  }

  public inactivarAnimal(id:number){
    this._animalAdminService.inactivarAnimal(id, this.listaAnimalesDto[id]).subscribe(dato => {
      this.listar();
    });
  }

  public activarAnimal(id:number){
    this._animalAdminService.activarAnimal(id, this.listaAnimalesDto[id]).subscribe(dato => {
      this.listar();
    });
  }

  cambiarEstadoAnimal(id: number) {
    let animal = this.listaAnimalesDto.find(a => a.idanimal === id);
    if (animal) {
      if (animal.enabled) {
        this.inactivarAnimal(id);
      } else {
        this.activarAnimal(id);
      }
    }
  }
 

  public busquedaPorNombre(nombre:string){
    this._animalAdminService.busquedaPorNombre(nombre).subscribe(dato => {
      this.listaAnimalesDto = dato;
      console.log("Este el nombre buscado: "+nombre)
      console.log("Esta es la lista de animales devuelta: "+this.listaAnimalesDto);
    });
  }



  getColor(enabled: boolean){
    return enabled ? '#1F9254' : 'red';
  }

  getStatus(enabled: boolean) {
    return enabled ? 'Activo' : 'Inactivo';
  }

 

}