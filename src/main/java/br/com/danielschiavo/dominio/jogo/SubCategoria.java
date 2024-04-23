package br.com.danielschiavo.dominio.jogo;

import java.util.UUID;

public class SubCategoria {

	private UUID id;
	
	private String nome;
	
	private String imagem;
	
	private Categoria categoria;
	
	private Short ordem;
	
	private SubCategoria subCcategoria;
}
