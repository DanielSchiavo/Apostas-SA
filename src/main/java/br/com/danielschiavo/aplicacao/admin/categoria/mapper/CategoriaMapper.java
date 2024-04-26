package br.com.danielschiavo.aplicacao.admin.categoria.mapper;

import java.util.UUID;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import br.com.danielschiavo.aplicacao.admin.categoria.AlterarCategoriaDTO;
import br.com.danielschiavo.aplicacao.admin.categoria.CadastrarCategoriaDTO;
import br.com.danielschiavo.dominio.categoria.Categoria;

@Mapper(builder = @Builder(disableBuilder = true), unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoriaMapper {
	
	Categoria formatarCadastrarCategoriaDTOParaCategoria(CadastrarCategoriaDTO cadastrarCategoriaDTO);

	@Mapping(source = "categoriaId", target = "id", qualifiedByName = "converterStringEmUUID")
	Categoria formatarAlterarCategoriaDTOParaCategoria(AlterarCategoriaDTO alterarCategoriaDTO);

	@Named(value = "converterStringEmUUID")
    default UUID converterStringEmUUID(String value) {
        return (value == null) ? null : UUID.fromString(value);
    }
}
