export class Usuario {
    id: number;
    apellidos: string;
    nombre: string;   
    email: any;
    password: string;
    dni: string;
    telefono: string;
    direccion: string;
    rol: string;
    idProtectora: number;

    isProtectora(): boolean {
        return this.rol == 'Protectora';
    }
    
    isAdoptante(): boolean {
        return this.rol == 'Adoptante';
    }

    public toString() : string {
        return JSON.stringify(this)
    }
}