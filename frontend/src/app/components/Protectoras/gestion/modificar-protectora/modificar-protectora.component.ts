import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Protectora } from 'src/app/entidades/protectora';
import { ProtectoraService } from 'src/app/service/protectora/protectora.service';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-modificar-protectora',
  templateUrl: './modificar-protectora.component.html',
  styleUrls: ['./modificar-protectora.component.css']
})
export class ModificarProtectoraComponent {

  protectora: Protectora = new Protectora();

  private fotoSeleccionada:File;

  constructor(
    private _protectoraService: ProtectoraService,
    private _router: Router,
    private route: ActivatedRoute,
    private usuarioService: UsuarioService
  ) { }

  ngOnInit() {
    const user = this.usuarioService.getUserData();
    if (user) {
      this.obtenerProtectora(user.id);
    } else {
      this._router.navigate(['/']);
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

  irPrincipal() {
    this._router.navigate(['protectora/gestion']);
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

  
//Pone la protectora en inactiva. NO BORRA LOS DATOS
  bajaProtectora() {
    Swal.fire({
      title: "¿Estás seguro de que quieres dar de baja a la protectora?",
      showDenyButton: true,
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      confirmButtonText: "Sí, dar de baja",
      denyButtonText: "No, cancelar"
    }).then((result) => {
      if (result.isConfirmed) {
        this._protectoraService.inactivarProtectora(this.protectora.idprotectora, this.protectora)
          .subscribe({
            next: dato => console.log(dato),
            error: error => console.log(error),
            complete: () => {
              console.log('Baja realizada');
              Swal.fire('Realizado', `La protectora ha sido dada de baja correctamente`, 'success');
              this.IrDetalleProtectora();
            }
          })
      } else if (result.isDenied) {
        Swal.fire("Los cambios no se han guardado", "", "info");
      }
    });
  }

  //Pone la protectora activa.
  activarProtectora() {
    Swal.fire({
      title: "¿Estás seguro de que quieres activar esta protectora?",
      showDenyButton: true,
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      confirmButtonText: "Sí, activar",
      denyButtonText: "No, cancelar"
    }).then((result) => {
      if (result.isConfirmed) {
        this._protectoraService.activarProtectora(this.protectora.idprotectora, this.protectora)
          .subscribe({
            next: dato => console.log(dato),
            error: error => console.log(error),
            complete: () => {
              console.log('Activación realizada');
              Swal.fire('Realizado', `La protectora ha sido activada correctamente`, 'success');
              this.IrDetalleProtectora();
            }
          })
      } else if (result.isDenied) {
        Swal.fire("Los cambios no se han guardado", "", "info");
      }
    });
  }

  IrDetalleProtectora() {
    this._router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this._router.navigate(['/protectora/gestion']);
    }); 
  }

  cambiarFoto() {
    this._router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this._router.navigate(['/protectora/gestion/subirfoto/']);
    }); 
  }


  IrGestionProtectora() {
    this._router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this._router.navigate(['/protectora/gestion/modificar']);
    }); 
  }
 
}
