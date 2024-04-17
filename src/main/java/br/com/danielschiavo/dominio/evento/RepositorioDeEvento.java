package br.com.danielschiavo.dominio.evento;

import java.util.UUID;

public interface RepositorioDeEvento {

	public Evento buscarEventoPorId(UUID id);
	
}
