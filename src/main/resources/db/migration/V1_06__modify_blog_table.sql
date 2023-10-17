use aurora_shop;

alter table blog
    add column description    varchar(2000),
    add column tag            varchar(255),
    add column blogCategoryId bigint,
    add column lastModify     datetime;

create table blog_category
(
    id   bigint primary key auto_increment,
    name varchar(255) not null
)