import { Component } from '@angular/core';
import { AxiosService } from './service/axios/axios.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private axiosService: AxiosService) {}
    title = 'frontend';
    changeTitle(){
      this.title="Happy Paws";
    }

    onLogin(input: any): void {
      this.axiosService.request(
        'POST',
        '/login',
        {
          email: input.email,
          password: input.password,
        });
    }
}
