import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Protectora } from 'src/app/entidades/protectora';
import { Provincia } from 'src/app/entidades/provincia';
import { ProtectoraService } from 'src/app/service/protectora/protectora.service';

@Component({
  selector: 'app-detalle-protectora',
  templateUrl: './detalle-protectora.component.html',
  styleUrls: ['./detalle-protectora.component.css']
})
export class DetalleProtectoraComponent {
  id: number;
  protectora: Protectora;
  listaProvincias: Provincia[]=[];

  constructor(private _protectoraService: ProtectoraService, private route: ActivatedRoute, private router: Router) {
    this.route = route;
  }

  ngOnInit() {
    this.listadoProvincias();
    this.id = this.route.snapshot.params['id'];
    this.protectora = new Protectora();
    this._protectoraService.obtenerProtectoraPorId(this.id).subscribe( (data: Protectora) => {
    this.protectora = data;
    });
  }

  actualizarProtectora(id:number) {
   this.router.navigate(['protectora/gestion/modificar/', id]);
    }

    private listadoProvincias() {
      this._protectoraService.listarProvincias()
        .subscribe(data => {
          this.listaProvincias = data;
          console.log(this.listaProvincias);
        });
    }

    public enviarMensaje (){
      this.router.navigate(['protectora/contacto/', this.id]);
    }



  }

