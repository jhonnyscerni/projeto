set foreign_key_checks = 0;

delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from usuario;
delete from usuario_grupo;
delete from oauth_client_details;
delete from verificar_token;

alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table usuario auto_increment = 1;
alter table verificar_token auto_increment = 1;

insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (1, 'CONSULTAR_DASHBOARD', 'Permite consultar dashboard');

insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (2, 'SEG_CONSULTAR_USUARIOS', 'Permite consultar usuarios');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (3, 'SEG_CADASTRAR_USUARIOS', 'Permite cadastrar usuarios');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (4, 'SEG_EDITAR_USUARIOS', 'Permite editar usuarios');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (5, 'SEG_REMOVER_USUARIOS', 'Permite remover usuarios');

insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (6, 'SEG_CONSULTAR_GRUPOS', 'Permite consultar grupos');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (7, 'SEG_CADASTRAR_GRUPOS', 'Permite cadastrar grupos');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (8, 'SEG_EDITAR_GRUPOS', 'Permite editar grupos');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (9, 'SEG_REMOVER_GRUPOS', 'Permite remover grupos');

insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (10, 'SEG_CONSULTAR_PERMISSOES', 'Permite consultar permissoes');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (11, 'SEG_CADASTRAR_PERMISSOES', 'Permite cadastrar permissoes');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (12, 'SEG_EDITAR_PERMISSOES', 'Permite editar permissoes');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (13, 'SEG_REMOVER_PERMISSOES', 'Permite remover permissoes');

insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (14, 'SEG_CONSULTAR_USUARIOS_GRUPOS', 'Permite consultar asossiações de usuario e grupo');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (15, 'SEG_ASSOCIAR_USUARIOS_GRUPOS', 'Permite cadastrar asossiações de usuario e grupo');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (16, 'SEG_DESASSOCIAR_USUARIOS_GRUPOS', 'Permite editar asossiações de usuario e grupo');

insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (17, 'SEG_CONSULTAR_GRUPOS_PERMISSOES', 'Permite consultar asossiações de grupo e permissão');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (18, 'SEG_ASSOCIAR_GRUPOS_PERMISSOES', 'Permite cadastrar asossiações de grupo e permissão');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (19, 'SEG_DESASSOCIAR_GRUPOS_PERMISSOES', 'Permite editar asossiações de grupo e permissão');

insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (20, 'SEG_CONSULTAR_PACIENTES', 'Permite consultar paciente');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (21, 'SEG_CADASTRAR_PACIENTES', 'Permite cadastrar paciente');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (22, 'SEG_EDITAR_PACIENTES', 'Permite editar paciente');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (23, 'SEG_REMOVER_PACIENTES', 'Permite remover pacientes');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (24, 'SEG_BUSCAR_PACIENTES', 'Permite buscar pacientes pelo id');

insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (25, 'SEG_CONSULTAR_PROFISSIONAIS', 'Permite consultar profissionais');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (26, 'SEG_CADASTRAR_PROFISSIONAIS', 'Permite cadastrar profissionais');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (27, 'SEG_EDITAR_PROFISSIONAIS', 'Permite editar profissionais');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (28, 'SEG_REMOVER_PROFISSIONAIS', 'Permite remover profissionais');

insert into grupo (cod_grupo, nm_grupo) values (1, 'Admin'), (2, 'Profissional'), (3, 'Paciente') ;

# Adiciona todas as permissoes no grupo do gerentegrupo_permissao
insert into grupo_permissao (cod_grupo, cod_permissao)
select 1, cod_permissao from permissao;

# Adiciona permissoes no grupo do vendedor
insert into grupo_permissao (cod_grupo, cod_permissao)
select 2, cod_permissao from permissao where nm_permissao like 'CONSULTAR_%';

insert into grupo_permissao (cod_grupo, cod_permissao) values
(2, 20),
(2, 21),
(2, 22),
(2, 23);

insert into usuario (cod_usuario, nm_usuario, email, senha, dt_cad_usuario, ativado, tp) values
(1, 'Administrador', 'administrador@projeto.com.br', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', utc_timestamp, true, 'Admin'),
(2, 'Profissional', 'profissional@projeto.com.br', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', utc_timestamp, true, 'User'),
(3, 'Paciente', 'paciente@projeto.com.br', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', utc_timestamp, true, 'Patient');

insert into usuario_grupo (cod_usuario, cod_grupo) values (1, 1), (2, 2);


insert into oauth_client_details (
  client_id, resource_ids, client_secret,
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
values (
  'projeto-web', null, '$2y$12$w3igMjsfS5XoAYuowoH3C.54vRFWlcXSHLjX7MwF990Kc2KKKh72e',
  'READ,WRITE', 'password', null, null,
  60 * 60 * 6, 60 * 24 * 60 * 60, null
);

insert into oauth_client_details (
  client_id, resource_ids, client_secret,
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
values (
  'projetoanalytics', null, '$2y$12$fahbH37S2pyk1RPuIHKP.earzFmgAJJGo26rE.59vf4wwiiTKHnzO',
  'READ,WRITE', 'authorization_code', 'http://www.projetoanalytics.local:8082', null,
  null, null, null
);

insert into oauth_client_details (
  client_id, resource_ids, client_secret,
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
values (
  'faturamento', null, '$2y$12$fHixriC7yXX/i1/CmpnGH.RFyK/l5YapLCFOEbIktONjE8ZDykSnu',
  'READ,WRITE', 'client_credentials', null, 'CONSULTAR_FATURAMENTO,GERAR_RELATORIOS',
  null, null, null
);

