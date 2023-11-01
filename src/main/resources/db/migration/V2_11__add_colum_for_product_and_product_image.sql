alter table product
    add column image_name varchar(255),
    add column height int;
alter table product_image
    add column image_name varchar(255);
