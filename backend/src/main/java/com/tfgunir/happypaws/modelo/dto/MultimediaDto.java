package com.tfgunir.happypaws.modelo.dto;

public class MultimediaDto {

    int idMultimedia;
    int idAnimal;
    String enlace;

    public MultimediaDto() {
    }

    public MultimediaDto(int idMultimedia, int idAnimal, String enlace) {
        this.idMultimedia = idMultimedia;
        this.idAnimal = idAnimal;
        this.enlace = enlace;
    }

    public int getidMultimedia() {
        return idMultimedia;
    }

    public void setidMultimedia(int idMultimedia) {
        this.idMultimedia = idMultimedia;
    }

    public int getidAnimal() {
        return idAnimal;
    }

    public void setidAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
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
        result = prime * result + idMultimedia;
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
        if (idMultimedia != other.idMultimedia)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MultimediaDto [idMultimedia=" + idMultimedia + ", idAnimal=" + idAnimal + ", enlace=" + enlace + "]";
    }


}