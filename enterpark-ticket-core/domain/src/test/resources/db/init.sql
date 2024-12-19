DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS performance;
DROP TABLE IF EXISTS schedule;
DROP TABLE IF EXISTS casting;
DROP TABLE IF EXISTS place;
DROP TABLE IF EXISTS grade_seat;
DROP TABLE IF EXISTS seat;
DROP TABLE IF EXISTS reservation;

CREATE TABLE `user`
(
    user_id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    oid                BIGINT       NOT NULL,
    provider           VARCHAR(15)  NOT NULL,
    name               VARCHAR(20)  NOT NULL,
    email              VARCHAR(255) NOT NULL,
    phone_number       VARCHAR(20)  NOT NULL,
    birth_date         DATE         NOT NULL,
    gender             VARCHAR(15)  NOT NULL,
    address            VARCHAR(255) NULL,
    role               VARCHAR(15)  NOT NULL,
    state              VARCHAR(15)  NOT NULL,
    created_date       TIMESTAMP(6) NULL,
    last_modified_date TIMESTAMP(6) NULL,
    deleted_date       TIMESTAMP(6) NULL
);
CREATE TABLE performance
(
    performance_id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    title              VARCHAR(255) NOT NULL,
    image_url          VARCHAR(255) NOT NULL,
    description        VARCHAR(255) NOT NULL,
    start_date         DATE         NOT NULL,
    end_date           DATE         NOT NULL,
    total_time         INT          NOT NULL,
    age_limit          VARCHAR(15)  NOT NULL,
    place_id           BIGINT       NOT NULL,
    created_date       TIMESTAMP(6) NULL,
    last_modified_date TIMESTAMP(6) NULL,
    deleted_date       TIMESTAMP(6) NULL,
    created_by         VARCHAR(255) NULL,
    last_modified_by   VARCHAR(255) NULL
);
CREATE TABLE schedule
(
    schedule_id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    date_time          TIMESTAMP(6) NOT NULL,
    sequence           INT          NOT NULL,
    performance_id     BIGINT       NOT NULL,
    created_date       TIMESTAMP(6) NULL,
    last_modified_date TIMESTAMP(6) NULL,
    deleted_date       TIMESTAMP(6) NULL,
    created_by         VARCHAR(255) NULL,
    last_modified_by   VARCHAR(255) NULL,
    CONSTRAINT fk_performance_schedule FOREIGN KEY (performance_id) REFERENCES performance (performance_id)
);
CREATE TABLE casting
(
    casting_id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name               VARCHAR(15)  NOT NULL,
    role               VARCHAR(20)  NOT NULL,
    image_url          VARCHAR(255) NULL,
    schedule_id        BIGINT       NOT NULL,
    created_date       TIMESTAMP(6) NULL,
    last_modified_date TIMESTAMP(6) NULL,
    deleted_date       TIMESTAMP(6) NULL,
    created_by         VARCHAR(255) NULL,
    last_modified_by   VARCHAR(255) NULL,
    CONSTRAINT fk_schedule_casting FOREIGN KEY (schedule_id) REFERENCES schedule (schedule_id)
);
CREATE TABLE place
(
    place_id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    name               VARCHAR(255) NOT NULL,
    address            VARCHAR(255) NOT NULL,
    created_date       TIMESTAMP(6) NULL,
    last_modified_date TIMESTAMP(6) NULL,
    deleted_date       TIMESTAMP(6) NULL,
    created_by         VARCHAR(255) NULL,
    last_modified_by   VARCHAR(255) NULL
);
CREATE TABLE grade_seat
(
    grade_seat_id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    grade              VARCHAR(15)  NOT NULL,
    seat_count         INT          NOT NULL,
    price              INT          NOT NULL,
    performance_id     BIGINT       NOT NULL,
    created_date       TIMESTAMP(6) NULL,
    last_modified_date TIMESTAMP(6) NULL,
    deleted_date       TIMESTAMP(6) NULL,
    created_by         VARCHAR(255) NULL,
    last_modified_by   VARCHAR(255) NULL,
    INDEX idx_performance_id (performance_id)
);
CREATE TABLE seat
(
    seat_id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    seat_number        VARCHAR(15)  NOT NULL,
    grade_seat_id      BIGINT       NOT NULL,
    is_reserved        BOOLEAN      NOT NULL,
    created_date       TIMESTAMP(6) NULL,
    last_modified_date TIMESTAMP(6) NULL,
    deleted_date       TIMESTAMP(6) NULL,
    created_by         VARCHAR(255) NULL,
    last_modified_by   VARCHAR(255) NULL,
    CONSTRAINT fk_grade_seat_seat FOREIGN KEY (grade_seat_id) REFERENCES grade_seat (grade_seat_id),
    UNIQUE unique_grade_seat_id_seat_number (grade_seat_id, seat_number)
);
CREATE TABLE reservation
(
    reservation_id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    state              VARCHAR(15)  NOT NULL,
    ticket_receipt     VARCHAR(15)  NOT NULL,
    user_id            BIGINT       NOT NULL,
    performance_id     BIGINT       NOT NULL,
    seat_id            BIGINT       NOT NULL,
    created_date       TIMESTAMP(6) NULL,
    last_modified_date TIMESTAMP(6) NULL,
    deleted_date       TIMESTAMP(6) NULL,
    created_by         VARCHAR(255) NULL,
    last_modified_by   VARCHAR(255) NULL,
    INDEX idx_user_id_performance_id (user_id, performance_id)
);
