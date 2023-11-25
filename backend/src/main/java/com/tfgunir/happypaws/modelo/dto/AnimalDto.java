package com.tfgunir.happypaws.modelo.dto;

import java.sql.Date;

public class AnimalDto {

    private int idAnimal;
    private String nombre;
    private String descripcion;
    private int idProtectora;
    private String nombreProtectora;
    private boolean enabled;
    private Date FECHA_enabled;
    private boolean envio;
    private Date fechaAlta;
    private Date fechaNacimiento;
    private int idEspecie;
    private String especie;
    private int idRaza;
    private String raza;
    private int idSexo;
    private String sexo;
    private int idTamano;
    private String tamano;    
    private int idMunicipio;
    private String nombreMunicipio;
    private int idProvincia;
    private String nombreProvincia;


    public AnimalDto(){
    }


    public AnimalDto(int idAnimal, String nombre, String descripcion, int idProtectora, String nombreProtectora,
            boolean enabled, Date FECHA_enabled, boolean envio, Date fechaAlta, Date fechaNacimiento, int idEspecie,
            String especie, int idRaza, String raza, int idSexo, String sexo, int idTamano, String tamano,
            int idMunicipio, String nombreMunicipio, int idProvincia, String nombreProvincia) {
        this.idAnimal = idAnimal;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idProtectora = idProtectora;
        this.nombreProtectora = nombreProtectora;
        this.enabled = enabled;
        this.FECHA_enabled = FECHA_enabled;
        this.envio = envio;
        this.fechaAlta = fechaAlta;
        this.fechaNacimiento = fechaNacimiento;
        this.idEspecie = idEspecie;
        this.especie = especie;
        this.idRaza = idRaza;
        this.raza = raza;
        this.idSexo = idSexo;
        this.sexo = sexo;
        this.idTamano = idTamano;
        this.tamano = tamano;
        this.idMunicipio = idMunicipio;
        this.nombreMunicipio = nombreMunicipio;
        this.idProvincia = idProvincia;
        this.nombreProvincia = nombreProvincia;
    }


    public int getIdAnimal() {
        return idAnimal;
    }


    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getDescripcion() {
        return descripcion;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public int getIdProtectora() {
        return idProtectora;
    }


    public void setIdProtectora(int idProtectora) {
        this.idProtectora = idProtectora;
    }


    public String getNombreProtectora() {
        return nombreProtectora;
    }


    public void setNombreProtectora(String nombreProtectora) {
        this.nombreProtectora = nombreProtectora;
    }


    public boolean isEnabled() {
        return enabled;
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public Date getFECHA_enabled() {
        return FECHA_enabled;
    }


    public void setFECHA_enabled(Date FECHA_enabled) {
        this.FECHA_enabled = FECHA_enabled;
    }


    public boolean isEnvio() {
        return envio;
    }


    public void setEnvio(boolean envio) {
        this.envio = envio;
    }


    public Date getFechaAlta() {
        return fechaAlta;
    }


    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }


    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }


    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    public int getIdEspecie() {
        return idEspecie;
    }


    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }


    public String getEspecie() {
        return especie;
    }


    public void setEspecie(String especie) {
        this.especie = especie;
    }


    public int getIdRaza() {
        return idRaza;
    }


    public void setIdRaza(int idRaza) {
        this.idRaza = idRaza;
    }


    public String getRaza() {
        return raza;
    }


    public void setRaza(String raza) {
        this.raza = raza;
    }


    public int getIdSexo() {
        return idSexo;
    }


    public void setIdSexo(int idSexo) {
        this.idSexo = idSexo;
    }


    public String getSexo() {
        return sexo;
    }


    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


    public int getIdTamano() {
        return idTamano;
    }


    public void setIdTamano(int idTamano) {
        this.idTamano = idTamano;
    }


    public String getTamano() {
        return tamano;
    }


    public void setTamano(String tamano) {
        this.tamano = tamano;
    }


    public int getIdMunicipio() {
        return idMunicipio;
    }


    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }


    public String getNombreMunicipio() {
        return nombreMunicipio;
    }


    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }


    public int getIdProvincia() {
        return idProvincia;
    }


    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }


    public String getNombreProvincia() {
        return nombreProvincia;
    }


    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idAnimal;
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
        AnimalDto other = (AnimalDto) obj;
        if (idAnimal != other.idAnimal)
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "AnimalDto [idAnimal=" + idAnimal + ", nombre=" + nombre + ", descripcion=" + descripcion
                + ", idProtectora=" + idProtectora + ", nombreProtectora=" + nombreProtectora + ", enabled=" + enabled
                + ", FECHA_enabled=" + FECHA_enabled + ", envio=" + envio + ", fechaAlta=" + fechaAlta
                + ", fechaNacimiento=" + fechaNacimiento + ", idEspecie=" + idEspecie + ", especie=" + especie
                + ", idRaza=" + idRaza + ", raza=" + raza + ", idSexo=" + idSexo + ", sexo=" + sexo + ", idTamano="
                + idTamano + ", tamano=" + tamano + ", idMunicipio=" + idMunicipio + ", nombreMunicipio="
                + nombreMunicipio + ", idProvincia=" + idProvincia + ", nombreProvincia=" + nombreProvincia + "]";
    }
}
