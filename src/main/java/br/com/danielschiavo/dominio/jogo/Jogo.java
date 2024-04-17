package br.com.danielschiavo.dominio.jogo;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Jogo {
	
	private UUID id;
	
	private String nome;
	
	private String descricao;
	
	private String imagem;
	
	private SubCategoria subCategoria;
	
	private Boolean ativo;
	
	private List<String> roles;
	
	private List<Mapa> mapas;
	
	public Jogo(String id) {
		this.id = UUID.fromString(id);
	}
	
	public Jogo() {
		
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public SubCategoria getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(SubCategoria subCategoria) {
		this.subCategoria = subCategoria;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public List<String> getRoles() {
		return Collections.unmodifiableList(this.roles);
	}

	public void adicionarRole(String role) {
		this.roles.add(role);
	}
	
	public void removerRole(String role) {
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
