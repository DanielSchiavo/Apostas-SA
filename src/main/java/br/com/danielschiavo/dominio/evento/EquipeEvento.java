package br.com.danielschiavo.dominio.evento;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.danielschiavo.dominio.GeradorUUID;
import br.com.danielschiavo.dominio.equipe.Equipe;
import br.com.danielschiavo.dominio.jogo.Jogo;
import br.com.danielschiavo.dominio.usuario.Jogador;

public class EquipeEvento {

	private UUID id;
	
	private UUID idEquipe;
	
	private String sigla;
	
	private String nome;
	
	private String imagem;
	
	private List<JogadorEvento> jogadores;
	
	public EquipeEvento(UUID id, UUID idEquipe, String sigla, String nome, String imagem,
			List<JogadorEvento> jogadores) {
		super();
		this.id = id;
		this.idEquipe = idEquipe;
		this.sigla = sigla;
		this.nome = nome;
		this.imagem = imagem;
		this.jogadores = jogadores;
	}

	public EquipeEvento(GeradorUUID geradorUuid, Equipe equipe, Jogo jogo) {
		this.id = geradorUuid.gerarUUID();
		this.idEquipe = equipe.getId();
		this.sigla = equipe.getSigla();
		this.nome = equipe.getNome();
		this.imagem = equipe.getImagem();
		this.jogadores = mapearJogadorParaJogadorEvento(equipe.getJogadoresPorJogo(jogo));
	}
	
	public List<JogadorEvento> mapearJogadorParaJogadorEvento(List<Jogador> jogadores) {
		List<JogadorEvento> jogadorEvento = new ArrayList<>();
		jogadores.forEach(a -> jogadorEvento.add(new JogadorEvento(a)));
		return jogadorEvento;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(UUID idEquipe) {
		this.idEquipe = idEquipe;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public List<JogadorEvento> getJogadores() {
		return jogadores;
	}

	public void setJogadores(List<JogadorEvento> jogadores) {
		this.jogadores = jogadores;
	}

}
