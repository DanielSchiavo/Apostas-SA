package br.com.danielschiavo.infra.aposta;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.danielschiavo.dominio.aposta.Aposta;
import br.com.danielschiavo.dominio.aposta.Aposta.ApostaBuilder;
import br.com.danielschiavo.dominio.aposta.RepositorioDeAposta;
import br.com.danielschiavo.dominio.aposta.StatusAposta;
import br.com.danielschiavo.dominio.aposta.TipoAposta;
import br.com.danielschiavo.dominio.equipe.Equipe;
import br.com.danielschiavo.dominio.evento.Evento;
import br.com.danielschiavo.infra.evento.RepositorioDeEventoComJdbcPostgres;

public class RepositorioDeApostaComJdbcPostgres implements RepositorioDeAposta {

	private final Connection connection;
	
	public RepositorioDeApostaComJdbcPostgres(Connection connection, RepositorioDeEventoComJdbcPostgres repositorioDeEventos) {
		this.connection = connection;
	}
	
	@Override
	public List<Aposta> buscarTodasApostasPorCPF(String cpf) {
		try {
			String sql = "SELECT * FROM apostas WHERE usuario_cpf = ? ORDER BY data_e_hora ASC";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();
			List<Aposta> apostas = new ArrayList<>();
			while (rs.next()) {
				String usuarioCpf = rs.getString("usuario_id");
				String eventoId = rs.getString("evento_id");
				String equipeEventoApostadaId = rs.getString("equipe_evento_apostada_id");
				String tipoAposta = rs.getString("tipo_aposta");
				BigDecimal valorApostado = rs.getBigDecimal("valor_apostado");
				double chancesNoMomentoDaAposta = rs.getDouble("chances_no_momento_da_aposta");
				double chancesNoEncerramentoDaAposta = rs.getDouble("chances_no_encerramento_da_aposta");
				String status = rs.getString("status");
				LocalDateTime dataEHora = rs.getTimestamp("data_e_hora").toLocalDateTime();
				
				ApostaBuilder apostaBuilder = Aposta.builder();
				Aposta aposta = apostaBuilder.usuarioId(usuarioCpf)
											 .evento(new Evento(eventoId))
											 .equipeEventoApostadaId(equipeEventoApostadaId)
											 .tipoAposta(TipoAposta.valueOf(tipoAposta))
											 .valorApostado(valorApostado)
											 .chancesNoMomentoDaAposta(chancesNoMomentoDaAposta)
											 .chancesNoEncerramentoDaAposta(chancesNoEncerramentoDaAposta)
											 .statusAposta(StatusAposta.valueOf(status))
											 .dataEHora(dataEHora).build();
				apostas.add(aposta);
			}
			
			ps.close();
			rs.close();
			connection.close();
			return apostas;
		} catch (SQLException e) {
			throw new RuntimeException("Não foi possivel recuperar as apostas");
		}
		
	}

	@Override
	public void fazerAposta(Evento evento, Equipe equipe, TipoAposta tipoAposta, BigDecimal valor) {
		// TODO Auto-generated method stub
		
	}

}
