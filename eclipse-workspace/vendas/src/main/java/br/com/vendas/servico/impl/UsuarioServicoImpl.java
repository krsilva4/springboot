package br.com.vendas.servico.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vendas.domain.entity.Usuario;
import br.com.vendas.domain.repository.Usuarios;

@Service
public class UsuarioServicoImpl implements UserDetailsService{

	
	@Autowired
	private  PasswordEncoder passwordEncoder;
	
	@Autowired
	private Usuarios usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByLogin(username)
				.orElseThrow(() -> 
				new UsernameNotFoundException("Usuario não encontrado"));
		
		String[] roles = usuario.isAdmin() ? new String[] {"ADMIN","USER"} : new String[] {"USER"};
		
		return User
				.builder()
				.username(usuario.getLogin())
				.password(usuario.getSenha())
				.roles(roles)
				.build();
		
	}
	
	@Transactional
	public Usuario salva(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

}
