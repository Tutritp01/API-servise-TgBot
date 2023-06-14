CREATE TABLE if not exists `users`
(
    `user_id`      varchar(255) DEFAULT NULL,
    `name`         varchar(255) DEFAULT NULL,
    `phone_number` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`user_id`)
) ;