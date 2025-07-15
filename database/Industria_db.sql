create database industria_db;
use industria_db;


create table setores (
	id_setor int primary key auto_increment,
    nome_setor varchar (100) not null,
    responsavel varchar (100) not null
);

create table funcionarios (
	id_funcionario int primary key auto_increment,
    nome varchar (100) not null,
    cargo varchar (100) not null,
    id_setor int not null,
	foreign key (id_setor) references setores (id_setor)
		on delete cascade
        on update cascade
);

create table produtos (
	id_produto int primary key auto_increment,
    nome_produto varchar (100) not null,
    descricao varchar (100) not null
);

create table producao (
	id_producao int primary key auto_increment,
    nome_produto varchar (100) not null,
    id_produto int not null,
	foreign key (id_produto) references produtos (id_produto)
		on delete cascade
        on update cascade,
	id_funcionario int not null,
	foreign key (id_funcionario) references funcionarios (id_funcionario)
		on delete cascade
        on update cascade,
    data_producao varchar (100) not null,
    quantidade int not null
);

select * from setores;
INSERT INTO setores (nome_setor, responsavel) VALUES
('SETOR 1', 'João Silva'),
('SETOR 2', 'Maria Santos'),
('SETOR 3', 'Carlos Oliveira'),
('SETOR 4', 'Ana Paula'),
('SETOR 5', 'Lucas Ferreira');


select * from funcionarios;
INSERT INTO funcionarios (nome, cargo, id_setor) VALUES
('Carolina Giordano', 'Operadora de Máquinas', 1),
('João Pedro', 'Supervisor de Qualidade', 2),
('Mariana Lima', 'Analista de Logística', 3),
('Bruno Martins', 'Recrutador', 4),
('Fernanda Souza', 'Desenvolvedora', 5),
('Rafael Dias', 'Técnico de Produção', 1),
('Aline Rocha', 'Engenheira de Qualidade', 2),
('Gustavo Costa', 'Auxiliar de Logística', 3),
('Juliana Alves', 'Analista de RH', 4),
('Paulo Henrique', 'Suporte Técnico', 5);

select * from produtos;
INSERT INTO produtos (nome_produto, descricao) VALUES
('Parafuso', 'Parafuso de aço 2cm'),
('Porca', 'Porca metálica de 1cm'),
('Arruela', 'Arruela de pressão 1cm'),
('Chapa', 'Chapa metálica de 20x20cm'),
('Cabo USB', 'Cabo USB tipo C 1m'),
('Teclado', 'Teclado mecânico ABNT2'),
('Mouse', 'Mouse óptico sem fio'),
('Fonte ATX', 'Fonte 500W para desktop'),
('Cooler', 'Cooler de 12cm'),
('Adaptador HDMI', 'HDMI para VGA');

select * from producao;
INSERT INTO producao (nome_produto, id_produto, id_funcionario, data_producao, quantidade) VALUES
('Parafuso', 1, 1, '2025-06-24', 100),
('Porca', 2, 2, '2025-06-24', 150),
('Arruela', 3, 3, '2025-06-25', 200),
('Chapa', 4, 4, '2025-06-25', 80),
('Cabo USB', 5, 5, '2025-06-26', 90),
('Teclado', 6, 6, '2025-06-26', 60),
('Mouse', 7, 7, '2025-06-26', 70),
('Fonte ATX', 8, 8, '2025-06-27', 50),
('Cooler', 9, 9, '2025-06-27', 110),
('Adaptador HDMI', 10, 10, '2025-06-27', 95);

-- Listar todos os funcionários de um setor específico
select nome, cargo
from funcionarios 
where id_setor = (
	select id_setor
    from setores
    where nome_setor = 'Produção'
);

-- Mostrar todos os produtos produzidos em uma determinada data
select nome_produto, quantidade
from producao
where data_producao = '2025-06-25' ;

-- Contar quantos produtos cada funcionário já produziu
select func.nome, count(prod.id_producao) as total_produzido
from producao prod
join funcionarios func on prod.id_funcionario = func.id_funcionario
group by func.nome;

-- Listar todos os funcionários, seus setores e produtos produzidos
select fun.nome, fun.cargo, fun.id_setor, prod.nome_produto, prod.quantidade
from funcionarios fun
join producao prod on fun.id_funcionario = prod.id_funcionario;

-- Listar produtos produzidos em uma data específica, com detalhes do funcionário e setor
select prod.nome_produto, prod.quantidade, fun.nome, fun.id_setor
from producao prod
join funcionarios fun on prod.id_funcionario = fun.id_funcionario
where prod.data_producao = '2025-06-25';