/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80028 (8.0.28)
 Source Host           : localhost:3306
 Source Schema         : stu-mana-sys

 Target Server Type    : MySQL
 Target Server Version : 80028 (8.0.28)
 File Encoding         : 65001

 Date: 27/04/2024 16:05:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for eas_base_course
-- ----------------------------
DROP TABLE IF EXISTS `eas_base_course`;
CREATE TABLE `eas_base_course`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `coursename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名',
  `synopsis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of eas_base_course
-- ----------------------------
INSERT INTO `eas_base_course` VALUES (1, 'C语言程序设计', '本课程介绍计算机结构化程序设计的思想、方法和技巧');
INSERT INTO `eas_base_course` VALUES (2, '数据结构与算法', '数据结构是计算机科学的一门非常重要的专业基础课');
INSERT INTO `eas_base_course` VALUES (3, '操作系统', '操作系统课程内容综合了基础理论教学、课程实践教学、最新技术追踪等多项内容。');
INSERT INTO `eas_base_course` VALUES (4, '计算机网络', '计算机专业学生必修的一门专业基础课和核心课程');
INSERT INTO `eas_base_course` VALUES (5, '软件工程', '该课程主要介绍软件工程的基本概念、原理和典型的技术方法。');
INSERT INTO `eas_base_course` VALUES (6, 'Linux入门', '本课程以Redhat Linux系统为基础，对Linux系统做一个简洁而全面的介绍，使学生在较短时间内对该操作系统有个大概的了解。');
INSERT INTO `eas_base_course` VALUES (7, 'java程序设计语言', '本课程介绍Java语言的基础语法，让学生了解面向对象的程序设计思想');
INSERT INTO `eas_base_course` VALUES (8, 'C++程序设计语言', '本课程介绍C++语言的基础语法，让学生了解面向对象的程序设计思想');
INSERT INTO `eas_base_course` VALUES (9, '中国近现代史纲要', '介绍中国近现代的历史');
INSERT INTO `eas_base_course` VALUES (10, '马克思主义基本原理', '介绍马克思主义的基本原理');
INSERT INTO `eas_base_course` VALUES (13, '软件工程实训', '小组成员合作开发，培养学生的开发能力以及团队协作能力');
INSERT INTO `eas_base_course` VALUES (14, '硬件实训', '每名学生独立完成心形灯的焊接，并编写程序');
INSERT INTO `eas_base_course` VALUES (15, '计算机组成原理', '计算机专业核心发展课程，帮助学生认知计算机内部的运作过程');
INSERT INTO `eas_base_course` VALUES (16, '计算机图形学', '了解计算机绘图的基本原理，能够用matlab绘制一些简单的图案');

