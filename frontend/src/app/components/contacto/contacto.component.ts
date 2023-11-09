import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ContactService } from 'src/app/service/contacto/contact.service';

@Component({
  selector: 'app-contacto',
  templateUrl: './contacto.component.html',
  styleUrls: ['./contacto.component.css']
})
export class ContactoComponent {

  contactForm: FormGroup;

  constructor(private fb: FormBuilder, private contactService: ContactService) {
    this.contactForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      message: ['', Validators.required],
    });
  }

  

  onSubmit() {
    if (this.contactForm.valid) {
      const formData = this.contactForm.value;
      this.contactService.sendContactForm(formData).subscribe(
        (response) => {
          console.log(response); // Manejar la respuesta del servidor
        },
        (error) => {
          console.error(error); // Manejar errores
        }
      );
    }
  }

}
