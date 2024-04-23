CREATE TABLE sub_categorias (
	id CHAR(36),
	nome VARCHAR(50) NOT NULL,
	imagem VARCHAR(100) NOT NULL,
	categoria_id CHAR(36) NOT NULL,
	ordem SMALLINT NOT NULL,
	sub_categoria_id CHAR(36),
	PRIMARY KEY (id),
	FOREIGN KEY (categoria_id)
		REFERENCES categorias (id),
	FOREIGN KEY (sub_categoria_id)
		REFERENCES sub_categorias (id)
);