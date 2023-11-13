import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import axios from "axios";
import { Observable, catchError, throwError } from "rxjs";
import { RespuestasAdoptante } from "src/app/entidades/respuestasAdoptante";

import { AxiosService } from "../axios/axios.service";


@Injectable({
  providedIn: 'root'
})


/**
 * Servicio para manejar las operaciones relacionadas con el cuestionario.
 */
export class CuestionarioService {

  //URL del servicio Rest
  readonly endpoint = axios.defaults.baseURL;


  /**
   * Crea una instancia de CuestionarioService.
   * @param {HttpClient} _httpClient - La instancia de HttpClient inyectada.
   * @param {AxiosService} axiosService - La instancia de AxiosService inyectada.
   * @memberof CuestionarioService
   */
  constructor(private _httpClient: HttpClient, private axiosService: AxiosService) {
  }


  /**
   * Obtiene la lista de preguntas para el cuestionario.
   * @returns Un Observable que emite la lista de preguntas.
   */
  obtenerPreguntas(): Observable<any> {
    return this._httpClient.get(`${this.endpoint}/cuestionario/preguntas`)
      .pipe(catchError(this.manejarError));
  }

  // ENVIA RESUPUESTAS FORMULARIO CON TOKEN SESION
  /**
   * Envía las respuestas dadas al servidor para su almacenamiento.
   * @param respuestas - Las respuestas a enviar.
   * @returns Un Observable que resuelve a la respuesta del servidor.
   * @throws Un error si no hay un token de autenticación.
   */
  enviarRespuestas(respuestas: RespuestasAdoptante[]): Observable<Object> {
    const token = this.axiosService.getAuthToken();
    if (!token) {
      throw new Error('No hay token');
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    console.log('Token:', token);
    console.log('Headers:', headers);
    return this._httpClient.post(`${this.endpoint}/cuestionario/guardar`, respuestas, { headers })
      .pipe(catchError(this.manejarError));;
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

  //ORIGINAL FUNCIONANDO SIN USUARIO EN SESIÓN
  // enviarRespuestas(respuestas: RespuestasAdoptante[]): Observable<any> {

  //   return this._httpClient.post<any>(`${this.endpoint}/cuestionario/guardar`, respuestas);
  // }


  // public guardarLasRespuestas(respuestas: Respuestas[]): Observable<any>{
  //   console.log(respuestas);
  //   return this._httpClient.post(`${this.endpoint}/cuestionario/respuestas`, respuestas)
  //   .pipe(catchError(this.manejarError));
  // }


}
