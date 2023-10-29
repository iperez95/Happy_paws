package com.tfgunir.happypaws.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {
    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private String direccion;
    private String dni;
    private String rol;
    private byte enabled;
    private String password;
    private String token;
}
