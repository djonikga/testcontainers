drop table if exists "user";

create table "user"
(
    id    serial primary key,
    name  varchar not null,
    email varchar not null
);

insert into "user" (id, name, email)
values (1, 'Ivan', 'i.ivanov@vtb.ru'),
       (2, 'Eugene', 'e.sidorov@vtb.ru'),
       (3, 'Maxim', 'm.petrov@vtb.ru');

