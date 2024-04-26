package br.com.danielschiavo.aplicacao.admin.categoria.subcategoria;

import java.time.LocalDateTime;
import java.util.UUID;

import org.mapstruct.factory.Mappers;

import br.com.danielschiavo.aplicacao.admin.categoria.subcategoria.mapper.SubCategoriaMapper;
import br.com.danielschiavo.dominio.GeradorUUID;
import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.categoria.subcategoria.RepositorioDeSubCategoriaAdmin;
import br.com.danielschiavo.dominio.categoria.subcategoria.SubCategoria;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;

public class CadastrarSubCategoria {

	private RepositorioDeSubCategoriaAdmin repositorioDeSubCategoria;
	
	private SubCategoriaMapper subCategoriaMapper;
	
	private GeradorUUID geradorUuid;
	
	public CadastrarSubCategoria(RepositorioDeSubCategoriaAdmin repositorioDeSubCategoria, GeradorUUID geradorUuid) {
		this.repositorioDeSubCategoria = repositorioDeSubCategoria;
		this.geradorUuid = geradorUuid;
		this.subCategoriaMapper = Mappers.getMapper(SubCategoriaMapper.class);
	}
	
	public String executa(CadastrarSubCategoriaDTO cadastrarSubCategoriaDTO, String usuarioId) throws AutenticacaoException, ValidacaoException {
		try {
			SubCategoria subcategoria = subCategoriaMapper.formatarCadastrarSubCategoriaDTOParaSubCategoria(cadastrarSubCategoriaDTO);
			subcategoria.setId(geradorUuid.gerarUUID());
			subcategoria.setDataCriacao(LocalDateTime.now());
			subcategoria.setCriadoPor(UUID.fromString(usuarioId));
			
			repositorioDeSubCategoria.verificarSeNomeSubCategoriaJaExiste(subcategoria.getNome());
			
			repositorioDeSubCategoria.cadastrarSubCategoria(subcategoria);
			
			repositorioDeSubCategoria.commitarTransacao();
			
			return subcategoria.getId().toString();
		} catch (ValidacaoException e) {
			e.printStackTrace();
			repositorioDeSubCategoria.rollbackTransacao();
			throw new ValidacaoException(e.getMessage());
		}
	}
	
}
