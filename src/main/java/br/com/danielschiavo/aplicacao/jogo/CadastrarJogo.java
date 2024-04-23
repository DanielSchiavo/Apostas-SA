package br.com.danielschiavo.aplicacao.jogo;

import br.com.danielschiavo.aplicacao.CriptografiaSenha;
import br.com.danielschiavo.dominio.GeradorUUID;
import br.com.danielschiavo.dominio.usuario.RepositorioDeUsuario;

public class CadastrarJogo {

	private RepositorioDeUsuario repositorioDeUsuario;
	
	private CriptografiaSenha criptografiaSenha;
	
	private GeradorUUID geradorUuid;
	
	public CadastrarJogo(RepositorioDeUsuario repositorioDeUsuario, CriptografiaSenha criptografiaSenha, GeradorUUID geradorUuid) {
		this.repositorioDeUsuario = repositorioDeUsuario;
		this.criptografiaSenha = criptografiaSenha;
		this.geradorUuid = geradorUuid;
	}
	
	public void executa(CadastrarJogoDTO cadastrarJogoDTO) {
		
	}
	
}
