package br.com.danielschiavo.dominio.usuario.perfiljogador;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PerfilJogador {

	private String foto;
	
	private String instagram;
	
	private String facebook;
	
	private String twitter;
	
	private String frase;
	
}
