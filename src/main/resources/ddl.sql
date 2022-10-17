create table atendimento (cod_atendimento bigint not null auto_increment, descrever_sessao varchar(255), objetivo_sessao varchar(255), cod_consulta bigint not null, primary key (cod_atendimento)) engine=InnoDB
create table categoria_lancamento (cod_lancamento bigint not null auto_increment, nm_categoria_lanc varchar(255), primary key (cod_lancamento)) engine=InnoDB
create table cidade (cod_cidade bigint not null auto_increment, nm_cidade varchar(255) not null, cod_estado bigint not null, primary key (cod_cidade)) engine=InnoDB
create table consulta (cod_consulta bigint not null auto_increment, class_name varchar(255), convenio_enum varchar(255), data_hora datetime, local_atendimento varchar(255), observacao varchar(255), procedimento_enum varchar(255), start datetime, status_consulta_enum varchar(255), title varchar(255), clinica bigint, paciente bigint, profissional bigint, primary key (cod_consulta)) engine=InnoDB
create table estado (cod_estado bigint not null auto_increment, nm_estado varchar(255) not null, sigla varchar(255) not null, primary key (cod_estado)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, data_atualizacao datetime, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table foto_perfil (cod_foto_perfil bigint not null auto_increment, content_type varchar(255), descricao varchar(255), nome_arquivo varchar(255), tamanho bigint, primary key (cod_foto_perfil)) engine=InnoDB
create table grupo (cod_grupo bigint not null auto_increment, nm_grupo varchar(255), primary key (cod_grupo)) engine=InnoDB
create table grupo_permissao (cod_grupo bigint not null, cod_permissao bigint not null) engine=InnoDB
create table hibernate_sequence (next_val bigint) engine=InnoDB
insert into hibernate_sequence values ( 1 )
create table lancamento (cod_lancamento bigint not null auto_increment, clinica_id bigint, descricao varchar(255), dt_lancamento datetime, profissional_id bigint, valor_total decimal(19,2), categoria_cod_lancamento bigint, consulta_cod_consulta bigint, forma_pagamento_id bigint, primary key (cod_lancamento)) engine=InnoDB
create table permissao (cod_permissao bigint not null auto_increment, desc_permissao varchar(255) not null, nm_permissao varchar(255) not null, primary key (cod_permissao)) engine=InnoDB
create table usuario (tp varchar(31) not null, cod_usuario bigint not null auto_increment, ativado bit, endereco_bairro varchar(255), celular varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), cpf varchar(255), dt_cad_usuario datetime not null, dt_nascimento datetime, email varchar(255), endereco_logradouro varchar(255), nm_usuario varchar(255), endereco_numero varchar(255), senha varchar(255), sexo varchar(255), telefone varchar(255), clinica_id bigint, cpf_responsavel varchar(255), nome_mae varchar(255), nome_pai varchar(255), profissional_id bigint, conselho varchar(255), formacao_academica varchar(255), registro_conselho varchar(255), endereco_cidade_id bigint, foto_perfil_cod_foto_perfil bigint, primary key (cod_usuario)) engine=InnoDB
create table usuario_grupo (cod_usuario bigint not null, cod_grupo bigint not null) engine=InnoDB
create table verificar_token (id bigint not null, expiry_date datetime, token varchar(255), cod_usuario bigint not null, primary key (id)) engine=InnoDB
alter table atendimento add constraint FK1a2q6tq6ext4h4l2or7ra81yj foreign key (cod_consulta) references consulta (cod_consulta)
alter table cidade add constraint FKqef7ghgr8yifkq3uf2coild6e foreign key (cod_estado) references estado (cod_estado)
alter table consulta add constraint FK83x4ld5iced1lmagoum9ipv9i foreign key (clinica) references usuario (cod_usuario)
alter table consulta add constraint FK5jod6lyptj2oetm5qyb8bqt2a foreign key (paciente) references usuario (cod_usuario)
alter table consulta add constraint FK9pn6y8dobcy547vtecnet7sqi foreign key (profissional) references usuario (cod_usuario)
alter table grupo_permissao add constraint FKa6x872qlli69ud5frwgwthx0 foreign key (cod_permissao) references permissao (cod_permissao)
alter table grupo_permissao add constraint FKgn73ra8jt0jr4upola8cinhyr foreign key (cod_grupo) references grupo (cod_grupo)
alter table lancamento add constraint FKpubcnd88hhobt30xn3wx8bdkg foreign key (categoria_cod_lancamento) references categoria_lancamento (cod_lancamento)
alter table lancamento add constraint FK36gsg0rj1p21mh0hwy2s9b0mf foreign key (consulta_cod_consulta) references consulta (cod_consulta)
alter table lancamento add constraint FKppoorqoht3gx5u9a4572847k7 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table usuario add constraint FK13u918h81mp7wgffsnh96a8jh foreign key (endereco_cidade_id) references cidade (cod_cidade)
alter table usuario add constraint FKq6dosy52cppd62rjp7mlkfp8v foreign key (foto_perfil_cod_foto_perfil) references foto_perfil (cod_foto_perfil)
alter table usuario_grupo add constraint FK6tge3vbl2fu51964nmqwwiiqk foreign key (cod_grupo) references grupo (cod_grupo)
alter table usuario_grupo add constraint FK8nrfpsmtqiv3je6ewqehqs2vy foreign key (cod_usuario) references usuario (cod_usuario)
alter table verificar_token add constraint FKqt9kid9njxsvbmx091bwjywgb foreign key (cod_usuario) references usuario (cod_usuario)
create sequence hibernate_sequence start 1 increment 1
create table atendimento (cod_atendimento  bigserial not null, descrever_sessao varchar(255), objetivo_sessao varchar(255), cod_consulta int8 not null, primary key (cod_atendimento))
create table categoria_lancamento (cod_lancamento  bigserial not null, nm_categoria_lanc varchar(255), primary key (cod_lancamento))
create table cidade (cod_cidade  bigserial not null, nm_cidade varchar(255) not null, cod_estado int8 not null, primary key (cod_cidade))
create table consulta (cod_consulta  bigserial not null, class_name varchar(255), convenio_enum varchar(255), data_hora timestamp, local_atendimento varchar(255), observacao varchar(255), procedimento_enum varchar(255), start timestamp, status_consulta_enum varchar(255), title varchar(255), clinica int8, paciente int8, profissional int8, primary key (cod_consulta))
create table estado (cod_estado  bigserial not null, nm_estado varchar(255) not null, sigla varchar(255) not null, primary key (cod_estado))
create table forma_pagamento (id  bigserial not null, data_atualizacao timestamp, descricao varchar(255) not null, primary key (id))
create table foto_perfil (cod_foto_perfil  bigserial not null, content_type varchar(255), descricao varchar(255), nome_arquivo varchar(255), tamanho int8, primary key (cod_foto_perfil))
create table grupo (cod_grupo  bigserial not null, nm_grupo varchar(255), primary key (cod_grupo))
create table grupo_permissao (cod_grupo int8 not null, cod_permissao int8 not null)
create table lancamento (cod_lancamento  bigserial not null, clinica_id int8, descricao varchar(255), dt_lancamento timestamp, profissional_id int8, valor_total numeric(19, 2), categoria_cod_lancamento int8, consulta_cod_consulta int8, forma_pagamento_id int8, primary key (cod_lancamento))
create table permissao (cod_permissao  bigserial not null, desc_permissao varchar(255) not null, nm_permissao varchar(255) not null, primary key (cod_permissao))
create table usuario (tp varchar(31) not null, cod_usuario  bigserial not null, ativado boolean, endereco_bairro varchar(255), celular varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), cpf varchar(255), dt_cad_usuario datetime not null, dt_nascimento timestamp, email varchar(255), endereco_logradouro varchar(255), nm_usuario varchar(255), endereco_numero varchar(255), senha varchar(255), sexo varchar(255), telefone varchar(255), clinica_id int8, cpf_responsavel varchar(255), nome_mae varchar(255), nome_pai varchar(255), profissional_id int8, conselho varchar(255), formacao_academica varchar(255), registro_conselho varchar(255), endereco_cidade_id int8, foto_perfil_cod_foto_perfil int8, primary key (cod_usuario))
create table usuario_grupo (cod_usuario int8 not null, cod_grupo int8 not null)
create table verificar_token (id int8 not null, expiry_date timestamp, token varchar(255), cod_usuario int8 not null, primary key (id))
alter table atendimento add constraint FK1a2q6tq6ext4h4l2or7ra81yj foreign key (cod_consulta) references consulta
alter table cidade add constraint FKqef7ghgr8yifkq3uf2coild6e foreign key (cod_estado) references estado
alter table consulta add constraint FK83x4ld5iced1lmagoum9ipv9i foreign key (clinica) references usuario
alter table consulta add constraint FK5jod6lyptj2oetm5qyb8bqt2a foreign key (paciente) references usuario
alter table consulta add constraint FK9pn6y8dobcy547vtecnet7sqi foreign key (profissional) references usuario
alter table grupo_permissao add constraint FKa6x872qlli69ud5frwgwthx0 foreign key (cod_permissao) references permissao
alter table grupo_permissao add constraint FKgn73ra8jt0jr4upola8cinhyr foreign key (cod_grupo) references grupo
alter table lancamento add constraint FKpubcnd88hhobt30xn3wx8bdkg foreign key (categoria_cod_lancamento) references categoria_lancamento
alter table lancamento add constraint FK36gsg0rj1p21mh0hwy2s9b0mf foreign key (consulta_cod_consulta) references consulta
alter table lancamento add constraint FKppoorqoht3gx5u9a4572847k7 foreign key (forma_pagamento_id) references forma_pagamento
alter table usuario add constraint FK13u918h81mp7wgffsnh96a8jh foreign key (endereco_cidade_id) references cidade
alter table usuario add constraint FKq6dosy52cppd62rjp7mlkfp8v foreign key (foto_perfil_cod_foto_perfil) references foto_perfil
alter table usuario_grupo add constraint FK6tge3vbl2fu51964nmqwwiiqk foreign key (cod_grupo) references grupo
alter table usuario_grupo add constraint FK8nrfpsmtqiv3je6ewqehqs2vy foreign key (cod_usuario) references usuario
alter table verificar_token add constraint FKqt9kid9njxsvbmx091bwjywgb foreign key (cod_usuario) references usuario
create sequence hibernate_sequence start 1 increment 1
create table atendimento (cod_atendimento  bigserial not null, descrever_sessao varchar(255), objetivo_sessao varchar(255), cod_consulta int8 not null, primary key (cod_atendimento))
create table categoria_lancamento (cod_lancamento  bigserial not null, nm_categoria_lanc varchar(255), primary key (cod_lancamento))
create table cidade (cod_cidade  bigserial not null, nm_cidade varchar(255) not null, cod_estado int8 not null, primary key (cod_cidade))
create table consulta (cod_consulta  bigserial not null, class_name varchar(255), convenio_enum varchar(255), data_hora timestamp, local_atendimento varchar(255), observacao varchar(255), procedimento_enum varchar(255), start timestamp, status_consulta_enum varchar(255), title varchar(255), clinica int8, paciente int8, profissional int8, primary key (cod_consulta))
create table estado (cod_estado  bigserial not null, nm_estado varchar(255) not null, sigla varchar(255) not null, primary key (cod_estado))
create table forma_pagamento (id  bigserial not null, data_atualizacao timestamp, descricao varchar(255) not null, primary key (id))
create table foto_perfil (cod_foto_perfil  bigserial not null, content_type varchar(255), descricao varchar(255), nome_arquivo varchar(255), tamanho int8, primary key (cod_foto_perfil))
create table grupo (cod_grupo  bigserial not null, nm_grupo varchar(255), primary key (cod_grupo))
create table grupo_permissao (cod_grupo int8 not null, cod_permissao int8 not null)
create table lancamento (cod_lancamento  bigserial not null, clinica_id int8, descricao varchar(255), dt_lancamento timestamp, profissional_id int8, valor_total numeric(19, 2), categoria_cod_lancamento int8, consulta_cod_consulta int8, forma_pagamento_id int8, primary key (cod_lancamento))
create table permissao (cod_permissao  bigserial not null, desc_permissao varchar(255) not null, nm_permissao varchar(255) not null, primary key (cod_permissao))
create table usuario (tp varchar(31) not null, cod_usuario  bigserial not null, ativado boolean, endereco_bairro varchar(255), celular varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), cpf varchar(255), dt_cad_usuario datetime not null, dt_nascimento timestamp, email varchar(255), endereco_logradouro varchar(255), nm_usuario varchar(255), endereco_numero varchar(255), senha varchar(255), sexo varchar(255), telefone varchar(255), clinica_id int8, cpf_responsavel varchar(255), nome_mae varchar(255), nome_pai varchar(255), profissional_id int8, conselho varchar(255), formacao_academica varchar(255), registro_conselho varchar(255), endereco_cidade_id int8, foto_perfil_cod_foto_perfil int8, primary key (cod_usuario))
create table usuario_grupo (cod_usuario int8 not null, cod_grupo int8 not null)
create table verificar_token (id int8 not null, expiry_date timestamp, token varchar(255), cod_usuario int8 not null, primary key (id))
alter table atendimento add constraint FK1a2q6tq6ext4h4l2or7ra81yj foreign key (cod_consulta) references consulta
alter table cidade add constraint FKqef7ghgr8yifkq3uf2coild6e foreign key (cod_estado) references estado
alter table consulta add constraint FK83x4ld5iced1lmagoum9ipv9i foreign key (clinica) references usuario
alter table consulta add constraint FK5jod6lyptj2oetm5qyb8bqt2a foreign key (paciente) references usuario
alter table consulta add constraint FK9pn6y8dobcy547vtecnet7sqi foreign key (profissional) references usuario
alter table grupo_permissao add constraint FKa6x872qlli69ud5frwgwthx0 foreign key (cod_permissao) references permissao
alter table grupo_permissao add constraint FKgn73ra8jt0jr4upola8cinhyr foreign key (cod_grupo) references grupo
alter table lancamento add constraint FKpubcnd88hhobt30xn3wx8bdkg foreign key (categoria_cod_lancamento) references categoria_lancamento
alter table lancamento add constraint FK36gsg0rj1p21mh0hwy2s9b0mf foreign key (consulta_cod_consulta) references consulta
alter table lancamento add constraint FKppoorqoht3gx5u9a4572847k7 foreign key (forma_pagamento_id) references forma_pagamento
alter table usuario add constraint FK13u918h81mp7wgffsnh96a8jh foreign key (endereco_cidade_id) references cidade
alter table usuario add constraint FKq6dosy52cppd62rjp7mlkfp8v foreign key (foto_perfil_cod_foto_perfil) references foto_perfil
alter table usuario_grupo add constraint FK6tge3vbl2fu51964nmqwwiiqk foreign key (cod_grupo) references grupo
alter table usuario_grupo add constraint FK8nrfpsmtqiv3je6ewqehqs2vy foreign key (cod_usuario) references usuario
alter table verificar_token add constraint FKqt9kid9njxsvbmx091bwjywgb foreign key (cod_usuario) references usuario
