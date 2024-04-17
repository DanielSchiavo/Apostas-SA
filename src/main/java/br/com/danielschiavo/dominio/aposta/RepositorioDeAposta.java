package br.com.danielschiavo.dominio.aposta;

import java.math.BigDecimal;
import java.util.List;

import br.com.danielschiavo.dominio.equipe.Equipe;
import br.com.danielschiavo.dominio.evento.Evento;

public interface RepositorioDeAposta {
	
	void fazerAposta(Evento evento, Equipe equipe, TipoAposta tipoAposta, BigDecimal valor);
	
	List<Aposta> buscarTodasApostasPorCPF(String cpf);

}
