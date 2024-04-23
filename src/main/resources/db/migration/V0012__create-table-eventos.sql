CREATE TABLE eventos (
	id CHAR(16),
	nome VARCHAR(100),
	jogo_id CHAR(16),
	equipe_evento_a_id CHAR(16),
	equipe_evento_b_id CHAR(16),
	formato VARCHAR(30),
	status VARCHAR(30),
	imagem VARCHAR(100),
	empate BOOLEAN,
	equipe_evento_vencedora_id CHAR(16),
	equipe_evento_perdedora_id CHAR(16),
	data_e_hora_inicio_evento TIMESTAMP NOT NULL,
	data_e_hora_fim_evento TIMESTAMP,
	data_e_hora_criacao_evento TIMESTAMP NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (jogo_id)
		REFERENCES jogos (id),
	FOREIGN KEY (equipe_evento_a_id)
		REFERENCES eventos_equipes (id),
	FOREIGN KEY (equipe_evento_b_id)
		REFERENCES eventos_equipes (id)
	FOREIGN KEY (equipe_evento_vencedora_id)
		REFERENCES eventos_equipes (id)
	FOREIGN KEY (equipe_evento_perdedora_id)
		REFERENCES eventos_equipes (id)
);