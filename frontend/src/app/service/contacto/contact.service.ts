// contact.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ContactService {
  private backendUrl = 'http://localhost:8087'; // Reemplaza con la URL de tu backend

  constructor(private http: HttpClient) {}

  sendContactForm(formData: any): Observable<any> {
    return this.http.post(`${this.backendUrl}/contacto`, formData,{ responseType: 'text' });
  }
}
