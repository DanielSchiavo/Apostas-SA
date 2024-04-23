package br.com.danielschiavo.aplicacao.usuario;

import org.mapstruct.factory.Mappers;

import br.com.danielschiavo.aplicacao.CriptografiaSenha;
import br.com.danielschiavo.aplicacao.Token;
import br.com.danielschiavo.aplicacao.usuario.mapper.UsuarioMapper;
import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.usuario.RepositorioDeUsuario;
import br.com.danielschiavo.dominio.usuario.Usuario;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;

public class AutenticarUsuario {

	private RepositorioDeUsuario repositorioDeUsuario;
	
	private CriptografiaSenha criptografiaSenha;
	
	private Token token;
	
	private UsuarioMapper usuarioMapper;
	
	public AutenticarUsuario(RepositorioDeUsuario repositorioDeUsuario, CriptografiaSenha criptografiaSenha, Token token) {
		this.criptografiaSenha = criptografiaSenha;
		this.repositorioDeUsuario = repositorioDeUsuario;
		this.token = token;
		this.usuarioMapper = Mappers.getMapper(UsuarioMapper.class);
	}

	public String executa(AutenticarUsuarioDTO autenticarUsuarioDTO) throws AutenticacaoException, ValidacaoException {
		Usuario usuario = usuarioMapper.formatarAutenticarUsuarioDTOParaUsuario(autenticarUsuarioDTO);
		
		try {
			repositorioDeUsuario.comecarTransacao();
			Usuario usuarioAutenticado = repositorioDeUsuario.autenticarUsuario(usuario);
		
			criptografiaSenha.verificarSenha(usuarioAutenticado.getSenha(), autenticarUsuarioDTO.getSenha());
			
			String tokenGerado = token.gerarToken(usuarioAutenticado);
			repositorioDeUsuario.commitarTransacao();
			return tokenGerado;
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