-- ----------------------------
-- Table structure for eas_course
-- ----------------------------
DROP TABLE IF EXISTS `eas_course`;
CREATE TABLE `eas_course`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `start_date` date NULL DEFAULT NULL COMMENT '开设日期',
  `end_date` date NULL DEFAULT NULL COMMENT '结束日期',
  `class_hour` smallint NULL DEFAULT NULL COMMENT '总课时',
  `test_mode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考核方式',
  `student_num` int NULL DEFAULT NULL COMMENT '学生数量',
  `choice_num` int NULL DEFAULT 0 COMMENT '选课人数',
  `complete` int UNSIGNED NULL DEFAULT 0 COMMENT '是否是完成的课程',
  `t_id` int UNSIGNED NOT NULL COMMENT '外键-教师号',
  `base_course_id` int UNSIGNED NOT NULL COMMENT '外键-课程号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `techer_msg`(`t_id` ASC) USING BTREE,
  INDEX `course_mag`(`base_course_id` ASC) USING BTREE,
  CONSTRAINT `course_mag` FOREIGN KEY (`base_course_id`) REFERENCES `eas_base_course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `techer_msg` FOREIGN KEY (`t_id`) REFERENCES `eas_teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of eas_course
-- ----------------------------
INSERT INTO `eas_course` VALUES (7, '2021-06-15', '2021-07-15', 10, '线上考试', 20, 2, 0, 1, 5);
INSERT INTO `eas_course` VALUES (10, '2021-06-15', '2021-07-15', 10, '线上考试', 20, 2, 0, 1, 1);
INSERT INTO `eas_course` VALUES (14, '2021-06-15', '2021-07-15', 10, '线上考试', 20, 2, 0, 2, 6);
INSERT INTO `eas_course` VALUES (16, '2024-04-26', '2024-04-30', 70, '闭卷考试', 20, 2, 0, 2, 15);
INSERT INTO `eas_course` VALUES (17, '2024-04-10', '2024-04-30', 70, '闭卷考试', 20, 2, 0, 3, 13);

-- ----------------------------
-- Table structure for eas_notice
-- ----------------------------
DROP TABLE IF EXISTS `eas_notice`;
CREATE TABLE `eas_notice`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `type` int NOT NULL DEFAULT 3 COMMENT '权限',
  `releasedate` date NOT NULL COMMENT '发布日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of eas_notice
-- ----------------------------
INSERT INTO `eas_notice` VALUES (1, '毕业通知', '管理员', '123sdafdaf', 1, '2024-04-23');
INSERT INTO `eas_notice` VALUES (2, '2018年运动会', '管理员', '456', 1, '2021-04-28');
INSERT INTO `eas_notice` VALUES (3, 'hello', '管理员', '123', 2, '2021-04-28');
INSERT INTO `eas_notice` VALUES (6, 'hello1', '管理员', '1231', 1, '2024-04-27');

-- ----------------------------
-- Table structure for eas_permission
-- ----------------------------
DROP TABLE IF EXISTS `eas_permission`;
CREATE TABLE `eas_permission`  (
  `id` int NOT NULL COMMENT '功能id',
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能名称',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能类型',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `percode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '别名',
  `parentid` int NULL DEFAULT NULL COMMENT '父级编号',
  `sortstring` int NULL DEFAULT NULL COMMENT '进行排序',
  `available` int NULL DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of eas_permission
