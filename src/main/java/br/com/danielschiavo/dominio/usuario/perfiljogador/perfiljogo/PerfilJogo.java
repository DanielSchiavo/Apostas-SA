package br.com.danielschiavo.dominio.usuario.perfiljogador.perfiljogo;

import java.util.UUID;

import br.com.danielschiavo.dominio.equipe.JogoEquipe;
import br.com.danielschiavo.dominio.jogo.Jogo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerfilJogo {

	private UUID usuarioId;
	
	private Jogo jogo;
	
	private String nomeNoJogo;
	
	private JogoEquipe jogoEquipe;
	
}
