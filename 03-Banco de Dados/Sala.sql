create table Sala
(
 codSala int primary key,
 nome varchar(40)not null,
 qtdMax int not null
)

insert into Sala values(1,'Lolzinho',3)
insert into Sala values(2,'Chat do MagMag',40)
insert into Sala values(3,'Criaturas do Mau',10)
insert into Sala values(4,'Sala pra Dormir',20)
insert into Sala values(5,'Férias',200)

select * from Sala