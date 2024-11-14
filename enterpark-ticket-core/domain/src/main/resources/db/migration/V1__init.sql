CREATE TABLE IF NOT EXISTS `user`
(
    user_id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    oid          BIGINT       NOT NULL,
    provider     VARCHAR(15)  NOT NULL,
    name         VARCHAR(20)  NOT NULL,
    email        VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20)  NOT NULL,
    birth_date   DATE         NOT NULL,
    gender       VARCHAR(15)  NOT NULL,
    address      VARCHAR(255) NULL,
    role         VARCHAR(15)  NOT NULL,
    state        VARCHAR(15)  NOT NULL
);
