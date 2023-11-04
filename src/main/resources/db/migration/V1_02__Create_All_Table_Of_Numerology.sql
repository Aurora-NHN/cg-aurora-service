create table life_phase
(
    id                  bigint primary key auto_increment,
    young_stage_number  int not null,
    mature_stage_number int not null,
    old_stage_number    int not null
);
create table challenge_number
(
    id                 bigint primary key auto_increment,
    challenge_number_1 int not null,
    challenge_number_2 int not null,
    challenge_number_3 int not null,
    challenge_number_4 int not null
);


create table numerology_report
(
    id                          bigint primary key auto_increment,
    full_name                   nvarchar(255) not null,
    day_of_birth                int           not null,
    month_of_birth              int           not null,
    year_of_birth               int           not null,
    is_deleted                  bit default 0,
    is_activated                bit default 1,
    user_id                     bigint,
    foreign key (user_id) references user (id),
    life_path_number            int,
    day_of_birth_number         int,
    attitude_number             int,
    soul_number                 int,
    personality_number          int,
    mission_number              int,
    middle_age_number           int,
    life_phase_id               bigint,
    foreign key (life_phase_id) references life_phase (id),
    karmic_debt                 int,
    pinnacle_of_life            int,
    feeling_inside_number       int,
    defect_number_of_name_chart int,
    challenge_number_id         bigint,
    foreign key (challenge_number_id) references challenge_number (id),
    numbers_in_chart            int,
    balance_chart               int
);

create table personal_year
(
    id                   bigint primary key auto_increment,
    personal_year_number int not null,
    numerology_report_id bigint,
    foreign key (numerology_report_id) references numerology_report (id)
);
create table personal_month
(
    id                    bigint primary key auto_increment,
    month                 int not null,
    personal_month_number int not null,
    personal_year_id      bigint,
    foreign key (personal_year_id) references personal_year (id)
);