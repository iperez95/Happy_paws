export class Protectora {

    idProtectora: number;
    idEstadoProtectora: number;
    nombre: string;
    direccion: string;
    urlLogo: string;
    email: string;
    
    constructor(){
        this.idProtectora = 0
        this.idEstadoProtectora = 0
        this.nombre = ""
        this.direccion = ""
        this.urlLogo = ""
        this.email = ""
    }

    public toString() : string {
        return JSON.stringify(this)
    }

}