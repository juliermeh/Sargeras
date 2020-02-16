CREATE TABLE Produto(
	codigo SERIAL,
	descricao VARCHAR[20],
	preco FLOAT,
	PRIMARY KEY (codigo)
);

CREATE TABLE Carrinho(
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
	FOREIGN KEY(itens) REFERENCES Carrinho(id)
);

CREATE TABLE listapedidos(
    codigopedido INTEGER,
    idcarrinho INTEGER,
    FOREIGN KEY(codigopedido) REFERENCES Pedido(codigo),
    FOREIGN KEY(idcarrinho) REFERENCES Carrinho(id),
);