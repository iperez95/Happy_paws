import { NumberSymbol } from '@angular/common';
import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { Animal } from 'src/app/entidades/animal';
import { Protectora } from 'src/app/entidades/protectora';
import { AnimalService } from 'src/app/service/animal/animal.service';
import { ProtectoraService } from 'src/app/service/protectora/protectora.service';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';

@Component({
  selector: 'app-gestion-animal',
  templateUrl: './gestion-animal.component.html',
  styleUrls: ['./gestion-animal.component.css']
})
export class GestionAnimalComponent {

  @Input() protectora: Protectora;
  @Input() animales: Animal[];
  public listaAnimales : Animal[] = [];
  public idUsuario : number;
  public idProtectora : number;

  constructor(private _animalService : AnimalService, private router :Router, private _protrectoraService: ProtectoraService, private _usuarioService: UsuarioService) {
  }

  ngOnInit():void {
    this.obtenerIdUsuario();
    this.obtenerIdProtectora(this.idUsuario);
  }

  public obtenerIdUsuario():void{
    const user: any = this._usuarioService.getUserData();
    this.idUsuario = user.id;    
    console.log(user);  
  }

  public obtenerIdProtectora(idUsuario: number): void {
    this._protrectoraService.obtenerProtectoraPorIdUsuario(idUsuario).pipe(
      switchMap(data => {
        this.idProtectora = data.idprotectora;
        return this._animalService.listarAnimalPorIdProtectora(this.idProtectora);
      })
    ).subscribe(dato => {
      this.listaAnimales = dato;
    });
  }

  public obtenerAnimalesIdProtectora(idProtectora:number):void{
    this._animalService.listarAnimalPorIdProtectora(idProtectora).subscribe(dato => {
      this.listaAnimales = dato;
    });
 }

  public irAltaAnimal() {
    this.router.navigate(['/animales/gestion/alta']);
   }

  public irModificalAnimal(idanimal: number) {
    this.router.navigate(['/animales/gestion/modificar', idanimal]);
   }

   public borrarAnimal(idanimal: number){
    this._animalService.eliminarAnimal(idanimal).subscribe({
      next: (res) => {
        location.reload();
      },
      error: (err) => {
        console.error(err);
      }
    });

   }

}
