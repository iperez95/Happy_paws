import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import axios from "axios";
import { AxiosService } from "../axios/axios.service";
import { Observable, catchError, throwError } from "rxjs";
import { Protectora } from "src/app/entidades/protectora";
import { AnimalDto } from "src/app/entidades/animalDto";
import { Animal } from "src/app/entidades/animal";

@Injectable({
    providedIn: 'root'
  })
  export class AnimalAdminService {
  
    readonly endpoint = axios.defaults.baseURL;
    constructor(private _httpClient : HttpClient, private axiosService: AxiosService) { 
    }
  
    public listaAnimalesDto(): Observable<any> {
      return this._httpClient.get(`${this.endpoint}/animales/listadodto`)
        .pipe(catchError(this.manejarError));
    }



    public inactivarAnimal (id:number, animalDto:AnimalDto): Observable<Object> {
      return this._httpClient.put(`${this.endpoint}/api/admin/animal/inactivar/${id}`,animalDto)
        .pipe(catchError(this.manejarError));
    }

    public activarAnimal (id:number, animalDto:AnimalDto): Observable<Object> {
      return this._httpClient.put(`${this.endpoint}/api/admin/animal/activar/${id}`,animalDto)
        .pipe(catchError(this.manejarError));
    }

    public obtenerAnimalPorId(idanimal : number): Observable<Animal> {
      return this._httpClient.get<Animal>(`${this.endpoint}/animales/verUno/${idanimal}`)
        .pipe(catchError(this.manejarError));
    }

    public actualizarAnimal(idanimal : number, animal : any): Observable<Object> {
      console.log("Esto es lo que manda el front"+animal);
      return this._httpClient.put<Animal>(`${this.endpoint}/api/admin/animal/modificar/${idanimal}`, animal)
        .pipe(catchError(this.manejarError));
    }

    public actualizarAnimalDto(idanimal : number, animalDto : any): Observable<Object> {
      console.log("Esto es lo que manda el front"+animalDto);
      return this._httpClient.put<Animal>(`${this.endpoint}/api/admin/animaldto/modificar/${idanimal}`, animalDto)
        .pipe(catchError(this.manejarError));
    }
  
    // public busquedaPorNombre(nombre:string): Observable<any> {
    //   return this._httpClient.get(`${this.endpoint}/api/admin/protectoras/busquedapornombre/${nombre}`)
    //     .pipe(catchError(this.manejarError));
    // }

    

    //   public inactivarProtectora (id:number, protectora:Protectora): Observable<Object> {
    //     return this._httpClient.put(`${this.endpoint}/api/admin/protectora/inactivar/${id}`,protectora)
    //       .pipe(catchError(this.manejarError));
    //   }

    //   public activarProtectora (id:number, protectora:Protectora): Observable<Object> {
    //     return this._httpClient.put(`${this.endpoint}/api/admin/protectora/activar/${id}`,protectora)
    //       .pipe(catchError(this.manejarError));
    //   }

    //   public pendienteProtectora (id:number, protectora:Protectora): Observable<Object> {
    //     return this._httpClient.put(`${this.endpoint}/api/admin/protectora/pendiente/${id}`,protectora)
    //       .pipe(catchError(this.manejarError));
    //   }


    
  
  
  
    
  


  
    // public animalesProtectora(idProtectora: number): Observable<any> {
    //   return this._httpClient.get(`${this.endpoint}/buscar/poridprotectora/${idProtectora}`)
    //     .pipe(catchError(this.manejarError));
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
  
  
  
    ngOnInit() {
  
    }
    
  }
  