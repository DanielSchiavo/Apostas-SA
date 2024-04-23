package br.com.danielschiavo.dominio.aposta;

import java.math.BigDecimal;
import java.util.List;

import br.com.danielschiavo.dominio.equipe.Equipe;
import br.com.danielschiavo.dominio.evento.Evento;

public interface RepositorioDeAposta {
	
	void fazerAposta(Evento evento, Equipe equipeApostada, TipoAposta tipoAposta, BigDecimal valorApostado);
	
	List<Aposta> buscarTodasApostasPorClienteId(String id);
	
	Aposta buscarApostaPorApostaIdEClienteId(String apostaId, String clienteId);

}
