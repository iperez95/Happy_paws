import {Component, Inject, Input} from '@angular/core';
import { LoginService } from '../../service/login/login.service';
import { AuthService } from '../../service/auth/auth.service';
import axios from 'axios';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css'],
})
export class NavComponent {
  loggedIn: boolean;

  constructor(private loginService: LoginService, private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.loggedIn$.subscribe((loggedIn) => {
      this.loggedIn = loggedIn;
    });
  }

  openDialog() {
    this.loginService.openDialog();
  }
}
