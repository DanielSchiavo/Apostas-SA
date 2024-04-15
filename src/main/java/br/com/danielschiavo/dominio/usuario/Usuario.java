package br.com.danielschiavo.dominio.usuario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.danielschiavo.dominio.usuario.aposta.Aposta;
import br.com.danielschiavo.dominio.usuario.transacao.Transacao;

public class Usuario {
	
	private CPF cpf;
	
	private String nome;
	
	private String sobrenome;
	
	private Email email;
	
	private Telefone telefone;
	
	private String senha;
	
	private String foto;
	
	private LocalDate dataNascimento;
	
	private LocalDateTime dataCriacaoConta;
	
	private BigDecimal saldo;
	
	private List<Aposta> apostas = new ArrayList<>();
	
	private List<Transacao> transacoes = new ArrayList<>();

	public Usuario(CPF cpf, String nome, String sobrenome, Email email, Telefone telefone, String senha, String foto,
			LocalDate dataNascimento, LocalDateTime dataCriacaoConta, BigDecimal saldo, List<Aposta> apostas,
			List<Transacao> transacoes) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.setTelefone(telefone);
		this.senha = senha;
		this.foto = foto;
		this.dataNascimento = dataNascimento;
		this.dataCriacaoConta = dataCriacaoConta;
		this.saldo = saldo;
		this.apostas = apostas;
		this.transacoes = transacoes;
	}

	public String getCpf() {
		return cpf.getNumero();
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String getEmail() {
		return email.getEndereco();
	}
	
	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public LocalDateTime getDataCriacaoConta() {
		return dataCriacaoConta;
	}
	
	public void setDataCriacaoConta(LocalDateTime dataCriacaoConta) {
		this.dataCriacaoConta = dataCriacaoConta;
	}
	
	public BigDecimal getSaldo() {
		return saldo;
	}
	
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public List<Aposta> getApostas() {
		return apostas;
	}
	
	public void setApostas(List<Aposta> apostas) {
		this.apostas = apostas;
	}
	
	public List<Transacao> getTransacoes() {
		return transacoes;
	}
	
	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}
	
	
}
