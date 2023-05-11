--liquibase formatted sql
--changeset Artem_Metushko:1
CREATE TABLE IF NOT EXISTS `users`
(
    `user_id`           BIGINT NOT NULL,
    `name`         varchar(255) DEFAULT NULL,
    `phone_number` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`user_id`)
);