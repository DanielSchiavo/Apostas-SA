package br.com.danielschiavo.aplicacao.admin.categoria.subcategoria.mapper;

import java.util.UUID;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import br.com.danielschiavo.aplicacao.admin.categoria.subcategoria.AlterarSubCategoriaDTO;
import br.com.danielschiavo.aplicacao.admin.categoria.subcategoria.CadastrarSubCategoriaDTO;
import br.com.danielschiavo.dominio.categoria.Categoria;
import br.com.danielschiavo.dominio.categoria.subcategoria.SubCategoria;

@Mapper(builder = @Builder(disableBuilder = true), unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubCategoriaMapper {
	
	@Mapping(source = "categoriaId", target = "categoria", qualifiedByName = "converterStringCategoriaIdEmCategoria")
	SubCategoria formatarCadastrarSubCategoriaDTOParaSubCategoria(CadastrarSubCategoriaDTO cadastrarSubCategoriaDTO);
	
	@Mapping(source = "subCategoriaId", target = "id", qualifiedByName = "converterStringEmUUID")
	@Mapping(source = "categoriaId", target = "categoria", qualifiedByName = "converterStringCategoriaIdEmCategoria")
	SubCategoria formatarAlterarSubCategoriaDTOParaCategoria(AlterarSubCategoriaDTO alterarSubCategoriaDTO);

	@Named(value = "converterStringEmUUID")
    default UUID converterStringEmUUID(String value) {
        return (value == null) ? null : UUID.fromString(value);
    }
	
	@Named(value = "converterStringCategoriaIdEmCategoria")
    default Categoria converterStringCategoriaIdEmCategoria(String value) {
		return Categoria.builder().id(UUID.fromString(value)).build();
	}

}
