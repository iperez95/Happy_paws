import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth/auth.service';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';

@Component({
  selector: 'app-panel-admin',
  templateUrl: './panel-admin.component.html',
  styleUrls: ['./panel-admin.component.css']
})
export class PanelAdminComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router, private usuarioService: UsuarioService) { }

  ngOnInit() {
    const usuario = this.usuarioService.getUserData();
    if (!usuario || !usuario.isAdministrador()) {
      this.router.navigate(['/']); 
    }
  }
}
