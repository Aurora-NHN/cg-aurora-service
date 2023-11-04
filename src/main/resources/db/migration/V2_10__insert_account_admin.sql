insert into `user` (id, username, password, roles, count, total_count, is_delete, is_activated)
values (1, 'hongocha88', '$2a$10$ipQm1NybSQt3C4V6MtfDVu9SBL/BgjG3RCCxKPTuV24lSsZ9XV7Ha', 'ROLE_ADMIN', 100, 150, false, true);
-- Mat khau: hongocha88
insert into user_detail (id, full_name, gender, phone_number, email, user_id)
values (1, 'Hồ Ngọc Hà', 'Female', '0978463831', 'quynhnhumaivtvn2012@gmail.com', 1);

insert into `aurora_shop`.`user` (`username`, `password`, `roles`) values ('admin111', '$2a$10$AMBtojmmVAPZ6O7FgHx0CuY51i6RplkuFJkgSd553n.H/tYVd33vm', 'ROLE_ADMIN');
insert into `aurora_shop`.`user_detail` (`full_name`, `gender`, `phone_number`, `email`, `user_id`) values ('Admin Test', 'Male', '0123456789', 'auroraweb2023@gmail.com', '2');
-- username: admin111 password: abcd1234