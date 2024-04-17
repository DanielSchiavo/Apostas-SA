CREATE TABLE transacoes (
	usuario_id CHAR(16) NOT NULL,
	tipo_transacao VARCHAR(40) NOT NULL,
	valor NUMERIC (10,2) NOT NULL,
	data_e_hora TIMESTAMP NOT NULL
	FOREIGN KEY (usuario_id)
		REFERENCES usuarios (id)
);