import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import axios from "axios";
import { AxiosService } from "../axios/axios.service";
import { Observable, catchError, throwError } from "rxjs";
import { Protectora } from "src/app/entidades/protectora";

@Injectable({
    providedIn: 'root'
  })
  export class ProtectoraAdminService {
  
    readonly endpoint = axios.defaults.baseURL;
    constructor(private _httpClient : HttpClient, private axiosService: AxiosService) { 
    }
  
    

    public obtenerProtectoraPorId(idprotectora : number): Observable<any> {
        return this._httpClient.get(`${this.endpoint}/protectora/${idprotectora}`)
          .pipe(catchError(this.manejarError));
      }


    public listaProtectoras(): Observable<any> {
      return this._httpClient.get(`${this.endpoint}/api/admin/protectoras/todas`)
        .pipe(catchError(this.manejarError));
    }

    public actualizarProtectora (id:number, protectora : Protectora): Observable<Object> {
        return this._httpClient.put(`${this.endpoint}/api/admin/protectora/modificar/${id}`, protectora)
          .pipe(catchError(this.manejarError));
      }

      public inactivarProtectora (id:number, protectora:Protectora): Observable<Object> {
        return this._httpClient.put(`${this.endpoint}/api/admin/protectora/inactivar/${id}`,protectora)
          .pipe(catchError(this.manejarError));
      }

      public activarProtectora (id:number, protectora:Protectora): Observable<Object> {
        return this._httpClient.put(`${this.endpoint}/api/admin/protectora/activar/${id}`,protectora)
          .pipe(catchError(this.manejarError));
      }

      public pendienteProtectora (id:number, protectora:Protectora): Observable<Object> {
        return this._httpClient.put(`${this.endpoint}/api/admin/protectora/pendiente/${id}`,protectora)
          .pipe(catchError(this.manejarError));
      }


  
    // public listarProvincias(): Observable<any> {
    //   return this._httpClient.get(`${this.endpoint}/provincia/todas`)
    //     .pipe(catchError(this.manejarError));
    // }
    
    // public listarProtectoraPorIdProvincia(provinciaId: number): Observable<any> {
    //   return this._httpClient.get(`${this.endpoint}/protectora/porprovincia/${provinciaId}`)
    //     .pipe(catchError(this.manejarError));
    // }
  


  
    
  
    // public obtenerProtectoraPorIdUsuario(idUsuario : number): Observable<any> {
    //   return this._httpClient.get(`${this.endpoint}/protectora/usuario/${idUsuario}`)
    //     .pipe(catchError(this.manejarError));
    // }
  
   
  
    
  


  
    public animalesProtectora(idProtectora: number): Observable<any> {
      return this._httpClient.get(`${this.endpoint}/buscar/poridprotectora/${idProtectora}`)
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
  
  
  
    ngOnInit() {
  
    }
    
  }
  