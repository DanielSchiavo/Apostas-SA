package br.com.danielschiavo.dominio.categoria.subcategoria;

import java.util.List;

import br.com.danielschiavo.dominio.ValidacaoException;

public interface RepositorioDeSubCategoriaAdmin {

	void cadastrarSubCategoria(SubCategoria subCategoria) throws ValidacaoException;
	
	void alterarSubCategoria(SubCategoria subCategoria) throws AlterarSubCategoriaException, ValidacaoException;
	
	void deletarSubCategoria(String subCategoriaId) throws DeletarSubCategoriaException, ValidacaoException;
	
	void verificarSeNomeSubCategoriaJaExiste(String nome) throws ValidacaoException;
	
	
	SubCategoria pegarSubCategoriaPorId(String subCategoriaId) throws ValidacaoException;
	
	List<SubCategoria> pegarTodasSubCategorias() throws ValidacaoException;
	
	
    void commitarTransacao();
    
    void rollbackTransacao();
	
}
