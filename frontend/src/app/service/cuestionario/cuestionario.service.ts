import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import axios from "axios";
import { Observable, catchError, throwError } from "rxjs";
import { RespuestasAdoptante } from "src/app/entidades/respuestasAdoptante";


@Injectable({
    providedIn: 'root'
  })

  
  export class CuestionarioService{

    //URL del servicio Rest
  readonly endpoint = axios.defaults.baseURL;
  
  /**
   * Encargado de hacer las peticiones HTTP a nuestro servicio REST
   * @param _httpClient 
   */
  constructor(private _httpClient : HttpClient) { 
  }

  

  obtenerPreguntas(): Observable<any>{
    return this._httpClient.get(`${this.endpoint}/cuestionario/preguntas`)
    .pipe(catchError(this.manejarError));
  }

  enviarRespuestas(respuestas: RespuestasAdoptante[]): Observable<any> {
    return this._httpClient.post<any>(`${this.endpoint}/cuestionario/guardar`, respuestas);
  }


  // public guardarLasRespuestas(respuestas: Respuestas[]): Observable<any>{
  //   console.log(respuestas);
  //   return this._httpClient.post(`${this.endpoint}/cuestionario/respuestas`, respuestas)
  //   .pipe(catchError(this.manejarError));
  // }




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