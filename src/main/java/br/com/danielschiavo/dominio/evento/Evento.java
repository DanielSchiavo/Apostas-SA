package br.com.danielschiavo.dominio.evento;

import java.time.LocalDateTime;
import java.util.List;

import br.com.danielschiavo.dominio.evento.equipe.Equipe;
import br.com.danielschiavo.dominio.evento.jogo.Jogo;

public class Evento {

	private String nome;
	
	private Jogo jogo;
	
	private Equipe equipeA;
	
	private Equipe equipeB;
	
	private Formato formato;
	
	private List<Partidas> partidasEvento;
	
	private StatusEvento status;
	
	private LocalDateTime dataEHoraInicioEvento;
	
	private LocalDateTime dataEHoraFimEvento;
	
	private LocalDateTime dataEHoraCriacao;
	
}
