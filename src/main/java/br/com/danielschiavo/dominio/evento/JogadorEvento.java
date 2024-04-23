package br.com.danielschiavo.dominio.evento;


import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JogadorEvento {
	
	private UUID equipeEventoId;
	
	private String nomeNoJogo;
	
	private String foto;
	
	private UUID jogadorId;
	
}
