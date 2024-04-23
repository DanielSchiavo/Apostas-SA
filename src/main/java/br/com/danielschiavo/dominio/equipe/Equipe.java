package br.com.danielschiavo.dominio.equipe;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.danielschiavo.dominio.jogo.Jogo;
import br.com.danielschiavo.dominio.usuario.perfiljogador.perfiljogo.PerfilJogo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Equipe {
	
	private UUID id;
	
	private String sigla;
	
	private String nome;
	
	private String imagem;
	
    private List<JogoEquipe> associacoes;

	public List<Jogo> getJogosEquipe(){
		return associacoes.stream()
					.map(JogoEquipe::getJogo)
					.collect(Collectors.toList());
	}
	
	public List<PerfilJogo> getJogadoresPorJogo(Jogo jogo) {
		return associacoes.stream()
					.filter(a -> a.getJogo() == jogo)
					.findFirst().map(JogoEquipe::getJogadores).get();
	}
	
}
