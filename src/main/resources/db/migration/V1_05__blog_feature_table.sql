use aurora_shop;
create table if not exists `blog`
(
    id         bigint primary key auto_increment,
    title      varchar(255) not null,
    author     varchar(255),
    publish    bit          not null default false,
    content    mediumtext,
    main_image varchar(1000),
    created_at datetime
);

create table if not exists blog_content_image
(
    id              bigint primary key auto_increment,
    image_file_name varchar(255),
    blog_id         bigint,
    foreign key (blog_id) references blog (id)
);