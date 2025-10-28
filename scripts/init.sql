CREATE DATABASE dev_TeaWIKI;

use dev_TeaWIKI;

CREATE TABLE users (
    user_id    INT          AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(32)  NOT NULL UNIQUE,
    nickname   VARCHAR(64)  NOT NULL,
    avatar     VARCHAR(255),
    department VARCHAR(32),
    password   CHAR(64)     NOT NULL,
    salt       CHAR(32)     NOT NULL,
    created_at DATETIME     DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted    TINYINT      DEFAULT 0
);

CREATE TABLE teachers (
    teacher_id   INT AUTO_INCREMENT PRIMARY KEY,
    teacher_name VARCHAR(32) NOT NULL,
    department   VARCHAR(32),
    created_at   DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at   DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted      TINYINT  DEFAULT 0
);

CREATE TABLE comments (
    comment_id   INT           AUTO_INCREMENT PRIMARY KEY,
    user_id      INT           NOT NULL,
    teacher_id   INT           NOT NULL,
    content      TEXT          NOT NULL,
    rating       DECIMAL(2, 1) CHECK (rating >= 0 AND rating <= 5),
    likes        INT           DEFAULT 0,
    dislikes     INT           DEFAULT 0,
    comment_time DATETIME      DEFAULT CURRENT_TIMESTAMP,
    updated_at   DATETIME      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted      TINYINT       DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id),
    UNIQUE KEY uk_user_teacher (user_id, teacher_id)
);

CREATE TABLE comment_votes (
    vote_id    INT                      AUTO_INCREMENT PRIMARY KEY,
    user_id    INT                      NOT NULL,
    comment_id INT                      NOT NULL,
    vote_type  ENUM ('like', 'dislike') NOT NULL,
    created_at DATETIME                 DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME                 DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (comment_id) REFERENCES comments (comment_id),
    UNIQUE KEY uk_user_comment (user_id, comment_id)
);

CREATE TABLE courses (
    course_id   INT          AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(32) NOT NULL,
    course_code VARCHAR(16) NOT NULL,
    course_type VARCHAR(32),
    department  VARCHAR(32),
    created_at  DATETIME     DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted     TINYINT      DEFAULT 0
);

CREATE TABLE course_teacher (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    course_id     INT NOT NULL,
    teacher_id    INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES courses (course_id),
    FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id),
    UNIQUE KEY uk_course_teacher (course_id, teacher_id)
);




