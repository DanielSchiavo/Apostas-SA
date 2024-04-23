package br.com.danielschiavo.dominio.equipe;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import br.com.danielschiavo.dominio.jogo.Jogo;
import br.com.danielschiavo.dominio.usuario.perfiljogador.perfiljogo.PerfilJogo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JogoEquipe {
	
	private UUID id;
	
	private UUID equipeId;
	
	private Jogo jogo;
	
	private Integer vitorias;
	
	private Integer empates;
	
	private Integer derrotas;
	
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private List<PerfilJogo> jogadores;

	public List<PerfilJogo> getJogadores() {
		return Collections.unmodifiableList(jogadores);
	}

	public void adicionarJogador(PerfilJogo jogador) {
		this.jogadores.add(jogador);
	}
	
	public void removerJogador(PerfilJogo jogador) {
		this.jogadores.remove(jogador);
	}
	
}
