package br.com.danielschiavo.aplicacao.usuario;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.danielschiavo.aplicacao.Token;
import br.com.danielschiavo.dominio.usuario.RepositorioDeUsuario;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;
import br.com.danielschiavo.dominio.usuario.perfiljogador.PerfilJogador;

public class PegarDadosDoUsuarioPerfilDeJogador {

	private RepositorioDeUsuario repositorioDeUsuario;
	
	private Token token;
	
	public PegarDadosDoUsuarioPerfilDeJogador(RepositorioDeUsuario repositorioDeUsuario, Token token) {
		this.repositorioDeUsuario = repositorioDeUsuario;
		this.token = token;
	}

	public String executa(String stringToken) throws AutenticacaoException {
		String usuarioId = token.getSubject(stringToken);
		PerfilJogador perfilJogador = repositorioDeUsuario.pegarDadosDoUsuarioPerfilDeJogador(usuarioId);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(perfilJogador);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Erro ao processar objeto para JSON");
		}
	}
	
}
