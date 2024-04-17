package br.com.danielschiavo.dominio.usuario;

public class UsuarioNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(CPF cpf) {
		super("Usuario nao encontrado com CPF: " + cpf.getNumero());
	}

}
