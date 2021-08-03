package br.com.vendas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.vendas.servico.impl.UsuarioServicoImpl;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UsuarioServicoImpl usuarioServico;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
	/*	exemplo de usurrio
	 * auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
		.withUser("kenne")
		.password(passwordEncoder().encode("123"))
		.roles("USER");*/
		
		auth
		 	.userDetailsService(usuarioServico)
			.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
			.antMatchers("/api/clientes/**")
				.hasAnyRole("USER")
			.antMatchers("/api/produtos/**")
				.hasAuthority("ADMIN")
			.antMatchers("/api/produtos/**")
				.hasAuthority("USER")
			.antMatchers(HttpMethod.POST, "/api/produtos/**")
				.permitAll()
			.anyRequest()
				.authenticated()
		.and()
			.httpBasic();
	}

	
}
