package com.tfgunir.happypaws.modelo.dto;

import java.util.Date;

public class AnimalDto {

    private int idanimal;
    private String nombre;
	private String descripcion;
	private boolean enabled;
	private boolean envio;
    private Date fechaAlta;
    private Date fechaNacimiento;
    private int idMunicipio;
    private String nombreMunicipio;
    private int idProvincia;
    private String nombreProvincia;
    private int idProtectora;
    private String nombreProtectora;
    private int idEspecie;
    private String nombreEspecie;
    private int idRaza;
    private String nombreRaza;
    private int idSexo;
    private String nombreSexo;
    private int idTamano;
    private String nombreTamano;
    private int idEstado;
    private String nombreEstado;

    public AnimalDto() {
    }

    public AnimalDto(int idanimal, String nombre, String descripcion, boolean enabled, boolean envio, Date fechaAlta,
            Date fechaNacimiento, int idMunicipio, String nombreMunicipio, int idProvincia, String nombreProvincia,
            int idProtectora, String nombreProtectora, int idEspecie, String nombreEspecie, int idRaza,
            String nombreRaza, int idSexo, String nombreSexo, int idTamano, String nombreTamano, int idEstado,
            String nombreEstado) {
        this.idanimal = idanimal;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.enabled = enabled;
        this.envio = envio;
        this.fechaAlta = fechaAlta;
        this.fechaNacimiento = fechaNacimiento;
        this.idMunicipio = idMunicipio;
        this.nombreMunicipio = nombreMunicipio;
        this.idProvincia = idProvincia;
        this.nombreProvincia = nombreProvincia;
        this.idProtectora = idProtectora;
        this.nombreProtectora = nombreProtectora;
        this.idEspecie = idEspecie;
        this.nombreEspecie = nombreEspecie;
        this.idRaza = idRaza;
        this.nombreRaza = nombreRaza;
        this.idSexo = idSexo;
        this.nombreSexo = nombreSexo;
        this.idTamano = idTamano;
        this.nombreTamano = nombreTamano;
        this.idEstado = idEstado;
        this.nombreEstado = nombreEstado;
    }

    public int getIdanimal() {
        return idanimal;
    }

    public void setIdanimal(int idanimal) {
        this.idanimal = idanimal;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public String getNombreEspecie() {
        return nombreEspecie;
    }

    public void setNombreEspecie(String nombreEspecie) {
        this.nombreEspecie = nombreEspecie;
    }

    public int getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(int idRaza) {
        this.idRaza = idRaza;
    }

    public String getNombreRaza() {
        return nombreRaza;
    }

    public void setNombreRaza(String nombreRaza) {
        this.nombreRaza = nombreRaza;
    }

    public int getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(int idSexo) {
        this.idSexo = idSexo;
    }

    public String getNombreSexo() {
        return nombreSexo;
    }

    public void setNombreSexo(String nombreSexo) {
        this.nombreSexo = nombreSexo;
    }

    public int getIdTamano() {
        return idTamano;
    }

    public void setIdTamano(int idTamano) {
        this.idTamano = idTamano;
    }

    public String getNombreTamano() {
        return nombreTamano;
    }

    public void setNombreTamano(String nombreTamano) {
        this.nombreTamano = nombreTamano;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idanimal;
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
        if (idanimal != other.idanimal)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AnimalDto [idanimal=" + idanimal + ", nombre=" + nombre + ", descripcion=" + descripcion + ", enabled="
                + enabled + ", envio=" + envio + ", fechaAlta=" + fechaAlta + ", fechaNacimiento=" + fechaNacimiento
                + ", idMunicipio=" + idMunicipio + ", nombreMunicipio=" + nombreMunicipio + ", idProvincia="
                + idProvincia + ", nombreProvincia=" + nombreProvincia + ", idProtectora=" + idProtectora
                + ", nombreProtectora=" + nombreProtectora + ", idEspecie=" + idEspecie + ", nombreEspecie="
                + nombreEspecie + ", idRaza=" + idRaza + ", nombreRaza=" + nombreRaza + ", idSexo=" + idSexo
                + ", nombreSexo=" + nombreSexo + ", idTamano=" + idTamano + ", nombreTamano=" + nombreTamano
                + ", idEstado=" + idEstado + ", nombreEstado=" + nombreEstado + "]";
    }

    
}