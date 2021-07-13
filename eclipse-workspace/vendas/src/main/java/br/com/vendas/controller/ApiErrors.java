package br.com.vendas.controller;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErrors {
	
	@Getter
	private List<String> errors;

	public ApiErrors(String mensagemErro) {
		this.errors = Arrays.asList(mensagemErro);
	}
	
	public ApiErrors(List<String> mensagemErro) {
		this.errors = mensagemErro;
	}
	
}
