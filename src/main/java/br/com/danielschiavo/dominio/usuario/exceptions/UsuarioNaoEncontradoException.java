package br.com.danielschiavo.dominio.usuario.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(String id) {
		super("Usuario nao encontrado com ID: " + id);
	}

}
