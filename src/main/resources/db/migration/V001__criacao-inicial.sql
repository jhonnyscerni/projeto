    create table grupo (
        cod_grupo bigint not null auto_increment,
        nm_grupo varchar(255),
        primary key (cod_grupo
        )) engine=InnoDB default charset=utf8;

    create table grupo_permissao (
        cod_grupo bigint not null,
        cod_permissao bigint not null,
        primary key (cod_grupo, cod_permissao)
        ) engine=InnoDB default charset=utf8;

    create table permissao (
        cod_permissao bigint not null auto_increment,
        desc_permissao varchar(255) not null,
        nm_permissao varchar(255) not null, primary key (cod_permissao)
        ) engine=InnoDB default charset=utf8;

    create table usuario (
        cod_usuario bigint not null auto_increment,
        dt_cad_usuario datetime not null,
        email varchar(255),
        nm_usuario varchar(255), senha varchar(255),
        primary key (cod_usuario)
        ) engine=InnoDB default charset=utf8;

    create table usuario_grupo (
        cod_usuario bigint not null,
        cod_grupo bigint not null, primary key (cod_usuario, cod_grupo)
        ) engine=InnoDB default charset=utf8;

    alter table grupo_permissao add constraint FKa6x872qlli69ud5frwgwthx0 foreign key (cod_permissao) references permissao (cod_permissao);
    alter table grupo_permissao add constraint FKgn73ra8jt0jr4upola8cinhyr foreign key (cod_grupo) references grupo (cod_grupo);
    alter table usuario_grupo add constraint FK6tge3vbl2fu51964nmqwwiiqk foreign key (cod_grupo) references grupo (cod_grupo);
    alter table usuario_grupo add constraint FK8nrfpsmtqiv3je6ewqehqs2vy foreign key (cod_usuario) references usuario (cod_usuario);
