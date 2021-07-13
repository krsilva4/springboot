package br.com.vendas.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.vendas.domain.entity.Cliente;
import br.com.vendas.domain.repository.Clientes;

@Controller
public class ClienteController {

	@Autowired
	private Clientes clientes;

	@RequestMapping("/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity getClienteById(@PathVariable Integer id) {
		Optional<Cliente> cliente = clientes.findById(id);
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("/api/clientes")
	@ResponseBody
	public ResponseEntity save(@RequestBody @Valid Cliente cliente) {
		Cliente clienteSalvo = clientes.save(cliente);
		return ResponseEntity.ok(clienteSalvo);

	}

	@DeleteMapping("/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity delete(@PathVariable Integer id) {
		Optional<Cliente> cliente = clientes.findById(id);
		if (cliente.isPresent()) {
			clientes.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid Cliente cliente) {

		return clientes.findById(id).map(clienteExistem -> {
			cliente.setId(clienteExistem.getId());
			clientes.save(cliente);
			return ResponseEntity.noContent().build();
		}).orElseGet(() -> ResponseEntity.notFound().build());

	}

	@GetMapping("/api/clientes")
	public ResponseEntity find(Cliente filtro) {
		// Configuracao de filtro como encontrar e usar os valores passados.
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example example = Example.of(filtro, matcher);
		List<Cliente> lista = clientes.findAll(example);
		return ResponseEntity.ok(lista);
		
	}

}
