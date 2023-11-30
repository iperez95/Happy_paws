// Clase Animal

import {Protectora} from "./protectora";

export class Animal {

  idanimal: number;
  descripcion: string;
  enabled: boolean;
  envio: boolean;
  fechaAlta: Date;
  fechaNacimiento: Date;
  municipio: {
    idmunicipio: number;
    municipio: string;
    provincia: {
      idprovincia: number;
      provincia: string;
    }
  } | any;
  protectora: Protectora;
  nombre: string;
  raza: {
    idraza: number;
    raza: string;
    especie: {
      idespecie: number;
      especie: string;
    }
  } | any;
  sexo: {
    idsexo: number;
    sexo: string;
  } | any;
  tamano: {
    idtamano: number;
    tamano: string;
  } | any;
  fecha_enabled: Date | any;

}
