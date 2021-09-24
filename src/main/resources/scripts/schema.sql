BEGIN;

drop table if exists users cascade;

drop table if exists appointments cascade;

drop table if exists roles cascade;

drop table if exists procedures cascade;

drop table if exists users_procedures cascade;

create table if not exists public.roles
(
    id           bigserial primary key,
    name         varchar   not null
);

create table if not exists public.users
(
    id           bigserial primary key,
    login        varchar not null unique,
    password     varchar not null,
    role_id      bigint not null,
    rating       double precision,
    recall_count integer
);

alter table public.users
    add foreign key (role_id)
        references public.roles (id);


create table if not exists public.procedures
(
    id       bigserial primary key,
    name     varchar not null unique,
    duration interval
);

create table if not exists public.appointments
(
    id           bigserial primary key,
    procedure_id bigint    not null,
    master_id    bigint    not null,
    client_id    bigint    not null,
    start_time   timestamp not null,
    status       varchar   not null
);

alter table public.appointments
    add foreign key (master_id)
        references public.users (id) on DELETE CASCADE ;

alter table public.appointments
    add foreign key (client_id)
        references public.users (id) on DELETE CASCADE ;

alter table public.appointments
    add foreign key (procedure_id)
        references public.procedures (id) on DELETE CASCADE ;

create table if not exists public.users_procedures
(
    user_id      bigint not null,
    procedure_id bigint not null,
    primary key (user_id, procedure_id)
);
alter table public.users_procedures
    add foreign key (user_id)
        references public.users (id) on DELETE CASCADE ;

alter table public.users_procedures
    add foreign key (procedure_id)
        references public.procedures (id) on DELETE CASCADE ;

END;
