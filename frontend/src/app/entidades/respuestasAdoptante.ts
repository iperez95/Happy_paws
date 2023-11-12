import { PreguntasAdoptante } from "./preguntasAdoptante";
import { Usuario } from "./usuario";

export class RespuestasAdoptante {
  idrespuesta: number;
  respuesta: string;

  preguntasAdoptante:{
    idpregunta: number;
    pregunta: string;
  }

  usuario:{
    idusuario: number;
    email: string;

  }
  // usuario: Usuario;


}