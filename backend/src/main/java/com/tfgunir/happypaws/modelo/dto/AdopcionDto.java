package com.tfgunir.happypaws.modelo.dto;


public class AdopcionDto {
    private int idAdopcion;
    private int idProtectora;
    private int idUsuario;
    private int idAnimal;
    private int idEstadoAdopcion;


    public AdopcionDto() {
    }


    public AdopcionDto(int idAdopcion, int idProtectora, int idUsuario, int idAnimal, int idEstadoAdopcion) {
        this.idAdopcion = idAdopcion;
        this.idProtectora = idProtectora;
        this.idUsuario = idUsuario;
        this.idAnimal = idAnimal;
        this.idEstadoAdopcion = idEstadoAdopcion;
    }


    public int getIdAdopcion() {
        return idAdopcion;
    }


    public void setIdAdopcion(int idAdopcion) {
        this.idAdopcion = idAdopcion;
    }


    public int getIdProtectora() {
        return idProtectora;
    }


    public void setIdProtectora(int idProtectora) {
        this.idProtectora = idProtectora;
    }


    public int getIdUsuario() {
        return idUsuario;
    }


    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }


    public int getIdAnimal() {
        return idAnimal;
    }


    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }


    public int getIdEstadoAdopcion() {
        return idEstadoAdopcion;
    }


    public void setIdEstadoAdopcion(int idEstadoAdopcion) {
        this.idEstadoAdopcion = idEstadoAdopcion;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idAdopcion;
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AdopcionDto other = (AdopcionDto) obj;
        if (idAdopcion != other.idAdopcion)
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "AdopcionDto [idAdopcion=" + idAdopcion + ", idProtectora=" + idProtectora + ", idUsuario=" + idUsuario
                + ", idAnimal=" + idAnimal + ", idEstadoAdopcion=" + idEstadoAdopcion + "]";
    }   



    
    
    

    
}
