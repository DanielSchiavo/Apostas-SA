CREATE TABLE jogos_mapas (
	id CHAR(36),
	nome VARCHAR(50) NOT NULL,
	imagem VARCHAR(100) NOT NULL,
	jogo_id CHAR(36) NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (jogo_id)
		REFERENCES jogos (id)
);