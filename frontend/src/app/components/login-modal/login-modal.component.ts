import { Component, EventEmitter, Output } from '@angular/core';
import { AxiosService } from 'src/app/service/axios/axios.service';

@Component({
  selector: 'app-login-modal',
  templateUrl: './login-modal.component.html',
  styleUrls: ['./login-modal.component.css']
})
export class LoginModalComponent {
  @Output() onSubmitLoginEvent = new EventEmitter();
  @Output() loggedInChange = new EventEmitter<boolean>();
  loggedIn: boolean = false;
  email: string;
  password: string;
  
  constructor(private axiosService: AxiosService) {}
  
  onSubmitLogin(): void {
    const url = '/api/login';
    const data = { email: this.email, password: this.password };
    
    this.axiosService.request("POST", url, data)
      .then(response => {
        this.loggedIn = true;
        this.loggedInChange.emit(this.loggedIn);
      })
      .catch(error => {
        console.log(error);
      });
  }
}
