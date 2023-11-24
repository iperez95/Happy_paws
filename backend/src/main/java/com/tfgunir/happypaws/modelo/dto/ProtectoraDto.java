package com.tfgunir.happypaws.modelo.dto;

public class ProtectoraDto {

    private int idprotectora;

	private String descripcion;

	private String direccion;

	private String nombre;

	private String email;

	private String telefono;

    private String urlLogo;

    private int idEstadoProtectora;

    private String nombreEstadoProtectora;

    private int idMunicipio;

    private String nombreMunicipio;

    private int idProvincia;

    private String nombreProvincia;

    public ProtectoraDto() {
    }

    public ProtectoraDto(int idprotectora, String descripcion, String direccion, String nombre, String email,
            String telefono, String urlLogo, int idEstadoProtectora, String nombreEstadoProtectora, int idMunicipio,
            String nombreMunicipio, int idProvincia, String nombreProvincia) {
        this.idprotectora = idprotectora;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.urlLogo = urlLogo;
        this.idEstadoProtectora = idEstadoProtectora;
        this.nombreEstadoProtectora = nombreEstadoProtectora;
        this.idMunicipio = idMunicipio;
        this.nombreMunicipio = nombreMunicipio;
        this.idProvincia = idProvincia;
        this.nombreProvincia = nombreProvincia;
    }

    public int getIdprotectora() {
        return idprotectora;
    }

    public void setIdprotectora(int idprotectora) {
        this.idprotectora = idprotectora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public int getIdEstadoProtectora() {
        return idEstadoProtectora;
    }

    public void setIdEstadoProtectora(int idEstadoProtectora) {
        this.idEstadoProtectora = idEstadoProtectora;
    }

    public String getNombreEstadoProtectora() {
        return nombreEstadoProtectora;
    }

    public void setNombreEstadoProtectora(String nombreEstadoProtectora) {
        this.nombreEstadoProtectora = nombreEstadoProtectora;
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
        result = prime * result + idprotectora;
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
        ProtectoraDto other = (ProtectoraDto) obj;
        if (idprotectora != other.idprotectora)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ProtectoraDto [idprotectora=" + idprotectora + ", descripcion=" + descripcion + ", direccion="
                + direccion + ", nombre=" + nombre + ", email=" + email + ", telefono=" + telefono + ", urlLogo="
                + urlLogo + ", idEstadoProtectora=" + idEstadoProtectora + ", nombreEstadoProtectora="
                + nombreEstadoProtectora + ", idMunicipio=" + idMunicipio + ", nombreMunicipio=" + nombreMunicipio
                + ", idProvincia=" + idProvincia + ", nombreProvincia=" + nombreProvincia + "]";
    }

    
    
    
}
