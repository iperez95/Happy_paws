import { Injectable } from '@angular/core';
import axios from "axios";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MultimediaService {

  // Attributes

  private readonly endpoint: string | undefined = axios.defaults.baseURL;

  // Constructor

  constructor(private _httpClient: HttpClient) { }

  // Methods Services

  public subirFotosAnimal(fotos: FormData): Observable<any> {
    return this._httpClient.post<any>(`${this.endpoint}/multimedia/gestion/upload`, fotos);
  }

  public recuperarFotosAnimales(idsAnimales: number[]): Observable<any>{
    const params: HttpParams = new HttpParams().set('idsAnimales', idsAnimales.toString());
    return this._httpClient.get(`${this.endpoint}/multimedia/fotos`, { params });
  }

  public recuperarEnlaceFoto(nombreArchivoImagen: string): Observable<any> {
    return this._httpClient.get(`${this.endpoint}/multimedia/${nombreArchivoImagen}`);
  }

}
