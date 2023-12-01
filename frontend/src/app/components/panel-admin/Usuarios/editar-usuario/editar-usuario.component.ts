import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Usuario } from 'src/app/entidades/usuario';
import { AxiosService } from 'src/app/service/axios/axios.service';

@Component({
  selector: 'app-editar-usuario',
  templateUrl: './editar-usuario.component.html',
  styleUrls: ['./editar-usuario.component.css']
})
export class EditarUsuarioComponent {
  usuario: Usuario = new Usuario();
  idUsuario: number;
  constructor(
    private router: Router, 
    private route:ActivatedRoute,
    private axiosService: AxiosService,
  ){}
  
  onSubmit(){
    this.actualizarUsuario();
    this.irGestion();
  }
  
  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!id) this.router.navigate(['/admin/gestion']);
    this.idUsuario = id;
    this.obtenerUsuario(id);
  }

  obtenerUsuario(id: number): void {
    this.axiosService.request('GET', `api/usuarios/${id}`, null).then((response) => {
      this.usuario = Object.assign(new Usuario, response.data);
    }).catch((error: any) => {
      console.error('There was an error!', error);
    });
  }

  actualizarUsuario(): void {
    this.axiosService.request('PUT', `api/admin/usuario/modificar/${this.idUsuario}`, this.usuario).then((response) => {
      console.log(response);
    }).catch((error: any) => {
      console.log(error);
    });
  }

  irGestion() {
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this.router.navigate(['/admin/gestion']);
    }); 
  }
}
