import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { Protectora } from 'src/app/entidades/protectora';


@Injectable({
  providedIn: 'root'
})
export class ProtectoraService {

  //URL del servicio Rest
  readonly endpoint = 'http://localhost:8087';

  /**
   * Encargado de hacer las peticiones HTTP a nuestro servicio REST
   * @param _httpClient 
   */
  constructor(private _httpClient : HttpClient) { 
  }

  /**
   * Método que lista todas las protectoras del servicio Rest
   * @returns un objeto de tipo Observable<any> con la llámada formada al servicio
   * Rest.
   */
  public listarProtectora(): Observable<any> {
    return this._httpClient.get(`${this.endpoint}/protectora/gestion/listado`)
      .pipe(catchError(this.manejarError));
  }

  public altaProtectora(protectora: Protectora): Observable<Object> {
    return this._httpClient.post(`${this.endpoint}/protectora/gestion/alta`, protectora)
      .pipe(catchError(this.manejarError));
  }

  public obtenerProtectoraPorId(): Observable<any> {
    return this._httpClient.get(`${this.endpoint}/protectora/{id}`)
      .pipe(catchError(this.manejarError));
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

  ngOnInit() {

  }
  
}
