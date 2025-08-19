create table produto(
    id Log not null primary key,
    titulo varchar(50) not null,
    autor varchar(50) not null,
    preco numeric(6, 2),
    categoria varchar
);