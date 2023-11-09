import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { correoformularioProtectora} from 'src/app/entidades/correoFormularioProtectora';
import { AsyncAction } from 'rxjs/internal/scheduler/AsyncAction';


@Injectable({
  providedIn: 'root'
})
export class EmailService {
  private apiUrl = 'http://localhost:8087/protectora/contacto/${id}'; // Reemplaza con la URL correcta de tu backend

  constructor(private http: HttpClient) {}

  
  sendEmailToProtectora(id: number, correoformularioProtectora: correoformularioProtectora): Observable<any> {
    const body = {
      id: id,
      correoformularioProtectora: correoformularioProtectora
    };
    return this.http.post(`${this.apiUrl}/protectora/contacto/${id}`, body);
  }
}
