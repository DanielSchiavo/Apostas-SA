package br.com.danielschiavo.aplicacao.admin.categoria.subcategoria;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.mapstruct.factory.Mappers;

import br.com.danielschiavo.aplicacao.admin.categoria.subcategoria.mapper.SubCategoriaMapper;
import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.categoria.subcategoria.AlterarSubCategoriaException;
import br.com.danielschiavo.dominio.categoria.subcategoria.RepositorioDeSubCategoriaAdmin;
import br.com.danielschiavo.dominio.categoria.subcategoria.SubCategoria;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;

public class AlterarSubCategoria {

	private RepositorioDeSubCategoriaAdmin repositorioDeSubCategoria;
	
	private SubCategoriaMapper subCategoriaMapper;
	
	public AlterarSubCategoria(RepositorioDeSubCategoriaAdmin repositorioDeSubCategoria) {
		this.repositorioDeSubCategoria = repositorioDeSubCategoria;
		this.subCategoriaMapper = Mappers.getMapper(SubCategoriaMapper.class);
	}
	
	public void executa(AlterarSubCategoriaDTO alterarSubCategoriaDTO, String usuarioId) throws AlterarSubCategoriaException, ValidacaoException, AutenticacaoException {
		Set<ConstraintViolation<AlterarSubCategoriaDTO>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(alterarSubCategoriaDTO);
        for (ConstraintViolation<AlterarSubCategoriaDTO> violation : violations) {
        	throw new ValidacaoException(violation.getMessage());
        }
		
		try {
			SubCategoria subCategoria = subCategoriaMapper.formatarAlterarSubCategoriaDTOParaCategoria(alterarSubCategoriaDTO);
			subCategoria.setDataUltimaAlteracao(LocalDateTime.now());
			subCategoria.setAlteradorPor(UUID.fromString(usuarioId));
			
			repositorioDeSubCategoria.verificarSeNomeSubCategoriaJaExiste(subCategoria.getNome());
			
			repositorioDeSubCategoria.alterarSubCategoria(subCategoria);
			
			repositorioDeSubCategoria.commitarTransacao();
			
		} catch (ValidacaoException e) {
			e.printStackTrace();
			repositorioDeSubCategoria.rollbackTransacao();
			throw new ValidacaoException(e.getMessage());
		} catch (AlterarSubCategoriaException e) {
			e.printStackTrace();
			repositorioDeSubCategoria.rollbackTransacao();
			throw new AlterarSubCategoriaException(e.getMessage());
		}
	}
	
}
