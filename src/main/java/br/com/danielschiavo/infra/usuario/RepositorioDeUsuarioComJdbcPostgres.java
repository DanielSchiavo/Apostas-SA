package br.com.danielschiavo.infra.usuario;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.danielschiavo.dominio.usuario.CPF;
import br.com.danielschiavo.dominio.usuario.Email;
import br.com.danielschiavo.dominio.usuario.RepositorioDeUsuario;
import br.com.danielschiavo.dominio.usuario.Telefone;
import br.com.danielschiavo.dominio.usuario.Usuario;
import br.com.danielschiavo.dominio.usuario.UsuarioNaoEncontradoException;

public class RepositorioDeUsuarioComJdbcPostgres implements RepositorioDeUsuario {

	private final Connection connection;
	
	public RepositorioDeUsuarioComJdbcPostgres(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void cadastrarUsuario(Usuario usuario) {
		try {
			String sql = "INSERT INTO usuarios VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, usuario.getId().toString());
			ps.setString(2, usuario.getCpf());
			ps.setString(3, usuario.getNome());
			ps.setString(4, usuario.getSobrenome());
			ps.setString(5, usuario.getEmail());
			ps.setString(6, usuario.getTelefone().getDdd());
			ps.setString(7, usuario.getTelefone().getNumero());
			ps.setString(8, usuario.getSenha());
			ps.setString(9, usuario.getFoto());
			ps.setDate(10, Date.valueOf(usuario.getDataNascimento()));
			ps.setTimestamp(11, Timestamp.valueOf(usuario.getDataCriacaoConta()));
			ps.setDouble(12, usuario.getSaldo().doubleValue());
			ps.execute();
			
			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Usuario buscarPorCPF(CPF cpf) {
		try {
			String sql = "SELECT cpf, nome, sobrenome, email, telefone, foto, data_nascimento, data_criacao_conta, saldo FROM usuarios WHERE cpf = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, cpf.getNumero());

			ResultSet rs = ps.executeQuery();
			boolean encontrou = rs.next();
			if (!encontrou) {
				throw new UsuarioNaoEncontradoException(cpf);
			}

			String nome = rs.getString("nome");
			String sobrenome = rs.getString("sobrenome");
			Email email = new Email(rs.getString("email"));
			Telefone telefone = new Telefone(rs.getString("ddd"), rs.getString("telefone"));
			String foto = rs.getString("foto");
			LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
			LocalDateTime dataCriacaoConta = rs.getTimestamp("data_criacao_conta").toLocalDateTime();
			BigDecimal saldo = BigDecimal.valueOf(rs.getDouble("saldo"));
			Usuario encontrado = new Usuario(cpf, nome, sobrenome, email, telefone, foto, dataNascimento, dataCriacaoConta, saldo);
			
			ps.close();
			rs.close();
			connection.close();
			
			return encontrado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
