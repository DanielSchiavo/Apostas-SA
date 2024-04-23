package br.com.danielschiavo.dominio.usuario;

import br.com.danielschiavo.dominio.ValidacaoException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CPF {

	private String numero;

	public CPF(String numero) throws ValidacaoException {
		if (numero == null || 
				!numero.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
			throw new ValidacaoException("CPF invalido!");
		}
		this.numero = numero;
	}
	
}