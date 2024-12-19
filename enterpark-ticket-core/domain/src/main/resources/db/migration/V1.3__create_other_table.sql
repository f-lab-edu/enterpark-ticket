CREATE TABLE IF NOT EXISTS performance
(
    performance_id     BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '공연 id',
    title              VARCHAR(255) NOT NULL COMMENT '제목',
    image_url          VARCHAR(255) NOT NULL COMMENT '이미지',
    description        VARCHAR(255) NOT NULL COMMENT '설명',
    start_date         DATE         NOT NULL COMMENT '시작 날짜',
    end_date           DATE         NOT NULL COMMENT '종료 날짜',
    total_time         INT          NOT NULL COMMENT '총 시간',
    age_limit          VARCHAR(15)  NOT NULL COMMENT '관람연령',
    place_id           BIGINT       NOT NULL COMMENT '장소 id',
    created_date       TIMESTAMP(6) NULL COMMENT '생성일',
    last_modified_date TIMESTAMP(6) NULL COMMENT '수정일',
    deleted_date       TIMESTAMP(6) NULL COMMENT '삭제일',
    created_by         VARCHAR(255) NULL COMMENT '생성자',
    last_modified_by   VARCHAR(255) NULL COMMENT '수정자'
);
CREATE TABLE IF NOT EXISTS schedule
(
    schedule_id        BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '스케줄 id',
    date_time          TIMESTAMP(6) NOT NULL COMMENT '날짜',
    sequence           INT          NOT NULL COMMENT '회차',
    performance_id     BIGINT       NOT NULL COMMENT '공연 id',
    created_date       TIMESTAMP(6) NULL COMMENT '생성일',
    last_modified_date TIMESTAMP(6) NULL COMMENT '수정일',
    deleted_date       TIMESTAMP(6) NULL COMMENT '삭제일',
    created_by         VARCHAR(255) NULL COMMENT '생성자',
    last_modified_by   VARCHAR(255) NULL COMMENT '수정자',
    CONSTRAINT fk_performance_schedule FOREIGN KEY (performance_id) REFERENCES performance (performance_id)
);
CREATE TABLE IF NOT EXISTS casting
(
    casting_id         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '캐스팅 id',
    name               VARCHAR(15)  NOT NULL COMMENT '이름',
    role               VARCHAR(20)  NOT NULL COMMENT '역할',
    image_url          VARCHAR(255) NULL COMMENT '이미지',
    schedule_id        BIGINT       NOT NULL COMMENT '스케줄 id',
    created_date       TIMESTAMP(6) NULL COMMENT '생성일',
    last_modified_date TIMESTAMP(6) NULL COMMENT '수정일',
    deleted_date       TIMESTAMP(6) NULL COMMENT '삭제일',
    created_by         VARCHAR(255) NULL COMMENT '생성자',
    last_modified_by   VARCHAR(255) NULL COMMENT '수정자',
    CONSTRAINT fk_schedule_casting FOREIGN KEY (schedule_id) REFERENCES schedule (schedule_id)
);
CREATE TABLE IF NOT EXISTS place
(
    place_id           BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '장소 id',
    name               VARCHAR(255) NOT NULL COMMENT '공연장 이름',
    address            VARCHAR(255) NOT NULL COMMENT '주소',
    created_date       TIMESTAMP(6) NULL COMMENT '생성일',
    last_modified_date TIMESTAMP(6) NULL COMMENT '수정일',
    deleted_date       TIMESTAMP(6) NULL COMMENT '삭제일',
    created_by         VARCHAR(255) NULL COMMENT '생성자',
    last_modified_by   VARCHAR(255) NULL COMMENT '수정자'
);
CREATE TABLE IF NOT EXISTS seat
(
    seat_id            BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '좌석 id',
    grade              VARCHAR(15)  NOT NULL COMMENT '등급',
    seat_number        VARCHAR(15)  NOT NULL COMMENT '좌석 번호',
    price              INT          NOT NULL COMMENT '가격',
    performance_id     BIGINT       NOT NULL COMMENT '공연 id',
    is_reserved        BOOLEAN      NOT NULL COMMENT '예매 여부',
    place_id           BIGINT       NOT NULL COMMENT '장소 id',
    created_date       TIMESTAMP(6) NULL COMMENT '생성일',
    last_modified_date TIMESTAMP(6) NULL COMMENT '수정일',
    deleted_date       TIMESTAMP(6) NULL COMMENT '삭제일',
    created_by         VARCHAR(255) NULL COMMENT '생성자',
    last_modified_by   VARCHAR(255) NULL COMMENT '수정자',
    CONSTRAINT fk_place_seat FOREIGN KEY (place_id) REFERENCES place (place_id)
);
CREATE TABLE IF NOT EXISTS reservation
(
    reservation_id     BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '예매 id',
    state              VARCHAR(15)  NOT NULL COMMENT '상태',
    ticket_receipt     VARCHAR(15)  NOT NULL COMMENT '티켓 수령 방법',
    user_id            BIGINT       NOT NULL COMMENT '회원 id',
    performance_id     BIGINT       NOT NULL COMMENT '공연 id',
    seat_id            BIGINT       NOT NULL COMMENT '좌석 id',
    created_date       TIMESTAMP(6) NULL COMMENT '생성일',
    last_modified_date TIMESTAMP(6) NULL COMMENT '수정일',
    deleted_date       TIMESTAMP(6) NULL COMMENT '삭제일',
    created_by         VARCHAR(255) NULL COMMENT '생성자',
    last_modified_by   VARCHAR(255) NULL COMMENT '수정자'
);
