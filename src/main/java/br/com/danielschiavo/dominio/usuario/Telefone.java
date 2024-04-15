package br.com.danielschiavo.dominio.usuario;

import br.com.danielschiavo.infra.ValidacaoException;

public class Telefone {
	
	private String ddd;
	private String numero;

	public Telefone(String ddd, String numero) {
		if (ddd == null || numero == null) {
			throw new ValidacaoException("DDD e Numero sao obrigatorios!");
		}

		if (!ddd.matches("\\d{2}")) {
			throw new ValidacaoException("DDD invalido!");
		}
		
		if (!numero.matches("\\d{8}|\\d{9}")) {
			throw new ValidacaoException("Numero invalido!");
		}
		this.ddd = ddd;
		this.numero = numero;
	}

	public String getDdd() {
		return ddd;
	}
	
	public String getNumero() {
		return numero;
	}

}