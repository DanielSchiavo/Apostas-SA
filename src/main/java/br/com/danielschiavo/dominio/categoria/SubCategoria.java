package br.com.danielschiavo.dominio.categoria;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoria {

	private UUID id;
	
	private String nome;
	
	private String imagem;
	
	private Categoria categoria;
	
	private Short ordem;
	
	private SubCategoria subCcategoria;
}
