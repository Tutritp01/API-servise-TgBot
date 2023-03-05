CREATE TABLE `order_engineers` (
    `order_id` bigint NOT NULL,
    `engineers_id` bigint NOT NULL,
    UNIQUE KEY `UK_dd5unj24nqubpr8os4xbu8y26` (`engineers_id`),
    KEY `FKin57gm1fgi2e712onbp0vqk14` (`order_id`),
    CONSTRAINT `FK3186xvsur540w66dpbs60amjk` FOREIGN KEY (`engineers_id`) REFERENCES `engineer` (`id`),
    CONSTRAINT `FKin57gm1fgi2e712onbp0vqk14` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
