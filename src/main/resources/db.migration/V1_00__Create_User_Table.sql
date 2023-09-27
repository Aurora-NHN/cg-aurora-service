create database aurora_shop;
use aurora_shop;

create table user(
    id bigint primary key auto_increment,
    username varchar(50) unique,
    `password` varchar(50),
    roles varchar(50),
    is_vip bit default 0,
    is_delete bit default 1,
    is_activated bit default 1
);

create table user_detail(
    id bigint primary key auto_increment,
    full_name nvarchar(255),
    gender nvarchar(10),
    phone_number varchar(20),
    email varchar(255),
    image_url text,
    user_id bigint,
    FOREIGN KEY(user_id) REFERENCES user(id)
);