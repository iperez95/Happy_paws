export class Especie {

    idespecie: number;
    especie: string;

    public toString() : string {
        return JSON.stringify(this)
    }
}