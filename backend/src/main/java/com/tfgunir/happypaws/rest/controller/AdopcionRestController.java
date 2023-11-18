package com.tfgunir.happypaws.rest.controller;

import com.tfgunir.happypaws.configuracion.UsuarioAuthProvider;
import com.tfgunir.happypaws.modelo.dao.AdopcionDao;
import com.tfgunir.happypaws.modelo.dto.AdopcionDto;
import com.tfgunir.happypaws.modelo.dto.UsuarioDto;
import com.tfgunir.happypaws.modelo.entities.Adopcion;
import com.tfgunir.happypaws.modelo.entities.Estadosadopcion;
import com.tfgunir.happypaws.modelo.entities.Usuario;
import com.tfgunir.happypaws.modelo.repository.AdopcionRepository;
import com.tfgunir.happypaws.modelo.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/adopcion")
public class AdopcionRestController {

    @Autowired
    AdopcionDao adopdao;

    @Autowired
    AdopcionRepository adoprepo;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final UsuarioAuthProvider usuarioAuthProvider;

 
    public AdopcionRestController(UsuarioAuthProvider usuarioAuthProvider) {
        this.usuarioAuthProvider = usuarioAuthProvider;
    }

    /**
     * Recupera una lista de todas las adopciones.
     * @return La entidad de respuesta que contiene la lista de DTO de adopción.
     */
    @GetMapping("/todas")
    public ResponseEntity<Iterable<AdopcionDto>> todasAdopciones() {
        Iterable<Adopcion> adopciones = adopdao.buscarTodasAdopciones();
        
        if (adopciones != null){
            List<AdopcionDto> adopcionesDto = new ArrayList<>();
            for (Adopcion adopcion : adopciones) {
                adopcionesDto.add(adopdao.convertirAdopcionDto(adopcion));
            }
            return new ResponseEntity<>(adopcionesDto, HttpStatus.OK);     
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  
        }
    }

    /**
     * Recupera todas las adopciones para un refugio dado por el ID del refugio.
     * @param idProtectora El ID del refugio.
     * @return La entidad de respuesta que contiene la lista de DTO de adopción.
     */
    @GetMapping(path="/protectora/{id}", produces = "application/json")
    public ResponseEntity<Iterable<AdopcionDto>> todasAdopcionesProtectoraId(@PathVariable("id") int idProtectora) {
        Iterable<Adopcion> adopciones = adopdao.adopcionesPorIDProtectora(idProtectora);
     
        if (adopciones != null){
            List <AdopcionDto> adopcionesDto = new ArrayList<>();
            for (Adopcion adopcion : adopciones) {
                adopcionesDto.add(adopdao.convertirAdopcionDto(adopcion));
            }
            return new ResponseEntity<>(adopcionesDto, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Recupera una adopción por su ID de adopción.
     * @param idAdopcion El ID de la adopción.
     * @return La entidad de respuesta que contiene el DTO de adopción.
     */
    @GetMapping(path="/{id}", produces = "application/json")
    public ResponseEntity<AdopcionDto> adopcionPorID(@PathVariable("id") int idAdopcion) {
        Adopcion adopcion = adopdao.buscarAdopcionId(idAdopcion);
        if (adopcion != null){
            AdopcionDto adopcionDto = adopdao.convertirAdopcionDto(adopcion);
            return ResponseEntity.ok(adopcionDto);
        } else {
            return ResponseEntity.notFound().build();
        }

    }


    @PostMapping(path = "/alta", consumes = "application/json")
    public ResponseEntity<AdopcionDto> altaAdopcion(@RequestBody Adopcion adopcion, 
                                    @RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.substring(7);

            // AÑADE EL USUARIO EN SESIÓN A LA ADOPCIÓN
                // Valida el token y obtén el objeto Authentication
                Authentication auth = usuarioAuthProvider.validateToken(token);

                // Obtén el correo electrónico del usuario
                UsuarioDto usuarioDto = (UsuarioDto) auth.getPrincipal();
                System.out.println("Usuario en token: " + usuarioDto);
                String email = usuarioDto.getEmail();
                System.out.println("email en token: " + email);

                // Encuentra el usuario en la base de datos
                Usuario usuario = usuarioRepository.findByEmail(email);
                System.out.println("Usuario en BD: " + usuario);

                adopcion.setUsuario(usuario);
            // FIN AÑADIR USUARIO EN SESIÓN A LA ADOPCIÓN

            // AÑADE EL ESTADO DE ADOPCIÓN "EN CURSO" A LA ADOPCIÓN
                // Encuentra el estado de adopción "En curso" en la base de datos
                Estadosadopcion estadoAdopcionTemp = new Estadosadopcion();
                estadoAdopcionTemp.setEnCurso();
                adopcion.setEstadosadopcion(estadoAdopcionTemp);
            // FIN AÑADIR ESTADO DE ADOPCIÓN "EN CURSO" A LA ADOPCIÓN

            // AÑADE LA FECHA DE ADOPCIÓN A LA ADOPCIÓN
                Date fechaActual = new Date();
                adopcion.setFechaAdopcion(fechaActual);
            // FIN AÑADIR FECHA DE ADOPCIÓN A LA ADOPCIÓN

            adopdao.altaAdopcion(adopcion);
            
            return ResponseEntity.ok().build();
            } 
            catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
    }   
}


    

