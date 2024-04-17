package br.com.danielschiavo.dominio.equipe;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.danielschiavo.dominio.jogo.Jogo;
import br.com.danielschiavo.dominio.usuario.Jogador;

public class Equipe {
	
	private UUID id;
	
	private String sigla;
	
	private String nome;
	
	private String imagem;
	
    private List<JogoEquipe> associacoes;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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
	
	public List<Jogo> getJogosEquipe(){
		return associacoes.stream()
					.map(JogoEquipe::getJogo)
					.collect(Collectors.toList());
	}
	
	public List<Jogador> getJogadoresPorJogo(Jogo jogo) {
		return associacoes.stream()
					.filter(a -> a.getJogo() == jogo)
					.findFirst().map(JogoEquipe::getJogadores).get();
	}
	
}
