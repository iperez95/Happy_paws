// Clase Animal

export class Animal {

    idanimal : number;
    protectora:{
      idprotectora: number;
      nombre: string;
    }
    raza: {
      idraza: number;
      raza: string;
      especie: {
        idespecie: number;
        especie: string;
      }
    }
    sexo: {
      idsexo: number;
      sexo: string;
    }
    tamano: {
      idtamano: number;
      tamano: string;
    }
    envio: boolean;
    nombre : string;
    fechaNacimiento: Date;
    fechaAlta: Date;
    descripcion : string;
    enabled: boolean;
    fechaEnabled: Date;

    municipio: {
        idmunicipio: number;
        municipio: string;
        provincia: {
          idprovincia: number;
          provincia: string;
        };
      };


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