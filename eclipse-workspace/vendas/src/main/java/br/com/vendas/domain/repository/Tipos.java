package br.com.vendas.domain.repository;

import br.com.vendas.domain.entity.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Tipos extends JpaRepository<Tipo,Long> {

}
