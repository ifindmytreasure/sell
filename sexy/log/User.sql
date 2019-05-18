-- auto Generated on 2019-05-11
-- DROP TABLE IF EXISTS user;
CREATE TABLE user(
	user_id INT (11) NOT NULL AUTO_INCREMENT COMMENT 'userId',
	user_name VARCHAR (50) DEFAULT '' COMMENT 'userName',
	birth DATETIME DEFAULT '1000-01-01 00:00:00' COMMENT 'birth',
	salary DECIMAL (13,4) DEFAULT -1 COMMENT '用户薪水',
INDEX `ix_user_name_salary`(user_name,salary),
	PRIMARY KEY (user_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'user';
show tables ;
desc user;