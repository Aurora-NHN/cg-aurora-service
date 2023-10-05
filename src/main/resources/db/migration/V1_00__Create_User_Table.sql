CREATE TABLE USER
(
    ID           BIGINT PRIMARY KEY AUTO_INCREMENT,
    USERNAME     VARCHAR(50) UNIQUE,
    `PASSWORD`   VARCHAR(1024),
    ROLES        VARCHAR(50),
    COUNT        INT DEFAULT 0,
    IS_VIP       BIT DEFAULT 0,
    IS_DELETE    BIT DEFAULT 1,
    IS_ACTIVATED BIT DEFAULT 1
);

CREATE TABLE USER_DETAIL
(
    ID           BIGINT PRIMARY KEY AUTO_INCREMENT,
    FULL_NAME    NVARCHAR(255),
    GENDER       NVARCHAR(10),
    PHONE_NUMBER VARCHAR(20),
    EMAIL        VARCHAR(255) UNIQUE,
    IMAGE_URL    TEXT,
    USER_ID      BIGINT,
    FOREIGN KEY (USER_ID) REFERENCES USER (ID)
);
