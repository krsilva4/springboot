package br.com.vendas.servico.impl;


import br.com.vendas.domain.entity.Tipo;
import br.com.vendas.domain.repository.Tipos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoService {

    @Autowired
    public Tipos tipoRepositorio;

    public Tipo registrarTipo(Tipo novoTipo){
        return tipoRepositorio.save(novoTipo);
    }
}
