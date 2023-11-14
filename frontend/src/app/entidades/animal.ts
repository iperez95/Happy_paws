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
      
    provincia: {
        idprovincia: number;
        provincia: string;
      };

    valoresAtributo: {
        idvalor: number;
        idtipo : number;
        valor: string;
    }

    public toString() : string {
        return JSON.stringify(this)
    }

}