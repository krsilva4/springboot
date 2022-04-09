package br.com.vendas.controller;

import br.com.vendas.domain.entity.Tipo;
import br.com.vendas.servico.impl.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/tipo")
public class TIpoController {

	@Autowired
	TipoService service;

	@PostMapping
	@ResponseBody
	public ResponseEntity save(@RequestBody Tipo dto) {
		return ResponseEntity.ok(service.registrarTipo(dto));
	}
	
	@GetMapping("{id}")
	@ResponseBody
	public  String getById(@PathVariable Integer id) {
		return "peppa";
				
	}
	
}
