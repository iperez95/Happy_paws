import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { tap } from 'rxjs';
import { ContactService } from 'src/app/service/contacto/contact.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-contacto',
  templateUrl: './contacto.component.html',
  styleUrls: ['./contacto.component.css']
})
export class ContactoComponent {

  contactForm: FormGroup;
  estadoEnvio: String;
  enviandoEmail = false;

  constructor(private fb: FormBuilder, private contactService: ContactService) {
    this.contactForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      message: ['', Validators.required],
    });
  }

  

  onSubmit() {
    if (this.contactForm.valid) {
      this.enviandoEmail = true;
      const formData = this.contactForm.value;
      this.contactService.sendContactForm(formData).pipe(
        tap(
          response => {
            
            this.estadoEnvio = response;
            swal.fire('Enviado', `El formulario se ha enviado correctamente`, 'success');
          },
          error => {
            this.enviandoEmail = false;
            this.estadoEnvio = error.error;
            console.error(error);
            swal.fire('Error de Envío', 'error');
          }
        )
      ).subscribe();
    }
  }
  
  

}
