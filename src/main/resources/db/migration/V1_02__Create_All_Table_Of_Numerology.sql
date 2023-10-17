CREATE TABLE LIFE_PHASE
(
    ID                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    YOUNG_STAGE_NUMBER  INT NOT NULL,
    MATURE_STAGE_NUMBER INT NOT NULL,
    OLD_STAGE_NUMBER    INT NOT NULL
);
CREATE TABLE CHALLENGE_NUMBER
(
    ID                 BIGINT PRIMARY KEY AUTO_INCREMENT,
    CHALLENGE_NUMBER_1 INT NOT NULL,
    CHALLENGE_NUMBER_2 INT NOT NULL,
    CHALLENGE_NUMBER_3 INT NOT NULL,
    CHALLENGE_NUMBER_4 INT NOT NULL
);


CREATE TABLE NUMEROLOGY_REPORT
(
    ID                          BIGINT PRIMARY KEY AUTO_INCREMENT,
    FULL_NAME                   NVARCHAR(255) NOT NULL,
    DAY_OF_BIRTH                INT NOT NULL,
    MONTH_OF_BIRTH              INT NOT NULL,
    YEAR_OF_BIRTH               INT NOT NULL,
    IS_DELETED                  BIT DEFAULT 0,
    IS_ACTIVATED                BIT DEFAULT 1,
    USER_ID                     BIGINT,
    FOREIGN KEY (USER_ID) REFERENCES USER (ID),
    LIFE_PATH_NUMBER            INT,
    DAY_OF_BIRTH_NUMBER         INT,
    ATTITUDE_NUMBER             INT,
    SOUL_NUMBER                 INT,
    PERSONALITY_NUMBER          INT,
    MISSION_NUMBER              INT,
    MIDDLE_AGE_NUMBER           INT,
    LIFE_PHASE_ID               BIGINT,
    FOREIGN KEY (LIFE_PHASE_ID) REFERENCES LIFE_PHASE (ID),
    KARMIC_DEBT                 INT,
    PINNACLE_OF_LIFE            INT,
    FEELING_INSIDE_NUMBER       INT,
    DEFECT_NUMBER_OF_NAME_CHART INT,
    CHALLENGE_NUMBER_ID         BIGINT,
    FOREIGN KEY (CHALLENGE_NUMBER_ID) REFERENCES CHALLENGE_NUMBER (ID),
    NUMBERS_IN_CHART            INT,
    BALANCE_CHART               INT
);

CREATE TABLE PERSONAL_YEAR
(
    ID                   BIGINT PRIMARY KEY AUTO_INCREMENT,
    PERSONAL_YEAR_NUMBER INT NOT NULL,
    NUMEROLOGY_REPORT_ID BIGINT,
    FOREIGN KEY (NUMEROLOGY_REPORT_ID) REFERENCES NUMEROLOGY_REPORT (ID)
);
CREATE TABLE PERSONAL_MONTH
(
    ID                    BIGINT PRIMARY KEY AUTO_INCREMENT,
    MONTH                 INT NOT NULL,
    PERSONAL_MONTH_NUMBER INT NOT NULL,
    PERSONAL_YEAR_ID      BIGINT,
    FOREIGN KEY (PERSONAL_YEAR_ID) REFERENCES PERSONAL_YEAR (ID)
);