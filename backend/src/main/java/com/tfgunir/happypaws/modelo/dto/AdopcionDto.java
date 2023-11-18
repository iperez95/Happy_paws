package com.tfgunir.happypaws.modelo.dto;

import java.util.Date;

public class AdopcionDto {
    private int idAdopcion;
    private int idProtectora;
    private int idUsuario;
    private int idAnimal;
    private int idEstadoAdopcion;
    private Date fechaAdopcion;

    private String nombreProtectora;
    private String nombreUsuario;
    private String nombreAnimal;

    public AdopcionDto() {
    }

    public AdopcionDto(int idAdopcion, int idProtectora, int idUsuario, int idAnimal, int idEstadoAdopcion, Date fechaAdopcion, String nombreProtectora, String nombreUsuario, String nombreAnimal /*, other fields...*/) {
        this.idAdopcion = idAdopcion;
        this.idProtectora = idProtectora;
        this.idUsuario = idUsuario;
        this.idAnimal = idAnimal;
        this.idEstadoAdopcion = idEstadoAdopcion;
        this.fechaAdopcion = fechaAdopcion;
        this.nombreProtectora = nombreProtectora;
        this.nombreUsuario = nombreUsuario;
        this.nombreAnimal = nombreAnimal;

    
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

    public Date getFechaAdopcion() {
        return fechaAdopcion;
    }

    public void setFechaAdopcion(Date fechaAdopcion) {
        this.fechaAdopcion = fechaAdopcion;
    }

    public String getNombreProtectora() {
        return nombreProtectora;
    }

    public void setNombreProtectora(String nombreProtectora) {
        this.nombreProtectora = nombreProtectora;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreAnimal() {
        return nombreAnimal;
    }

    public void setNombreAnimal(String nombreAnimal) {
        this.nombreAnimal = nombreAnimal;
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
                + ", idAnimal=" + idAnimal + ", idEstadoAdopcion=" + idEstadoAdopcion + ", fechaAdopcion="
                + fechaAdopcion + ", nombreProtectora=" + nombreProtectora + ", nombreUsuario=" + nombreUsuario
                + ", nombreAnimal=" + nombreAnimal + "]";
    }





    
    
    

    
}
