export class Usuario {
    apellidos: string;
    nombre: string;   
    email: any;
    password: string;
    dni: string;
    telefono: string;
    direccion: string;

    public toString() : string {
        return JSON.stringify(this)
    }
}