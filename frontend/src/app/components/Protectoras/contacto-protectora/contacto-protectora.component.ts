import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Protectora } from 'src/app/entidades/protectora';
import { ProtectoraContactService } from 'src/app/service/email/protectoraContact.service';
import { ProtectoraService } from 'src/app/service/protectora/protectora.service';
import { tap } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import swal from 'sweetalert2';

@Component({
  selector: 'app-contacto-protectora',
  templateUrl: './contacto-protectora.component.html',
  styleUrls: ['./contacto-protectora.component.css']
})
export class ContactoProtectoraComponent {

  contactForm: FormGroup;
  protectora: Protectora = new Protectora();
  estadoEnvio: String;
  enviandoEmail = false;

  constructor(private fb: FormBuilder, private protectoraContactService: ProtectoraContactService,private _protectoraService: ProtectoraService, private router: Router, private route: ActivatedRoute) {
    this.contactForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      message: ['', Validators.required],
    });
  }

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.obtenerProtectora(id);
    console.log(this.protectora);
  }

  obtenerProtectora(id: number) {
    this._protectoraService.obtenerProtectoraPorId(id)
      .subscribe({
        next: protectora => this.protectora = protectora,
        error: error => console.log(error),
        complete: () => console.log('Obtenida Protectora + ' + this.protectora.nombre )
      })
  }

  onSubmit() {
      if (this.contactForm.valid) {
        this.enviandoEmail = true;
        const formData = this.contactForm.value;
        const id = Number(this.route.snapshot.paramMap.get('id'));
        this.protectoraContactService.sendEmailToProtectora(id, formData).pipe(
          tap(response => {
            this.estadoEnvio = response;
            swal.fire('Enviado', `El formulario se ha enviado correctamente`, 'success');
            this.volverDetalleProtectora()
          }),
          catchError(error => {
            this.enviandoEmail = false;
            this.estadoEnvio = error.error;
            console.error(error);
            swal.fire('Error de Envío', 'error');
            return of(null); // return a safe value or observable
          })
        ).subscribe();
      }
  
  }

  volverDetalleProtectora(){
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['/protectora/detalle/', id]);
  }
}
