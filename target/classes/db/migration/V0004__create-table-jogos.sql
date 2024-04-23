CREATE TABLE jogos (
	id CHAR(36),
	nome VARCHAR(50) NOT NULL,
	icone VARCHAR(100) NOT NULL,
	descricao TEXT NOT NULL,
	imagem VARCHAR(100) NOT NULL,
	numero_eventos BIGSERIAL NOT NULL,
	sub_categoria_id CHAR(36) NOT NULL,
	ativo BOOLEAN NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (sub_categoria_id)
		REFERENCES sub_categorias (id)
);