package br.com.danielschiavo.dominio.usuario;

import br.com.danielschiavo.dominio.ValidacaoException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Email {
	
	private String endereco;

	public Email(String endereco) throws ValidacaoException {
		if (endereco == null || 
				!endereco.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			throw new ValidacaoException("E-mail invalido!");
		}

		this.endereco = endereco;
	}
}