package br.com.danielschiavo.aplicacao.categoria;

import br.com.danielschiavo.aplicacao.Token;
import br.com.danielschiavo.dominio.categoria.RepositorioDeCategoria;

public class CadastrarCategoria {

	private RepositorioDeCategoria repositorioDeCategoria;
	
	private Token token;
	
	public CadastrarCategoria(RepositorioDeCategoria repositorioDeCategoria, Token token) {
		this.repositorioDeCategoria = repositorioDeCategoria;
		this.token = token;
	}
	
}
