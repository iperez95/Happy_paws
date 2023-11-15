import {Component, Inject, Input} from '@angular/core';
import { LoginService } from '../../service/login/login.service';
import { AuthService } from '../../service/auth/auth.service';
import { AxiosService } from '../../service/axios/axios.service';
import { Usuario } from 'src/app/entidades/usuario';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css'],
})
export class NavComponent {
  loggedIn: boolean;
  user: Usuario | null | undefined;
  constructor(
    private loginService: LoginService, 
    private authService: AuthService, 
    private axiosService: AxiosService, 
    private usuarioService: UsuarioService) { }

  ngOnInit(): void {
    this.authService.loggedIn$.subscribe((loggedIn) => {
      this.loggedIn = loggedIn;
    });

    this.user = this.usuarioService.getUserData();
  }

  ngAfterContentChecked()	{
    this.user = this.usuarioService.getUserData();
    console.log(this.user);
  }

  openDialog() {
    this.loginService.openDialog();
  }

  logout(): void {
    // Remove the token from the local storage
    this.axiosService.removeAuthToken();  
    // Update the loggedIn property
    this.authService.setLoggedIn(false);
  }

}
