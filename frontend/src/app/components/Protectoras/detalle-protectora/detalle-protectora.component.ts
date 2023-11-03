import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Protectora } from 'src/app/entidades/protectora';
import { ProtectoraService } from 'src/app/service/protectora/protectora.service';

@Component({
  selector: 'app-detalle-protectora',
  templateUrl: './detalle-protectora.component.html',
  styleUrls: ['./detalle-protectora.component.css']
})
export class DetalleProtectoraComponent {
  id: number;
  protectora: Protectora;
  route: ActivatedRoute;

  constructor(private _protectoraService: ProtectoraService, route: ActivatedRoute) {
    this.route = route;
  }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this._protectoraService.obtenerProtectoraPorId(this.id).subscribe( (data: Protectora) => {
      this.protectora = data;
    });
  }
}
