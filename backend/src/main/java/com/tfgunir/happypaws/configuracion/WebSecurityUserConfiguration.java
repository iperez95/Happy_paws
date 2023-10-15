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
			// Directorios de aplicación que no necesitan autenticación
			.antMatchers("/bootstrap/**",  "/images/**", "/css/**", "js/**").permitAll()
			.antMatchers("/rest/demo-bcrypt/**").permitAll()
			
			// Vistas que no requieren autorización
			.antMatchers("/", "/login", "/logout", "/registro","/search", "/app/producto/verUno/**").permitAll()

			// Autorizaciones según roles
                //PROTECTORAS
                .antMatchers("/protectora/todas").hasAnyAuthority("Administrador")
                //ADMINISTRADOR
                .antMatchers("/gestion/**").hasAnyAuthority("Administrador")

			.antMatchers("/app/usuarios/**").hasAnyAuthority("ROLE_GESTOR","ROLE_ADMINISTRADOR")
			.antMatchers("/app/perfiles/**").hasAnyAuthority("ROLE_ADMINISTRADOR")
			.antMatchers("/app/tipos/**").hasAnyAuthority("ROLE_GESTOR")
			
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
