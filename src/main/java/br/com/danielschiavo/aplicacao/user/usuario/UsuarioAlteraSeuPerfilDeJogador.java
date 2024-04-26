package br.com.danielschiavo.aplicacao.user.usuario;

import org.mapstruct.factory.Mappers;

import br.com.danielschiavo.aplicacao.user.usuario.mapper.UsuarioMapper;
import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.usuario.RepositorioDeUsuarioUser;
import br.com.danielschiavo.dominio.usuario.Usuario;
import br.com.danielschiavo.dominio.usuario.exceptions.AlterarUsuarioException;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;
import br.com.danielschiavo.dominio.usuario.perfiljogador.PerfilJogador;

public class UsuarioAlteraSeuPerfilDeJogador {

	private RepositorioDeUsuarioUser repositorioDeUsuario;
	
	private UsuarioMapper usuarioMapper;
	
	public UsuarioAlteraSeuPerfilDeJogador(RepositorioDeUsuarioUser repositorioDeUsuario) {
		this.repositorioDeUsuario = repositorioDeUsuario;
		this.usuarioMapper = Mappers.getMapper(UsuarioMapper.class);
	}

	public void executa(UsuarioAlteraSeuPerfilDeJogadorDTO dadosQueSeraoAlterados, String usuarioId) throws AutenticacaoException, ValidacaoException, AlterarUsuarioException {
		PerfilJogador perfilJogador = usuarioMapper.formatarUsuarioAlteraSeuPerfilDeJogadorDTOParaUsuario(dadosQueSeraoAlterados);
		try {
		Usuario usuario = Usuario.builder().id(usuarioId)
										   .perfilJogador(perfilJogador)
										   .build();
		repositorioDeUsuario.usuarioAlteraSeuPerfilDeJogador(usuario);
		repositorioDeUsuario.commitarTransacao();
		} catch (ValidacaoException e) {
			e.printStackTrace();
			repositorioDeUsuario.rollbackTransacao();
			throw new ValidacaoException(e.getMessage());
		} catch (AlterarUsuarioException e) {
			e.printStackTrace();
			repositorioDeUsuario.rollbackTransacao();
			throw new AlterarUsuarioException(e.getMessage());
		}
		
	}
	
}
