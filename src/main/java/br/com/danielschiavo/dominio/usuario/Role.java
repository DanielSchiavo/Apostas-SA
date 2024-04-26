package br.com.danielschiavo.dominio.usuario;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
	
	private UUID usuarioId;
	
	private LocalDateTime dataAtribuicao;
	
	private NomeRole role;

}
