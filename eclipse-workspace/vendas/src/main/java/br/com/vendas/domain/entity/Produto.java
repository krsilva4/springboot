package br.com.vendas.domain.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "descricao")
	@NotEmpty(message = "{campo.descricao.obrigatorio}")
	private String descricao;
	
	@Column(name = "preco_unitario")
	@NotNull(message = "{campo.preco.obrigatorio}")
	private Integer preco;

	@OneToOne
	@JoinColumn(name = "tipo_id")
	private Tipo tipo;

}
