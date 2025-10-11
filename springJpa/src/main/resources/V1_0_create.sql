create database users;

create table users(
    id bigint,
    username text
);

insert into users (id, username)
values
    (1, "test"),
    (2, "test2"),
    (3, "test3");