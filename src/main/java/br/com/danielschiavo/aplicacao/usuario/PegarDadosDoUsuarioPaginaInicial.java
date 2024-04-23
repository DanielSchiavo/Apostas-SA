package br.com.danielschiavo.aplicacao.usuario;

import org.mapstruct.factory.Mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.danielschiavo.aplicacao.Token;
import br.com.danielschiavo.aplicacao.usuario.mapper.UsuarioMapper;
import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.usuario.RepositorioDeUsuario;
import br.com.danielschiavo.dominio.usuario.Usuario;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;

public class PegarDadosDoUsuarioPaginaInicial {

	private RepositorioDeUsuario repositorioDeUsuario;
	
	private Token token;
	
	private UsuarioMapper usuarioMapper;
	
	public PegarDadosDoUsuarioPaginaInicial(RepositorioDeUsuario repositorioDeUsuario, Token token) {
		this.repositorioDeUsuario = repositorioDeUsuario;
		this.token = token;
		this.usuarioMapper = Mappers.getMapper(UsuarioMapper.class);
	}

	public String executa(String stringToken) throws ValidacaoException, AutenticacaoException {
		String usuarioId = token.getSubject(stringToken);
		Usuario usuario = repositorioDeUsuario.pegarDadosDoUsuarioPaginaInicial(usuarioId);
		UsuarioPaginaInicialDTO usuarioPaginaInicialDTO = usuarioMapper.formatarUsuarioParaUsuarioPaginaInicialDTO(usuario);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(usuarioPaginaInicialDTO);
			return json;
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Erro ao processar json de resposta");
		}
	}
	
}
