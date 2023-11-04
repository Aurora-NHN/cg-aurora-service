create table category
(
    id           bigint primary key auto_increment,
    name         nvarchar(255) not null,
    is_delete    bit default 0,
    is_activated bit default 1
);
create table sub_category
(
    id           bigint primary key auto_increment,
    name         nvarchar(50) not null,
    category_id  bigint,
    foreign key (category_id) references category (id),
    is_delete    bit default 0,
    is_activated bit default 1
);
create table product
(
    id              bigint primary key auto_increment,
    product_name    nvarchar(255) not null,
    price           bigint        not null,
    quantity_sold   int default 0,
    weight          int,
    quantity        int,
    `description`   text,
    producer        nvarchar(50),
    author          nvarchar(50),
    include         nvarchar(255),
    image_url       text,
    sub_category_id bigint,
    foreign key (sub_category_id) references sub_category (id),
    is_delete       bit default 0,
    is_activated    bit default 1
);
create table product_image
(
    id           bigint primary key auto_increment,
    image_url    text not null,
    product_id   bigint,
    foreign key (product_id) references product (id),
    is_delete    bit default 0,
    is_activated bit default 1
);
