package com.tfgunir.happypaws.modelo.dao;

import java.nio.CharBuffer;
import java.util.List;

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
                .rol(usuario.getRol().getNombre())
                .enabled(usuario.getEnabled())
                .password(usuario.getPassword())
                .build();
        } catch (Exception e) {
            throw new AppException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public int borrarUsuario(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borrarUsuario'");
    }

    @Override
    public Usuario buscarUnUsuario(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public UsuarioDto login(CredentialsDto credentialsDto) {
        Usuario usuario = usuarioRepository.findByEmail(credentialsDto.email().toLowerCase().trim());
        if (usuario == null) {
            throw new AppException("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }

        if (usuario.getEnabled() == (byte)0) {
            throw new AppException("El usuario esta inactivo", HttpStatus.UNAUTHORIZED);
        }
        
        /**
         * Compara la contraseña introducida con la contraseña encriptada en la base de datos.
         * CharBuffer.wrap() es necesario para que el método matches() funcione correctamente, 
         * porque el método matches() de PasswordEncoder espera un CharSequence y no un String,
         * ya que el dto almacena la contraseña como un char[] y el usuario como un String.
         */
        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), usuario.getPassword())) {
            return UsuarioDto.builder()
                .id(usuario.getIdusuario())
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .email(usuario.getEmail())
                .direccion(usuario.getDireccion())
                .dni(usuario.getDni())
                .rol(usuario.getRol().getNombre())
                .enabled(usuario.getEnabled())
                .password(usuario.getPassword())
                .build();
        }
        return null;
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public UsuarioDto convertirADto(Usuario usuario) {
        return UsuarioDto.builder()
            .id(usuario.getIdusuario())
            .nombre(usuario.getNombre())
            .apellidos(usuario.getApellidos())
            .email(usuario.getEmail())
            .direccion(usuario.getDireccion())
            .dni(usuario.getDni())
            .rol(usuario.getRol().getNombre())
            .enabled(usuario.getEnabled())
            .password(usuario.getPassword())
            .build();
    }

    public List<Usuario> buscarPorEmailContiene(String email) {
        try{
            return usuarioRepository.findByEmailLike(email);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }    
    }

    public int actualizar(Usuario usuario) {
        try{
            usuarioRepository.save(usuario);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
}
