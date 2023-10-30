export class Protectora {

    idprotectora: number;
    nombre: string;
    direccion: string;
    urlLogo: string;
    email: string;
    descripcion: string;
    idmunicipio: number;
    municipio: string;
    idprovincia: number;
    provincia: string;
    
    

    public toString() : string {
        return JSON.stringify(this)
    }

}