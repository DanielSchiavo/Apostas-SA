package br.com.danielschiavo.aplicacao.user.usuario;

import org.mapstruct.factory.Mappers;

import br.com.danielschiavo.aplicacao.CriptografiaSenha;
import br.com.danielschiavo.aplicacao.Token;
import br.com.danielschiavo.aplicacao.user.usuario.mapper.UsuarioMapper;
import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.usuario.RepositorioDeUsuarioUser;
import br.com.danielschiavo.dominio.usuario.Usuario;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;

public class AutenticarUsuario {

	private RepositorioDeUsuarioUser repositorioDeUsuario;
	
	private CriptografiaSenha criptografiaSenha;
	
	private Token token;
	
	private UsuarioMapper usuarioMapper;
	
	public AutenticarUsuario(RepositorioDeUsuarioUser repositorioDeUsuario, CriptografiaSenha criptografiaSenha, Token token) {
		this.criptografiaSenha = criptografiaSenha;
		this.repositorioDeUsuario = repositorioDeUsuario;
		this.token = token;
		this.usuarioMapper = Mappers.getMapper(UsuarioMapper.class);
	}

	public String executa(AutenticarUsuarioDTO autenticarUsuarioDTO) throws AutenticacaoException, ValidacaoException {
		Usuario usuario = usuarioMapper.formatarAutenticarUsuarioDTOParaUsuario(autenticarUsuarioDTO);
		
		try {
			Usuario usuarioPreAutenticado = repositorioDeUsuario.autenticarUsuario(usuario);
			
			criptografiaSenha.verificarSenha(usuarioPreAutenticado.getSenha(), autenticarUsuarioDTO.getSenha());
			
			String tokenUsuarioAutenticado = token.gerarToken(usuarioPreAutenticado);
			repositorioDeUsuario.commitarTransacao();
			return tokenUsuarioAutenticado;
		} catch (AutenticacaoException e) {
			e.printStackTrace();
			repositorioDeUsuario.rollbackTransacao();
			throw new AutenticacaoException(e.getMessage());
		} catch (ValidacaoException e) {
			e.printStackTrace();
			repositorioDeUsuario.rollbackTransacao();
			throw new ValidacaoException(e.getMessage());
		}
	}
}
