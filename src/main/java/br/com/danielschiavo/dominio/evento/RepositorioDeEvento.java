package br.com.danielschiavo.dominio.evento;

import java.util.List;
import java.util.UUID;

public interface RepositorioDeEvento {

	Evento pegarEventoPorId(UUID id);
	
	List<Evento> pegarTodosEventos();
	
	List<Evento> pegarTodosEventosPorSubCategoriaId(String subCategoriaId);
}
