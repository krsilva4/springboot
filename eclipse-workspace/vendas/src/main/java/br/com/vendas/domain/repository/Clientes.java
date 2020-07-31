package br.com.vendas.domain.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.vendas.domain.entity.Cliente;

@Repository
public class Clientes {

	private static String INSERT  = "insert into cliente (nome) values (?)";
	
	private static String SELECTALL  = "select * from cliente";
		
	private static String DELETE = "delete from cliente where id = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private EntityManager entityManager;
	
	
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		// TODO Auto-generated method stub
		entityManager.persist(cliente);
		return new Cliente();
	}
	
	@Transactional
	public Cliente atualizar(Cliente cliente) {
		// TODO Auto-generated method stub
		entityManager.merge(cliente);
		return new Cliente();
	}
	@Transactional
	public void deletar(Cliente cliente) {
		// TODO Auto-generated method stub
		if(!entityManager.contains(cliente)) {
			cliente = entityManager.merge(cliente);
		}
		entityManager.remove(cliente);
	}
	
	@Transactional(readOnly = true)
	public List<Cliente> obterTodos() {
		return entityManager.createQuery("from Cliente",Cliente.class).getResultList();
	}
	
	@Transactional(readOnly = true)
	public List<Cliente> buscarPorNome(String nome) {
		String jpql = "select c from Cliente c where c.nome like :nome ";
		TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
		query.setParameter("nome","%"+nome +"%");
		return query.getResultList();
	}
	
}
