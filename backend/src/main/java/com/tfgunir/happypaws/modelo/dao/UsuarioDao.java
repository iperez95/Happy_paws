package com.tfgunir.happypaws.modelo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tfgunir.happypaws.modelo.entities.Usuario;
import com.tfgunir.happypaws.modelo.repository.UsuarioRepository;

@Repository
public class UsuarioDao implements IUsuarioDao {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean altaUsuario(Usuario usuario) {
        Usuario tmpUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        if (tmpUsuario != null) {
            return false;
        }

        try {
            usuarioRepository.save(usuario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int modificarUsuario(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarUsuario'");
    }

    @Override
    public int borrarUsuario(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borrarUsuario'");
    }

    @Override
    public Usuario buscarUnUsuario(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarUnUsuario'");
    }
    
}
