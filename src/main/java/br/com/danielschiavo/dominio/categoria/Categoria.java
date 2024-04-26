package br.com.danielschiavo.dominio.categoria;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Categoria {
	
	private UUID id;

	private String nome;
	
	private String icone;
	
	private LocalDateTime dataCriacao;
	
	private UUID criadoPor;
	
	private LocalDateTime dataUltimaAlteracao;
	
	private UUID alteradorPor;
	
	private Long numeroApostas;
	
	private Long numeroEventos;
	
	private BigDecimal totalReaisMovimentado;
	
	private BigDecimal totalDolarMovimentado;
	
	private BigDecimal totalEuroMovimentado;
	
	private BigDecimal totalBitcoinMovimentado;
	
	private Boolean ativo;
	
}
