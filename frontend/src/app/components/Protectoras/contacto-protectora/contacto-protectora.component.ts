import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Protectora } from 'src/app/entidades/protectora';
import { ContactService } from 'src/app/service/contacto/contact.service';
import { ProtectoraContactService } from 'src/app/service/email/protectoraContact.service';
import { ProtectoraService } from 'src/app/service/protectora/protectora.service';

@Component({
  selector: 'app-contacto-protectora',
  templateUrl: './contacto-protectora.component.html',
  styleUrls: ['./contacto-protectora.component.css']
})
export class ContactoProtectoraComponent {

  contactForm: FormGroup;
  protectora: Protectora = new Protectora();

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
      const formData = this.contactForm.value;
      const id = Number(this.route.snapshot.paramMap.get('id'));
      this.protectoraContactService.sendProtectoraContactForm(formData, id).subscribe(
        (response) => {
          console.log(response); // Aqui poner la respuesta del Servidor
        },
        (error) => {
          console.error(error); // Implementar los errores recibidos del Servidor
        }
      );
    }
  }

}
