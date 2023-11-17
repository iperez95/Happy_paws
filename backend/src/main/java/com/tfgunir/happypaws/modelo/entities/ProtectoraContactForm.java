package com.tfgunir.happypaws.modelo.entities;

public class ProtectoraContactForm {
    private String name;
    private String email;
    private String message;
    private String protectoraEmail;

    public ProtectoraContactForm(String name, String email, String message, String protectoraEmail) {
        this.name = name;
        this.email = email;
        this.message = message;
        this.protectoraEmail = protectoraEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProtectoraEmail() {
        return protectoraEmail;
    }

    public void setProtectoraEmail(String protectoraEmail) {
        this.protectoraEmail = protectoraEmail;
    }
    
}
