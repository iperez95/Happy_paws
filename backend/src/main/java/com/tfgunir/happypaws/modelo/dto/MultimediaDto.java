package com.tfgunir.happypaws.modelo.dto;

public class MultimediaDto {

    int idmultimedia;
    int idanimal;
    String enlace;

    public MultimediaDto() {
    }

    public MultimediaDto(int idmultimedia, int idanimal, String enlace) {
        this.idmultimedia = idmultimedia;
        this.idanimal = idanimal;
        this.enlace = enlace;
    }

    public int getIdmultimedia() {
        return idmultimedia;
    }

    public void setIdmultimedia(int idmultimedia) {
        this.idmultimedia = idmultimedia;
    }

    public int getIdanimal() {
        return idanimal;
    }

    public void setIdanimal(int idanimal) {
        this.idanimal = idanimal;
    }
    
    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idmultimedia;
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
        MultimediaDto other = (MultimediaDto) obj;
        if (idmultimedia != other.idmultimedia)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MultimediaDto [idmultimedia=" + idmultimedia + ", idanimal=" + idanimal + ", enlace=" + enlace + "]";
    }

    
}

