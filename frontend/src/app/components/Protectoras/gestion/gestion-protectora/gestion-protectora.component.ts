import { Component, ViewEncapsulation } from '@angular/core';
import { ThemePalette } from '@angular/material/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Protectora } from 'src/app/entidades/protectora';
import { ProtectoraService } from 'src/app/service/protectora/protectora.service';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';



@Component({
  selector: 'app-gestion-protectora',
  templateUrl: './gestion-protectora.component.html',
  styleUrls: ['./gestion-protectora.component.css'],

})
export class GestionProtectoraComponent {
  protectora: Protectora;
  route: ActivatedRoute;
  background: ThemePalette = undefined;

  toggleBackground() {
    this.background = this.background ? undefined : 'primary';
  }

  constructor(
    private _protectoraService: ProtectoraService,
    private router: Router,
    route: ActivatedRoute,
    private usuarioService: UsuarioService
  ) {
    this.route = route;
  }

  ngOnInit() {
    const user = this.usuarioService.getUserData();
    if (user) {
      this._protectoraService.obtenerProtectoraPorIdUsuario(user.id).subscribe( (data: Protectora) => {
        this.protectora = data;
      });
    } else {
      this.router.navigate(['/']);
    }
  }

  actualizarProtectora(id:number) {
    this.router.navigate(['protectora/gestion/modificar']);
  }
}
