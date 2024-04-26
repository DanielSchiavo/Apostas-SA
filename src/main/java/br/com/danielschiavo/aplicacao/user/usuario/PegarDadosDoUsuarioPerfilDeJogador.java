package br.com.danielschiavo.aplicacao.user.usuario;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.danielschiavo.dominio.usuario.RepositorioDeUsuarioUser;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;
import br.com.danielschiavo.dominio.usuario.perfiljogador.PerfilJogador;

public class PegarDadosDoUsuarioPerfilDeJogador {

	private RepositorioDeUsuarioUser repositorioDeUsuario;
	
	public PegarDadosDoUsuarioPerfilDeJogador(RepositorioDeUsuarioUser repositorioDeUsuario) {
		this.repositorioDeUsuario = repositorioDeUsuario;
	}

	public String executa(String usuarioId) throws AutenticacaoException {
		PerfilJogador perfilJogador = repositorioDeUsuario.pegarDadosDoUsuarioPerfilDeJogador(usuarioId);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(perfilJogador);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Erro ao processar objeto para JSON");
		}
	}
	
}
