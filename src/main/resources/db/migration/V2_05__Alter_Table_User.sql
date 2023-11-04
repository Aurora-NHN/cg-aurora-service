alter table `aurora_shop`.`user`
    drop column `is_vip`,
    add column `total_count` int null default '0' after `count`;