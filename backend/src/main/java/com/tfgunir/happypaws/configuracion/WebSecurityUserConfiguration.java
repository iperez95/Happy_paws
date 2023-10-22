package com.tfgunir.happypaws.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class WebSecurityUserConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private DataSource dataSource;

    @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       
            auth
                .jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email, password, enabled from usuarios where email=?")
                .authoritiesByUsernameQuery("select u.email, r.nombre from usuarios u " + 
                                            "inner join roles r on r.IDROL = u.IDROL " +
                                              "where u.email = ?");
        }

    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()

			// Directorios y vistas NO REQUIEREN AUTORIZACIÓN
			.antMatchers("/bootstrap/**",  "/images/**", "/css/**", "js/**").permitAll()
			.antMatchers("/rest/demo-bcrypt/**").permitAll()
			.antMatchers("/", "/login", "/logout", "/registro","/search").permitAll()

			// REQUIEREN AUTORIZACIÓN SEGÚN ROLES
                                //ADMINISTRADOR
                .antMatchers("/gestion/**").hasAnyAuthority("Administrador")

				//PROTECTORAS
                .antMatchers("/protectora/gestion/**").hasAnyAuthority("Protectora","Administrador")

				//ADOPTANTES
                .antMatchers("/cuestionario/**").hasAnyAuthority("Adoptante","Administrador")
			
			// Todas las demás URLs de la Aplicación requieren autenticación
			.anyRequest().authenticated()
			// El formulario de Login no requiere autenticacion
			.and().formLogin().permitAll()
			// El formulario de logout no requiere autenticacion
	        //.and().logout().permitAll()
			;	
	}    

    @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
