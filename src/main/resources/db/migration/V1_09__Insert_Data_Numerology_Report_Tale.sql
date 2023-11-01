CREATE TABLE DATA_NUMEROLOGY_REPORT
(
    ID                          BIGINT PRIMARY KEY AUTO_INCREMENT,
    FULL_NAME                   NVARCHAR(255) NOT NULL,
    NICKNAME                    NVARCHAR(255),
    DAY_OF_BIRTH                INT NOT NULL,
    MONTH_OF_BIRTH              INT NOT NULL,
    YEAR_OF_BIRTH               INT NOT NULL,
    CREATED_TIME                DATETIME,
    USER_ID                     BIGINT,
    FOREIGN KEY (USER_ID) REFERENCES USER (ID),
    IS_DELETED                  BIT DEFAULT 0,
    IS_ACTIVATED                BIT DEFAULT 1

);
