package com.tfgunir.happypaws.modelo.dao;

import com.tfgunir.happypaws.modelo.entities.Usuario;

public interface IUsuarioDao {
    boolean altaUsuario(Usuario usuario);
    int modificarUsuario(Usuario usuario);
    int borrarUsuario(Usuario usuario);
    Usuario buscarUnUsuario(int id);
}
