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
    enabled: number;

    isProtectora(): boolean {
        return this.rol == 'Protectora';
    }
    
    isAdoptante(): boolean {
        return this.rol == 'Adoptante';
    }

    isAdministrador(): boolean {
        return this.rol == 'Administrador';
    }

    public toString() : string {
        return JSON.stringify(this)
    }

    estado(): string {
        if (this.enabled == 1) {
            return "Activo"
        } else {
            return "Inactivo"
        }
    }

}