import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { catchError, map, Observable, throwError } from 'rxjs';
import { Animal } from 'src/app/entidades/animal';
import axios from 'axios';
import { AxiosService } from '../axios/axios.service';
import { Multimedia } from 'src/app/entidades/multimedia';

@Injectable({
  providedIn: 'root'
})

export class AnimalService {


  //URL del servicio Rest
  readonly endpoint = axios.defaults.baseURL;
  //readonly endpoint = 'http://localhost:8087';

    /**
   * Encargado de hacer las peticiones HTTP a nuestro servicio REST
   * @param _httpClient
   */
  //constructor(private _httpClient : HttpClient, private axiosService: AxiosService) { }
  constructor(private _httpClient : HttpClient) { }
  
  // Método que lista todos los animales del servicio Rest
  public listarAnimales(): Observable<any> {
    return this._httpClient.get(`${this.endpoint}/animales/listado`)
      .pipe(catchError(this.manejarError));
  }

  public listarAnimalesDto(): Observable<any> {
    return this._httpClient.get(`${this.endpoint}/animales/listadodto`)
      .pipe(catchError(this.manejarError));
  }

  // Método para ver un animal por su id
  public verAnimal(idanimal: number): Observable<Animal> {
    return this._httpClient.get<Animal>(`${this.endpoint}/animales/verUno/${idanimal}`)
      .pipe(catchError(this.manejarError));
  }

  // Método para dar de alta un animal
  public altaAnimal(animal: Animal): Observable<any> {
    return this._httpClient.post<any>(`${this.endpoint}/animales/gestion/alta`, animal)
      .pipe(catchError(this.manejarError));
  }

  // Método para eliminar un animal
  public eliminarAnimal(idanimal: number): Observable<any> {
    return this._httpClient.delete(`${this.endpoint}/animales/gestion/eliminar/${idanimal}`)
      .pipe(catchError(this.manejarError));
  }

  // Método para modificar un animal
  public modificarAnimal(idanimal: number, animal : Animal): Observable<any> {
    return this._httpClient.put(`${this.endpoint}/animales/gestion/modificar/${idanimal}`, animal)
      .pipe(catchError(this.manejarError));
  }

  // Método para buscar animales por provincia
  public listarAnimalPorIdProvincia(idprovincia: number): Observable<any> {
    return this._httpClient.get(`${this.endpoint}/animales/buscar/poridprovincia/${idprovincia}`)
      .pipe(catchError(this.manejarError));
  }

  //Método que lista todos los animales de una protectora por su id
  public listarAnimalPorIdProtectora(idprotectora: number): Observable<any> {
    return this._httpClient.get(`${this.endpoint}/animales/buscar/poridprotectora/${idprotectora}`)
      .pipe( catchError(this.manejarError));
  }

  // Método que lista todos los animales de una raza
  public listarAnimalPorRaza(idraza: number): Observable<any> {
    return this._httpClient.get(`${this.endpoint}/animales/buscar/porraza/${idraza}`)
     .pipe( catchError(this.manejarError));
  }

  // Método que lista todos los animales de una especie
  public listarAnimalPorEspecie(idespecie: number): Observable<any> {
    return this._httpClient.get(`${this.endpoint}/animales/buscar/porespecie/${idespecie}`)
    .pipe( catchError(this.manejarError));
  }

  // Método que lista todos los animales de un sexo
  public listarAnimalPorSexo(idsexo: number): Observable<any> {
    return this._httpClient.get(`${this.endpoint}/animales/buscar/porsexo/${idsexo}`)
   .pipe( catchError(this.manejarError));
  }

  // Método que lista todos los animales de un tamano
  public listarAnimalPorTamano(idtamano: number): Observable<any> {
    return this._httpClient.get(`${this.endpoint}/animales/buscar/ortamano/${idtamano}`)
  .pipe( catchError(this.manejarError));
  }

  // Método que lista todos los animales que son Perros
  public listarPerros(): Observable<any> {
    return this._httpClient.get(`${this.endpoint}/animales/buscar/soloperros`)
  .pipe( catchError(this.manejarError));
  }

  // Método que lista todos los animales que son Gatos
  public listarGatos(): Observable<any> {
    return this._httpClient.get(`${this.endpoint}/animales/buscar/sologatos`)
 .pipe( catchError(this.manejarError));
  }

  // Método para fotos del animal
  public fotosAnimal (idAnimal:number): Observable<any> {
    return this._httpClient.get(`${this.endpoint}/multimedia/animal/${idAnimal}`)
      .pipe(catchError(this.manejarError));
  }


  // Método para subir foto de animal
  public subirFoto(archivo: File, id: number): Observable <Multimedia> {
    let formData = new FormData();
    formData.append("archivo", archivo);
    formData.append("id", id.toString());
    return this._httpClient.post(`${this.endpoint}/animales/gestion/upload`, formData).pipe(
    map((  resp: any ) => resp.multimedia as Multimedia),
    catchError(this.manejarError)
    );
  }

  //Método para borrar foto de animal
  public borrarFoto(id: number): Observable<any> {
    return this._httpClient.delete(`${this.endpoint}/animales/gestion/borrarfoto/${id}`)
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
              `Body:`
      }
      //Imprimimos el mensaje de error y lo arrojamos médiante una función lambda
      //Esta manerá tenemos que hacerla así cuando trabajamos con Observables.
      console.error(mensajeError);
      console.error(e.error);
      console.error(e.status);
      return throwError(() => new Error(mensajeError));
    }

    public listarProvincias(): Observable<any> {
      return this._httpClient.get(`${this.endpoint}/provincia/todas`)
        .pipe(catchError(this.manejarError));
    }

    // Método para filtrar animales segun sus atributos
    public filtrarAnimales(
      especie: string,
      raza: string,
      sexo: string,
      tamano: string,
      provincia: string,
      envio: boolean
    ): Observable<any> {
      let params = new HttpParams()
      if (especie) params = params.set('especie', especie);
      if (raza) params = params.set('raza', raza);
      if (sexo) params = params.set('sexo', sexo);
      if (tamano) params = params.set('tamano', tamano);
      if (provincia) params = params.set('provincia', provincia);
      if (envio !== null && envio !== undefined) params = params.set('envio', envio ? 'true' : 'false');
      return this._httpClient.get<any>(`${this.endpoint}/animales/filtrar`, { params });
    }
  

  ngOnInit() {
  }
}
