import { Component } from '@angular/core';
import { AxiosService } from './service/axios/axios.service';
import { AuthService } from './service/auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private axiosService: AxiosService, private authService: AuthService) {}
  title = 'frontend';
  changeTitle(){
    this.title="Happy Paws";
  }

  ngOnInit(): void {
    const token = this.axiosService.getAuthToken();

    if (token) {
      this.authService.setLoggedIn(true);
    }
  }
}
