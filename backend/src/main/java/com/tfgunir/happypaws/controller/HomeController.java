package com.tfgunir.happypaws.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String handleFormSubmission(@RequestBody ContactForm form) {
        // Validar el formulario (puedes agregar lógica de validación aquí)

        // Procesar el formulario y enviar el correo electrónico
        sendEmail(form);

        return "¡Formulario enviado con éxito!";
    }

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
