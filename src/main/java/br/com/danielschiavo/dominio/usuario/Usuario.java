package br.com.danielschiavo.dominio.usuario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.danielschiavo.dominio.GeradorUUID;
import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.aposta.Aposta;
import br.com.danielschiavo.dominio.transacao.TransacaoSaque;
import br.com.danielschiavo.dominio.usuario.perfiljogador.PerfilJogador;
import br.com.danielschiavo.dominio.usuario.perfiljogador.perfiljogo.PerfilJogo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_EMPTY)
public class Usuario {
	
	private UUID id;
	
	@Getter(value = AccessLevel.NONE)
	private CPF cpf;
	
	@Getter(value = AccessLevel.NONE)
	private RG rg;
	
	private String imagemComprovanteIdentidadeFrente;
	
	private String imagemComprovanteIdentidadeVerso;
	
	private String nome;
	
	private String sobrenome;
	
	@Getter(value = AccessLevel.NONE)
	private Email email;
	
	private Celular celular;
	
	private String senha;
	
	private LocalDate dataNascimento;
	
	private LocalDateTime dataCriacaoConta;
	
	private BigDecimal saldo;
	
	private Boolean confirmouIdentidade;
	
	private PerfilJogador perfilJogador;
	
	@Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
	private List<Aposta> apostas = new ArrayList<>();
	
	@Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
	private List<TransacaoSaque> transacoes = new ArrayList<>();
	
	@Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
	private List<PerfilJogo> perfisJogos = new ArrayList<>();
	
	public String getCpf() {
		return cpf.getNumero();
	}
	
	public String getRg() {
		if (this.rg != null) {
			return rg.getNumero();
		}
		return null;
	}

	public String getEmail() {
		return email.getEndereco();
	}
	
	public List<Aposta> getApostas() {
		return Collections.unmodifiableList(this.apostas);
	}
	
	public void adicionarAposta(Aposta aposta) {
		this.apostas.add(aposta);
	}
	
	public List<TransacaoSaque> getTransacoes() {
		return Collections.unmodifiableList(this.transacoes);
	}
	
	public void adicionarTransacao(TransacaoSaque transacao) {
		this.transacoes.add(transacao);
	}

	public List<PerfilJogo> getPerfisJogos() {
		return Collections.unmodifiableList(this.perfisJogos);
	}

	public void adicionarPerfilJogo(PerfilJogo perfilJogo) {
		this.perfisJogos.add(perfilJogo);
	}

	
	public static UsuarioBuilder builder(GeradorUUID geradorUuid) {
		return new UsuarioBuilder(geradorUuid);
	}
	
	public static UsuarioBuilder builder() {
		return new UsuarioBuilder();
	}
	
	public static class UsuarioBuilder {
		
		private Usuario usuario;
		
		private GeradorUUID geradorUuid;
		
		public UsuarioBuilder(GeradorUUID geradorUuid) {
			this.geradorUuid = geradorUuid;
			this.usuario = new Usuario();
			this.usuario.setId(this.geradorUuid.gerarUUID());
		}
		
		public UsuarioBuilder() {
			this.usuario = new Usuario();
		}
		
		public UsuarioBuilder id(String id) {
			this.usuario.setId(UUID.fromString(id));
			return this;
		}
		
		public UsuarioBuilder cpf(String cpf) throws ValidacaoException {
			this.usuario.setCpf(new CPF(cpf));
			return this;
		}
		
		public UsuarioBuilder rg(String rg) throws ValidacaoException {
			this.usuario.setRg(new RG(rg));
			return this;
		}
		
		public UsuarioBuilder nome(String nome) {
			this.usuario.setNome(nome);
			return this;
		}
		
		public UsuarioBuilder sobrenome(String nome) {
			this.usuario.setSobrenome(nome);
			return this;
		}
		
		public UsuarioBuilder email(String email) throws ValidacaoException {
			this.usuario.setEmail(new Email(email));
			return this;
		}
		
		public UsuarioBuilder celular(String ddd, String numero) throws ValidacaoException {
			this.usuario.setCelular(new Celular(ddd, numero));
			return this;
		}
		
		public UsuarioBuilder celular(Celular telefone) {
			this.usuario.setCelular(telefone);
			return this;
		}
		
		public UsuarioBuilder senha(String senha) {
			this.usuario.setSenha(senha);
			return this;
		}
		
		public UsuarioBuilder dataNascimento(LocalDate data) {
			this.usuario.setDataNascimento(data);
			return this;
		}
		
		public UsuarioBuilder dataCriacaoConta(LocalDateTime data) {
			this.usuario.setDataCriacaoConta(data);
			return this;
		}
		
		public UsuarioBuilder saldo(BigDecimal saldo) {
			this.usuario.setSaldo(saldo);
			return this;
		}
		
		public UsuarioBuilder saldo(Double saldo) {
			this.usuario.setSaldo(BigDecimal.valueOf(saldo));
			return this;
		}
		
		public UsuarioBuilder confirmouIdentidade(Boolean confirmou) {
			this.usuario.setConfirmouIdentidade(confirmou);
			return this;
		}
		
		public UsuarioBuilder perfilJogador(PerfilJogador perfilJogador) {
			this.usuario.setPerfilJogador(perfilJogador);
			return this;
		}
		
		public Usuario build() throws ValidacaoException {
			if (this.usuario != null) {
				Usuario copiaUsuario = new Usuario();
				copiaUsuario.setId(this.usuario.getId());
				if (this.usuario.cpf != null) {
					copiaUsuario.setCpf(new CPF(this.usuario.getCpf()));
				}
				if (this.usuario.rg != null) {
					copiaUsuario.setRg(new RG(this.usuario.getRg()));
				}
				copiaUsuario.setNome(this.usuario.getNome());
				copiaUsuario.setSobrenome(this.usuario.getSobrenome());
				if (this.usuario.email != null) {
					copiaUsuario.setEmail(new Email(this.usuario.getEmail()));
				}
				if (this.usuario.celular != null) {
					copiaUsuario.setCelular(this.usuario.getCelular());
				}
				copiaUsuario.setSenha(this.usuario.getSenha());
				copiaUsuario.setDataNascimento(this.usuario.getDataNascimento());
				copiaUsuario.setDataCriacaoConta(this.usuario.getDataCriacaoConta());
				copiaUsuario.setSaldo(this.usuario.getSaldo());
				copiaUsuario.setConfirmouIdentidade(this.usuario.getConfirmouIdentidade());
				copiaUsuario.setPerfilJogador(this.usuario.getPerfilJogador());
				this.usuario = null;
				return copiaUsuario;
			}
			return null;
		}
	}
}
