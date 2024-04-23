package br.com.danielschiavo.aplicacao.categoria;

import org.mapstruct.factory.Mappers;

import br.com.danielschiavo.aplicacao.CriptografiaSenha;
import br.com.danielschiavo.aplicacao.Token;
import br.com.danielschiavo.aplicacao.usuario.mapper.UsuarioMapper;
import br.com.danielschiavo.dominio.categoria.RepositorioDeCategoria;
import br.com.danielschiavo.dominio.usuario.RepositorioDeUsuario;

public class CadastrarCategoria {

	private RepositorioDeCategoria repositorioDeUsuario;
	
	private CriptografiaSenha criptografiaSenha;
	
	private Token token;
	
	private UsuarioMapper usuarioMapper;
	
	public AutenticarUsuario(RepositorioDeUsuario repositorioDeUsuario, CriptografiaSenha criptografiaSenha, Token token) {
		this.criptografiaSenha = criptografiaSenha;
		this.repositorioDeUsuario = repositorioDeUsuario;
		this.token = token;
		this.usuarioMapper = Mappers.getMapper(UsuarioMapper.class);
	}
	
}
