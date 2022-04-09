package br.com.vendas.servico.impl;

import br.com.vendas.controller.dto.ProdutoDTO;
import br.com.vendas.domain.entity.Produto;
import br.com.vendas.domain.entity.Tipo;
import br.com.vendas.domain.repository.Produtos;
import br.com.vendas.domain.repository.Tipos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService{

    @Autowired
    private Produtos repositorioProduto;

    @Autowired
    private Tipos repositorioTipo;

    public Produto registrarProduto(ProdutoDTO novoProduto){
        Produto produto = new Produto();
        produto.setPreco(novoProduto.getPreco());
        produto.setDescricao(novoProduto.getDescricao());
        produto.setTipo(repositorioTipo.findById(novoProduto.getTipo().longValue()).get());
        return repositorioProduto.save(produto);
    }
}
