package br.com.vendas.servico;

import java.util.Optional;

import br.com.vendas.controller.dto.PedidoDTO;
import br.com.vendas.domain.entity.Pedido;
import br.com.vendas.domain.entity.enums.StatusPedido;

public interface PedidoServico {

	Pedido salvar (PedidoDTO dto);
	Optional<Pedido> obterPedidoCompleto(Integer id);
	void atualizaStatus(Integer id, StatusPedido statusPedido);
}
