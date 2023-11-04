create table data_numerology_report
(
    id             bigint primary key auto_increment,
    full_name      nvarchar(255) not null,
    nickname       nvarchar(255),
    day_of_birth   int           not null,
    month_of_birth int           not null,
    year_of_birth  int           not null,
    created_time   datetime,
    user_id        bigint,
    foreign key (user_id) references user (id),
    is_deleted     bit default 0,
    is_activated   bit default 1
);
