package com.tfgunir.happypaws.configuracion;

import org.springframework.web.filter.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class WebSecurityUserConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private DataSource dataSource;

    private UsuarioAuthProvider usuarioAuthProvider;

    /**
     * Este método registra el proveedor de autenticación que se utilizará para validar a los usuarios.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery("select email, password, enabled from usuarios where email=?")
            .authoritiesByUsernameQuery("select u.email, r.nombre from usuarios u " + 
                                        "inner join roles r on r.IDROL = u.IDROL " +
                                        "where u.email = ?");
    }

    /**
     * Este método registra un filtro que intercepta las solicitudes entrantes y valida el token JWT en el encabezado de Autorización.
     */
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .addFilterBefore(new JwtAuthFilter(usuarioAuthProvider), BasicAuthenticationFilter.class)
            .sessionManagement(customizer -> 
                customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeRequests((requests) -> 
                requests.antMatchers(HttpMethod.OPTIONS).permitAll()
                        .anyRequest().authenticated()
            );

            return http.build();
    }

    @Override
	protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().authorizeRequests()
            .antMatchers("/api/**").permitAll()
            // .antMatchers("/protectora/gestion/**").hasAnyAuthority("Administrador", "Protectora")
            .and().httpBasic()
            .and().csrf().disable();	
	}    

    @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    /**
     * Este método registra un filtro CORS que permite todas las solicitudes desde localhost:4200.
     * 
     * Verifique si este método es necesario o el de abajo.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Este método registra un filtro CORS que permite todas las solicitudes desde localhost:4200.
     * 
     * Verifique si este método es necesario o el de abajo.
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
