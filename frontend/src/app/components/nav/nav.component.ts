import {Component, Inject} from '@angular/core';
import { LoginService } from '../../service/login/login.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css'],
})
export class NavComponent {
  constructor(private loginService: LoginService) {}

  openDialog() {
    this.loginService.openDialog();
  }
}
