CREATE TABLE `car` (
    `id` bigint NOT NULL,
    `brand` varchar(255) DEFAULT NULL,
    `car_id` varchar(255) DEFAULT NULL,
    `engine` varchar(255) DEFAULT NULL,
    `engine2` varchar(255) DEFAULT NULL,
    `generation` varchar(255) DEFAULT NULL,
    `made_year` int NOT NULL,
    `model` varchar(255) DEFAULT NULL,
    `modification` varchar(255) DEFAULT NULL,
    `owner` varchar(255) DEFAULT NULL,
    `plate_number` varchar(255) DEFAULT NULL,
    `vin` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
