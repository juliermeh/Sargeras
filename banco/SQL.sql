CREATE TABLE Produto(
	codigo SERIAL,
	descricao VARCHAR[20],
	preco FLOAT,
	PRIMARY KEY (codigo)
);

CREATE TABLE ItemPedido(
	id SERIAL,
	quant INTEGER,
	produto INTEGER,
	PRIMARY KEY(id),
	FOREIGN KEY(produto) REFERENCES Produto(codigo)
);

CREATE TABLE Pedido(
	codigo SERIAL,
	data DATE,
	itens INTEGER,
	PRIMARY KEY(codigo),
	FOREIGN KEY(itens) REFERENCES ItemPedido(id)
);