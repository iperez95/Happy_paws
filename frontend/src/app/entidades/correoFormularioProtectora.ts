export class correoformularioProtectora{
    nombre: string;
    formEmail: string;
    telefono: string;
    mensaje: string;
    idprotectora: number;

    public toString() : string {
        return JSON.stringify(this)
    }
}