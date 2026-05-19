create database db_spotfy;
use db_spotfy;

create table tb_musica (
	titulo varchar(100) primary key,
    duracao integer,
    avaliacao decimal(5, 2)
);

insert into tb_musica values
	("Si", 320, 10.0),
	("Azul", 350, 9.8),
	("Exagerado", 295, 9.5);
    
select * from tb_musica;

