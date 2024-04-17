package br.com.danielschiavo.dominio.evento;


import java.util.UUID;

import br.com.danielschiavo.dominio.usuario.Jogador;

public class JogadorEvento {
	
	private UUID equipeEventoId;
	
	private String nomeNoJogo;
	
	private String foto;
	
	private UUID jogadorId;
	
	public JogadorEvento(UUID equipeEventoId, String nomeNoJogo, String foto, UUID jogadorId) {
		super();
		this.equipeEventoId = equipeEventoId;
		this.nomeNoJogo = nomeNoJogo;
		this.foto = foto;
		this.jogadorId = jogadorId;
	}

	public JogadorEvento(Jogador jogador) {
		this.nomeNoJogo = jogador.getNomeNoJogo();
	}
	
}
