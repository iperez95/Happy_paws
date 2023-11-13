import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import axios from "axios";
import { Observable, catchError, throwError } from "rxjs";
import { RespuestasAdoptante } from "src/app/entidades/respuestasAdoptante";

import { AxiosService } from "../axios/axios.service";


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
  constructor(private _httpClient : HttpClient, private axiosService: AxiosService) { 
  }
 
  

  obtenerPreguntas(): Observable<any>{
    return this._httpClient.get(`${this.endpoint}/cuestionario/preguntas`)
    .pipe(catchError(this.manejarError));
  }

// 2DO INTENTO ENVIO RESPUESTAS CON TOKEN 
 enviarRespuestas(respuestas: RespuestasAdoptante[]): Observable<any> {
  const token = this.axiosService.getAuthToken();
  if (!token) {
    throw new Error('No hay token');
  }
  const headers = new HttpHeaders().set(
    'Authorization', 
    `Bearer ${token}`);
   
  return this._httpClient.post<any>(`${this.endpoint}/cuestionario/guardar`, respuestas, {headers});
    // return this.axiosService.request('post', `${this.endpoint}/cuestionario/guardar`, respuestas)
    // .then(response => response.data)
    // .catch(error => {
    //   console.log('Error en solicitud Posst',error);
    //   throw error;
  }


  //PARA MANDAR EL TOKEN EN LA CABECERA
  // enviarRespuestas(respuestas: RespuestasAdoptante[]): Promise<any> {
  //   return this.axiosService.request('post', `${this.endpoint}/cuestionario/guardar`, respuestas);
  // }

  //ORIGINAL FUNCIONANDO SIN USUARIO EN SESIÓN
  // enviarRespuestas(respuestas: RespuestasAdoptante[]): Observable<any> {
    
  //   return this._httpClient.post<any>(`${this.endpoint}/cuestionario/guardar`, respuestas);
  // }


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