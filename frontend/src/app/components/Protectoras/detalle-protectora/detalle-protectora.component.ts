import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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

  constructor(private _protectoraService: ProtectoraService, private route: ActivatedRoute, private router: Router) {
    this.route = route;
  }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.protectora = new Protectora();
    this._protectoraService.obtenerProtectoraPorId(this.id).subscribe( (data: Protectora) => {
      this.protectora = data;
    });
  }

  actualizarProtectora(id:number) {
   this.router.navigate(['protectora/gestion/modificar/', id]);
    }




  }

