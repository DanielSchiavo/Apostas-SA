package br.com.danielschiavo.aplicacao.admin.categoria;

import java.time.LocalDateTime;
import java.util.UUID;

import org.mapstruct.factory.Mappers;

import br.com.danielschiavo.aplicacao.admin.categoria.mapper.CategoriaMapper;
import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.categoria.AlterarCategoriaException;
import br.com.danielschiavo.dominio.categoria.Categoria;
import br.com.danielschiavo.dominio.categoria.RepositorioDeCategoriaAdmin;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;

public class AlterarCategoria {

	private RepositorioDeCategoriaAdmin repositorioDeCategoria;
	
	private CategoriaMapper categoriaMapper;
	
	public AlterarCategoria(RepositorioDeCategoriaAdmin repositorioDeCategoria) {
		this.repositorioDeCategoria = repositorioDeCategoria;
		this.categoriaMapper = Mappers.getMapper(CategoriaMapper.class);
	}
	
	public void executa(AlterarCategoriaDTO alterarCategoriaDTO, String usuarioId) throws AlterarCategoriaException, ValidacaoException, AutenticacaoException {
		try {
			Categoria categoria = categoriaMapper.formatarAlterarCategoriaDTOParaCategoria(alterarCategoriaDTO);
			categoria.setDataUltimaAlteracao(LocalDateTime.now());
			categoria.setAlteradorPor(UUID.fromString(usuarioId));
			
			repositorioDeCategoria.verificarSeNomeCategoriaJaExiste(categoria.getNome());
			
			repositorioDeCategoria.alterarCategoria(categoria);
			
			repositorioDeCategoria.commitarTransacao();
			
		} catch (ValidacaoException e) {
			e.printStackTrace();
			repositorioDeCategoria.rollbackTransacao();
			throw new ValidacaoException(e.getMessage());
		} catch (AlterarCategoriaException e) {
			e.printStackTrace();
			repositorioDeCategoria.rollbackTransacao();
			throw new AlterarCategoriaException(e.getMessage());
		}
	}
}
