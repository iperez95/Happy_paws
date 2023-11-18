import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Protectora } from 'src/app/entidades/protectora';
import { ProtectoraService } from 'src/app/service/protectora/protectora.service';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';

@Component({
  selector: 'app-modificar-protectora',
  templateUrl: './modificar-protectora.component.html',
  styleUrls: ['./modificar-protectora.component.css']
})
export class ModificarProtectoraComponent {

  protectora: Protectora = new Protectora();

  constructor(
    private _protectoraService: ProtectoraService,
    private router: Router,
    private route: ActivatedRoute,
    private usuarioService: UsuarioService
  ) { }

  ngOnInit() {
    const user = this.usuarioService.getUserData();
    if (user) {
      this.obtenerProtectora(user.id);
    } else {
      this.router.navigate(['/']);
    }
  }

  onSubmit(){
    this.actualizarProtectora();
    this.IrDetalleProtectora();
  }

  obtenerProtectora(id: number) {
    this._protectoraService.obtenerProtectoraPorIdUsuario(id)
      .subscribe({
        next: protectora => this.protectora = protectora,
        error: error => console.log(error),
        complete: () => console.log('Obtención de protectora realizada')
        
      })
      console.log("Protectora Almacenada :" + this.protectora)
  }

  actualizarProtectora( ) {
    this._protectoraService.actualizarProtectora(this.protectora.idprotectora, this.protectora)
      .subscribe({
        next: dato => console.log(dato),
        error: error => console.log(error),
        complete: () => {
          console.log('Modificación realizada');
          this.IrDetalleProtectora();
        }
      })
  }

  IrDetalleProtectora() {
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this.router.navigate(['/protectora/gestion']);
    }); 
  }
 
}
