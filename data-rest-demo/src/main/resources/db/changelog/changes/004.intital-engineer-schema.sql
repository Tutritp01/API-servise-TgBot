CREATE TABLE `engineer` (
    `id` bigint NOT NULL,
    `category` varchar(255) DEFAULT NULL,
    `education` varchar(255) DEFAULT NULL,
    `engineer_id` varchar(255) DEFAULT NULL,
    `experience` int NOT NULL,
    `first_name` varchar(255) DEFAULT NULL,
    `general_experience` int NOT NULL,
    `job_function` varchar(255) DEFAULT NULL,
    `last_name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
