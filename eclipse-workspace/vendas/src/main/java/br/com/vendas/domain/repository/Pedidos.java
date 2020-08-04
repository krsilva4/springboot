package br.com.vendas.domain.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vendas.domain.entity.Cliente;
import br.com.vendas.domain.entity.Pedido;

public interface Pedidos extends JpaRepository<Pedido,Integer> {

	Set<Pedido> findByCliente(Cliente cliente);
}
