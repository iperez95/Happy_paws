import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Protectora } from 'src/app/entidades/protectora';
import { Provincia } from 'src/app/entidades/provincia';
import { LocationService } from 'src/app/service/localizacion/location.service';
import { ProtectoraService } from 'src/app/service/protectora/protectora.service';

@Component({
  selector: 'app-detalle-protectora',
  templateUrl: './detalle-protectora.component.html',
  styleUrls: ['./detalle-protectora.component.css']
})
export class DetalleProtectoraComponent {
  id: number;
  protectora: Protectora;
  provincias: Provincia[]=[];

  constructor(private _protectoraService: ProtectoraService, private _locationService :LocationService, private route: ActivatedRoute, private router: Router) {
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
      this._locationService.listarProvincias()
        .subscribe(data => {
          this.provincias = data;
          
          console.log(this.provincias);
        });
    }

    public enviarMensaje (){
      this.router.navigate(['protectora/contacto/', this.id]);
    }



  }

