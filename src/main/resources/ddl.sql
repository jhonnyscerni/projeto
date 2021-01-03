create table cidade (cod_cidade bigint not null auto_increment, nm_cidade varchar(255) not null, cod_estado bigint not null, primary key (cod_cidade)) engine=InnoDB
create table estado (cod_estado bigint not null auto_increment, nm_estado varchar(255) not null, silga varchar(255) not null, primary key (cod_estado)) engine=InnoDB
create table grupo (cod_grupo bigint not null auto_increment, nm_grupo varchar(255), primary key (cod_grupo)) engine=InnoDB
create table grupo_permissao (cod_grupo bigint not null, cod_permissao bigint not null) engine=InnoDB
create table hibernate_sequence (next_val bigint) engine=InnoDB
insert into hibernate_sequence values ( 1 )
create table permissao (cod_permissao bigint not null auto_increment, desc_permissao varchar(255) not null, nm_permissao varchar(255) not null, primary key (cod_permissao)) engine=InnoDB
create table usuario (tp varchar(31) not null, cod_usuario bigint not null auto_increment, ativado bit, endereco_bairro varchar(255), celular varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), cpf varchar(255), dt_cad_usuario datetime not null, dt_nascimento datetime, email varchar(255), endereco_logradouro varchar(255), nm_usuario varchar(255), endereco_numero varchar(255), senha varchar(255), sexo varchar(255), telefone varchar(255), cpf_responsavel varchar(255), nome_mae varchar(255), nome_pai varchar(255), conselho varchar(255), formacao_academica varchar(255), registro_conselho varchar(255), endereco_cidade_id bigint, cod_profissional bigint not null, primary key (cod_usuario)) engine=InnoDB
create table usuario_grupo (cod_usuario bigint not null, cod_grupo bigint not null) engine=InnoDB
create table verificar_token (id bigint not null, expiry_date datetime, token varchar(255), cod_usuario bigint not null, primary key (id)) engine=InnoDB
alter table cidade add constraint FKqef7ghgr8yifkq3uf2coild6e foreign key (cod_estado) references estado (cod_estado)
alter table grupo_permissao add constraint FKa6x872qlli69ud5frwgwthx0 foreign key (cod_permissao) references permissao (cod_permissao)
alter table grupo_permissao add constraint FKgn73ra8jt0jr4upola8cinhyr foreign key (cod_grupo) references grupo (cod_grupo)
alter table usuario add constraint FK13u918h81mp7wgffsnh96a8jh foreign key (endereco_cidade_id) references cidade (cod_cidade)
alter table usuario add constraint FK4vkt1rtewumgic691iqlosesk foreign key (cod_profissional) references usuario (cod_usuario)
alter table usuario_grupo add constraint FK6tge3vbl2fu51964nmqwwiiqk foreign key (cod_grupo) references grupo (cod_grupo)
alter table usuario_grupo add constraint FK8nrfpsmtqiv3je6ewqehqs2vy foreign key (cod_usuario) references usuario (cod_usuario)
alter table verificar_token add constraint FKqt9kid9njxsvbmx091bwjywgb foreign key (cod_usuario) references usuario (cod_usuario)
