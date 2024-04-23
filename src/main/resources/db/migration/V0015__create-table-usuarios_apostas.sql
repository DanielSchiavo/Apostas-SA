CREATE TABLE usuarios_apostas (
	id CHAR(36),
	usuario_id CHAR(36) NOT NULL,
	evento_id CHAR(36) NOT NULL,
	equipe_evento_apostada_id CHAR(36) NOT NULL,
	tipo_aposta VARCHAR(30) NOT NULL,
	valor_apostado NUMERIC(10,2) NOT NULL,
	chances_no_momento_da_aposta NUMERIC(6,4) NOT NULL,
	chances_no_encerramento_da_aposta NUMERIC(6,4),
	status VARCHAR(30) NOT NULL,
	data_e_hora TIMESTAMP NOT NULL,
	PRIMARY KEY (id)
);