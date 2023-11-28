// Clase Animal

import { Especie } from "./especie";
import { Municipio } from "./municipio";
import { Protectora } from "./protectora";
import { Provincia } from "./provincia";
import { Raza } from "./raza";
import { Sexo } from "./sexo";
import { Tamano } from "./tamano";

export class Animal {

    idanimal : number;
    protectora: Protectora;
    raza: Raza;
    especie: Especie;
    sexo: Sexo;
    tamano: Tamano;
    envio: boolean;
    nombre : string;
    fechaNacimiento: Date;
    fechaAlta: Date;
    descripcion : string;
    enabled: boolean;
    fechaEnabled: Date;
    municipio: Municipio;
    provincia: Provincia;
    


    // raza: {
    //   idraza: number;
    //   raza: string;
    //   idespecie: {
    //     idespecie: number;
    //     especie: string;
    //   }
    // }
    // sexo: {
    //   idsexo: number;
    //   sexo: string;
    // }
    // tamano: {
    //   idtamano: number;
    //   tamano: string;
    // }
    // municipio: {
    //     idmunicipio: number;
    //     municipio: string;
    //     provincia: {
    //       idprovincia: number;
    //       provincia: string;
    //     };
    //   };
    //  Constructor
    // constructor() {
    //     this.idanimal = 0;
    //     this.nombre = '';
    //     this.fechaNacimiento = new Date();
    //     this.fechaAlta = new Date();
    //     this.descripcion = '';
    //     this.enabled = false;
    //     this.fechaEnabled = new Date();
    //     this.municipio = {
    //         idmunicipio: 0,
    //         municipio: '',
    //         provincia: {
    //           idprovincia: 0,
    //           provincia: ''
    //         }
    //     };
    // }



    // Método ToString

    public toString() : string {
        return JSON.stringify(this)
    }

}