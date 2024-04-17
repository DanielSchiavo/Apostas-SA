package br.com.danielschiavo.dominio.evento;

import java.util.UUID;

import br.com.danielschiavo.dominio.equipe.Equipe;
import br.com.danielschiavo.dominio.evento.estatisticas.lol.Estatisticas;
import br.com.danielschiavo.dominio.jogo.Mapa;

public class PartidasEvento {
	
	private UUID eventoId;
	
	private Short partidaNumero;
	
	private UUID mapaId;
	
	private EquipeEvento equipeEventoVencedora;
	
	private EquipeEvento equipeEventoPerdedora;
	
	private Estatisticas estatisticas;

}
