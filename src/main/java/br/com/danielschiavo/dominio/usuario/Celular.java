package br.com.danielschiavo.dominio.usuario;

import br.com.danielschiavo.dominio.ValidacaoException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Celular {
	
	private String ddd;
	
	private String numero;

	public Celular(String ddd, String numero) throws ValidacaoException {
		if (ddd == null || numero == null) {
			throw new ValidacaoException("DDD e Numero sao obrigatorios!");
		}

		if (!ddd.matches("\\d{2}")) {
			throw new ValidacaoException("DDD invalido!");
		}
		
		if (!numero.matches("\\d{9}")) {
			throw new ValidacaoException("Numero invalido!");
		}
		this.ddd = ddd;
		this.numero = numero;
	}
	
	public void setDdd(String ddd) throws ValidacaoException {
		if (!ddd.matches("\\d{2}")) {
			throw new ValidacaoException("DDD invalido!");
		}
		this.ddd = ddd;
	}
	
	public void setNumero(String numero) throws ValidacaoException {
		if (!numero.matches("\\d{9}")) {
			throw new ValidacaoException("O número do celular deve conter 9 digitos");
		}
		if (!numero.matches("9\\d{8}")) {
			throw new ValidacaoException("O número do celular deve iniciar com o número 9");
		}
		this.numero = numero;
	}
}