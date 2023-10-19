use aurora_shop;

alter table blog_category add constraint unique (name);

insert into blog_category (name)
values ('Thần số học'),
       ('Tarot');

alter table blog add column blog_category_id bigint,
    add constraint foreign key (blog_category_id) references blog_category(id);