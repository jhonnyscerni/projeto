# Adiciona todas as permissoes no grupo do ADMIN
insert into grupo_permissao (cod_grupo, cod_permissao)
select 1, cod_permissao from permissao;

# Adiciona permissoes no grupo da CLINICA
insert into grupo_permissao (cod_grupo, cod_permissao)
select 2, cod_permissao from permissao where nm_permissao like '%PACIENTES%';
insert into grupo_permissao (cod_grupo, cod_permissao)
select 2, cod_permissao from permissao where nm_permissao like '%PROFISSIONAIS%';
insert into grupo_permissao (cod_grupo, cod_permissao)
select 2, cod_permissao from permissao where nm_permissao like '%ESTADOS%';
insert into grupo_permissao (cod_grupo, cod_permissao)
select 2, cod_permissao from permissao where nm_permissao like '%CIDADES%';
insert into grupo_permissao (cod_grupo, cod_permissao)
select 2, cod_permissao from permissao where nm_permissao like '%SEG_CONSULTAR_DASHBOARD_CLINICAS%';
insert into grupo_permissao (cod_grupo, cod_permissao)
select 2, cod_permissao from permissao where nm_permissao like '%CONSULTAS%';
insert into grupo_permissao (cod_grupo, cod_permissao)
select 2, cod_permissao from permissao where nm_permissao like '%ATENDIMENTOS%';


# Adiciona permissoes no grupo do PROFISSIONAL
insert into grupo_permissao (cod_grupo, cod_permissao)
select 3, cod_permissao from permissao where nm_permissao like '%PACIENTES%';
insert into grupo_permissao (cod_grupo, cod_permissao)
select 3, cod_permissao from permissao where nm_permissao like '%ESTADOS%';
insert into grupo_permissao (cod_grupo, cod_permissao)
select 3, cod_permissao from permissao where nm_permissao like '%CIDADES%';
insert into grupo_permissao (cod_grupo, cod_permissao)
select 3, cod_permissao from permissao where nm_permissao like '%SEG_CONSULTAR_DASHBOARD_PROFISSIONAIS%';
insert into grupo_permissao (cod_grupo, cod_permissao)
select 3, cod_permissao from permissao where nm_permissao like '%CONSULTAS%';
insert into grupo_permissao (cod_grupo, cod_permissao)
select 3, cod_permissao from permissao where nm_permissao like '%ATENDIMENTOS%';

# Adiciona permissoes no grupo do PACIENTE
insert into grupo_permissao (cod_grupo, cod_permissao)
select 4, cod_permissao from permissao where nm_permissao like 'CONSULTAR_%';
insert into grupo_permissao (cod_grupo, cod_permissao)
select 4, cod_permissao from permissao where nm_permissao like '%SEG_CONSULTAR_CONSULTAS%';
insert into grupo_permissao (cod_grupo, cod_permissao)
select 4, cod_permissao from permissao where nm_permissao like '%SEG_CONSULTAR_PROFISSIONAIS%';
insert into grupo_permissao (cod_grupo, cod_permissao)
select 4, cod_permissao from permissao where nm_permissao like '%SEG_CONSULTAR_ATENDIMENTOS%';