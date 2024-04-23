package br.com.danielschiavo.dominio.categoria;

import br.com.danielschiavo.dominio.ValidacaoException;

public interface RepositorioDeCategoria {
	
	void cadastrarCategoria(Categoria categoria) throws ValidacaoException;
	
	void alterarCategoria(Categoria categoria) throws AlterarCategoriaException;
	
	void removerCategoria(Categoria categoria) throws RemoverCategoriaException;
	

}
