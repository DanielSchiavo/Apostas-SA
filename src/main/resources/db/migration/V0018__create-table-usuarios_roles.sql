CREATE TABLE usuarios_roles (
	usuario_id CHAR(36) NOT NULL,
	data_atribuicao TIMESTAMP NOT NULL,
	role VARCHAR(20) NOT NULL,
	PRIMARY KEY (usuario_id),
	FOREIGN KEY (usuario_id)
		REFERENCES usuarios (id)
);