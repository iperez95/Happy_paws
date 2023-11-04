import { Component, ViewEncapsulation } from '@angular/core';
import { ThemePalette } from '@angular/material/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Protectora } from 'src/app/entidades/protectora';
import { ProtectoraService } from 'src/app/service/protectora/protectora.service';



@Component({
  selector: 'app-gestion-protectora',
  templateUrl: './gestion-protectora.component.html',
  styleUrls: ['./gestion-protectora.component.css'],

})
export class GestionProtectoraComponent {
  id: number;
  protectora: Protectora;
  route: ActivatedRoute;
  background: ThemePalette = undefined;

  

  toggleBackground() {
    this.background = this.background ? undefined : 'primary';
  }

  constructor(private _protectoraService: ProtectoraService, private router: Router, route: ActivatedRoute) {
    this.route = route;
  }

  

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this._protectoraService.obtenerProtectoraPorId(this.id).subscribe( (data: Protectora) => {
      this.protectora = data;
    });
  }

  actualizarProtectora(id:number) {
    this.router.navigate(['protectora/gestion/modificar/', id]);
     }


}
