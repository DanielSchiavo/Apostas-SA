package br.com.danielschiavo.aplicacao.admin.categoria;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.mapstruct.factory.Mappers;

import br.com.danielschiavo.aplicacao.admin.categoria.mapper.CategoriaMapper;
import br.com.danielschiavo.dominio.GeradorUUID;
import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.categoria.Categoria;
import br.com.danielschiavo.dominio.categoria.RepositorioDeCategoriaAdmin;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;

public class CadastrarCategoria {

	private RepositorioDeCategoriaAdmin repositorioDeCategoria;
	
	private CategoriaMapper categoriaMapper;
	
	private GeradorUUID geradorUuid;
	
	public CadastrarCategoria(RepositorioDeCategoriaAdmin repositorioDeCategoria, GeradorUUID geradorUuid) {
		this.repositorioDeCategoria = repositorioDeCategoria;
		this.geradorUuid = geradorUuid;
		this.categoriaMapper = Mappers.getMapper(CategoriaMapper.class);
	}
	
	public String executa(CadastrarCategoriaDTO cadastrarCategoriaDTO, String usuarioId) throws AutenticacaoException, ValidacaoException {
		Set<ConstraintViolation<CadastrarCategoriaDTO>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(cadastrarCategoriaDTO);
        for (ConstraintViolation<CadastrarCategoriaDTO> violation : violations) {
        	throw new ValidacaoException(violation.getMessage());
        }
		
		try {
			Categoria categoria = categoriaMapper.formatarCadastrarCategoriaDTOParaCategoria(cadastrarCategoriaDTO);
			categoria.setId(geradorUuid.gerarUUID());
			categoria.setDataCriacao(LocalDateTime.now());
			categoria.setCriadoPor(UUID.fromString(usuarioId));
			
			repositorioDeCategoria.verificarSeNomeCategoriaJaExiste(categoria.getNome());
			
			repositorioDeCategoria.cadastrarCategoria(categoria);
			
			repositorioDeCategoria.commitarTransacao();
			
			return categoria.getId().toString();
		} catch (ValidacaoException e) {
			e.printStackTrace();
			repositorioDeCategoria.rollbackTransacao();
			throw new ValidacaoException(e.getMessage());
		}
	}
	
}
