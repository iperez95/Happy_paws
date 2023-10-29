export class Protectora {

    idProtectora: number;
    idEstadoProtectora: number;
    nombre: string;
    direccion: string;
    urlLogo: string;
    email: string;
    municipio: number;
    
    // constructor(){
    //     this.idProtectora 
    //     this.idEstadoProtectora 
    //     this.nombre 
    //     this.direccion 
    //     this.urlLogo
    //     this.email
    // }

    public toString() : string {
        return JSON.stringify(this)
    }

}