CREATE TABLE usuarios (
	id CHAR(16),
	cpf CHAR(14) NOT NULL UNIQUE,
	nome VARCHAR(50) NOT NULL,
	sobrenome VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	ddd CHAR(2) NOT NULL,
	telefone CHAR(9) NOT NULL,
	senha VARCHAR(100) NOT NULL,
	foto VARCHAR(100) NOT NULL,
	data_nascimento DATE NOT NULL,
	saldo NUMERIC(10,2) NOT NULL,
	instagram VARCHAR(50),
	facebook VARCHAR(50),
	twitter VARCHAR(50),
	frase VARCHAR(200),
	PRIMARY KEY (id)
);