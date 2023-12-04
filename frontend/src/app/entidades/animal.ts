// Clase Animal

import { Municipio } from "./municipio";
import {Protectora} from "./protectora";
import { Raza } from "./raza";
import { Sexo } from "./sexo";
import { Tamano } from "./tamano";

export class Animal {

  idanimal: number;
  descripcion: string;
  enabled: boolean;
  envio: boolean;
  fechaAlta: Date;
  fechaNacimiento: Date;
  municipio: Municipio | any;
  protectora: Protectora;
  nombre: string;
  raza: Raza | any;
  sexo: Sexo | any;
  tamano: Tamano | any;
  fecha_enabled: Date | any;
}
