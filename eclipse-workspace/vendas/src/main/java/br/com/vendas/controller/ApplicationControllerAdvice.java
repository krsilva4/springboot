package br.com.vendas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.vendas.exception.PedidoNaoEncontradoException;
import br.com.vendas.exception.RegraNegocioException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(RegraNegocioException.class) 
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleRegraNegocioException(RegraNegocioException ex) {
		return new ApiErrors(ex.getMessage());
	}
	
	@ExceptionHandler(PedidoNaoEncontradoException.class) 
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors PedidoNaoEncontradoException(PedidoNaoEncontradoException ex) {
		return new ApiErrors(ex.getMessage());
	}
	
	
}
