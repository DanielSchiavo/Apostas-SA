package br.com.danielschiavo.dominio.usuario.exceptions;

public class AutenticacaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public AutenticacaoException() {
		super("Email ou senha incorretos!");
	}
	
	public AutenticacaoException(String msg) {
		super(msg);
	}
}
