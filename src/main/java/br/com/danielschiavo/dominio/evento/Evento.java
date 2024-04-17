package br.com.danielschiavo.dominio.evento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import br.com.danielschiavo.dominio.GeradorUUID;
import br.com.danielschiavo.dominio.equipe.Equipe;
import br.com.danielschiavo.dominio.jogo.Jogo;

public class Evento {

	private UUID id;
	
	private String nome;
	
	private Jogo jogo;
	
	private EquipeEvento equipeEventoA;
	
	private EquipeEvento equipeEventoB;
	
	private Formato formato;
	
	private StatusEvento status;
	
	private String imagem;
	
	private Boolean empate;
	
	private EquipeEvento equipeEventoVencedora;
	
	private EquipeEvento equipeEventoPerdedora;
	
	private LocalDateTime dataEHoraInicioEvento;
	
	private LocalDateTime dataEHoraFimEvento;
	
	private LocalDateTime dataEHoraCriacaoEvento;
	
	private List<PartidasEvento> partidasEvento = new ArrayList<>();
	
	public Evento(String id) {
		this.id = UUID.fromString(id);
	}
	
	public Evento() {
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

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public EquipeEvento getEquipeEventoA() {
		return equipeEventoA;
	}

	public void setEquipeEventoA(EquipeEvento equipeA) {
		this.equipeEventoA = equipeA;
	}

	public EquipeEvento getEquipeEventoB() {
		return equipeEventoB;
	}

	public void setEquipeEventoB(EquipeEvento equipeB) {
		this.equipeEventoB = equipeB;
	}

	public Formato getFormato() {
		return formato;
	}

	public void setFormato(Formato formato) {
		this.formato = formato;
	}

	public StatusEvento getStatus() {
		return status;
	}

	public void setStatus(StatusEvento status) {
		this.status = status;
	}

	public EquipeEvento getEquipeEventoVencedora() {
		return equipeEventoVencedora;
	}

	public void setEquipeEventoVencedora(EquipeEvento equipeVencedora) {
		this.equipeEventoVencedora = equipeVencedora;
	}

	public EquipeEvento getEquipeEventoPerdedora() {
		return equipeEventoPerdedora;
	}

	public void setEquipeEventoPerdedora(EquipeEvento equipePerdedora) {
		this.equipeEventoPerdedora = equipePerdedora;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Boolean getEmpate() {
		return empate;
	}

	public void setEmpate(Boolean empate) {
		this.empate = empate;
	}

	public LocalDateTime getDataEHoraInicioEvento() {
		return dataEHoraInicioEvento;
	}

	public void setDataEHoraInicioEvento(LocalDateTime dataEHoraInicioEvento) {
		this.dataEHoraInicioEvento = dataEHoraInicioEvento;
	}

	public LocalDateTime getDataEHoraFimEvento() {
		return dataEHoraFimEvento;
	}

	public void setDataEHoraFimEvento(LocalDateTime dataEHoraFimEvento) {
		this.dataEHoraFimEvento = dataEHoraFimEvento;
	}

	public LocalDateTime getDataEHoraCriacaoEvento() {
		return dataEHoraCriacaoEvento;
	}

	public void setDataEHoraCriacaoEvento(LocalDateTime dataEHoraCriacaoEvento) {
		this.dataEHoraCriacaoEvento = dataEHoraCriacaoEvento;
	}

	public List<PartidasEvento> getPartidasEvento() {
		return Collections.unmodifiableList(this.partidasEvento);
	}
	
	public void adicionarPartidaEvento(PartidasEvento partida) {
		this.partidasEvento.add(partida);
	}
	
	public void removerPartidaEvento(PartidasEvento partida) {
		this.partidasEvento.remove(partida);
	}

	
	public static EventoBuilder builder(GeradorUUID geradorUuid) {
		return new EventoBuilder(geradorUuid);
	}
	
	public static EventoBuilder builder() {
		return new EventoBuilder();
	}
	
	public static class EventoBuilder {
		
		private Evento evento;
		
		private GeradorUUID geradorUuid;
		
		public EventoBuilder(GeradorUUID geradorUuid) {
			this.geradorUuid = geradorUuid;
			this.evento = new Evento();
			this.evento.setId(this.geradorUuid.gerarUUID());
		}
		
		public EventoBuilder() {
			this.evento = new Evento();
		}
		
		public EventoBuilder id(String id) {
			this.evento.id = UUID.fromString(id);
			return this;
		}
		
		public EventoBuilder id(UUID id) {
			this.evento.id = id;
			return this;
		}
		
		public EventoBuilder nome(String nome) {
			this.evento.setNome(nome);
			return this;
		}
		
		public EventoBuilder jogo(Jogo jogo) {
			this.evento.setJogo(jogo);
			return this;
		}
		
		public EventoBuilder equipeEventoA(Equipe equipe) {
			EquipeEvento equipeEvento = new EquipeEvento(geradorUuid, equipe, this.evento.getJogo());
			this.evento.setEquipeEventoA(equipeEvento);
			return this;
		}
		
		public EventoBuilder equipeEventoB(Equipe equipe) {
			EquipeEvento equipeEvento = new EquipeEvento(geradorUuid, equipe, this.evento.getJogo());
			this.evento.setEquipeEventoB(equipeEvento);
			return this;
		}
		
		public EventoBuilder formato(Formato formato) {
			this.evento.setFormato(formato);
			return this;
		}
		
		public EventoBuilder status(StatusEvento statusEvento) {
			this.evento.setStatus(statusEvento);
			return this;
		}
		
		public EventoBuilder imagem(String imagem) {
			this.evento.setImagem(imagem);
			return this;
		}
		
		public EventoBuilder dataEHoraInicioEvento(LocalDateTime time) {
			this.evento.setDataEHoraInicioEvento(time);
			return this;
		}
		
		public EventoBuilder dataEHoraFimEvento(LocalDateTime time) {
			this.evento.setDataEHoraFimEvento(time);
			return this;
		}
		
		public EventoBuilder dataEHoraCriacaoEvento(LocalDateTime time) {
			this.evento.setDataEHoraCriacaoEvento(time);
			return this;
		}
		
		public Evento build() {
			if (this.evento != null) {
				Evento copiaEvento = new Evento();
				copiaEvento.setId(this.evento.getId());
				copiaEvento.setNome(this.evento.getNome());
				copiaEvento.setJogo(this.evento.getJogo());
				copiaEvento.setEquipeEventoA(this.evento.getEquipeEventoA());
				copiaEvento.setEquipeEventoB(this.evento.getEquipeEventoB());
				copiaEvento.setFormato(this.evento.getFormato());
				copiaEvento.setStatus(this.evento.getStatus());
				copiaEvento.setImagem(this.evento.getImagem());
				copiaEvento.setDataEHoraInicioEvento(this.evento.getDataEHoraInicioEvento());
				copiaEvento.setDataEHoraFimEvento(this.evento.getDataEHoraFimEvento());
				copiaEvento.setDataEHoraCriacaoEvento(this.evento.getDataEHoraCriacaoEvento());
				this.evento = null;
				return copiaEvento;
			}
			return null;
		}
	}

}
