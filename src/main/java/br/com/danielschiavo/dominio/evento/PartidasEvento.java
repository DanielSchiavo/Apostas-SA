package br.com.danielschiavo.dominio.evento;

import java.util.UUID;

import br.com.danielschiavo.dominio.evento.estatisticas.lol.Estatisticas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartidasEvento {
	
	private UUID eventoId;
	
	private Short partidaNumero;
	
	private UUID mapaId;
	
	private EquipeEvento equipeEventoVencedora;
	
	private EquipeEvento equipeEventoPerdedora;
	
	private Estatisticas estatisticas;

}
