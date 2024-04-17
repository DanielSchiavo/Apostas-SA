CREATE TABLE eventos_partidas (
	evento_id CHAR(16),
	partida_numero SMALLINT,
	mapa_id CHAR(16),
	equipe_evento_vencedora_id CHAR(16),
	equipe_evento_perdedora_id CHAR(16),
	FOREIGN KEY (evento_id)
		REFERENCES eventos (id),
	FOREIGN KEY (mapa_id)
		REFERENCES mapas (id),
	FOREIGN KEY (equipe_evento_vencedora_id)
		REFERENCES eventos_equipes (id),
	FOREIGN KEY (equipe_evento_perdedora_id)
		REFERENCES eventos_equipes (id)
);