package br.com.danielschiavo.dominio.jogo;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import br.com.danielschiavo.dominio.categoria.subcategoria.SubCategoria;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Jogo {
	
	private UUID id;
	
	private String nome;
	
	private String icone;
	
	private String descricao;
	
	private String imagem;
	
	private Boolean ativo;
	
	private Long numeroEventos;
	
	private SubCategoria subCategoria;
	
	@Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
	private List<Role> roles;
	
	@Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
	private List<Mapa> mapas;
	
	public List<Role> getRoles() {
		return Collections.unmodifiableList(this.roles);
	}

	public void adicionarRole(Role role) {
		this.roles.add(role);
	}
	
	public void removerRole(Role role) {
		this.roles.remove(role);
	}

	public List<Mapa> getMapas() {
		return Collections.unmodifiableList(this.mapas);
	}

	public void adicionarMapa(Mapa mapa) {
		this.mapas.add(mapa);
	}
	
	public void removerMapa(Mapa mapa) {
		this.mapas.remove(mapa);
	}
	
	
}
