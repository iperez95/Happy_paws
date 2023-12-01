package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import com.tfgunir.happypaws.modelo.dto.CredentialsDto;
import com.tfgunir.happypaws.modelo.dto.UsuarioDto;
import com.tfgunir.happypaws.modelo.entities.Usuario;

public interface IUsuarioDao {
    UsuarioDto altaUsuario(Usuario usuario);
    int actualizar(Usuario usuario);
    int borrarUsuario(Usuario usuario);
    Usuario buscarUnUsuario(int id);
    UsuarioDto login(CredentialsDto credentialsDto);
    List<Usuario> buscarPorEmailContiene(String email);
    UsuarioDto convertirADto(Usuario usuario);
    List<Usuario> buscarTodos();
}
