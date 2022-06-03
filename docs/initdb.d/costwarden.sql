SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for costs
-- ----------------------------
DROP TABLE IF EXISTS `costs`;
CREATE TABLE `costs` (
  `cid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) unsigned DEFAULT NULL,
  `tid` bigint(20) unsigned DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `note` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`cid`),
  KEY `idx_costs_uid` (`uid`) USING BTREE,
  KEY `idx_costs_tid` (`tid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of costs
-- ----------------------------
BEGIN;
INSERT INTO `costs` VALUES (1, 1, 4, 1.20, '早餐', '2022-05-30 16:02:21.322');
INSERT INTO `costs` VALUES (2, 1, 5, 2.51, '洗衣服', '2022-05-30 10:30:00.000');
INSERT INTO `costs` VALUES (4, 1, 5, 10.44, '零食', '2022-05-28 07:30:00.000');
INSERT INTO `costs` VALUES (5, 1, 6, 17.82, '充值', '2022-05-28 09:20:00.000');
INSERT INTO `costs` VALUES (6, 1, 7, 12.23, '书本费', '2022-05-28 10:26:00.000');
INSERT INTO `costs` VALUES (7, 1, 8, 2.73, '寄快递', '2022-05-21 06:30:00.000');
INSERT INTO `costs` VALUES (8, 1, 8, 5.33, '超市', '2022-05-01 14:30:00.000');
INSERT INTO `costs` VALUES (9, 1, 8, 3.21, NULL, '2022-05-01 17:34:00.000');
INSERT INTO `costs` VALUES (10, 1, 6, 10.00, NULL, '2022-04-11 23:10:00.000');
INSERT INTO `costs` VALUES (11, 1, 6, 8.42, NULL, '2022-04-01 18:30:00.000');
INSERT INTO `costs` VALUES (12, 1, 4, 5.33, NULL, '2022-04-01 20:10:00.000');
INSERT INTO `costs` VALUES (13, 1, 4, 6.31, NULL, '2022-04-01 12:40:00.000');
INSERT INTO `costs` VALUES (14, 1, 4, 4.33, NULL, '2022-04-01 05:30:00.000');
INSERT INTO `costs` VALUES (15, 1, 4, 12.10, NULL, '2022-04-01 23:20:00.000');
INSERT INTO `costs` VALUES (16, 1, 4, 5.20, NULL, '2022-02-20 10:11:00.000');
INSERT INTO `costs` VALUES (17, 1, 4, 64.33, '充值', '2022-02-20 23:10:00.000');
INSERT INTO `costs` VALUES (18, 1, 6, 23.11, NULL, '2022-02-20 20:30:00.000');
INSERT INTO `costs` VALUES (19, 1, 4, 52.12, NULL, '2022-02-10 19:24:00.000');
INSERT INTO `costs` VALUES (22, 1, 4, 3.33, '', '2022-05-31 14:12:10.929');
INSERT INTO `costs` VALUES (24, 1, 7, 5.00, '充值', '2022-06-03 20:58:35.402');
COMMIT;

-- ----------------------------
-- Table structure for tags
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `tid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `desc` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`tid`),
  KEY `idx_tags_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of tags
-- ----------------------------
BEGIN;
INSERT INTO `tags` VALUES (4, '餐饮', NULL, NULL);
INSERT INTO `tags` VALUES (5, '交通', NULL, NULL);
INSERT INTO `tags` VALUES (6, '学习', NULL, NULL);
INSERT INTO `tags` VALUES (7, '娱乐', NULL, NULL);
INSERT INTO `tags` VALUES (8, '水电煤', NULL, NULL);
INSERT INTO `tags` VALUES (9, '其他', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_admin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`uid`),
  KEY `idx_users_email` (`email`) USING BTREE,
  KEY `idx_users_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES (1, 'qwqcode@gmail.com', 'admin', 'admin', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
