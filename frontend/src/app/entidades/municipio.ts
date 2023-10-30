export class Municipio {
    
    idmunicipio: number;
    municipio: string;   

    public toString() : string {
        return JSON.stringify(this)
    }

}