-- Banco de Dados II
-- Por Duncan Ruiz
--
-- Este arquivo contem a criacao das tabelas para o primeiro trabalho.
--
-- O modelo foi corrigido para implementar a hierarquia gen-espec como uma tabela somente.
--
-- Versao 2023/1
--

use T1_BD2;
create database T1_BD2;

CREATE TABLE CURSOS (
 Codigo CHAR(5) NOT NULL,
 Titulo VARCHAR(120) NOT NULL
);
ALTER TABLE CURSOS ADD CONSTRAINT PK_CURSOS PRIMARY KEY (Codigo);

CREATE TABLE DISCIPLINAS (
 Codicred CHAR(8) NOT NULL,
 Denominacao VARCHAR(120) NOT NULL
);
ALTER TABLE DISCIPLINAS ADD CONSTRAINT PK_DISCIPLINAS PRIMARY KEY (Codicred);

CREATE TABLE NIVEIS (
 Codicred CHAR(8) NOT NULL,
 Codigo CHAR(5) NOT NULL,
 Nivel NUMERIC(10) NOT NULL
);
ALTER TABLE NIVEIS ADD CONSTRAINT PK_NIVEIS PRIMARY KEY (Codicred,Codigo);

CREATE TABLE TURMAS (
 Codicred CHAR(8) NOT NULL,
 Turma NUMERIC(3) NOT NULL,
 Ano_Semestre CHAR(6) NOT NULL,
 Horarios VARCHAR(10) NOT NULL,
 Sala VARCHAR(50) NOT NULL,
 Professor VARCHAR(120) NOT NULL
);
ALTER TABLE TURMAS ADD CONSTRAINT PK_TURMAS PRIMARY KEY (Codicred,Turma,Ano_Semestre);

CREATE TABLE ALUNOS (
 Matricula NUMERIC(9) NOT NULL,
 Nome VARCHAR(120) NOT NULL,
 Endereco VARCHAR(200),
 Data_Ingresso DATE,
 Codigo CHAR(5)
);
ALTER TABLE ALUNOS ADD CONSTRAINT PK_ALUNOS PRIMARY KEY (Matricula);

CREATE TABLE HISTORICO (
 Matricula NUMERIC(9) NOT NULL,
 Codicred CHAR(8) NOT NULL,
 Turma NUMERIC(3) NOT NULL,
 Ano_Semestre CHAR(6) NOT NULL,
 Situacao VARCHAR(20) NOT NULL,
 Nota NUMERIC(3,1),
 Frequencia NUMERIC(4)
);

ALTER TABLE HISTORICO ADD CONSTRAINT PK_HISTORICO PRIMARY KEY (Matricula,Codicred,Turma,Ano_Semestre);

ALTER TABLE NIVEIS ADD CONSTRAINT FK_NIVEIS_DISCIPLINAS FOREIGN KEY (Codicred) REFERENCES DISCIPLINAS (Codicred);
ALTER TABLE NIVEIS ADD CONSTRAINT FK_NIVEIS_CURSOS FOREIGN KEY (Codigo) REFERENCES CURSOS (Codigo);
ALTER TABLE TURMAS ADD CONSTRAINT FK_TURMAS_DISCIPLINAS FOREIGN KEY (Codicred) REFERENCES DISCIPLINAS (Codicred);
ALTER TABLE ALUNOS ADD CONSTRAINT FK_ALUNOS_CURSOS FOREIGN KEY (Codigo) REFERENCES CURSOS (Codigo);
ALTER TABLE HISTORICO ADD CONSTRAINT FK_HISTORICO_ALUNOS FOREIGN KEY (Matricula) REFERENCES ALUNOS (Matricula);
ALTER TABLE HISTORICO ADD CONSTRAINT FK_HISTORICO_TURMAS FOREIGN KEY (Codicred,Turma,Ano_Semestre) REFERENCES TURMAS (Codicred,Turma,Ano_Semestre);

ALTER TABLE ALUNOS ADD Email VARCHAR(120);

INSERT INTO DISCIPLINAS
(Codicred,denominacao)
VALUES 
('98902-02','Fundamentos de Sistemas Computacionais'),
('98902-03','Programacao Orientada a Objetos'),
('98902-04','Banco de Dados II'),
('98902-05','Probabilidade e Estatistica'),
('98902-06','Linguagens Automatos e Computacao');

select * from DISCIPLINAS;

INSERT INTO TURMAS
(Codicred,Turma,Ano_Semestre,Horarios,Sala,Professor)
VALUES 
('98902-02',10,'2022/2','2CD','32/A/214','Sergio'),
('98902-03',11,'2022/2','6Ex','32/A/215','Garibodi'),
('98902-04',12,'2023/1','3LM','32/A/317','Duncan'),
('98902-05',30,'2023/1','6LM','32/A/314','Felipe Zabala'),
('98902-06',31,'2023/1','3LM','32/A/216','Andrea Aparecida');

select * from TURMAS;

INSERT INTO CURSOS
(Codigo,Titulo)
VALUES 
('46515','Linguagens Automatos e Computação'),
('4745G','Fundamentos de Sistemas Computacionais'),
('95304','Probabilidade e Estatistica'),
('47913','Programacao Orientada a Objetos'),
('98902','Banco de Dados II');

select * from CURSOS;

INSERT INTO ALUNOS
(Matricula,Nome,Endereco,Data_Ingresso,Codigo,Email)
VALUES 
(221060882,'Augusto',null,null,'98902','augusto.baldino@edu.pucrs.br');

SELECT *FROM ALUNOS;

INSERT INTO HISTORICO
(Matricula,Codicred,Turma,Ano_Semestre,Situacao,Nota,Frequencia)
VALUES 
(221060882,'98902-02',10,'2022/2','Cursanda',7,100),
(221060882,'98902-03',11,'2022/2','Cursanda',7,100),
(221060882,'98902-04',34,'2023/1','Cursando',0,0),
(221060882,'98902-05',30,'2023/1','Cursando',0,0),
(221060882,'98902-06',31,'2023/1','Cursando',0,0);

SELECT *FROM HISTORICO;






















