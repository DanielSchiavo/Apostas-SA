CREATE TABLE usuarios_transacoes_depositos (
	usuario_id CHAR(36) NOT NULL,
	valor NUMERIC (10,7) NOT NULL,
	moeda_id CHAR(5) NOT NULL,
	saldo_atual NUMERIC (10,7) NOT NULL,
	data_e_hora TIMESTAMP NOT NULL,
	FOREIGN KEY (usuario_id)
		REFERENCES usuarios (id),
	FOREIGN KEY (moeda_id)
		REFERENCES moedas (id)
);