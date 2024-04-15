package br.com.danielschiavo.dominio.usuario.aposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.danielschiavo.dominio.evento.Evento;
import br.com.danielschiavo.dominio.usuario.Usuario;

public class Aposta {

	private Usuario usuario;
	private Evento evento;
	private TipoAposta tipoAposta;
	private BigDecimal valorApostado;
	private Double chances;
	private StatusAposta status;
	private ResultadoAposta resultado;
	private LocalDateTime dataEHora;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public TipoAposta getTipoAposta() {
		return tipoAposta;
	}
	public void setTipoAposta(TipoAposta tipoAposta) {
		this.tipoAposta = tipoAposta;
	}
	public BigDecimal getValorApostado() {
		return valorApostado;
	}
	public void setValorApostado(BigDecimal valorApostado) {
		this.valorApostado = valorApostado;
	}
	public Double getChances() {
		return chances;
	}
	public void setChances(Double chances) {
		this.chances = chances;
	}
	public StatusAposta getStatus() {
		return status;
	}
	public void setStatus(StatusAposta status) {
		this.status = status;
	}
	public ResultadoAposta getResultado() {
		return resultado;
	}
	public void setResultado(ResultadoAposta resultado) {
		this.resultado = resultado;
	}
	public LocalDateTime getDataEHora() {
		return dataEHora;
	}
	public void setDataEHora(LocalDateTime dataEHora) {
		this.dataEHora = dataEHora;
	}
	
}
