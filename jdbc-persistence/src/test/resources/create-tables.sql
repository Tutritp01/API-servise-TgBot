CREATE TABLE IF NOT EXISTS users
(
    id           SERIAL   NOT NULL,
    name         varchar(255) DEFAULT NULL,
    phone_number varchar(255) DEFAULT NULL,
    PRIMARY KEY (id)
);