-- ----------------------------
INSERT INTO `eas_permission` VALUES (1, '系统管理', 'menu', NULL, 'system:*', NULL, 0, 1);
INSERT INTO `eas_permission` VALUES (2, '用户管理', 'menu', 'easUser/index', 'user:query', 1, 1, 1);
INSERT INTO `eas_permission` VALUES (3, '添加用户', 'function', 'easUser/add', 'user:add', 2, 2, 1);
INSERT INTO `eas_permission` VALUES (4, '删除用户', 'function', 'easUser/delete', 'user:delete', 2, 2, 1);
INSERT INTO `eas_permission` VALUES (5, '修改用户', 'function', 'easUser/update', 'user:update', 2, 2, 1);
INSERT INTO `eas_permission` VALUES (6, '角色管理', 'menu', 'easRole/index', 'role:query', 1, 1, 1);
INSERT INTO `eas_permission` VALUES (7, '添加角色', 'function', 'easRole/add', 'role:add', 6, 3, 1);
INSERT INTO `eas_permission` VALUES (8, '删除角色', 'function', 'easRole/add', 'role:delete', 6, 3, 1);
INSERT INTO `eas_permission` VALUES (9, '修改角色', 'function', 'easRole/update', 'role:update', 6, 3, 1);
INSERT INTO `eas_permission` VALUES (10, '学生信息', 'menu', 'easStudent/index', 'student:query', 18, 1, 1);
INSERT INTO `eas_permission` VALUES (11, '添加学生', 'function', 'easStudent/add', 'student:add', 10, 4, 1);
INSERT INTO `eas_permission` VALUES (12, '删除学生', 'function', 'easStudent/delete', 'student:delete', 10, 4, 1);
INSERT INTO `eas_permission` VALUES (13, '修改学生', 'function', 'easStudent/update', 'student:update', 10, 4, 1);
INSERT INTO `eas_permission` VALUES (14, '权限管理', 'menu', 'easPermission/index', 'permission:query', 1, 1, 1);
INSERT INTO `eas_permission` VALUES (15, '添加权限', 'function', 'easPermission/add', 'permission:add', 14, 2, 1);
INSERT INTO `eas_permission` VALUES (16, '修改权限', 'function', 'easPermission/update', 'permission:update', 14, 2, 1);
INSERT INTO `eas_permission` VALUES (17, '删除权限', 'function', 'easPermission/delete', 'permission:delete', 14, 2, 1);
INSERT INTO `eas_permission` VALUES (18, '信息管理', 'menu', NULL, 'message:*', NULL, 0, 1);
INSERT INTO `eas_permission` VALUES (19, '教师信息', 'menu', 'easTeacher/index', 'teacher:query', 18, 1, 1);
INSERT INTO `eas_permission` VALUES (20, '查看教师', 'function', 'easTeacher/index', 'teacher:query', 19, 5, 1);
INSERT INTO `eas_permission` VALUES (21, '添加教师', 'function', 'easTeacher/add', 'teacher:add', 19, 5, 1);
INSERT INTO `eas_permission` VALUES (22, '删除教师', 'function', 'easTeacher/delete', 'teacher:delete', 19, 5, 1);
INSERT INTO `eas_permission` VALUES (23, '修改教师', 'function', 'easTeacher/update', 'teacher:update', 19, 5, 1);
INSERT INTO `eas_permission` VALUES (24, '查看学生', 'function', 'easStudent/index', 'student:query', 10, 4, 1);
INSERT INTO `eas_permission` VALUES (25, '基本课程管理', 'menu', 'easBaseCourse/index', 'basecourse:query', 18, 1, 1);
INSERT INTO `eas_permission` VALUES (26, '查看基本课程', 'function', 'easBaseCourse/index', 'basecourse:query', 25, 6, 1);
INSERT INTO `eas_permission` VALUES (27, '添加基本课程', 'function', 'easBaseCourse/add', 'basecourse:add', 25, 6, 1);
INSERT INTO `eas_permission` VALUES (28, '删除基本课程', 'function', 'easBaseCourse/delete', 'basecourse:delete', 25, 6, 1);
INSERT INTO `eas_permission` VALUES (29, '修改基本课程', 'function', 'easBaseCourse/update', 'basecourse:update', 25, 6, 1);
INSERT INTO `eas_permission` VALUES (30, '课程管理', 'menu', NULL, 'curriculum:*', NULL, 0, 1);
INSERT INTO `eas_permission` VALUES (31, '课程信息(管理员)', 'menu', 'easCourse/adminIndex', 'course:adminIndex', 30, 1, 1);
INSERT INTO `eas_permission` VALUES (32, '查看课程信息', 'function', 'easCourse/adminIndex', 'course:adminIndex', 31, 7, 1);
INSERT INTO `eas_permission` VALUES (33, '添加课程信息', 'function', 'easCourse/adminAdd', 'course:adminAdd', 31, 7, 1);
INSERT INTO `eas_permission` VALUES (34, '删除课程信息', 'function', 'easCourse/adminDelete', 'course:adminDelete', 31, 7, 1);
INSERT INTO `eas_permission` VALUES (35, '修改课程信息', 'function', 'easCourse/adminUpdate', 'course:adminUpdate', 31, 7, 1);
INSERT INTO `eas_permission` VALUES (36, '课程信息(学生)', 'menu', 'easCourse/studentIndex', 'course:studentIndex', 30, 1, 1);
INSERT INTO `eas_permission` VALUES (37, '选择课程', 'function', 'easCourse/studentSelect', 'course:studentSelect', 36, 8, 1);
INSERT INTO `eas_permission` VALUES (38, '退选课程', 'function', 'easCourse/studentDrop', 'course:studentDrop', 36, 8, 1);
INSERT INTO `eas_permission` VALUES (39, '查看课程', 'function', 'easCourse/studentIndex', 'course:studentIndex', 36, 8, 1);
INSERT INTO `eas_permission` VALUES (40, '课程信息(教师)', 'menu', 'easCourse/teacherIndex', 'course:teacherIndex', 30, 11, 1);
INSERT INTO `eas_permission` VALUES (41, '查看课程', 'function', 'easCourse/teacherIndex', 'course:teacherIndex', 40, 9, 1);
INSERT INTO `eas_permission` VALUES (42, '结束课程', 'function', 'easCourse/teacherEndCourse', 'course:teacherEndCourse', 40, 9, 1);
INSERT INTO `eas_permission` VALUES (43, '成绩管理', 'menu', '', 'result:*', NULL, 0, 1);
INSERT INTO `eas_permission` VALUES (44, '学生成绩列表', 'function', 'easScore/scoreIndex', 'score:scoreIndex', 43, 10, 1);
INSERT INTO `eas_permission` VALUES (45, '我的成绩', 'function', 'easScore/myScoreIndex', 'score:myScoreIndex', 43, 10, 1);
INSERT INTO `eas_permission` VALUES (47, '通知管理', 'menu', 'easNotice/index', 'notice:query', 1, 1, 1);
INSERT INTO `eas_permission` VALUES (48, '添加通知', 'function', 'easNotice/add', 'notice:add', 47, 12, 1);
INSERT INTO `eas_permission` VALUES (49, '删除通知', 'function', 'easNotice/delete', 'notice:delete', 47, 12, 1);
INSERT INTO `eas_permission` VALUES (50, '修改通知', 'function', 'easNotice/update', 'notice:update', 47, 12, 1);
INSERT INTO `eas_permission` VALUES (51, '信息报表', 'menu', '', 'echarts:*', NULL, 0, 1);
INSERT INTO `eas_permission` VALUES (53, '人数报表', 'function', 'easEchart/peopleEchart', 'echart:people', 51, 13, 1);

