package br.com.danielschiavo.aplicacao.usuario;

import br.com.danielschiavo.dominio.usuario.RepositorioDeUsuario;
import br.com.danielschiavo.dominio.usuario.Usuario;

public class CadastrarUsuario {
	
	private RepositorioDeUsuario repositorioDeUsuario;
	
	public CadastrarUsuario(RepositorioDeUsuario repositorioDeUsuario) {
		this.repositorioDeUsuario = repositorioDeUsuario;
	}

	public void executa(CadastrarUsuarioDTO cadastrarUsuarioDTO) {
		Usuario usuario = cadastrarUsuarioDTO.criarUsuario();
		repositorioDeUsuario.cadastrarUsuario(usuario);
	}
	
}
