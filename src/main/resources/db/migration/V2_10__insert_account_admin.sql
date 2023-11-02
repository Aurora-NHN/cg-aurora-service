INSERT INTO `user` (ID, USERNAME, PASSWORD, ROLES, COUNT, TOTAL_COUNT, IS_DELETE, IS_ACTIVATED)
VALUES (1, 'hongocha88', '$2a$10$ipQm1NybSQt3C4V6MtfDVu9SBL/BgjG3RCCxKPTuV24lSsZ9XV7Ha', 'ROLE_ADMIN', 100, 150, false, true);
-- Mat khau: hongocha88
INSERT INTO user_detail (ID, FULL_NAME, GENDER, PHONE_NUMBER, EMAIL, USER_ID)
VALUES (1, 'Hồ Ngọc Hà', 'Female', '0978463831', 'quynhnhumaivtvn2012@gmail.com', 1);

INSERT INTO `aurora_shop`.`user` (`USERNAME`, `PASSWORD`, `ROLES`) VALUES ('admin111', '$2a$10$AMBtojmmVAPZ6O7FgHx0CuY51i6RplkuFJkgSd553n.H/tYVd33vm', 'ROLE_ADMIN');
INSERT INTO `aurora_shop`.`user_detail` (`FULL_NAME`, `GENDER`, `PHONE_NUMBER`, `EMAIL`, `USER_ID`) VALUES ('Admin Test', 'Male', '0123456789', 'auroraweb2023@gmail.com', '2');
-- username: admin111 password: abcd1234