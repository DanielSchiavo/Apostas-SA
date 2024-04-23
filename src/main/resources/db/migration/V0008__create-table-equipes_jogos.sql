CREATE TABLE equipes_jogos (
	id CHAR(36),
	equipe_id CHAR(36) NOT NULL,
	jogo_id CHAR(36) NOT NULL,
	vitorias INTEGER NOT NULL,
	empates INTEGER NOT NULL,
	derrotas INTEGER NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (equipe_id)
		REFERENCES equipes (id),
	FOREIGN KEY (jogo_id)
		REFERENCES jogos (id)
);