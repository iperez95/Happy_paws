import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Protectora } from 'src/app/entidades/protectora';
import { ProtectoraService } from 'src/app/service/protectora/protectora.service';

@Component({
  selector: 'app-modificar-protectora',
  templateUrl: './modificar-protectora.component.html',
  styleUrls: ['./modificar-protectora.component.css']
})
export class ModificarProtectoraComponent {


  // protectoraModificada: Protectora = new Protectora();
  protectoraAlmacenada: Protectora = new Protectora();

  constructor(private _protectoraService: ProtectoraService,  private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.obtenerProtectora(id);
    }

  onSubmit(){
    this.modificarProtectora();    
  }

  obtenerProtectora(id: number) {
    this._protectoraService.obtenerProtectoraPorId(id)
      .subscribe({
        next: protectora => this.protectoraAlmacenada = protectora,
        error: error => console.log(error),
        complete: () => console.log('Obtención de protectora realizada')
      })
  }

  modificarProtectora( ) {
    this._protectoraService.modificarUnaProtectora(this.protectoraAlmacenada)
      .subscribe({
        next: dato => console.log(dato),
        error: error => console.log(error),
        complete: () => console.log('Modificación realizada')
      })
  }
}
