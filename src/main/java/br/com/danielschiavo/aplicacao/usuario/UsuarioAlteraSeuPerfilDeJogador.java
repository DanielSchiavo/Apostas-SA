package br.com.danielschiavo.aplicacao.usuario;

import org.mapstruct.factory.Mappers;

import br.com.danielschiavo.aplicacao.Token;
import br.com.danielschiavo.aplicacao.usuario.mapper.UsuarioMapper;
import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.usuario.RepositorioDeUsuario;
import br.com.danielschiavo.dominio.usuario.Usuario;
import br.com.danielschiavo.dominio.usuario.exceptions.AlterarUsuarioException;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;
import br.com.danielschiavo.dominio.usuario.perfiljogador.PerfilJogador;

public class UsuarioAlteraSeuPerfilDeJogador {

	private RepositorioDeUsuario repositorioDeUsuario;
	
	private UsuarioMapper usuarioMapper;
	
	private Token token;
	
	public UsuarioAlteraSeuPerfilDeJogador(RepositorioDeUsuario repositorioDeUsuario, Token token) {
		this.repositorioDeUsuario = repositorioDeUsuario;
		this.token = token;
		this.usuarioMapper = Mappers.getMapper(UsuarioMapper.class);
	}

	public void executa(UsuarioAlteraSeuPerfilDeJogadorDTO dadosQueSeraoAlterados, String stringToken) throws AutenticacaoException, AlterarUsuarioException, ValidacaoException {
		String usuarioId = token.getSubject(stringToken);
		
		PerfilJogador perfilJogador = usuarioMapper.formatarUsuarioAlteraSeuPerfilDeJogadorDTOParaUsuario(dadosQueSeraoAlterados);
		Usuario usuario = Usuario.builder().id(usuarioId)
										   .perfilJogador(perfilJogador)
										   .build();
		
		repositorioDeUsuario.usuarioAlteraSeuPerfilDeJogador(usuario);
	}
	
}
