CREATE TABLE eventos_jogadores (
	evento_equipe_id CHAR(36) NOT NULL,
	nome_no_jogo VARCHAR(50) NOT NULL,
	foto VARCHAR(100) NOT NULL,
	usuario_id CHAR(36) NOT NULL,
	FOREIGN KEY (evento_equipe_id)
		REFERENCES eventos_equipes (id)
);