package br.com.danielschiavo.dominio.usuario;

import br.com.danielschiavo.dominio.ValidacaoException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RG {
	private String numero;

	public RG(String numero) throws ValidacaoException {
		if (numero == null || 
				!numero.matches("^\\d{6,10}$")) {
			System.out.println(numero);
			throw new ValidacaoException("RG invalido!");
		}
		this.numero = numero;
	}
}
