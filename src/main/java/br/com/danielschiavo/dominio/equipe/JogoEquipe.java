package br.com.danielschiavo.dominio.equipe;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import br.com.danielschiavo.dominio.jogo.Jogo;
import br.com.danielschiavo.dominio.usuario.Jogador;

public class JogoEquipe {
	
	private UUID equipeId;
	
	private Jogo jogo;
	
	private Integer vitorias;
	
	private Integer empates;
	
	private Integer derrotas;
	
	private List<Jogador> jogadores;

	public UUID getEquipeId() {
		return equipeId;
	}

	public void setEquipeId(UUID equipeId) {
		this.equipeId = equipeId;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public Integer getVitorias() {
		return vitorias;
	}

	public void setVitorias(Integer vitorias) {
		this.vitorias = vitorias;
	}

	public Integer getEmpates() {
		return empates;
	}

	public void setEmpates(Integer empates) {
		this.empates = empates;
	}

	public Integer getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(Integer derrotas) {
		this.derrotas = derrotas;
	}

	public List<Jogador> getJogadores() {
		return Collections.unmodifiableList(jogadores);
	}

	public void adicionarJogador(Jogador jogador) {
		this.jogadores.add(jogador);
	}
	
	public void removerJogador(Jogador jogador) {
		this.jogadores.remove(jogador);
	}
	
}
