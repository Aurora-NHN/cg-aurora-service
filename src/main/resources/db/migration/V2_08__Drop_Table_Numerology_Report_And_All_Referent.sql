ALTER TABLE NUMEROLOGY_REPORT
DROP FOREIGN KEY numerology_report_ibfk_1;

ALTER TABLE NUMEROLOGY_REPORT
DROP FOREIGN KEY numerology_report_ibfk_2;

ALTER TABLE NUMEROLOGY_REPORT
DROP FOREIGN KEY numerology_report_ibfk_3;

DROP TABLE IF EXISTS CHALLENGE_NUMBER;
DROP TABLE IF EXISTS LIFE_PHASE;
DROP TABLE IF EXISTS PERSONAL_MONTH;
DROP TABLE IF EXISTS PERSONAL_YEAR;
DROP TABLE IF EXISTS PINNACLE_OF_LIFE;



DROP TABLE IF EXISTS  NUMEROLOGY_REPORT;