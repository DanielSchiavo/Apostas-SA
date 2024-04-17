package br.com.danielschiavo.dominio.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.danielschiavo.dominio.usuario.Usuario;

public class Transacao {

	private UUID usuarioId;
	private TipoTransacao tipo;
	private BigDecimal valor;
	private LocalDateTime dataEHora;
}
