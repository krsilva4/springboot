package br.com.vendas;

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
	public CommandLineRunner commandLineRunner (@Autowired Clientes clientes) {
		return args -> {
			clientes.save(new Cliente(null, "Kenne"));
		};
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(VendasApplication.class, args);

	}

}
