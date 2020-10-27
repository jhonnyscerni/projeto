set foreign_key_checks = 0;

delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from usuario;
delete from usuario_grupo;

alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table usuario auto_increment = 1;

insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (1, 'CONSULTAR_USUARIOS', 'Permite consultar usuarios');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (2, 'EDITAR_USUARIOS', 'Permite editar usuarios');

insert into grupo (cod_grupo, nm_grupo) values (1, 'Gerente'), (2, 'Administrador');

insert into grupo_permissao (cod_grupo, cod_permissao) values (1, 1), (1, 2), (2, 1), (2, 2);

insert into usuario (cod_usuario, nm_usuario, email, senha, dt_cad_usuario) values
(1, 'Administrador', 'administrador@projeto.com.br', '123', utc_timestamp),
(2, 'Gerente', 'gerente@projeto.com.br', '123', utc_timestamp),
(3, 'Administrador02', 'administrador02@projeto.com.br', '123', utc_timestamp),
(4, 'Sebastião Martins', 'sebastiao.cad@projeto.com.br', '123', utc_timestamp),
(5, 'Manoel Lima', 'manoel.loja@gmail.com', '123', utc_timestamp),
(6, 'Débora Mendonça', 'siberiusapp+debora@gmail.com', '123', utc_timestamp),
(7, 'Carlos Lima', 'siberiusapp+carlos@gmail.com', '123', utc_timestamp);

insert into usuario_grupo (cod_usuario, cod_grupo) values (1, 1), (1, 2), (2, 2);