// Clase Animal

export class Animal {

    _idanimal : number;
    _nombre : string;
    _fechaNacimiento: Date;
    _fechaAlta: Date;
    _descripcion : string;
    _enabled: boolean;
    _fechaEnabled: Date;

    _municipio: {
        _idmunicipio: number;
        _municipio: string;
        _provincia: {
          _idprovincia: number;
          _provincia: string;
        };
      };

    _valoresAtributo: {
        idvalor: number;
        idtipo : number;
        valor: string;
    }

    //  Constructor

    constructor() {}

    //  Getters and Setters

    public get idanimal(): number {
        return this._idanimal;
    }

    public set idanimal(value: number) {
        this._idanimal = value;
    }

    public get nombre(): string {
        return this._nombre;
    }

    public set nombre (value: string) {
        this._nombre = value;
    }

    public get fechaNacimiento(): Date {
      return this._fechaNacimiento;
    }

    public set fechaNacimiento(value: Date) {
      this._fechaNacimiento = value;
    }

    public get fechaAlta(): Date {
      return this._fechaAlta;
    }

    public set fechaAlta(value: Date) {
      this._fechaAlta = value;
    }

    public get descripcion(): string {
      return this._descripcion;
    }

    public set descripcion(value: string) {
      this._descripcion = value;
    }

    public get enabled(): boolean {
      return this._enabled;
    }

    public set enabled(value: boolean) {
      this._enabled = value;
    }

    public get fechaEnabled(): Date {
      return this._fechaEnabled;
    }

    public set fechaEnabled(value: Date) {
      this._fechaEnabled = value;
    }

    public get municipio(): {_idmunicipio: number, _municipio: string, _provincia: {_idprovincia: number, _provincia: string}} {
      return this._municipio;
    }

    public set municipio(value: {_idmunicipio: number, _municipio: string, _provincia: {_idprovincia: number, _provincia: string}}) {
      this._municipio = value;
    }

    public get valoresAtributos(): {idvalor: number, idtipo: number, valor: string} {
      return this._valoresAtributo;
    }

    public set valoresAtributos(value: {idvalor: number, idtipo: number, valor: string}) {
      this._valoresAtributo = value;
    }

    // Método ToString

    public toString() : string {
        return JSON.stringify(this)
    }

}