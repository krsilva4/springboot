package br.com.vendas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.vendas.domain.entity.Cliente;

@Controller
@RequestMapping("api/clientes")
public class ClienteController {

	@RequestMapping(value = "/hello/{nome}", 
					method = RequestMethod.POST ,
					//recebe um json ou xml
					consumes = {"application/json", "application/xml"},
					//envia um json ou xml
					produces = {"application/json", "application/xml"})
	@ResponseBody
	public Cliente helloCliente(@PathVariable("nome") 
			String nomeCliente, @RequestBody Cliente cliente) {
		return new Cliente();
	}
}
