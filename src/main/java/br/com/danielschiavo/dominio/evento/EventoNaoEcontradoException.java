package br.com.danielschiavo.dominio.evento;

import java.util.UUID;


public class EventoNaoEcontradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EventoNaoEcontradoException(UUID id) {
		super("Evento não encontrado com ID: " + id.toString());
	}

}
