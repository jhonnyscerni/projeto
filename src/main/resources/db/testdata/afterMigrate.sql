set foreign_key_checks = 0;

delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from usuario;
delete from usuario_grupo;
delete from oauth_client_details;

alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table usuario auto_increment = 1;

insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (1, 'CONSULTAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite consultar usuarios');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (2, 'EDITAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite editar usuarios');
insert into permissao (cod_permissao, nm_permissao, desc_permissao) values (3, 'REMOVER_USUARIOS_GRUPOS_PERMISSOES', 'Permite remover usuarios');

insert into grupo (cod_grupo, nm_grupo) values (1, 'Administrador'), (2, 'Gerente');

# Adiciona todas as permissoes no grupo do gerentegrupo_permissao
insert into grupo_permissao (cod_grupo, cod_permissao)
select 1, cod_permissao from permissao;

# Adiciona permissoes no grupo do vendedor
insert into grupo_permissao (cod_grupo, cod_permissao)
select 2, cod_permissao from permissao where nm_permissao like 'CONSULTAR_%';

insert into usuario (cod_usuario, nm_usuario, email, senha, dt_cad_usuario) values
(1, 'Administrador', 'administrador@projeto.com.br', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', utc_timestamp),
(2, 'Gerente', 'gerente@projeto.com.br', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', utc_timestamp),
(3, 'Administrador02', 'administrador02@projeto.com.br', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', utc_timestamp),
(4, 'Sebastião Martins', 'sebastiao.cad@projeto.com.br', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', utc_timestamp),
(5, 'Manoel Lima', 'manoel.loja@gmail.com', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', utc_timestamp),
(6, 'Débora Mendonça', 'siberiusapp+debora@gmail.com', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', utc_timestamp),
(7, 'Carlos Lima', 'siberiusapp+carlos@gmail.com', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', utc_timestamp);

insert into usuario_grupo (cod_usuario, cod_grupo) values (1, 1), (1, 2), (2, 2);


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

