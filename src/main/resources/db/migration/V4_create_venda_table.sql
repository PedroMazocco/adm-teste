create table venda(
	codigo BIGINT primary key AUTO_INCREMENT,
	codigo_cliente BIGINT NOT NULL,
	codigo_produto BIGINT NOT NULL,
	FOREIGN KEY (codigo_cliente) REFERENCES cliente(codigo),
	FOREIGN KEY (codigo_produto) REFERENCES produto(codigo)
);