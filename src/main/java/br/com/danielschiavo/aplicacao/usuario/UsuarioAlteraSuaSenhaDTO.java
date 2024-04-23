package br.com.danielschiavo.aplicacao.usuario;

import br.com.danielschiavo.aplicacao.usuario.validation.RegrasComumDeValidacao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioAlteraSuaSenhaDTO {
	
	@NotNull(message = "A senha atual não pode ser nula")
	@Size(min = RegrasComumDeValidacao.MIN_PASSWORD_LENGTH, message = RegrasComumDeValidacao.MESSAGE_MIN_PASSWORD_LENGTH)
	private String senhaAtual;
	
	@NotNull(message = "A senha nova não pode ser nula")
	@Size(min = RegrasComumDeValidacao.MIN_PASSWORD_LENGTH, message = RegrasComumDeValidacao.MESSAGE_MIN_PASSWORD_LENGTH)
	private String senhaNova;
	
}
