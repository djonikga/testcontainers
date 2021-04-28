drop table if exists user_role;
drop table if exists notification;
drop table if exists user;

create table user
(
    id    int AUTO_INCREMENT primary key,
    name  varchar(250) not null,
    email varchar(250) not null
);


insert into user (id, name, email)
values (1, 'Ivan', 'i.ivanov@vtb.ru'),
       (2, 'Eugene', 'e.sidorov@vtb.ru'),
       (3, 'Maxim', 'm.petrov@vtb.ru');