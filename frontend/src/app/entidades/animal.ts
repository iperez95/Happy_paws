// Clase Animal

export class Animal {

    idanimal : number;
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

    // valoresAtributo: {
    //     idvalor: number;
    //     idtipo : number;
    //     valor: string;
    //}

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
    //     this.valoresAtributo = {
    //         idvalor: 0,
    //         idtipo : 0,
    //         valor: ''
    //     };
    // }





    // Método ToString

    public toString() : string {
        return JSON.stringify(this)
    }

}