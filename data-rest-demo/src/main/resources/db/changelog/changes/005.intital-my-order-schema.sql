CREATE TABLE `order` (
    `id` bigint NOT NULL,
    `order_id` varchar(255) DEFAULT NULL,
    `order_status` varchar(255) DEFAULT NULL,
    `car_id` bigint DEFAULT NULL,
    `customer_id` bigint DEFAULT NULL,
    `user_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK8cq0mwni8xi2odes1s9i7dbxn` (`car_id`),
    KEY `FKfl2uk880wx9xddhjl29ns9yh8` (`customer_id`),
    KEY `FKf82f9vl0nrkxfop6eydpq8av9` (`user_id`),
    CONSTRAINT `FK8cq0mwni8xi2odes1s9i7dbxn` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`),
    CONSTRAINT `FKf82f9vl0nrkxfop6eydpq8av9` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    CONSTRAINT `FKfl2uk880wx9xddhjl29ns9yh8` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
