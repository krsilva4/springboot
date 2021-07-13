package br.com.vendas.domain.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "nome", length = 100)
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	private String nome;

	@Column(name = "cpf", length = 11)
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	@CPF(message = "{campo.cpf.invalido}")
	private String cpf;
	
	// tirar propriedade do json
	@JsonIgnore
	//fetch = FetchType.EAGER carrega o tributo. Lazi nao carrega o atributo
	@OneToMany(mappedBy = "cliente" , fetch = FetchType.LAZY)
	private Set<Pedido> pedidos;

	public Cliente(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
}
