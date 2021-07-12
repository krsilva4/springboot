package br.com.vendas.servico.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vendas.controller.dto.ItemPedidoDTO;
import br.com.vendas.controller.dto.PedidoDTO;
import br.com.vendas.domain.entity.ItemPedido;
import br.com.vendas.domain.entity.Pedido;
import br.com.vendas.domain.entity.Produto;
import br.com.vendas.domain.entity.enums.StatusPedido;
import br.com.vendas.domain.repository.Clientes;
import br.com.vendas.domain.repository.ItemsPedido;
import br.com.vendas.domain.repository.Pedidos;
import br.com.vendas.domain.repository.Produtos;
import br.com.vendas.exception.PedidoNaoEncontradoException;
import br.com.vendas.exception.RegraNegocioException;
import br.com.vendas.servico.PedidoServico;
import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class PedidoServicoImpl implements PedidoServico {

	private final Pedidos repositorio;
	private final Clientes clientesRepositorio;
	private final Produtos produtoRepositorio;
	private final ItemsPedido itemsPedidoRepositorio;

	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		// TODO Auto-generated method stub
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(clientesRepositorio.findById(dto.getCliente())
				.orElseThrow(() -> new RegraNegocioException("CODIGO DO CLIENTE INVALIDO " + dto.getCliente())));
		List<ItemPedido> itemPedidos = converteItems(pedido, dto.getItems());
		pedido.setStatus(StatusPedido.REALIZADO);
		pedido.setItens(itemPedidos);
		repositorio.save(pedido);
		itemsPedidoRepositorio.saveAll(itemPedidos);
		return pedido;
		
	}

	private List<ItemPedido> converteItems(Pedido pedido, List<ItemPedidoDTO> items) {
		if (items.isEmpty()) {
			throw new RegraNegocioException("Não e possivel realizar um pedido sem itens ");
		}
		return items.stream().map(dto -> {
			Produto produto = produtoRepositorio.findById(dto.getProduto())
					.orElseThrow(() -> new RegraNegocioException("CODIGO DO PRODUTO INVALIDO " + dto.getProduto()));
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);
			itemPedido.setQuantidade(dto.getQuantidade());
			return itemPedido;
		}).toList();

	}

	@Override
	public Optional<Pedido> obterPedidoCompleto(Integer id) {
		return repositorio.findByIdfetchItens(id);
	}

	@Override
	@Transactional
	public void atualizaStatus(Integer id, StatusPedido statusPedido) {
		repositorio.findById(id).map(pedido -> { 
			pedido.setStatus(statusPedido);
			return repositorio.save(pedido); 
		})
		.orElseThrow(
				() -> new PedidoNaoEncontradoException());
				
	}
}
