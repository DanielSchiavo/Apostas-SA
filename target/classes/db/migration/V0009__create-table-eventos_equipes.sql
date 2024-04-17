CREATE TABLE eventos_equipes (
	id CHAR(16)
	equipe_id CHAR(16) NOT NULL,
	sigla VARCHAR (5) NOT NULL UNIQUE,
	nome VARCHAR(50) NOT NULL,
	imagem VARCHAR(100) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (equipe_id)
		REFERENCES equipe_id (id)
);