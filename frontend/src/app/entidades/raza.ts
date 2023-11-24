export class Raza {

    idraza: number;
    idespecie: number;
    raza: string;

    public toString() : string {
        return JSON.stringify(this)
    }
}