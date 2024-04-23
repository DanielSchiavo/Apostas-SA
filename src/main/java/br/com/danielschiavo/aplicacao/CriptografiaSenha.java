package br.com.danielschiavo.aplicacao;

import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;

public interface CriptografiaSenha {

	String criptografarSenha(String senha);
	
	void verificarSenha(String senhaCriptografada, String senhaSemCriptografia) throws AutenticacaoException ;
}
