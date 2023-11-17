package com.tfgunir.happypaws.modelo.dao;

import java.nio.CharBuffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tfgunir.happypaws.exceptions.AppException;
import com.tfgunir.happypaws.modelo.dto.CredentialsDto;
import com.tfgunir.happypaws.modelo.dto.UsuarioDto;
import com.tfgunir.happypaws.modelo.entities.Usuario;
import com.tfgunir.happypaws.modelo.repository.UsuarioRepository;

@Repository
public class UsuarioDao implements IUsuarioDao {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UsuarioDto altaUsuario(Usuario usuario) {
        Usuario tmpUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        if (tmpUsuario != null) {
            throw new AppException("Un usuario con este email ya existe", HttpStatus.BAD_REQUEST);
        }

        try {
            usuarioRepository.save(usuario);
            return UsuarioDto.builder()
                .id(usuario.getIdusuario())
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .email(usuario.getEmail())
                .direccion(usuario.getDireccion())
                .dni(usuario.getDni())
                .rol(usuario.getRol().toString())
                .enabled(usuario.getEnabled())
                .password(usuario.getPassword())
                .build();
        } catch (Exception e) {
            throw new AppException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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

    @Override
    public UsuarioDto login(CredentialsDto credentialsDto) {
        Usuario usuario = usuarioRepository.findByEmail(credentialsDto.email().toLowerCase().trim());
        if (usuario == null) {
            throw new AppException("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), usuario.getPassword())) {
            return UsuarioDto.builder()
                .id(usuario.getIdusuario())
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .email(usuario.getEmail())
                .direccion(usuario.getDireccion())
                .dni(usuario.getDni())
                .rol(usuario.getRol().toString())
                .enabled(usuario.getEnabled())
                .password(usuario.getPassword())
                .build();
        }
        return null;
    }
    
}
