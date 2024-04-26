package br.com.danielschiavo.aplicacao.admin.categoria.subcategoria;

import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.categoria.subcategoria.DeletarSubCategoriaException;
import br.com.danielschiavo.dominio.categoria.subcategoria.RepositorioDeSubCategoriaAdmin;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;

public class DeletarSubCategoria {
	
	private RepositorioDeSubCategoriaAdmin repositorioDeSubCategoria;
	
	public DeletarSubCategoria(RepositorioDeSubCategoriaAdmin repositorioDeSubCategoria) {
		this.repositorioDeSubCategoria = repositorioDeSubCategoria;
	}

	public void executa(String categoriaId) throws AutenticacaoException, DeletarSubCategoriaException, ValidacaoException {
		try {
			repositorioDeSubCategoria.deletarSubCategoria(categoriaId);
			
			repositorioDeSubCategoria.commitarTransacao();
		} catch (DeletarSubCategoriaException e) {
			e.printStackTrace();
			repositorioDeSubCategoria.rollbackTransacao();
			throw new DeletarSubCategoriaException(e.getMessage());
		} catch (ValidacaoException e) {
			e.printStackTrace();
			repositorioDeSubCategoria.rollbackTransacao();
			throw new ValidacaoException(e.getMessage());
		}
	}
	
}
