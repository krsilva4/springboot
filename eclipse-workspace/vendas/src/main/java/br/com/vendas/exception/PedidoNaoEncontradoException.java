package br.com.vendas.exception;

public class PedidoNaoEncontradoException extends RuntimeException{
	
	public PedidoNaoEncontradoException () {
		super("Pedido nao Encontrado");
	}
}
