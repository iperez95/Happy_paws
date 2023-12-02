import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { AxiosService } from '../../service/axios/axios.service';
import { AuthService } from '../../service/auth/auth.service';

@Component({
  selector: 'app-login-modal',
  templateUrl: './login-modal.component.html',
  styleUrls: ['./login-modal.component.css']
})
export class LoginModalComponent implements OnInit {
  email: string;
  password: string;
  errorMessage: string;

  @Output() loggedInChange = new EventEmitter<boolean>();

  constructor(
    private dialogRef: MatDialogRef<LoginModalComponent>,
    private axiosService: AxiosService,
    private authService: AuthService // Inject the AuthService service
  ) { }

  ngOnInit(): void {
  }

  onSubmitLogin(): void {
    const data = { email: this.email, password: this.password };

    this.axiosService.request("POST", '/api/login', data)
    .then(response => {
      this.authService.setLoggedIn(true); // Update the loggedIn property
      this.axiosService.setAuthToken(response.data.token); // Save the token in the local storage
      this.dialogRef.close();
      this.errorMessage = ''; // Reset the error message
    })
    .catch(error => {
      console.log('Login error:', error);

      switch (error.response.status) {
        case 404:
          this.errorMessage = "Usuario o contraseña incorrectos";
          break;
        case 401:
          this.errorMessage = error.response.data.message;
          break;
        default:
          console.log(error);
          this.errorMessage = "Error desconocido";
      }
    });
  }

}