-- ----------------------------
-- Table structure for eas_role
-- ----------------------------
DROP TABLE IF EXISTS `eas_role`;
CREATE TABLE `eas_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `available` int NOT NULL DEFAULT 0 COMMENT '是否启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of eas_role
-- ----------------------------
INSERT INTO `eas_role` VALUES (1, '超级管理员', 1);
INSERT INTO `eas_role` VALUES (2, '学生', 1);
INSERT INTO `eas_role` VALUES (3, '教师', 1);
INSERT INTO `eas_role` VALUES (5, '通知管理员', 1);

-- ----------------------------
-- Table structure for eas_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `eas_role_permission`;
CREATE TABLE `eas_role_permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `eas_role_id` int NOT NULL COMMENT '角色id',
  `eas_permission_id` int NOT NULL COMMENT '功能id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 827 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of eas_role_permission
-- ----------------------------
INSERT INTO `eas_role_permission` VALUES (421, 3, 18);
INSERT INTO `eas_role_permission` VALUES (422, 3, 10);
INSERT INTO `eas_role_permission` VALUES (423, 3, 24);
INSERT INTO `eas_role_permission` VALUES (424, 3, 19);
INSERT INTO `eas_role_permission` VALUES (425, 3, 20);
INSERT INTO `eas_role_permission` VALUES (426, 3, 23);
INSERT INTO `eas_role_permission` VALUES (427, 3, 25);
INSERT INTO `eas_role_permission` VALUES (428, 3, 26);
INSERT INTO `eas_role_permission` VALUES (429, 3, 30);
INSERT INTO `eas_role_permission` VALUES (430, 3, 40);
INSERT INTO `eas_role_permission` VALUES (431, 3, 41);
INSERT INTO `eas_role_permission` VALUES (432, 3, 42);
INSERT INTO `eas_role_permission` VALUES (433, 3, 46);
INSERT INTO `eas_role_permission` VALUES (434, 3, 43);
INSERT INTO `eas_role_permission` VALUES (435, 3, 44);
INSERT INTO `eas_role_permission` VALUES (436, 3, 51);
INSERT INTO `eas_role_permission` VALUES (437, 3, 52);
INSERT INTO `eas_role_permission` VALUES (438, 3, 53);
INSERT INTO `eas_role_permission` VALUES (444, 5, 1);
INSERT INTO `eas_role_permission` VALUES (445, 5, 47);
INSERT INTO `eas_role_permission` VALUES (446, 5, 48);
INSERT INTO `eas_role_permission` VALUES (447, 5, 49);
INSERT INTO `eas_role_permission` VALUES (448, 5, 50);
INSERT INTO `eas_role_permission` VALUES (449, 5, 51);
INSERT INTO `eas_role_permission` VALUES (450, 5, 52);
INSERT INTO `eas_role_permission` VALUES (451, 5, 53);
INSERT INTO `eas_role_permission` VALUES (565, 1, 1);
INSERT INTO `eas_role_permission` VALUES (566, 1, 2);
INSERT INTO `eas_role_permission` VALUES (567, 1, 3);
INSERT INTO `eas_role_permission` VALUES (568, 1, 4);
INSERT INTO `eas_role_permission` VALUES (569, 1, 5);
INSERT INTO `eas_role_permission` VALUES (570, 1, 6);
INSERT INTO `eas_role_permission` VALUES (571, 1, 7);
INSERT INTO `eas_role_permission` VALUES (572, 1, 8);
INSERT INTO `eas_role_permission` VALUES (573, 1, 9);
INSERT INTO `eas_role_permission` VALUES (574, 1, 14);
INSERT INTO `eas_role_permission` VALUES (575, 1, 15);
INSERT INTO `eas_role_permission` VALUES (576, 1, 16);
INSERT INTO `eas_role_permission` VALUES (577, 1, 17);
INSERT INTO `eas_role_permission` VALUES (578, 1, 47);
INSERT INTO `eas_role_permission` VALUES (579, 1, 48);
INSERT INTO `eas_role_permission` VALUES (580, 1, 49);
INSERT INTO `eas_role_permission` VALUES (581, 1, 50);
INSERT INTO `eas_role_permission` VALUES (582, 1, 18);
INSERT INTO `eas_role_permission` VALUES (583, 1, 10);
INSERT INTO `eas_role_permission` VALUES (584, 1, 11);
INSERT INTO `eas_role_permission` VALUES (585, 1, 12);
INSERT INTO `eas_role_permission` VALUES (586, 1, 13);
INSERT INTO `eas_role_permission` VALUES (587, 1, 24);
INSERT INTO `eas_role_permission` VALUES (588, 1, 19);
INSERT INTO `eas_role_permission` VALUES (589, 1, 20);
INSERT INTO `eas_role_permission` VALUES (590, 1, 21);
INSERT INTO `eas_role_permission` VALUES (591, 1, 22);
INSERT INTO `eas_role_permission` VALUES (592, 1, 23);
INSERT INTO `eas_role_permission` VALUES (593, 1, 25);
INSERT INTO `eas_role_permission` VALUES (594, 1, 26);
INSERT INTO `eas_role_permission` VALUES (595, 1, 27);
INSERT INTO `eas_role_permission` VALUES (596, 1, 28);
INSERT INTO `eas_role_permission` VALUES (597, 1, 29);
INSERT INTO `eas_role_permission` VALUES (598, 1, 54);
INSERT INTO `eas_role_permission` VALUES (599, 1, 55);
INSERT INTO `eas_role_permission` VALUES (600, 1, 56);
INSERT INTO `eas_role_permission` VALUES (601, 1, 57);
INSERT INTO `eas_role_permission` VALUES (602, 1, 58);
INSERT INTO `eas_role_permission` VALUES (603, 1, 30);
INSERT INTO `eas_role_permission` VALUES (604, 1, 31);
INSERT INTO `eas_role_permission` VALUES (605, 1, 32);
INSERT INTO `eas_role_permission` VALUES (606, 1, 33);
INSERT INTO `eas_role_permission` VALUES (607, 1, 34);
INSERT INTO `eas_role_permission` VALUES (608, 1, 35);
INSERT INTO `eas_role_permission` VALUES (609, 1, 51);
INSERT INTO `eas_role_permission` VALUES (610, 1, 52);
INSERT INTO `eas_role_permission` VALUES (611, 1, 53);
INSERT INTO `eas_role_permission` VALUES (820, 2, 30);
INSERT INTO `eas_role_permission` VALUES (821, 2, 31);
INSERT INTO `eas_role_permission` VALUES (822, 2, 36);
INSERT INTO `eas_role_permission` VALUES (823, 2, 40);
INSERT INTO `eas_role_permission` VALUES (824, 2, 43);
INSERT INTO `eas_role_permission` VALUES (826, 2, 45);

