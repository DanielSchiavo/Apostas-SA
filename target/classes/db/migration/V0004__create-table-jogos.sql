CREATE TABLE jogos (
	id CHAR(16),
	nome VARCHAR(50) NOT NULL,
	descricao TEXT NOT NULL,
	imagem VARCHAR(100) NOT NULL,
	sub_categoria_id CHAR(16) NOT NULL,
	ativo BOOLEAN NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (sub_categoria_id)
		REFERENCES sub_categorias (id)
);