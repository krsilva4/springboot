package br.com.vendas.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder.Coalesce;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class) 
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors PedidoNaoEncontradoException(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult()
							   .getAllErrors()
							   .stream().map(erros -> erros.getDefaultMessage())
							   .collect(Collectors.toList());
		return new ApiErrors(errors);
	}
	
}
