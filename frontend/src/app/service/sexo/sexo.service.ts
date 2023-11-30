import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import axios from 'axios';
import {Sexo} from "../../entidades/sexo";

@Injectable({
  providedIn: 'root'
})
export class SexoService {

  readonly endpoint = axios.defaults.baseURL;

  constructor(private _httpClient: HttpClient) {
  }

  public listarSexos(): Observable<any> {
    return this._httpClient.get(`${this.endpoint}/atributos/sexos`).pipe(catchError(this.manejarError));
  }

  public obtenerSexoPorId(id: number): Observable<Sexo>{
    return this._httpClient.get<Sexo>(`${this.endpoint}/atributos/sexo/buscar/${id}`);
  }

  /**
   * Método que maneja los posibles errores de las llamadas al servicio rest
   * @param error
   * @returns un objeto de tipo Observable que contendrá el error que ha ocurrido
   */
  private manejarError(e: HttpErrorResponse) {
    let mensajeError = ''
    //Diferenciamos si el error es del servidor o más genérico
    if (e.error instanceof ErrorEvent) {
      mensajeError = 'A ocurrido un error:' + e.error
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
