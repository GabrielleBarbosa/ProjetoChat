create table Usuario
(
	codUsuario int primary key not null,
	nome varchar(50) not null
)


create table Sala
(
	codSala int primary key not null,
	nome varchar(50) not null,
	tema varchar (25) not null,
	qtdMax int not null,
	codUsuario int not null,
	constraint fkUsuario foreign key(codUsuario) references Usuario(codUsuario)
)