create table `orders`
(
    id                bigint primary key auto_increment,
    user_id           bigint,
    order_date        date,
    total_amount      bigint,
    expected_delivery date,
    status            nvarchar(255),
    foreign key (user_id) references user (id)
);

create table `address`
(
    id                     bigint primary key auto_increment,
    consignee_name         nvarchar(50),
    full_name              nvarchar(255),
    phone_number           varchar(11),
    city                   nvarchar(50),
    delivery_address       nvarchar(255),
    delivery_charges       bigint,
    additional_information text,
    order_id               bigint unique,
    foreign key (order_id) references `orders` (id)
);

create table order_detail
(
    id          bigint primary key auto_increment,
    quantity    int,
    total_price bigint,
    order_id    bigint,
    product_id  bigint,
    foreign key (order_id) references `orders` (id),
    foreign key (product_id) references product (id)
);

create table cart
(
    id           bigint primary key auto_increment,
    total_amount bigint,
    user_id      bigint,
    foreign key (user_id) references user (id)
);

create table cart_line
(
    id          bigint primary key auto_increment,
    cart_id     bigint,
    product_id  bigint,
    quantity    int,
    total_price bigint,
    foreign key (cart_id) references `cart` (id),
    foreign key (product_id) references product (id)
);