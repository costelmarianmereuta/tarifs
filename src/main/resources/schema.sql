create table hibernate_sequence
(
    next_val bigint null
);

create table tarifs
(
    id_tarif      bigint       not null
        primary key,
    actif         bit          null,
    default_tarif bit          null,
    end_date      date         not null,
    end_time      time         not null,
    name          varchar(255) not null,
    prix          float        not null,
    special_tarif bit          null,
    start_date    date         not null,
    start_time    time         not null,
    weekend       bit          null,
    constraint name
        unique (name)
);

create table tarifs_entity_terrains_ids
(
    tarifs_entity_id_tarif bigint       not null,
    terrains_ids           varchar(255) null,
    constraint FKmm9g5u7ffmqq2ep4felubquva
        foreign key (tarifs_entity_id_tarif) references tarifs (id_tarif)
);
