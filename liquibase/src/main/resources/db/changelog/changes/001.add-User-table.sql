--liquibase formatted sql
--changeset Artem_Metushko:1
CREATE TABLE IF NOT EXISTS `users`
(
    `id`           BIGINT NOT NULL,
    `name`         varchar(255) DEFAULT NULL,
    `phone_number` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
