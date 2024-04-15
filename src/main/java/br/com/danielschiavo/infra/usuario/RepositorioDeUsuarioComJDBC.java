package br.com.danielschiavo.infra.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.danielschiavo.dominio.usuario.CPF;
import br.com.danielschiavo.dominio.usuario.RepositorioDeUsuario;
import br.com.danielschiavo.dominio.usuario.Usuario;

public class RepositorioDeUsuarioComJDBC implements RepositorioDeUsuario {

	private final Connection connection;
	
	public RepositorioDeUsuarioComJDBC(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void cadastrarUsuario(Usuario usuario) {
		try {
			String sql = "INSERT INTO usuarios VALUES(?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, usuario.getCpf());
			ps.setString(2, usuario.getNome());
			ps.setString(3, usuario.getEmail());
			ps.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Usuario buscarPorCPF(CPF cpf) {
		return null;
	}

}
