package com.tfgunir.happypaws.modelo.entities;

public class ContactoFormulario {

    private String nombre;
    private String email;
    private String mensaje;

    // Constructores, getters y setters

    public ContactoFormulario() {
    }

    public ContactoFormulario(String nombre, String email, String mensaje) {
        this.nombre = nombre;
        this.email = email;
        this.mensaje = mensaje;
    }

    // Getters y Setters

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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "CorreoFormulario [nombre=" + nombre + ", email=" + email + ", mensaje=" + mensaje + "]";
    }



}
