import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class AxiosService {
  constructor() { 
    axios.defaults.baseURL = 'http://localhost:8087';
    axios.defaults.headers.post['Content-Type'] = 'application/json';
  }

  getAuthToken(): string|null {
    return window.localStorage.getItem('auth_token');
  }

  setAuthToken(token: string | null): void {
    if (token !== null) {
      window.localStorage.setItem('auth_token', token);
    } else {
      window.localStorage.removeItem('auth_token');
    }
  }

  readToken(): any {
    const token = this.getAuthToken();

    if (token) {
      const helper = new JwtHelperService();
      return helper.decodeToken(token);
    }

    return null;
  }

  request(method: string, url: string, data: any): Promise<any> {
    let headers = {};

    if (this.getAuthToken()) {
      headers = {
        'Authorization': 'Bearer ' + this.getAuthToken()
      }
    }

    return axios({
      method: method,
      url: url,
      data: data,
      headers: headers
    }).then(response => {
      return response;
    })
  }
  
  removeAuthToken(): void {
    window.localStorage.removeItem('auth_token');
  }
}
