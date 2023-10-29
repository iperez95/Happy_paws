export class Protectora {

    nombre: string;
    direccion: string;
    urlLogo: string;
    email: string;
    descripcion: string;
    municipio: number;
    
    

    public toString() : string {
        return JSON.stringify(this)
    }

}