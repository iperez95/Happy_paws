import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { correoformularioProtectora} from 'src/app/entidades/correoFormularioProtectora';
import { Protectora } from 'src/app/entidades/protectora';
import { ProtectoraService } from '../protectora/protectora.service';



@Injectable({
  providedIn: 'root'
})

export class ProtectoraContactService {

  readonly endpoint= 'http://localhost:8087';


  constructor(private _httpClient: HttpClient, private _protectoraService : ProtectoraService) {}

  // public contactProtectora (id:number, protectora : Protectora): Observable<Object> {
  //   return this._httpClient.put(`${this.endpoint}/protectora/contacto/${id}`, protectora)
  //     .pipe(catchError(this.manejarError));
  // }

  sendEmailToProtectora(id: number, formData: any): Observable<any> {
    return this._httpClient.post(`${this.endpoint}/protectora/contacto/${id}`, formData);
  }
  
    /**
   * Método que maneja los posibles errores de las llamadas al servicio rest
   * @param error 
   * @returns un objeto de tipo Observable que contendrá el error que ha ocurrido
   */
    private manejarError(e: HttpErrorResponse){
      let mensajeError = ''
      //Diferenciamos si el error es del servidor o más genérico
      if (e.error instanceof ErrorEvent) {
        mensajeError = 'Ha ocurrido un error:' + e.error
      } else {
        mensajeError = `El servicio Rest retorno: Status: ${e.status}, ` +
              `Body: ${e.error}`
      }
      //Imprimimos el mensaje de error y lo arrojamos médiante una función lambda
      //Esta manerá tenemos que hacerla así cuando trabajamos con Observables.
      console.error(mensajeError)
      return throwError(() => new Error(mensajeError));
    }

}
