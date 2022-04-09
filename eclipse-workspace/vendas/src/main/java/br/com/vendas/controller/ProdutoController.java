package br.com.vendas.controller;

import br.com.vendas.controller.dto.ProdutoDTO;
import br.com.vendas.servico.impl.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@PostMapping
	@ResponseBody
	public ResponseEntity save(@RequestBody ProdutoDTO dto) {
		return ResponseEntity.ok(service.registrarProduto(dto));
	}
	
	@GetMapping("{id}")
	@ResponseBody
	public  String getById(@PathVariable Integer id) {
		return "peppa";
				
	}
	
}
