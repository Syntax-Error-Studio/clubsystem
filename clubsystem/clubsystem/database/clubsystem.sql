-- 校园社团活动报名与签到管理系统初始化脚本
CREATE DATABASE IF NOT EXISTS clubsystem DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE clubsystem;

DROP TABLE IF EXISTS checkin;
DROP TABLE IF EXISTS signup;
DROP TABLE IF EXISTS notice;
DROP TABLE IF EXISTS activity;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  real_name VARCHAR(50) NOT NULL,
  student_no VARCHAR(30),
  phone VARCHAR(20),
  role VARCHAR(20) NOT NULL DEFAULT 'student',
  create_time DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE activity (
  id INT PRIMARY KEY AUTO_INCREMENT,
  category_id INT NOT NULL DEFAULT 1,
  title VARCHAR(120) NOT NULL,
  content TEXT,
  location VARCHAR(120),
  start_time DATETIME,
  end_time DATETIME,
  deadline DATETIME,
  max_count INT NOT NULL DEFAULT 100,
  current_count INT NOT NULL DEFAULT 0,
  creator_id INT,
  status VARCHAR(20) NOT NULL DEFAULT '报名中',
  cover_image VARCHAR(255),
  create_time DATETIME NOT NULL,
  CONSTRAINT fk_activity_creator FOREIGN KEY (creator_id) REFERENCES `user`(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE signup (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL,
  activity_id INT NOT NULL,
  signup_time DATETIME NOT NULL,
  status VARCHAR(20) NOT NULL DEFAULT '已报名',
  CONSTRAINT fk_signup_user FOREIGN KEY (user_id) REFERENCES `user`(id),
  CONSTRAINT fk_signup_activity FOREIGN KEY (activity_id) REFERENCES activity(id),
  UNIQUE KEY uk_signup_user_activity (user_id, activity_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE checkin (
  id INT PRIMARY KEY AUTO_INCREMENT,
  signup_id INT NOT NULL,
  user_id INT NOT NULL,
  activity_id INT NOT NULL,
  checkin_time DATETIME NOT NULL,
  checkin_status VARCHAR(20) NOT NULL DEFAULT '已签到',
  operator_id INT,
  CONSTRAINT fk_checkin_signup FOREIGN KEY (signup_id) REFERENCES signup(id),
  CONSTRAINT fk_checkin_user FOREIGN KEY (user_id) REFERENCES `user`(id),
  CONSTRAINT fk_checkin_activity FOREIGN KEY (activity_id) REFERENCES activity(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE notice (
  id INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(120) NOT NULL,
  content TEXT,
  publisher_id INT,
  create_time DATETIME NOT NULL,
  status VARCHAR(20) NOT NULL DEFAULT '有效',
  CONSTRAINT fk_notice_publisher FOREIGN KEY (publisher_id) REFERENCES `user`(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 初始管理员
INSERT INTO `user`(username, password, real_name, student_no, phone, role, create_time)
VALUES
('admin', 'admin123', '系统管理员', NULL, '13800000000', 'admin', NOW()),
('superadmin', 'superadmin123', '超级管理员', NULL, '13900000000', 'superadmin', NOW());

-- 示例活动
INSERT INTO activity(category_id, title, content, location, start_time, end_time, deadline, max_count, current_count, creator_id, status, create_time)
VALUES
(1, '新生见面会', '欢迎加入社团，进行破冰交流。', '学生活动中心101', '2026-09-10 19:00:00', '2026-09-10 21:00:00', '2026-09-09 23:59:59', 120, 0, 1, '报名中', NOW()),
(2, '技术分享沙龙', '学长学姐做项目经验分享。', '图书馆报告厅', '2026-09-15 14:00:00', '2026-09-15 16:00:00', '2026-09-14 23:59:59', 80, 0, 1, '报名中', NOW());
