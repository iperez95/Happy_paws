package com.tfgunir.happypaws.modelo.dao;

import com.tfgunir.happypaws.modelo.dto.CredentialsDto;
import com.tfgunir.happypaws.modelo.dto.UsuarioDto;
import com.tfgunir.happypaws.modelo.entities.Usuario;

public interface IUsuarioDao {
    boolean altaUsuario(Usuario usuario);
    int modificarUsuario(Usuario usuario);
    int borrarUsuario(Usuario usuario);
    Usuario buscarUnUsuario(int id);
    UsuarioDto login(CredentialsDto credentialsDto);
}
