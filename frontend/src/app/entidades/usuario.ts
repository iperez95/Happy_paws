export class Usuario {
    apellidos: string;
    nombre: string;   
    email: any;

    public toString() : string {
        return JSON.stringify(this)
    }
}