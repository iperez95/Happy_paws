package com.tfgunir.happypaws.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfgunir.happypaws.modelo.entities.ContactForm;
import com.tfgunir.happypaws.modelo.entities.Usuario;
import com.tfgunir.happypaws.modelo.repository.UsuarioRepository;

@Controller
public class HomeController {

	@Autowired
    private JavaMailSender emailSender;

    @GetMapping("/index")
    public String mostrarIndex(){
        return "index";
    }

    @GetMapping("/menu")
    public String mostrarMenu(){
        return "menu";
    }

@PostMapping("/contacto")
public ResponseEntity<String> manejoEnvioformulario(@RequestBody ContactForm form) {
    // Aquí podemos añadir la validacion del back del formulario.

    try {
        // Esto proceso y envía el formulario
        sendEmail(form);
        return new ResponseEntity<>("¡Tu email ha sido enviado correctamente!", HttpStatus.OK);
    } catch (MailException e) {
        // manejo de la excepción
        return new ResponseEntity<>("Error al enviar el mensaje", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

	//Método para enviar el formulario al email de HappyPaws
    private void sendEmail(ContactForm form) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("happypawsunir@gmail.com");
        message.setSubject("Nuevo mensaje de contacto de " + form.getName());
        message.setText("Correo electrónico: " + form.getEmail() + "\n\n" + form.getMessage());
        emailSender.send(message);
    }

	 //TODO DAV Aqui tiene que llamar al dao en vez de al repository comprobar si Irene ha creado el buscar usuario por email
    //  @Autowired
    // UsuarioDao usudao;

    //LOGIN Y REGISTRO
    //TODO DAV - Almacena en sesión el idusuario para poder obtenerlo durante la sesion.
    // @GetMapping("/login")
	// public String inicio() {
	// 	return "Logina";
	// }
		
	// @GetMapping("/logout")
	// public String logout(HttpSession sesion) {
	// 	sesion.removeAttribute("usuarioSesion");
	// 	sesion.invalidate();
	// 	return "Login";
	// }
	
	// @PostMapping("/login")
	// public String login(@RequestParam("email") int idUsuario, HttpSession sesion, Model model) {
	// 	Usuario usuario = usuado.buscarPorEmail(email);
	// 	if (usuario != null) {
	// 		sesion.setAttribute("usuarioSesion", usuario);
	// 		return "redirect:/index";
	// 	}
	// 	model.addAttribute("mensaje", "Usuario no existe");
		
	// 	return "Login";
	// }
    
}
