package br.com.danielschiavo.dominio.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoSaque {

	private UUID usuarioId;
	private BigDecimal valor;
	private LocalDateTime dataEHora;
}
