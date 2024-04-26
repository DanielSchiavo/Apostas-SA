package br.com.danielschiavo.dominio.categoria;

import br.com.danielschiavo.dominio.ValidacaoException;

public interface RepositorioDeCategoriaAdmin {
	
	void cadastrarCategoria(Categoria categoria) throws ValidacaoException;
	
	void alterarCategoria(Categoria categoria) throws AlterarCategoriaException, ValidacaoException;
	
	void removerCategoria(Categoria categoria) throws RemoverCategoriaException;
	
	void verificarSeNomeCategoriaJaExiste(String nome) throws ValidacaoException;
	
	
	Categoria pegarCategoriaPorId(String categoriaId) throws ValidacaoException;
	
	
    void commitarTransacao();
    
    void rollbackTransacao();


}
