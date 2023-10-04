CREATE TABLE CATEGORY
(
    ID            BIGINT PRIMARY KEY AUTO_INCREMENT,
    NAME NVARCHAR(255) NOT NULL,
-- IMAGE_URL
    IS_DELETE     BIT DEFAULT 1,
    IS_ACTIVATED  BIT DEFAULT 1
);
CREATE TABLE SUB_CATEGORY
(
    ID                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    NAME NVARCHAR(50) NOT NULL,
    CATEGORY_ID BIGINT,
    FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY (ID),
    IS_DELETE           BIT DEFAULT 1,
    IS_ACTIVATED        BIT DEFAULT 1
);
CREATE TABLE PRODUCT
(
    ID                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    PRODUCT_NAME             NVARCHAR(255) NOT NULL,
    PRICE               BIGINT NOT NULL,
    WEIGHT              INT    NOT NULL,
    QUANTITY            INT    NOT NULL,
    `DESCRIPTION`       TEXT   NOT NULL,
    QUANTITY_SOLD       INT DEFAULT 0,
    IMAGE_URL           TEXT,
    SUB_CATEGORY_ID BIGINT,
    FOREIGN KEY (SUB_CATEGORY_ID) REFERENCES SUB_CATEGORY (ID),
    IS_DELETE           BIT DEFAULT 1,
    IS_ACTIVATED        BIT DEFAULT 1
);
CREATE TABLE PRODUCT_IMAGE
(
    ID           BIGINT PRIMARY KEY AUTO_INCREMENT,
    IMAGE_URL    TEXT NOT NULL,
    PRODUCT_ID   BIGINT,
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (ID),
    IS_DELETE    BIT DEFAULT 1,
    IS_ACTIVATED BIT DEFAULT 1
);
INSERT INTO `aurora_shop`.`product` (`ID`, `PRODUCT_NAME`, `PRICE`, `WEIGHT`, `QUANTITY`, `DESCRIPTION`) VALUES ('1', 'nsdjkfn', '12333', '1', '1', '1213');
INSERT INTO `aurora_shop`.`product` (`ID`, `PRODUCT_NAME`, `PRICE`, `WEIGHT`, `QUANTITY`, `DESCRIPTION`) VALUES ('2', 'ghf', '222', '1', '3', 'sdgrds');
INSERT INTO `aurora_shop`.`product` (`ID`, `PRODUCT_NAME`, `PRICE`, `WEIGHT`, `QUANTITY`, `DESCRIPTION`) VALUES ('3', 'xfgdf', '343', '1', '324', 'dcfgd');
INSERT INTO `aurora_shop`.`product` (`ID`, `PRODUCT_NAME`, `PRICE`, `WEIGHT`, `QUANTITY`, `DESCRIPTION`) VALUES ('4', 'sdfgd', '333', '33', '33', 'dfxgd');
INSERT INTO `aurora_shop`.`product` (`ID`, `PRODUCT_NAME`, `PRICE`, `WEIGHT`, `QUANTITY`, `DESCRIPTION`) VALUES ('5', 'sdfsd', '3', '2', '2', 'sfsg');
INSERT INTO `aurora_shop`.`product` (`ID`, `PRODUCT_NAME`, `PRICE`, `WEIGHT`, `QUANTITY`, `DESCRIPTION`) VALUES ('6', 'sfds', '4', '4', '4', 'sdgrs');
INSERT INTO `aurora_shop`.`product` (`ID`, `PRODUCT_NAME`, `PRICE`, `WEIGHT`, `QUANTITY`, `DESCRIPTION`) VALUES ('7', 'ghf', '222', '1', '3', 'sdgrds');
INSERT INTO `aurora_shop`.`product` (`ID`, `PRODUCT_NAME`, `PRICE`, `WEIGHT`, `QUANTITY`, `DESCRIPTION`) VALUES ('8', 'xfgdf', '343', '1', '324', 'dcfgd');
INSERT INTO `aurora_shop`.`product` (`ID`, `PRODUCT_NAME`, `PRICE`, `WEIGHT`, `QUANTITY`, `DESCRIPTION`) VALUES ('9', 'sdfgd', '333', '33', '33', 'dfxgd');
INSERT INTO `aurora_shop`.`product` (`ID`, `PRODUCT_NAME`, `PRICE`, `WEIGHT`, `QUANTITY`, `DESCRIPTION`) VALUES ('10', 'sdfsd', '3', '2', '2', 'sfsg');
INSERT INTO `aurora_shop`.`product` (`ID`, `PRODUCT_NAME`, `PRICE`, `WEIGHT`, `QUANTITY`, `DESCRIPTION`) VALUES ('11', 'sfds', '4', '4', '4', 'sdgrs');

INSERT INTO `aurora_shop`.`category` (`ID`, `NAME`) VALUES ('1', 'vong');
INSERT INTO `aurora_shop`.`category` (`ID`, `NAME`) VALUES ('2', 'chuoi');
INSERT INTO `aurora_shop`.`category` (`ID`, `NAME`) VALUES ('3', 'bai');
INSERT INTO `aurora_shop`.`category` (`ID`, `NAME`) VALUES ('4', 'sach');

INSERT INTO `aurora_shop`.`sub_category` (`ID`, `NAME`, `CATEGORY_ID`) VALUES ('1', 'esrfg', '1');
INSERT INTO `aurora_shop`.`sub_category` (`ID`, `NAME`, `CATEGORY_ID`) VALUES ('2', 'dfg', '1');
INSERT INTO `aurora_shop`.`sub_category` (`ID`, `NAME`, `CATEGORY_ID`) VALUES ('3', 'dfg', '3');
INSERT INTO `aurora_shop`.`sub_category` (`ID`, `NAME`, `CATEGORY_ID`) VALUES ('4', 'dfg', '3');
INSERT INTO `aurora_shop`.`sub_category` (`ID`, `NAME`, `CATEGORY_ID`) VALUES ('5', 'serg', '2');
INSERT INTO `aurora_shop`.`sub_category` (`ID`, `NAME`, `CATEGORY_ID`) VALUES ('6', 'dgrs', '4');

INSERT INTO `aurora_shop`.`product_image` (`ID`, `IMAGE_URL`, `PRODUCT_ID`) VALUES ('1', 'rtgrte', '1');
INSERT INTO `aurora_shop`.`product_image` (`ID`, `IMAGE_URL`, `PRODUCT_ID`) VALUES ('2', 'erth', '3');
INSERT INTO `aurora_shop`.`product_image` (`ID`, `IMAGE_URL`, `PRODUCT_ID`) VALUES ('3', 'ertgh', '1');
INSERT INTO `aurora_shop`.`product_image` (`ID`, `IMAGE_URL`, `PRODUCT_ID`) VALUES ('4', 'erth', '3');

