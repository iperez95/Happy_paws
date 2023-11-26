export class Adopcion {
    idAdopcion: number;
    idUsuario: number;
    nombreAnimal: string;
    nombreUsuario: string;
    fechaAdopcion: string;
    idAnimal: number;
    idEstadoAdopcion: number;
    estado: string;

    getEstado(): string {
        switch (this.idEstadoAdopcion) {
            case 1:
                return "En curso";
            case 2:
                return "Aprobada";
            case 3:
                return "Rechazada";
            default:
                return "Error";
        }
    }
}