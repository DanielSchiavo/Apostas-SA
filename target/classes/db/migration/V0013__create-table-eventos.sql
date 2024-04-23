CREATE TABLE eventos (
	id CHAR(36),
	nome VARCHAR(100) NOT NULL UNIQUE,
	jogo_id CHAR(36) NOT NULL,
	equipe_evento_a_id CHAR(36) NOT NULL,
	equipe_evento_b_id CHAR(36) NOT NULL,
	formato VARCHAR(30) NOT NULL,
	status VARCHAR(30) NOT NULL,
	imagem VARCHAR(100) NOT NULL,
	empate BOOLEAN,
	equipe_evento_vencedora_id CHAR(36),
	equipe_evento_perdedora_id CHAR(36),
	data_e_hora_inicio_evento TIMESTAMP NOT NULL,
	data_e_hora_fim_evento TIMESTAMP,
	data_e_hora_criacao_evento TIMESTAMP NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (jogo_id)
		REFERENCES jogos (id),
	FOREIGN KEY (equipe_evento_a_id)
		REFERENCES eventos_equipes (id),
	FOREIGN KEY (equipe_evento_b_id)
		REFERENCES eventos_equipes (id),
	FOREIGN KEY (equipe_evento_vencedora_id)
		REFERENCES eventos_equipes (id),
	FOREIGN KEY (equipe_evento_perdedora_id)
		REFERENCES eventos_equipes (id)
);