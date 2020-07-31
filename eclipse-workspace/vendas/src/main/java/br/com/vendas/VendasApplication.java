package br.com.vendas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.vendas.domain.entity.Cliente;
import br.com.vendas.domain.repository.Clientes;

@SpringBootApplication
public class VendasApplication {
	
	@Bean
	public CommandLineRunner  init(@Autowired Clientes clientes) {
	
		return args -> {
			clientes.salvar(new Cliente(null,"Kenne"));
			clientes.salvar(new Cliente(null,"Peppa"));
			
			List<Cliente> todosClientes = clientes.obterTodos();
			
			for(Cliente c : todosClientes) {
				System.out.println(c.getNome());
			}
			
			for(Cliente c : todosClientes) {
				c.setNome(c.getNome() + "Atualizado!");
				clientes.atualizar(c);
			}
			
			todosClientes = clientes.obterTodos();
			
			for(Cliente c : todosClientes) {
				System.out.println(c.getNome());
			}
			
			todosClientes = clientes.buscarPorNome("Kenne");
			
			for(Cliente c : todosClientes) {
				System.out.println(c.getNome());
			}
			
			todosClientes = clientes.obterTodos();
			todosClientes.forEach(c -> {
				clientes.deletar(c);
			});
			
			todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);
			
		};
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(VendasApplication.class, args);

	}

}
