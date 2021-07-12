package br.com.vendas.controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.vendas.controller.dto.AtualizaStatusPedidoDTO;
import br.com.vendas.controller.dto.InformacaoItemPedidoDTO;
import br.com.vendas.controller.dto.InformacoesPedidoDTO;
import br.com.vendas.controller.dto.PedidoDTO;
import br.com.vendas.domain.entity.ItemPedido;
import br.com.vendas.domain.entity.Pedido;
import br.com.vendas.domain.entity.enums.StatusPedido;
import br.com.vendas.servico.PedidoServico;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	
	private PedidoServico pedidoServico;

	public PedidoController(PedidoServico service) {
		this.pedidoServico = service;
	}
	
	@PostMapping
	@ResponseBody
	public Integer save(@RequestBody PedidoDTO dto) {
		return pedidoServico.salvar(dto).getId();
	}
	
	@GetMapping("{id}")
	@ResponseBody
	public  InformacoesPedidoDTO getById(@PathVariable Integer id) {
		return pedidoServico.obterPedidoCompleto(id)
				   .map( p -> {
					   return converte(p);
	                }).orElseThrow( () ->
	                new ResponseStatusException(HttpStatus.NOT_FOUND,
	                        "Pedido não encontrado."));
				
	}
	
	@PatchMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStatus(@PathVariable Integer id,
							 @RequestBody AtualizaStatusPedidoDTO dto) {
		pedidoServico.atualizaStatus(id, StatusPedido.valueOf(dto.getNovoStatus()));
	}
	
	private InformacoesPedidoDTO converte(Pedido pedido) {
		return InformacoesPedidoDTO
				.builder()
				.codigo(pedido.getId())
				.cpf(pedido.getCliente().getCpf())
				.nomeCliente(pedido.getCliente().getNome())
				.total(pedido.getTotal())
				.dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
				.items(converte(pedido.getItens()))
				.status(pedido.getStatus().name())
				.build();
	}
	
	private List<InformacaoItemPedidoDTO> converte(List<ItemPedido> itens) {
		if(CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		return itens.stream()
				.map(i -> 
					InformacaoItemPedidoDTO
					.builder()
					.descricaoProduto(i.getProduto().getDescricao())
					.precoUnitario(i.getProduto().getPreco())
					.quantidade(i.getQuantidade())
					.build() 
				).toList();
	}
	
}
