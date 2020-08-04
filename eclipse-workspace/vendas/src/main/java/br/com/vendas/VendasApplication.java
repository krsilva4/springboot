package br.com.vendas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.vendas.domain.entity.Cliente;
import br.com.vendas.domain.entity.Pedido;
import br.com.vendas.domain.repository.Clientes;
import br.com.vendas.domain.repository.Pedidos;

@SpringBootApplication
public class VendasApplication {
	
	@Bean
	public CommandLineRunner  init(@Autowired Clientes clientes, 
								   @Autowired Pedidos pedidos) {
	
		return args -> {
		Cliente cliente = clientes.save(new Cliente(null,"Kenne"));
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setDataPedido(LocalDate.now());
		pedido.setTotal(BigDecimal.valueOf(100.00));
		pedidos.save(pedido);
		
		System.out.println(clientes.findClienteFetchPedidos(cliente.getId()).getPedidos().toString());
		Set<Pedido> pedidos2 = pedidos.findByCliente(cliente);
		
		for(Pedido p : pedidos2) {
			System.out.println(p.getCliente().getNome());
		}
		};
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(VendasApplication.class, args);

	}

}
