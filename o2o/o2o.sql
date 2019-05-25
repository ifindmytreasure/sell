/*
 Navicat Premium Data Transfer

 Source Server         : zddatabase
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : o2o

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 25/05/2019 11:33:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_area
-- ----------------------------
DROP TABLE IF EXISTS `tb_area`;
CREATE TABLE `tb_area`  (
  `area_id` int(2) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `priority` int(2) NOT NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `last_edit_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`area_id`) USING BTREE,
  UNIQUE INDEX `UK_AREA`(`area_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_area
-- ----------------------------
INSERT INTO `tb_area` VALUES (2, '北苑', 1, '2019-01-31 16:13:45', '2019-01-31 16:13:48');
INSERT INTO `tb_area` VALUES (3, '西苑', 2, '2019-01-31 16:13:50', '2019-01-31 16:13:54');

-- ----------------------------
-- Table structure for tb_award
-- ----------------------------
DROP TABLE IF EXISTS `tb_award`;
CREATE TABLE `tb_award`  (
  `award_id` int(10) NOT NULL AUTO_INCREMENT,
  `award_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `award_desc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `award_img` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `point` int(10) NOT NULL DEFAULT 0,
  `priority` int(2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `last_edit_time` datetime(0) NULL DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT 0,
  `shop_id` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`award_id`) USING BTREE,
  INDEX `fk_award_shop_idx`(`shop_id`) USING BTREE,
  CONSTRAINT `fk_award_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_award
-- ----------------------------
INSERT INTO `tb_award` VALUES (2, '冰箱', '耐用', '\\upload\\item\\shop\\1\\2019031210273918119.jpg', 10, 4, '2019-03-03 16:43:14', '2019-04-09 22:21:09', 0, 1);
INSERT INTO `tb_award` VALUES (3, '脸盆', '塑料脸盆', '\\upload\\item\\shop\\1\\2019031114183332698.jpg', 3, 4, '2019-03-10 18:47:01', '2019-03-11 14:18:33', 1, 1);
INSERT INTO `tb_award` VALUES (4, '西瓜', '又大又甜', '\\upload\\item\\shop\\1\\2019031114291515841.jpg', 5, 10, '2019-03-11 14:29:15', '2019-04-10 10:29:48', 1, 1);

-- ----------------------------
-- Table structure for tb_head_line
-- ----------------------------
DROP TABLE IF EXISTS `tb_head_line`;
CREATE TABLE `tb_head_line`  (
  `line_id` int(100) NOT NULL AUTO_INCREMENT,
  `line_name` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `line_link` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `line_img` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `priority` int(2) NULL DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT 0 COMMENT '0:不可用，1：可用',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `last_edit_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`line_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_head_line
-- ----------------------------
INSERT INTO `tb_head_line` VALUES (1, '2', 'shopdetail?shopId=1', '/upload/item/headtitle/2017061320315746624.jpg', 1, 1, '2019-01-29 16:51:33', '2019-01-29 16:51:36');
INSERT INTO `tb_head_line` VALUES (2, '3', 'test2', '/upload/item/headtitle/2017061320371786788.jpg', 2, 1, '2019-01-29 16:52:36', '2019-01-29 16:52:39');
INSERT INTO `tb_head_line` VALUES (3, '4', 'test3', '/upload/item/headtitle/2017061320393452772.jpg', 3, 1, '2019-01-29 16:52:55', '2019-01-29 16:52:58');
INSERT INTO `tb_head_line` VALUES (4, '5', 'test4', '/upload/item/headtitle/2017061320400198256.jpg', 4, 1, '2019-01-29 18:09:48', '2019-01-29 18:09:52');

-- ----------------------------
-- Table structure for tb_local_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_local_auth`;
CREATE TABLE `tb_local_auth`  (
  `local_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `username` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `last_edit_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`local_auth_id`) USING BTREE,
  UNIQUE INDEX `uk_local_profile`(`username`) USING BTREE,
  INDEX `fk_localauth_profile`(`user_id`) USING BTREE,
  CONSTRAINT `fk_localauth_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_local_auth
-- ----------------------------
INSERT INTO `tb_local_auth` VALUES (1, 1, 'testusername', '6559sq606y6l92e9ss5qsqb5q92e566b', '2019-02-15 13:15:56', '2019-02-15 13:15:56');
INSERT INTO `tb_local_auth` VALUES (2, 2, 'zhaodong', 'b055es00609s922259y5y6ss052eqs52', '2019-03-03 13:45:22', '2019-03-03 13:45:25');
INSERT INTO `tb_local_auth` VALUES (3, 4, 'zhangsan', 's05bse6q2qlb9qblls96s592y55y556s', '2019-04-10 09:15:51', '2019-04-10 09:15:55');

-- ----------------------------
-- Table structure for tb_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_detail`;
CREATE TABLE `tb_order_detail`  (
  `detail_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `order_id` smallint(6) NOT NULL,
  `shop_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_price` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '当前价格',
  `product_quantity` int(11) NOT NULL COMMENT '数量',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`detail_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_order_detail
-- ----------------------------
INSERT INTO `tb_order_detail` VALUES (1, 2, 1, 2, '西红柿', '23', 10, '2019-05-06 10:43:16', '2019-05-06 10:43:16');
INSERT INTO `tb_order_detail` VALUES (2, 3, 1, 1, '面包', '20', 12, '2019-05-06 15:55:28', '2019-05-06 15:55:28');
INSERT INTO `tb_order_detail` VALUES (3, 4, 1, 1, '面包', '20', 21, '2019-05-06 16:00:44', '2019-05-06 16:00:44');
INSERT INTO `tb_order_detail` VALUES (4, 5, 11, 5, '苹果', '70', 21, '2019-05-07 18:42:54', '2019-05-07 18:42:54');
INSERT INTO `tb_order_detail` VALUES (5, 6, 1, 5, '苹果', '70', 12, '2019-05-08 09:00:44', '2019-05-08 09:00:44');

-- ----------------------------
-- Table structure for tb_order_master
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_master`;
CREATE TABLE `tb_order_master`  (
  `order_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `buyer_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家地址',
  `buyer_id` int(10) NOT NULL COMMENT '买家id',
  `order_amount` decimal(8, 2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT 0 COMMENT '订单状态, 默认为新下单',
  `pay_status` tinyint(3) NOT NULL DEFAULT 0 COMMENT '支付状态, 默认未支付',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_order_master
-- ----------------------------
INSERT INTO `tb_order_master` VALUES (2, '李四', '1231451243', '江苏盐城', 4, 35.00, 0, 0, '2019-05-06 10:38:16', '2019-05-06 10:38:19');
INSERT INTO `tb_order_master` VALUES (3, '小明', '2132131', '江苏苏州', 2, 240.00, 0, 0, '2019-05-06 15:55:28', '2019-05-06 15:55:28');
INSERT INTO `tb_order_master` VALUES (4, '小明', '31131', '江苏苏州', 2, 420.00, 0, 0, '2019-05-06 16:00:44', '2019-05-06 16:00:44');
INSERT INTO `tb_order_master` VALUES (5, '小明', '10086', '北京', 2, 1470.00, 0, 0, '2019-05-07 18:42:54', '2019-05-07 18:42:54');
INSERT INTO `tb_order_master` VALUES (6, '小明', '17802532423', '江苏', 2, 840.00, 0, 0, '2019-05-08 09:00:44', '2019-05-08 09:00:44');

-- ----------------------------
-- Table structure for tb_person_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_person_info`;
CREATE TABLE `tb_person_info`  (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `profile_img` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT 0 COMMENT '0:禁止使用本商场，1：允许使用本商场',
  `user_type` int(2) NOT NULL DEFAULT 1 COMMENT '1:顾客,2:商家,3.超级管理员',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `last_edit_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_person_info
-- ----------------------------
INSERT INTO `tb_person_info` VALUES (1, '测试', '/upload/item/user/2.jpg', 'test', '1', 1, 2, '2019-04-10 10:09:25', '2019-04-10 10:09:21');
INSERT INTO `tb_person_info` VALUES (2, '小明', '/upload/item/user/1.gif', 'test', '女', 1, 2, '2019-02-13 13:09:06', '2019-02-13 13:09:06');
INSERT INTO `tb_person_info` VALUES (3, '张三', '/upload/item/user/4.jpg', 'test', '男', 1, 1, '2019-02-13 14:12:58', '2019-04-10 10:09:23');
INSERT INTO `tb_person_info` VALUES (4, '李四', '/upload/item/user/3.jpg', 'test', '男', 1, 3, '2019-04-10 09:14:49', '2019-04-10 09:14:52');

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product`  (
  `product_id` int(100) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_desc` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img_addr` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `normal_price` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `promotion_price` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `last_edit_time` datetime(0) NULL DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT 0,
  `product_category_id` int(11) NULL DEFAULT NULL,
  `shop_id` int(20) NOT NULL DEFAULT 0,
  `point` int(10) NULL DEFAULT 0,
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `fk_product_procate`(`product_category_id`) USING BTREE,
  INDEX `fk_product_shop`(`shop_id`) USING BTREE,
  CONSTRAINT `fk_product_procate` FOREIGN KEY (`product_category_id`) REFERENCES `tb_product_category` (`product_category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_product_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES (1, '面包', '紫米面包，香甜可口', '\\upload\\item\\shop\\1\\2019012912435261063.jpg', '100', '20', 100, '2019-01-22 14:16:56', '2019-03-11 09:17:43', 1, 25, 1, 3);
INSERT INTO `tb_product` VALUES (2, '西红柿', '测试Desc2', 'test2', '30', '23', 2, '2019-01-22 14:16:56', '2019-01-22 14:16:56', 0, 23, 1, 0);
INSERT INTO `tb_product` VALUES (3, '香蕉', '海南香蕉', '\\upload\\item\\shop\\1\\2019012615243872117.jpg', '12', '10', 3, '2019-01-22 14:16:56', '2019-01-26 15:24:37', 1, 22, 1, 0);
INSERT INTO `tb_product` VALUES (4, '雪碧', '测试商品1', '\\upload\\item\\shop\\1\\2019012219385023646.jpg', '5', '3', 20, '2019-01-22 19:38:50', '2019-02-02 12:53:57', 1, 24, 1, 0);
INSERT INTO `tb_product` VALUES (5, '苹果', '测试', '\\upload\\item\\shop\\1\\2019012416102157726.jpg', '90', '70', 100, '2019-01-24 16:10:20', '2019-01-24 16:10:20', 1, 22, 1, 0);
INSERT INTO `tb_product` VALUES (6, '测试商品', '测试商品描述', '\\upload\\item\\shop\\1\\2019012613524312681.jpg', '78', '87', 100, '2019-01-26 13:52:43', '2019-01-26 13:52:43', 1, 21, 1, 0);
INSERT INTO `tb_product` VALUES (7, '测试10', '测试', '\\upload\\item\\shop\\1\\2019012613561524832.gif', '56', '34', 67, '2019-01-26 13:56:15', '2019-01-26 13:56:15', 1, 23, 1, 0);
INSERT INTO `tb_product` VALUES (12, '拉面', '好吃', '\\upload\\item\\shop\\1\\2019030314443127597.png', '15', '13', 12, '2019-03-03 14:44:31', '2019-03-03 14:44:31', 1, 25, 1, 12);

-- ----------------------------
-- Table structure for tb_product_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_category`;
CREATE TABLE `tb_product_category`  (
  `product_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_category_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `priority` int(2) NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `shop_id` int(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`product_category_id`) USING BTREE,
  INDEX `fk_procate_shop`(`shop_id`) USING BTREE,
  CONSTRAINT `fk_procate_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_product_category
-- ----------------------------
INSERT INTO `tb_product_category` VALUES (21, '饮品', 1, '2019-01-29 12:55:28', 1);
INSERT INTO `tb_product_category` VALUES (22, '水果', 2, '2019-01-29 12:55:36', 1);
INSERT INTO `tb_product_category` VALUES (23, '生活用品', 100, '2019-01-29 12:56:14', 1);
INSERT INTO `tb_product_category` VALUES (24, '床单', 20, '2019-01-29 12:56:14', 1);
INSERT INTO `tb_product_category` VALUES (25, '面点', 3, '2019-02-19 12:01:32', 1);

-- ----------------------------
-- Table structure for tb_product_img
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_img`;
CREATE TABLE `tb_product_img`  (
  `product_img_id` int(20) NOT NULL AUTO_INCREMENT,
  `img_addr` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img_desc` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `priority` int(2) NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `product_id` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`product_img_id`) USING BTREE,
  INDEX `fk_proimg_product`(`product_id`) USING BTREE,
  CONSTRAINT `fk_proimg_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_product_img
-- ----------------------------
INSERT INTO `tb_product_img` VALUES (8, '\\upload\\item\\shop\\1\\2019012416102179945.jpg', NULL, NULL, '2019-01-24 16:10:21', 5);
INSERT INTO `tb_product_img` VALUES (9, '\\upload\\item\\shop\\1\\2019012613524439567.jpg', NULL, NULL, '2019-01-26 13:52:44', 6);
INSERT INTO `tb_product_img` VALUES (10, '\\upload\\item\\shop\\1\\2019012613524459829.jpg', NULL, NULL, '2019-01-26 13:52:44', 6);
INSERT INTO `tb_product_img` VALUES (11, '\\upload\\item\\shop\\1\\2019012613561626648.jpg', NULL, NULL, '2019-01-26 13:56:16', 7);
INSERT INTO `tb_product_img` VALUES (18, '\\upload\\item\\shop\\1\\2019012615243997474.jpg', NULL, NULL, '2019-01-26 15:24:39', 3);
INSERT INTO `tb_product_img` VALUES (19, '\\upload\\item\\shop\\1\\2019012615243913230.jpg', NULL, NULL, '2019-01-26 15:24:39', 3);
INSERT INTO `tb_product_img` VALUES (24, '\\upload\\item\\shop\\1\\2019030314443158132.png', NULL, NULL, '2019-03-03 14:44:31', 12);
INSERT INTO `tb_product_img` VALUES (25, '\\upload\\item\\shop\\1\\2019030314443138548.jpeg', NULL, NULL, '2019-03-03 14:44:31', 12);
INSERT INTO `tb_product_img` VALUES (26, '\\upload\\item\\shop\\1\\2019012416102179945.jpg', NULL, NULL, '2019-04-10 09:43:40', 1);

-- ----------------------------
-- Table structure for tb_product_sell_daily
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_sell_daily`;
CREATE TABLE `tb_product_sell_daily`  (
  `product_sell_daily_id` int(100) NOT NULL AUTO_INCREMENT,
  `product_id` int(100) NULL DEFAULT NULL,
  `shop_id` int(10) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `total` int(10) NULL DEFAULT 0,
  PRIMARY KEY (`product_sell_daily_id`) USING BTREE,
  UNIQUE INDEX `uc_product_sell`(`product_id`, `shop_id`, `create_time`) USING BTREE,
  INDEX `fk_product_sell_product`(`product_id`) USING BTREE,
  INDEX `fk_product_sell_shop`(`shop_id`) USING BTREE,
  CONSTRAINT `fk_product_sell_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_product_sell_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_product_sell_daily
-- ----------------------------
INSERT INTO `tb_product_sell_daily` VALUES (22, 1, 11, '2019-04-08 00:00:00', 1);
INSERT INTO `tb_product_sell_daily` VALUES (23, 2, 1, '2019-04-17 00:00:00', 1);
INSERT INTO `tb_product_sell_daily` VALUES (24, 3, 1, '2019-03-15 00:00:00', 2);
INSERT INTO `tb_product_sell_daily` VALUES (32, 2, 1, '2019-04-21 00:00:00', 1);
INSERT INTO `tb_product_sell_daily` VALUES (33, 2, 1, '2019-04-20 00:00:00', 1);
INSERT INTO `tb_product_sell_daily` VALUES (34, 2, 1, '2019-04-07 02:00:00', 1);
INSERT INTO `tb_product_sell_daily` VALUES (36, 1, 11, '2019-04-18 00:00:00', 3);
INSERT INTO `tb_product_sell_daily` VALUES (37, 1, 11, '2019-03-15 00:00:00', 5);
INSERT INTO `tb_product_sell_daily` VALUES (38, 1, 11, '2019-04-15 00:00:00', 0);
INSERT INTO `tb_product_sell_daily` VALUES (39, 1, 11, '2019-04-21 00:00:00', 7);
INSERT INTO `tb_product_sell_daily` VALUES (40, 1, 11, '2019-03-19 00:00:00', 2);
INSERT INTO `tb_product_sell_daily` VALUES (41, 1, 11, '2019-04-05 00:00:00', 4);
INSERT INTO `tb_product_sell_daily` VALUES (42, 2, 1, '2019-03-04 00:00:00', 2);
INSERT INTO `tb_product_sell_daily` VALUES (43, 2, 1, '2019-04-15 00:00:00', 0);
INSERT INTO `tb_product_sell_daily` VALUES (44, 2, 1, '2019-03-21 00:00:00', 4);
INSERT INTO `tb_product_sell_daily` VALUES (45, 3, 1, '2019-03-02 00:00:00', 2);
INSERT INTO `tb_product_sell_daily` VALUES (46, 3, 1, '2019-04-15 00:00:00', 2);
INSERT INTO `tb_product_sell_daily` VALUES (47, 3, 1, '2019-04-17 00:00:00', 1);
INSERT INTO `tb_product_sell_daily` VALUES (48, 3, 1, '2019-04-14 00:00:00', 0);
INSERT INTO `tb_product_sell_daily` VALUES (49, 3, 1, '2019-03-06 00:00:00', 3);
INSERT INTO `tb_product_sell_daily` VALUES (50, 3, 1, '2019-03-07 00:00:00', 5);

-- ----------------------------
-- Table structure for tb_shop
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop`;
CREATE TABLE `tb_shop`  (
  `shop_id` int(10) NOT NULL AUTO_INCREMENT,
  `owner_id` int(10) NOT NULL COMMENT '店铺创建人',
  `area_id` int(5) NULL DEFAULT NULL,
  `shop_category_id` int(11) NULL DEFAULT NULL,
  `shop_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_desc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shop_addr` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shop_img` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `priority` int(3) NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `last_edit_time` datetime(0) NULL DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT 0,
  `advice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`shop_id`) USING BTREE,
  INDEX `fk_shop_area`(`area_id`) USING BTREE,
  INDEX `fk_shop_profile`(`owner_id`) USING BTREE,
  INDEX `fk_shop_shopcate`(`shop_category_id`) USING BTREE,
  CONSTRAINT `fk_shop_area` FOREIGN KEY (`area_id`) REFERENCES `tb_area` (`area_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_shop_profile` FOREIGN KEY (`owner_id`) REFERENCES `tb_person_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_shop_shopcate` FOREIGN KEY (`shop_category_id`) REFERENCES `tb_shop_category` (`shop_category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_shop
-- ----------------------------
INSERT INTO `tb_shop` VALUES (1, 2, 2, 8, '食品店', '味道醇厚', 'test', 'test', '\\upload\\item\\shop\\1\\2019013120345178365.jpg', 1, '2018-12-24 15:26:00', '2019-04-10 22:58:05', 1, '审核通过');
INSERT INTO `tb_shop` VALUES (11, 1, 2, 9, '停车吃饭', '饭菜可口，价格合适', 'test11', 'test11', '/upload/item/shop/11/2019012218173715989.jpg', 2, '2018-12-25 16:25:48', '2019-04-10 22:56:43', 0, '审核通过');
INSERT INTO `tb_shop` VALUES (12, 1, 2, 1, 'test3', 'test3', 'test3', 'test3', '/upload/item/shop/12/2018122714531591855.jpg', NULL, '2018-12-27 14:53:15', '2018-12-27 14:53:15', 1, '审核通过');
INSERT INTO `tb_shop` VALUES (24, 1, 3, 2, '500cc', '香喷喷的奶茶，你值得拥有', '测试', '4132', '/upload/item/shop/24/2018123112503284866.jpg', 4, '2018-12-31 12:50:32', '2019-04-10 22:58:10', 0, '审核通过啦');
INSERT INTO `tb_shop` VALUES (25, 1, 2, 2, '北京朝阳区分店', '测试', '测试', '4132', '/upload/item/shop/25/2018123113225435418.jpg', NULL, '2018-12-31 13:22:54', '2018-12-31 13:22:54', 1, '审核通过');
INSERT INTO `tb_shop` VALUES (26, 2, 2, 2, '奶茶', '测试', '测试', '12312312', '\\upload\\item\\shop\\26\\2019041820363787524.jpg', NULL, '2019-04-18 20:36:37', '2019-04-18 20:36:37', 1, '审核通过');

-- ----------------------------
-- Table structure for tb_shop_auth_map
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop_auth_map`;
CREATE TABLE `tb_shop_auth_map`  (
  `shop_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `employee_id` int(10) NOT NULL,
  `shop_id` int(10) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `title_flag` int(2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `last_edit_time` datetime(0) NULL DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`shop_auth_id`) USING BTREE,
  INDEX `fk_shop_auth_map_shop`(`shop_id`) USING BTREE,
  INDEX `uk_shop_auth_map`(`employee_id`, `shop_id`) USING BTREE,
  CONSTRAINT `fk_shop_auth_map_employee` FOREIGN KEY (`employee_id`) REFERENCES `tb_person_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_shop_auth_map_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_shop_auth_map
-- ----------------------------
INSERT INTO `tb_shop_auth_map` VALUES (2, 1, 1, '打工仔', 2, '2019-03-05 11:12:43', '2019-03-13 18:51:33', 1);
INSERT INTO `tb_shop_auth_map` VALUES (3, 2, 1, '服务员', 2, '2019-03-06 10:51:19', '2019-04-10 22:36:53', 1);
INSERT INTO `tb_shop_auth_map` VALUES (4, 2, 26, '店家', 0, '2019-04-18 20:36:38', '2019-04-18 20:36:38', 1);

-- ----------------------------
-- Table structure for tb_shop_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop_category`;
CREATE TABLE `tb_shop_category`  (
  `shop_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_category_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `shop_category_desc` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `shop_category_img` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `last_edit_time` datetime(0) NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`shop_category_id`) USING BTREE,
  INDEX `fk_shop_category_self`(`parent_id`) USING BTREE,
  CONSTRAINT `fk_shop_category_self` FOREIGN KEY (`parent_id`) REFERENCES `tb_shop_category` (`shop_category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_shop_category
-- ----------------------------
INSERT INTO `tb_shop_category` VALUES (1, '咖啡奶茶', '香甜可口', '/upload/item/shopcategory/2017060420464594520.png', 1, '2019-01-29 16:42:28', '2019-01-29 16:42:32', NULL);
INSERT INTO `tb_shop_category` VALUES (2, '500cc', '测试', '/upload/item/shopcategory/2017060422121144586.png', 2, '2018-12-30 16:21:45', '2018-12-30 16:21:55', 5);
INSERT INTO `tb_shop_category` VALUES (3, '二手市场', '二手商品交易', '/upload/item/shopcategory/2017061223272255687.png', 100, '2019-01-29 18:47:09', '2019-01-29 18:47:13', NULL);
INSERT INTO `tb_shop_category` VALUES (4, '美容美发', '美容美发', '/upload/item/shopcategory/2017060420372391702.png', 99, '2019-01-29 18:47:44', '2019-01-29 18:47:47', NULL);
INSERT INTO `tb_shop_category` VALUES (5, '休闲娱乐', '休闲娱乐', '/upload/item/shopcategory/2017061223275121460.png', 88, '2019-01-29 18:48:19', '2019-01-29 18:48:21', NULL);
INSERT INTO `tb_shop_category` VALUES (6, '培训教育', 'IT培训、学科教育', '/upload/item/shopcategory/2017061223274213433.png', 77, '2019-01-29 18:49:10', '2019-01-29 18:49:13', NULL);
INSERT INTO `tb_shop_category` VALUES (7, '租赁市场', '提供各种设备租赁', '/upload/item/shopcategory/2017061223273314635.png', 99, '2019-01-29 18:51:05', '2019-01-29 18:51:08', NULL);
INSERT INTO `tb_shop_category` VALUES (8, '电器城', '出售各种家用电器', '/upload/item/shopcategory/2017060422114076152.png', 66, '2019-01-29 18:52:18', '2019-01-29 18:52:21', 3);
INSERT INTO `tb_shop_category` VALUES (9, '大排档', '各种炒菜', '/upload/item/shopcategory/2017060420460491494.png', 55, '2019-01-31 15:51:50', '2019-01-31 15:51:53', 5);

-- ----------------------------
-- Table structure for tb_user_award_map
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_award_map`;
CREATE TABLE `tb_user_award_map`  (
  `user_award_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `award_id` int(10) NOT NULL,
  `shop_id` int(10) NOT NULL,
  `operator_id` int(10) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `used_status` int(2) NOT NULL DEFAULT 0,
  `point` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`user_award_id`) USING BTREE,
  INDEX `fk_user_award_map_profile`(`user_id`) USING BTREE,
  INDEX `fk_user_award_map_award`(`award_id`) USING BTREE,
  INDEX `fk_user_award_map_shop`(`shop_id`) USING BTREE,
  INDEX `fk_user_award_map_operator`(`operator_id`) USING BTREE,
  CONSTRAINT `fk_user_award_map_award` FOREIGN KEY (`award_id`) REFERENCES `tb_award` (`award_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_award_map_operator` FOREIGN KEY (`operator_id`) REFERENCES `tb_person_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_award_map_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_award_map_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user_award_map
-- ----------------------------
INSERT INTO `tb_user_award_map` VALUES (1, 2, 2, 1, 2, '2019-03-03 18:50:57', 1, 10);
INSERT INTO `tb_user_award_map` VALUES (2, 2, 2, 1, 1, '2019-03-03 18:50:58', 0, 10);
INSERT INTO `tb_user_award_map` VALUES (3, 3, 3, 1, 2, '2019-03-10 18:47:57', 0, 3);
INSERT INTO `tb_user_award_map` VALUES (4, 2, 4, 1, 2, '2019-03-12 10:53:51', 1, 5);
INSERT INTO `tb_user_award_map` VALUES (5, 2, 3, 1, NULL, '2019-04-09 22:30:52', 0, 3);

-- ----------------------------
-- Table structure for tb_user_product_map
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_product_map`;
CREATE TABLE `tb_user_product_map`  (
  `user_product_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NULL DEFAULT NULL,
  `product_id` int(100) NULL DEFAULT NULL,
  `shop_id` int(10) NULL DEFAULT NULL,
  `operator_id` int(10) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `point` int(10) NULL DEFAULT 0,
  PRIMARY KEY (`user_product_id`) USING BTREE,
  INDEX `fk_user_product_map_profile`(`user_id`) USING BTREE,
  INDEX `fk_user_product_map_product`(`product_id`) USING BTREE,
  INDEX `fk_user_product_map_shop`(`shop_id`) USING BTREE,
  INDEX `fk_user_product_map_operator`(`operator_id`) USING BTREE,
  CONSTRAINT `fk_user_product_map_operator` FOREIGN KEY (`operator_id`) REFERENCES `tb_person_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_product_map_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_product_map_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_product_map_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user_product_map
-- ----------------------------
INSERT INTO `tb_user_product_map` VALUES (1, 2, 1, 11, 2, '2019-03-08 10:01:55', 5);
INSERT INTO `tb_user_product_map` VALUES (2, 2, 3, 1, 2, '2019-03-08 10:01:56', 6);
INSERT INTO `tb_user_product_map` VALUES (3, 2, 3, 1, 2, '2019-03-08 10:01:56', 4);
INSERT INTO `tb_user_product_map` VALUES (4, 3, 2, 1, 2, '2019-03-08 14:30:15', 4);
INSERT INTO `tb_user_product_map` VALUES (5, 3, 2, 1, 2, '2019-03-07 14:12:38', 3);
INSERT INTO `tb_user_product_map` VALUES (6, 2, 2, 1, 3, '2019-03-06 14:13:08', 3);
INSERT INTO `tb_user_product_map` VALUES (7, 2, 2, 11, 3, '2019-03-05 14:14:08', 4);

-- ----------------------------
-- Table structure for tb_user_shop_map
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_shop_map`;
CREATE TABLE `tb_user_shop_map`  (
  `user_shop_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `shop_id` int(10) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `point` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`user_shop_id`) USING BTREE,
  UNIQUE INDEX `uq_user_shop`(`user_id`, `shop_id`) USING BTREE,
  INDEX `fk_user_shop_shop`(`shop_id`) USING BTREE,
  CONSTRAINT `fk_user_shop_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_shop_user` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user_shop_map
-- ----------------------------
INSERT INTO `tb_user_shop_map` VALUES (1, 2, 1, '2019-03-05 10:38:01', 0);
INSERT INTO `tb_user_shop_map` VALUES (2, 1, 11, '2019-03-05 10:38:03', 15);
INSERT INTO `tb_user_shop_map` VALUES (3, 2, 12, '2019-03-13 15:41:04', 20);

-- ----------------------------
-- Table structure for tb_wechat_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_wechat_auth`;
CREATE TABLE `tb_wechat_auth`  (
  `wechat_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `open_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`wechat_auth_id`) USING BTREE,
  UNIQUE INDEX `open_id`(`open_id`) USING BTREE,
  INDEX `fk_wechatauth_profile`(`user_id`) USING BTREE,
  CONSTRAINT `fk_wechatauth_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_wechat_auth
-- ----------------------------
INSERT INTO `tb_wechat_auth` VALUES (1, 2, 'abcdefg', '2019-02-13 13:19:38');
INSERT INTO `tb_wechat_auth` VALUES (2, 3, '12345', '2019-02-13 14:12:58');
INSERT INTO `tb_wechat_auth` VALUES (4, 1, '123456', '2019-02-13 14:18:52');

SET FOREIGN_KEY_CHECKS = 1;
