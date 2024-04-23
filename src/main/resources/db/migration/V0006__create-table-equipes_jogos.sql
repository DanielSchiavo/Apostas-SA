CREATE TABLE equipes_jogos (
	equipe_id CHAR(16) NOT NULL,
	jogo_id CHAR(16) NOT NULL,
	vitorias INTEGER NOT NULL,
	empates INTEGER NOT NULL,
	derrotas INTEGER NOT NULL,
	FOREIGN KEY (equipe_id)
		REFERENCES equipes (id),
	FOREIGN KEY (jogo_id)
		REFERENCES jogos (id)
);