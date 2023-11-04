create table user
(
    id           bigint primary key auto_increment,
    username     varchar(50) unique,
    `password`   varchar(1024),
    roles        varchar(50),
    count        int default 0,
    is_vip       bit default 0,
    is_delete    bit default 0,
    is_activated bit default 1
);

create table user_detail
(
    id           bigint primary key auto_increment,
    full_name    nvarchar(255),
    gender       nvarchar(10),
    phone_number varchar(20),
    email        varchar(255) unique,
    user_id      bigint,
    foreign key (user_id) references user (id)
);
