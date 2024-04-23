package br.com.danielschiavo.dominio.jogo;

public interface RepositorioDeJogo {
	
	Jogo cadastrarJogo(Jogo jogo);
	
	void removerJogoPorJogoId(String jogoId);
}
