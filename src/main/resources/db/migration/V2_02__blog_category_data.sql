use aurora_shop;

alter table blog_category add constraint unique (name);

insert into blog_category (name)
values ('Thần số học'),
       ('Tarot');