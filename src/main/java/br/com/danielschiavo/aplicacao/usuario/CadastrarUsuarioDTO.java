package br.com.danielschiavo.aplicacao.usuario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.danielschiavo.aplicacao.usuario.validation.RegrasComumDeValidacao;
import br.com.danielschiavo.aplicacao.usuario.validation.IdadeMinima;
import br.com.danielschiavo.dominio.usuario.CPF;
import br.com.danielschiavo.dominio.usuario.Celular;
import br.com.danielschiavo.dominio.usuario.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class CadastrarUsuarioDTO {
	
	private UUID id;
	
	private CPF cpf;
	
	@NotBlank(message =  RegrasComumDeValidacao.MESSAGE_NOME_NOTNULL)
	@Size(min = RegrasComumDeValidacao.MIN_NOME_LENGTH, message = RegrasComumDeValidacao.MESSAGE_MIN_NOME_LENGTH)
	private String nome;
	
	private String sobrenome;
	
	private Email email;
	
	private Celular celular;
	
	@NotNull(message = RegrasComumDeValidacao.MESSAGE_PASSWORD_NOTNULL)
	@Size(min = RegrasComumDeValidacao.MIN_PASSWORD_LENGTH, message = RegrasComumDeValidacao.MESSAGE_MIN_PASSWORD_LENGTH)
	private String senha;
	
	@IdadeMinima(value = RegrasComumDeValidacao.IDADE_MINIMA)
	private LocalDate dataNascimento;
	
	private LocalDateTime dataCriacaoConta;
	
	private BigDecimal saldo;
	
	
}
