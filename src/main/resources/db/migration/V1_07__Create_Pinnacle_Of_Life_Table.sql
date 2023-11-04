create table pinnacle_of_life
(
    id                      bigint primary key auto_increment,
    pinnacle_of_life_number int not null,
    age                     int not null,
    numerology_report_id    bigint,
    foreign key (numerology_report_id) references numerology_report (id)
);

alter table numerology_report
    change balance_chart balance_number int;