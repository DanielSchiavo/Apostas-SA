package br.com.danielschiavo.aplicacao.admin.categoria.subcategoria;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.categoria.Categoria;
import br.com.danielschiavo.dominio.categoria.RepositorioDeCategoriaAdmin;
import br.com.danielschiavo.dominio.categoria.subcategoria.RepositorioDeSubCategoriaAdmin;
import br.com.danielschiavo.dominio.categoria.subcategoria.SubCategoria;

public class PegarSubCategoriaPorId {
	
	private RepositorioDeSubCategoriaAdmin repositorioDeSubCategoria;
	
	private RepositorioDeCategoriaAdmin repositorioDeCategoria;
	
	public PegarSubCategoriaPorId(RepositorioDeSubCategoriaAdmin repositorioDeSubCategoria, RepositorioDeCategoriaAdmin repositorioDeCategoria) {
		this.repositorioDeSubCategoria = repositorioDeSubCategoria;
		this.repositorioDeCategoria = repositorioDeCategoria;
	}
	
	public String executa(String subCategoriaId) throws ValidacaoException, JsonProcessingException{
		SubCategoria subCategoria = repositorioDeSubCategoria.pegarSubCategoriaPorId(subCategoriaId);
		
		Categoria categoria = repositorioDeCategoria.pegarCategoriaPorId(subCategoria.getCategoria().getId().toString());
		
		repositorioDeSubCategoria.commitarTransacao();
		
		subCategoria.setCategoria(categoria);
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.configOverride(LocalDateTime.class).setFormat(JsonFormat.Value.forPattern("dd/MM/yyyy hh:MM:ss"));
		return objectMapper.writeValueAsString(subCategoria);
	}
}
