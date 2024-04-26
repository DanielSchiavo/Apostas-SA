package br.com.danielschiavo.aplicacao.admin.jogo;

import br.com.danielschiavo.aplicacao.CriptografiaSenha;
import br.com.danielschiavo.dominio.GeradorUUID;
import br.com.danielschiavo.dominio.usuario.RepositorioDeUsuarioUser;

public class CadastrarJogo {

	private RepositorioDeUsuarioUser repositorioDeUsuario;
	
	private CriptografiaSenha criptografiaSenha;
	
	private GeradorUUID geradorUuid;
	
	public CadastrarJogo(RepositorioDeUsuarioUser repositorioDeUsuario, CriptografiaSenha criptografiaSenha, GeradorUUID geradorUuid) {
		this.repositorioDeUsuario = repositorioDeUsuario;
		this.criptografiaSenha = criptografiaSenha;
		this.geradorUuid = geradorUuid;
	}
	
	public void executa(CadastrarJogoDTO cadastrarJogoDTO) {
		
	}
	
}