-- ----------------------------
-- Table structure for eas_score
-- ----------------------------
DROP TABLE IF EXISTS `eas_score`;
CREATE TABLE `eas_score`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `score` int NULL DEFAULT NULL COMMENT '考试分数',
  `result` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '暂未考核' COMMENT '考试结果',
  `s_id` int UNSIGNED NOT NULL COMMENT '学生id',
  `c_id` int UNSIGNED NOT NULL COMMENT '课程id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `student_msg`(`s_id` ASC) USING BTREE,
  INDEX `course_msg`(`c_id` ASC) USING BTREE,
  CONSTRAINT `course_msg` FOREIGN KEY (`c_id`) REFERENCES `eas_course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_msg` FOREIGN KEY (`s_id`) REFERENCES `eas_student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of eas_score
-- ----------------------------
INSERT INTO `eas_score` VALUES (33, 100, '通过', 2, 7);
INSERT INTO `eas_score` VALUES (34, 100, '通过', 2, 10);
INSERT INTO `eas_score` VALUES (35, 50, '通过', 2, 14);
INSERT INTO `eas_score` VALUES (37, NULL, '暂未考核', 21, 7);
INSERT INTO `eas_score` VALUES (38, NULL, '暂未考核', 21, 10);
INSERT INTO `eas_score` VALUES (39, NULL, '暂未考核', 21, 14);
INSERT INTO `eas_score` VALUES (40, NULL, '暂未考核', 21, 16);
INSERT INTO `eas_score` VALUES (42, NULL, '暂未考核', 21, 17);
INSERT INTO `eas_score` VALUES (43, NULL, '暂未考核', 2, 16);
INSERT INTO `eas_score` VALUES (44, NULL, '暂未考核', 2, 17);

-- ----------------------------
-- Table structure for eas_student
-- ----------------------------
DROP TABLE IF EXISTS `eas_student`;
CREATE TABLE `eas_student`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `motto` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '座右铭',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `username`(`username` ASC) USING BTREE,
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `eas_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of eas_student
-- ----------------------------
INSERT INTO `eas_student` VALUES (2, 'lisi', '李四', '男', '1998-04-15', '15653454542', '略略略');
INSERT INTO `eas_student` VALUES (5, 'zhangsan', '其其', '女', '2014-06-01', '17353218221', '是了就睡');
INSERT INTO `eas_student` VALUES (18, 'test00001', 'test00001', '女', '2016-01-24', '17353218332', '好好学习');
INSERT INTO `eas_student` VALUES (19, 'zhaoyun', '赵云', '男', '2005-04-24', '17353218997', '天天向上');
INSERT INTO `eas_student` VALUES (20, 'test000', 'test000', NULL, NULL, NULL, NULL);
INSERT INTO `eas_student` VALUES (21, 'user', 'user', '男', '2024-04-27', '1775698745', 'qqq');
INSERT INTO `eas_student` VALUES (23, 'user02', 'user02', NULL, NULL, NULL, NULL);
INSERT INTO `eas_student` VALUES (24, 'user03', 'user03', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for eas_teacher
-- ----------------------------
DROP TABLE IF EXISTS `eas_teacher`;
CREATE TABLE `eas_teacher`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师姓名',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '出生年月',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `education` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历',
  `motto` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '座右铭',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_name`(`username` ASC) USING BTREE,
  CONSTRAINT `user_name` FOREIGN KEY (`username`) REFERENCES `eas_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of eas_teacher
-- ----------------------------
INSERT INTO `eas_teacher` VALUES (1, 'wangliu', '王六', '男', '1975-01-15', '13563471234', '博士', '我是最棒的！');
INSERT INTO `eas_teacher` VALUES (2, 'gonggong', '龚工', '男', '1998-04-19', '17353218234', '博士', '哦');
INSERT INTO `eas_teacher` VALUES (3, 'teacher李', 'teacher李', '男', '2024-04-18', '17785236987', '博士', '111');

-- ----------------------------
-- Table structure for eas_user
-- ----------------------------
DROP TABLE IF EXISTS `eas_user`;
CREATE TABLE `eas_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `locked` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否锁定',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of eas_user
-- ----------------------------
INSERT INTO `eas_user` VALUES (1, 'admin', '123456', '0');
INSERT INTO `eas_user` VALUES (2, 'zhangsan', '123456', '0');
INSERT INTO `eas_user` VALUES (3, 'lisi', '1234', '0');
INSERT INTO `eas_user` VALUES (4, 'wangliu', '123456', '0');
INSERT INTO `eas_user` VALUES (5, 'zhaoyun', '123456', '0');
INSERT INTO `eas_user` VALUES (13, 'tongzhiadmin', '123456', '0');
INSERT INTO `eas_user` VALUES (15, 'gonggong', '123456', '0');
INSERT INTO `eas_user` VALUES (24, 'test00001', '111111', '0');
INSERT INTO `eas_user` VALUES (26, 'test', '123456', '0');
INSERT INTO `eas_user` VALUES (27, 'test000', '123456', '0');
INSERT INTO `eas_user` VALUES (28, 'user', '123456', '0');
INSERT INTO `eas_user` VALUES (30, 'user02', '123456', '0');
INSERT INTO `eas_user` VALUES (31, 'user03', '123456', '0');
INSERT INTO `eas_user` VALUES (32, 'teacher李', '123456', '0');

-- ----------------------------
-- Table structure for eas_user_role
-- ----------------------------
DROP TABLE IF EXISTS `eas_user_role`;
CREATE TABLE `eas_user_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `eas_user_id` int NOT NULL COMMENT '用户id',
  `eas_role_id` int NOT NULL DEFAULT 1000 COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userid`(`eas_user_id` ASC) USING BTREE,
  INDEX `roleid`(`eas_role_id` ASC) USING BTREE,
  CONSTRAINT `roleid` FOREIGN KEY (`eas_role_id`) REFERENCES `eas_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userid` FOREIGN KEY (`eas_user_id`) REFERENCES `eas_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of eas_user_role
-- ----------------------------
INSERT INTO `eas_user_role` VALUES (11, 4, 3);
INSERT INTO `eas_user_role` VALUES (16, 13, 5);
INSERT INTO `eas_user_role` VALUES (21, 1, 5);
INSERT INTO `eas_user_role` VALUES (27, 1, 1);
INSERT INTO `eas_user_role` VALUES (29, 15, 3);
INSERT INTO `eas_user_role` VALUES (30, 3, 2);
INSERT INTO `eas_user_role` VALUES (31, 2, 2);
INSERT INTO `eas_user_role` VALUES (32, 24, 2);
INSERT INTO `eas_user_role` VALUES (33, 5, 2);
INSERT INTO `eas_user_role` VALUES (34, 26, 2);
INSERT INTO `eas_user_role` VALUES (35, 27, 2);
INSERT INTO `eas_user_role` VALUES (36, 28, 2);
INSERT INTO `eas_user_role` VALUES (38, 30, 2);
INSERT INTO `eas_user_role` VALUES (39, 31, 2);
INSERT INTO `eas_user_role` VALUES (40, 32, 3);

SET FOREIGN_KEY_CHECKS = 1;
