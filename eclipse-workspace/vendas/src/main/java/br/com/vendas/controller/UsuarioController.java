package br.com.vendas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.vendas.domain.entity.Usuario;
import br.com.vendas.servico.impl.UsuarioServicoImpl;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioServicoImpl servicoImpl;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/api/usuarios")
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario save(@RequestBody @Valid Usuario usuario) {
		String senhaCriptografia = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografia);
		return servicoImpl.salva(usuario);
	}

}
