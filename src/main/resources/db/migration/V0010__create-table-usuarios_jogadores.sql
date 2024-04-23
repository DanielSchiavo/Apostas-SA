CREATE TABLE usuarios_jogadores (
	usuario_id CHAR(36) NOT NULL,
	jogo_id CHAR(36) NOT NULL,
	nome_no_jogo VARCHAR(50) NOT NULL,
	equipe_jogo_id CHAR(36) NOT NULL,
	FOREIGN KEY (usuario_id)
		REFERENCES usuarios (id),
	FOREIGN KEY (jogo_id)
		REFERENCES jogos (id),
	FOREIGN KEY (equipe_jogo_id)
		REFERENCES equipes_jogos (id)
);