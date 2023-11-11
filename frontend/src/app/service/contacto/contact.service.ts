// contact.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import axios from 'axios';

@Injectable({
  providedIn: 'root',
})
export class ContactService {
  readonly endpoint = axios.defaults.baseURL;

  constructor(private http: HttpClient) {}

  sendContactForm(formData: any): Observable<any> {
    return this.http.post(`${this.endpoint}/contacto`, formData,{ responseType: 'text' });
  }
}
