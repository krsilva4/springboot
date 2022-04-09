package br.com.vendas.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {
    private Integer id;
    private String descricao;
    private Integer preco;
    private Long tipo;
}
