package br.com.danielschiavo.aplicacao.usuario;

import java.time.LocalDate;

import br.com.danielschiavo.aplicacao.usuario.validation.IdadeMinima;
import br.com.danielschiavo.aplicacao.usuario.validation.RegrasComumDeValidacao;
import br.com.danielschiavo.dominio.usuario.CPF;
import br.com.danielschiavo.dominio.usuario.Celular;
import br.com.danielschiavo.dominio.usuario.Email;
import br.com.danielschiavo.dominio.usuario.RG;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioAlteraSeusDadosPessoaisDTO {
	
	private CPF cpf;
	
	private RG rg;
	
	@NotBlank(message =  RegrasComumDeValidacao.MESSAGE_NOME_NOTNULL)
	@Size(min = RegrasComumDeValidacao.MIN_NOME_LENGTH, message = RegrasComumDeValidacao.MESSAGE_MIN_NOME_LENGTH)
	private String nome;
	
	private String sobrenome;
	
	private Email email;
	
	private Celular celular;
	
	@IdadeMinima(value = RegrasComumDeValidacao.IDADE_MINIMA)
	private LocalDate dataNascimento;

}
