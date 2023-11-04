create table history_payment
(
    id             bigint primary key auto_increment,
    payment_id     varchar(55) unique,
    order_info     varchar(255),
    status         bit default 0,
    payment_time   varchar(55),
    transaction_id varchar(255),
    total_price    varchar(55),
    user_id        bigint,
    is_delete      bit default 0,
    is_activated   bit default 1,
    foreign key (user_id) references user (id)
);

