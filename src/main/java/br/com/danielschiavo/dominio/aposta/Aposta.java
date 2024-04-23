package br.com.danielschiavo.dominio.aposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.danielschiavo.dominio.evento.Evento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aposta {

	private UUID id;
	
	private String usuarioId;
	
	private Evento evento;
	
	private UUID equipeEventoApostadaId;
	
	private TipoAposta tipoAposta;
	
	private BigDecimal valorApostado;
	
	private BigDecimal chancesNoMomentoDaAposta;
	
	private BigDecimal chancesNoEncerramentoDaAposta;
	
	private StatusAposta status;
	
	private LocalDateTime dataEHora;
	
	
	public static ApostaBuilder builder() {
		return new ApostaBuilder();
	}
	
	public static class ApostaBuilder {
		
		private Aposta aposta;
		
		public ApostaBuilder usuarioId(String usuarioId) {
			this.aposta.setUsuarioId(usuarioId);
			return this;
		}
		
		public ApostaBuilder evento(Evento evento) {
			this.aposta.setEvento(evento);
			return this;
		}
		
		public ApostaBuilder equipeEventoApostadaId(String id) {
			this.aposta.setEquipeEventoApostadaId(UUID.fromString(id));
			return this;
		}
		
		public ApostaBuilder tipoAposta(TipoAposta tipo) {
			this.aposta.setTipoAposta(tipo);
			return this;
		}
		
		public ApostaBuilder valorApostado(BigDecimal valor) {
			this.aposta.setValorApostado(valor);
			return this;
		}
		
		public ApostaBuilder chancesNoMomentoDaAposta(Double chancesNoMomentoDaAposta) {
			this.aposta.setChancesNoMomentoDaAposta(BigDecimal.valueOf(chancesNoMomentoDaAposta));
			return this;
		}
		
		public ApostaBuilder chancesNoEncerramentoDaAposta(Double chancesNoEncerramentoDaAposta) {
			this.aposta.setChancesNoEncerramentoDaAposta(BigDecimal.valueOf(chancesNoEncerramentoDaAposta));
			return this;
		}
		
		public ApostaBuilder statusAposta(StatusAposta status) {
			this.aposta.setStatus(status);
			return this;
		}
		
		public ApostaBuilder dataEHora(LocalDateTime data) {
			this.aposta.setDataEHora(data);
			return this;
		}
		
		public Aposta build() {
			if (this.aposta != null) {
				Aposta copiaAposta = new Aposta();
				copiaAposta.setUsuarioId(this.aposta.getUsuarioId());
				copiaAposta.setEvento(this.aposta.getEvento());
				copiaAposta.setEquipeEventoApostadaId(this.aposta.getEquipeEventoApostadaId());
				copiaAposta.setTipoAposta(this.aposta.getTipoAposta());
				copiaAposta.setValorApostado(this.aposta.getValorApostado());
				copiaAposta.setChancesNoMomentoDaAposta(this.aposta.getChancesNoMomentoDaAposta());
				copiaAposta.setChancesNoEncerramentoDaAposta(this.aposta.getChancesNoEncerramentoDaAposta());
				copiaAposta.setStatus(this.aposta.getStatus());
				copiaAposta.setDataEHora(this.aposta.getDataEHora());
				this.aposta = null;
				return copiaAposta;
			}
			return null;
		}
	}
}
