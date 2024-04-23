CREATE TABLE jogos_roles (
	id CHAR(36),
	nome VARCHAR(50) NOT NULL,
	descricao VARCHAR(200) NOT NULL,
	icone VARCHAR(100) NOT NULL,
	jogo_id CHAR(36),
	PRIMARY KEY (id),
	FOREIGN KEY (jogo_id)
		REFERENCES jogos (id) ON DELETE CASCADE
);