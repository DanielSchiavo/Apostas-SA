package br.com.danielschiavo.dominio.usuario.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.danielschiavo.dominio.usuario.Usuario;

public class Transacao {

	private Usuario usuario;
	private TipoTransacao tipo;
	private BigDecimal valor;
	private LocalDateTime dataEHora;
}
