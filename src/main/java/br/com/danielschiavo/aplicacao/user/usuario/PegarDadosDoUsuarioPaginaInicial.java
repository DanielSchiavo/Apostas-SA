package br.com.danielschiavo.aplicacao.user.usuario;

import org.mapstruct.factory.Mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.danielschiavo.aplicacao.user.usuario.mapper.UsuarioMapper;
import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.usuario.RepositorioDeUsuarioUser;
import br.com.danielschiavo.dominio.usuario.Usuario;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;

public class PegarDadosDoUsuarioPaginaInicial {

	private RepositorioDeUsuarioUser repositorioDeUsuario;
	
	private UsuarioMapper usuarioMapper;
	
	public PegarDadosDoUsuarioPaginaInicial(RepositorioDeUsuarioUser repositorioDeUsuario) {
		this.repositorioDeUsuario = repositorioDeUsuario;
		this.usuarioMapper = Mappers.getMapper(UsuarioMapper.class);
	}

	public String executa(String usuarioId) throws ValidacaoException, AutenticacaoException {
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
