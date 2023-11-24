export class Protectora {
    idprotectora: number;
    nombre: string;
    direccion: string;
    urlLogo: string;
    email: string;
    telefono: number;
    descripcion: string;
    idmunicipio: string;
    nombreMunicipio: string;
    idprovincia: string;
    nombreProvincia: string;
    idEstadoProtectora: number;
    nombreEstadoProtectora: string;


    
    // municipio: {
    //   idmunicipio: number;
    //   municipio: string;
    //   provincia: {
    //     idprovincia: number;
    //     provincia: string;
    //   };
    // };

    // provincia: {
    //   idprovincia: number;
    //   provincia: string;
    // };

    public toString() : string {
        return JSON.stringify(this)
    }

}

