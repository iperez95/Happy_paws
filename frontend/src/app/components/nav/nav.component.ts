import {Component, Inject, Input} from '@angular/core';
import { LoginService } from '../../service/login/login.service';
import axios from 'axios';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css'],
})
export class NavComponent {
  @Input() loggedIn: boolean;
  constructor(private loginService: LoginService) {}

  openDialog() {
    this.loginService.openDialog();
  }

  // onSubmitLoginForm(event: Event): void {
  //   event.preventDefault(); // Prevent the default form submission behavior
  //   const form = event.target as HTMLFormElement; // Get a reference to the form element
  //   const email = form['email'].value; // Get the email value from the form
  //   const password = form['password'].value; // Get the password value from the form
    
  //   axios.post('/api/login', { email, password })
  //     .then(response => {
  //       this.loggedIn = true;
  //       console.log(this.loggedIn)
  //     })
  //     .catch(error => {
  //       console.log(error); // Handle the error response
  //     });
  // }
}
