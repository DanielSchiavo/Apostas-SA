package br.com.danielschiavo.infra.util;

import java.util.UUID;

import br.com.danielschiavo.dominio.GeradorUUID;

public class GeradorUUIDImpl implements GeradorUUID {

	@Override
	public UUID gerarUUID() {
		return UUID.randomUUID();
	}

}
