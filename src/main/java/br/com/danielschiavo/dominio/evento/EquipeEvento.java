package br.com.danielschiavo.dominio.evento;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipeEvento {

	private UUID id;
	
	private UUID equipeId;
	
	private String sigla;
	
	private String nome;
	
	private String imagem;
	
	@Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
	private List<JogadorEvento> jogadores;
	
	public List<JogadorEvento> getJogadores(){
		return Collections.unmodifiableList(this.jogadores);
	}
	
	public void adicionarJogador(JogadorEvento jogadorEvento) {
		this.jogadores.add(jogadorEvento);
	}
}
