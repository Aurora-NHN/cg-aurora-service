alter table numerology_report
    drop foreign key numerology_report_ibfk_1;

alter table numerology_report
    drop foreign key numerology_report_ibfk_2;

alter table numerology_report
    drop foreign key numerology_report_ibfk_3;

drop table if exists challenge_number;
drop table if exists life_phase;
drop table if exists personal_month;
drop table if exists personal_year;
drop table if exists pinnacle_of_life;
drop table if exists numerology_report;