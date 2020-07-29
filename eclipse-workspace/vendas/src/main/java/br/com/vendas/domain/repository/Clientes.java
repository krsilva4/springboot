package br.com.vendas.domain.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.vendas.domain.entity.Cliente;

@Repository
public class Clientes {

	private static String INSERT  = "insert into cliente (nome) values (?)";
	
	private static String SELECTALL  = "select * from cliente";
	
	private static String UPDATE  = "update cliente set nome = ? where id = ?";
	
	private static String DELETE = "delete from cliente where id = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Cliente salvar(Cliente cliente) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(INSERT, new Object[] {cliente.getNome(), });
		return new Cliente();
	}
	
	public Cliente atualizar(Cliente cliente) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(UPDATE, new Object[] {cliente.getNome(), cliente.getId()});
		return new Cliente();
	}
	
	public Cliente deletar(Cliente cliente) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(DELETE, new Object[] {cliente.getId()});
		return new Cliente();
	}
	
	public List<Cliente> obterTodos() {
		return jdbcTemplate.query(SELECTALL, obterClienteMapper());
	}
	
	public List<Cliente> buscarPorNome(String nome) {
		return jdbcTemplate.query(SELECTALL.concat(" where nome like ? "), new Object[] {"%"+nome+"%"},obterClienteMapper());
	}
	
	private RowMapper<Cliente> obterClienteMapper(){
		return new RowMapper<Cliente>() {
			@Override
			public Cliente mapRow(ResultSet resultSet, int i) throws SQLException{
				return new Cliente(resultSet.getInt("id"),resultSet.getString("nome"));
				
			}
		};
	}
}
