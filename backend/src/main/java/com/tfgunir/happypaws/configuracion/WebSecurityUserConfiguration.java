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
     * This method registers the authentication provider that will be used to validate users.
     * Possible @DELETE because we are using JWT.
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
     * This method registers a filter that intercepts incoming requests and validates the JWT token in the Authorization header.
     * @param http
     * @return
     * @throws Exception
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
            .and().httpBasic()
            .and().csrf().disable();	
	}    

    @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    /**
     * This method registers a CORS filter that allows all requests from localhost:4200.
     * 
     * Check if this method is necessary or the one below.
     * @return
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
     * This method registers a CORS filter that allows all requests from localhost:4200.
     * So in the browser we can access the API from Angular.
     * 
     * Check if this method is necessary or the one above.
     * @return
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
