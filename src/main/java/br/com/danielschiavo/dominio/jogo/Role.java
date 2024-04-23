package br.com.danielschiavo.dominio.jogo;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

	private UUID id;
	
	private String nome;
	
	private String descricao;
	
	private String icone;
	
	private String jogoId;
}
