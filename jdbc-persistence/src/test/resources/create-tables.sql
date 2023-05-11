CREATE TABLE IF NOT EXISTS users
(
    user_id      SERIAL NOT NULL,
    name         varchar(255) DEFAULT NULL,
    phone_number varchar(255) DEFAULT NULL,
    PRIMARY KEY (user_id)
);
