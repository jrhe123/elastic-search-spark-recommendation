CREATE TABLE `rating`.`user` (
                                 `id` INT NOT NULL AUTO_INCREMENT,
                                 `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 `telephone` VARCHAR(40) NOT NULL DEFAULT '',
                                 `password` VARCHAR(200) NOT NULL DEFAULT '',
                                 `nick_name` VARCHAR(40) NOT NULL DEFAULT '',
                                 `gender` INT NOT NULL DEFAULT 0,
                                 PRIMARY KEY (`id`),
                                 UNIQUE INDEX `telephone_unique_index` USING BTREE (`telephone`) VISIBLE
);
