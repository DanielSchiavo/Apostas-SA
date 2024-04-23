package br.com.danielschiavo.aplicacao.usuario;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.danielschiavo.aplicacao.Token;
import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.usuario.RepositorioDeUsuario;
import br.com.danielschiavo.dominio.usuario.Usuario;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;

public class PegarDadosDoUsuarioPessoais {

	private RepositorioDeUsuario repositorioDeUsuario;
	
	private Token token;
	
	public PegarDadosDoUsuarioPessoais(RepositorioDeUsuario repositorioDeUsuario, Token token) {
		this.repositorioDeUsuario = repositorioDeUsuario;
		this.token = token;
	}

	public String executa(String stringToken) throws ValidacaoException, AutenticacaoException {
		try {
			String usuarioId = token.getSubject(stringToken);
			Usuario usuario = repositorioDeUsuario.pegarDadosDoUsuarioPessoais(usuarioId);
			
			ObjectMapper objectMapper = new ObjectMapper();
		    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    objectMapper.setDefaultPropertyInclusion(Include.NON_NULL);
		    return objectMapper.writeValueAsString(usuario);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Erro ao processar objeto para JSON", e);
		}
	}
}
