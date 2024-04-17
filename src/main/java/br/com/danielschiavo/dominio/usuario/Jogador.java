package br.com.danielschiavo.dominio.usuario;

import java.util.UUID;

import br.com.danielschiavo.dominio.equipe.JogoEquipe;
import br.com.danielschiavo.dominio.jogo.Jogo;

public class Jogador {

	private UUID usuarioId;
	
	private Jogo jogo;
	
	private String nomeNoJogo;
	
	private JogoEquipe jogoEquipe;
	
	public UUID getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(UUID usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNomeNoJogo() {
		return nomeNoJogo;
	}

	public void setNomeNoJogo(String nomeNoJogo) {
		this.nomeNoJogo = nomeNoJogo;
	}
	
	public JogoEquipe getJogoEquipe() {
		return jogoEquipe;
	}

	public void setJogoEquipe(JogoEquipe jogoEquipe) {
		this.jogoEquipe = jogoEquipe;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

}
