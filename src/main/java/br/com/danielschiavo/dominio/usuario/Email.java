package br.com.danielschiavo.dominio.usuario;

import br.com.danielschiavo.infra.ValidacaoException;

public class Email {
	
	private String endereco;

	public Email(String endereco) {
		if (endereco == null || 
				!endereco.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			throw new ValidacaoException("E-mail invalido!");
		}

		this.endereco = endereco;
	}
	
	public String getEndereco() {
		return endereco;
	}

}