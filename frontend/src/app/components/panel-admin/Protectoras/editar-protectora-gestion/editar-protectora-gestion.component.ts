import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Protectora } from 'src/app/entidades/protectora';
import { ProtectoraAdminService } from 'src/app/service/panel-admin/protectora-admin.service';
import { ProtectoraService } from 'src/app/service/protectora/protectora.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-editar-protectora-gestion',
  templateUrl: './editar-protectora-gestion.component.html',
  styleUrls: ['./editar-protectora-gestion.component.css']
})
export class EditarProtectoraGestionComponent {

protectora: Protectora = new Protectora();

constructor(private _protectoraAdminService: ProtectoraAdminService,private router: Router, private route:ActivatedRoute ){}

onSubmit(){
  this.actualizarProtectora();
  this.irGestion();
}

ngOnInit() {
  const id = Number(this.route.snapshot.paramMap.get('id'));
  this.obtenerProtectora(id);
}

obtenerProtectora(id: number) {
  this._protectoraAdminService.obtenerProtectoraPorId(id)
    .subscribe({
      next: protectora => this.protectora = protectora,
      error: error => console.log(error),
      complete: () => console.log('Obtención de protectora realizada')
    })
    console.log("Protectora Almacenada :" + this.protectora)
}


  actualizarProtectora( ) {
    this._protectoraAdminService.actualizarProtectora(this.protectora.idprotectora, this.protectora)
      .subscribe({
        next: dato => console.log(dato),
        error: error => console.log(error),
        complete: () => {
          console.log('Modificación realizada');
          
        }
      })
  }

  // //Pone la protectora en inactiva. NO BORRA LOS DATOS
  // bajaProtectora() {
  //   Swal.fire({
  //     title: "¿Estás seguro de que quieres dar de baja a la protectora?",
  //     showDenyButton: true,
  //     showCancelButton: true,
  //     confirmButtonColor: "#3085d6",
  //     confirmButtonText: "Sí, dar de baja",
  //     denyButtonText: "No, cancelar"
  //   }).then((result) => {
  //     if (result.isConfirmed) {
  //       this._protectoraService.inactivarProtectora(this.protectora.idprotectora, this.protectora)
  //         .subscribe({
  //           next: dato => console.log(dato),
  //           error: error => console.log(error),
  //           complete: () => {
  //             console.log('Baja realizada');
  //             Swal.fire('Realizado', `La protectora ha sido dada de baja correctamente`, 'success');
   
  //           }
  //         })
  //     } else if (result.isDenied) {
  //       Swal.fire("Los cambios no se han guardado", "", "info");
  //     }
  //   });
  // }

  // //Pone la protectora activa.
  // activarProtectora() {
  //   Swal.fire({
  //     title: "¿Estás seguro de que quieres activar esta protectora?",
  //     showDenyButton: true,
  //     showCancelButton: true,
  //     confirmButtonColor: "#3085d6",
  //     confirmButtonText: "Sí, activar",
  //     denyButtonText: "No, cancelar"
  //   }).then((result) => {
  //     if (result.isConfirmed) {
  //       this._protectoraService.activarProtectora(this.protectora.idprotectora, this.protectora)
  //         .subscribe({
  //           next: dato => console.log(dato),
  //           error: error => console.log(error),
  //           complete: () => {
  //             console.log('Activación realizada');
  //             Swal.fire('Realizado', `La protectora ha sido activada correctamente`, 'success');
           
  //           }
  //         })
  //     } else if (result.isDenied) {
  //       Swal.fire("Los cambios no se han guardado", "", "info");
  //     }
  //   });
  // }

  irGestion() {
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this.router.navigate(['/admin/gestion']);
    }); 
  }

}
