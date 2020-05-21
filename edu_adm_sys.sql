/*
 Navicat Premium Data Transfer

 Source Server         : mysql8.0.19
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : edu_adm_sys

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 21/05/2020 18:52:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for logging
-- ----------------------------
DROP TABLE IF EXISTS `logging`;
CREATE TABLE `logging`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `message` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `level_string` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '级别',
  `created_time` datetime(0) NOT NULL COMMENT '时间',
  `logger_name` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '全类名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '自定义日志记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of logging
-- ----------------------------
INSERT INTO `logging` VALUES (1, '- 登录失败', 'INFO', '2020-05-19 15:21:54', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (2, '- 有减号', 'INFO', '2020-05-19 15:31:22', 'com.qifangli.edumanage.EdumanageApplicationTests');
INSERT INTO `logging` VALUES (3, '- 登录失败id:123', 'INFO', '2020-05-19 17:47:54', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (4, '- 登录失败id:', 'INFO', '2020-05-19 19:53:35', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (5, '- 验证码错误', 'ERROR', '2020-05-19 22:06:52', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (6, '- 登录成功id:20011103', 'INFO', '2020-05-19 22:07:04', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (7, '- 登录成功id:20011103', 'INFO', '2020-05-19 22:51:53', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (8, '- 验证码错误', 'ERROR', '2020-05-19 22:53:28', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (9, '- 验证码错误', 'ERROR', '2020-05-19 22:53:35', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (10, '- 登录成功id:20011103', 'INFO', '2020-05-19 22:53:44', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (11, '- 登录成功id:20011103', 'INFO', '2020-05-20 00:39:19', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (12, '- 验证码错误', 'ERROR', '2020-05-20 00:57:03', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (13, '- 登录成功id:20011103', 'INFO', '2020-05-20 00:57:47', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (14, '- 登录成功id:20011103', 'INFO', '2020-05-20 10:05:14', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (15, '- 登录成功id:20011103', 'INFO', '2020-05-20 11:13:15', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (16, '- 登录成功id:20011103', 'INFO', '2020-05-20 12:42:47', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (17, '- 登录成功id:20011103', 'INFO', '2020-05-20 15:32:47', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (18, '- 登录成功id:20011103', 'INFO', '2020-05-20 16:19:03', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (19, '- 登录成功id:20011103', 'INFO', '2020-05-20 16:25:34', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (20, '- 登录成功id:20011103', 'INFO', '2020-05-20 16:40:56', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (21, '- 登录成功id:20011103', 'INFO', '2020-05-20 16:41:29', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (22, '- 登录成功id:20011103', 'INFO', '2020-05-20 17:51:39', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (23, '- 登录成功id:2017110323', 'INFO', '2020-05-20 19:19:24', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (24, '- 登录成功id:20011103', 'INFO', '2020-05-20 19:24:46', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (25, '- 登录成功id:20011103', 'INFO', '2020-05-20 22:48:53', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (26, '- 登录成功id:20011103', 'INFO', '2020-05-21 10:06:52', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (27, '- 登录成功id:20011103', 'INFO', '2020-05-21 11:46:14', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (28, '- 登录成功id:20011103', 'INFO', '2020-05-21 11:48:45', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (29, '- 登录成功id:20011103', 'INFO', '2020-05-21 11:51:48', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (30, '- 登录成功id:20011103', 'INFO', '2020-05-21 13:57:25', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (31, '- 登录成功id:20011103', 'INFO', '2020-05-21 15:11:22', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (32, '- 登录成功id:20011103', 'INFO', '2020-05-21 15:25:03', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (33, '- 登录成功id:20011103', 'INFO', '2020-05-21 15:43:41', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (34, '- 登录成功id:20011103', 'INFO', '2020-05-21 16:19:59', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (35, '- 登录成功id:2017110323', 'INFO', '2020-05-21 17:04:15', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (36, '- 登录成功id:20011103', 'INFO', '2020-05-21 17:44:32', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (37, '- 登录成功id:2017110323', 'INFO', '2020-05-21 17:49:08', 'com.qifangli.edumanage.controller.LoginController');
INSERT INTO `logging` VALUES (38, '- 登录成功id:20011103', 'INFO', '2020-05-21 17:49:38', 'com.qifangli.edumanage.controller.LoginController');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `pid` int(0) NOT NULL AUTO_INCREMENT COMMENT '唯一标识一个操作权限',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '一个操作权限的名称',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '授权管理', 'admin_authorized', '/admin/authorized/**');
INSERT INTO `permission` VALUES (2, '教师档案管理', 'admin_teaAdmin', '/admin/teacher/**');
INSERT INTO `permission` VALUES (3, '课程档案管理', 'admin_crsAdmin', '/admin/course/**');
INSERT INTO `permission` VALUES (4, '排课', 'admin_selectCrs', '/admin/select/**');
INSERT INTO `permission` VALUES (6, '学生档案管理', 'admin_stuAdmin', '/admin/student/**');
INSERT INTO `permission` VALUES (7, '教师普通操作', 'teacher_all', '/teacher/**');
INSERT INTO `permission` VALUES (8, '普通学生操作', 'student_all', '/student/**');
INSERT INTO `permission` VALUES (9, '学生选课通道', 'student_select', '/student/selectCrs');
INSERT INTO `permission` VALUES (10, '开启或关闭选课通道', 'super_selectCrs', '/admin/arrange/openOrCloseStuSelect');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `rid` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色名',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('admin', '管理员');
INSERT INTO `role` VALUES ('student', '学生');
INSERT INTO `role` VALUES ('super_admin', '超级管理员');
INSERT INTO `role` VALUES ('teacher', '教师');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `rid` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `pid` int(0) NOT NULL,
  PRIMARY KEY (`rid`, `pid`) USING BTREE,
  INDEX `fk_rp_p`(`pid`) USING BTREE,
  CONSTRAINT `fk_rp_p` FOREIGN KEY (`pid`) REFERENCES `permission` (`pid`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_rp_r` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('super_admin', 1);
INSERT INTO `role_permission` VALUES ('admin', 2);
INSERT INTO `role_permission` VALUES ('super_admin', 2);
INSERT INTO `role_permission` VALUES ('admin', 3);
INSERT INTO `role_permission` VALUES ('super_admin', 3);
INSERT INTO `role_permission` VALUES ('admin', 4);
INSERT INTO `role_permission` VALUES ('super_admin', 4);
INSERT INTO `role_permission` VALUES ('admin', 6);
INSERT INTO `role_permission` VALUES ('super_admin', 6);
INSERT INTO `role_permission` VALUES ('admin', 7);
INSERT INTO `role_permission` VALUES ('super_admin', 7);
INSERT INTO `role_permission` VALUES ('teacher', 7);
INSERT INTO `role_permission` VALUES ('student', 8);
INSERT INTO `role_permission` VALUES ('super_admin', 10);

-- ----------------------------
-- Table structure for tbl_active
-- ----------------------------
DROP TABLE IF EXISTS `tbl_active`;
CREATE TABLE `tbl_active`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '当前排课学期',
  `name` int(0) NULL DEFAULT NULL,
  INDEX `fk_active_term`(`id`) USING BTREE,
  INDEX `fk_active_term_1`(`name`) USING BTREE,
  CONSTRAINT `fk_active_term_1` FOREIGN KEY (`name`) REFERENCES `tbl_term` (`term_no`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_active
-- ----------------------------
INSERT INTO `tbl_active` VALUES ('active_term_no', 20202);

-- ----------------------------
-- Table structure for tbl_class
-- ----------------------------
DROP TABLE IF EXISTS `tbl_class`;
CREATE TABLE `tbl_class`  (
  `class_no` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '班级编号 4位年级2位学院2位班级',
  `class_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '专业名称',
  `class_dpt` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所属学院编号',
  `class_grade` char(4) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所属年级',
  PRIMARY KEY (`class_no`) USING BTREE,
  INDEX `fk_class_department`(`class_dpt`) USING BTREE,
  CONSTRAINT `fk_class_department` FOREIGN KEY (`class_dpt`) REFERENCES `tbl_department` (`dpt_no`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_class
-- ----------------------------
INSERT INTO `tbl_class` VALUES ('20170101', '文学一班', '01', '2017');
INSERT INTO `tbl_class` VALUES ('20170803', '测试班级', '08', '2017');
INSERT INTO `tbl_class` VALUES ('20171101', '计算机科学一班', '11', '2017');
INSERT INTO `tbl_class` VALUES ('20171102', '计算机科学二班', '11', '2017');
INSERT INTO `tbl_class` VALUES ('20171103', '网路工程', '11', '2017');
INSERT INTO `tbl_class` VALUES ('20180803', '测试班级2', '08', '2018');
INSERT INTO `tbl_class` VALUES ('20181103', '网路工程', '11', '2018');

-- ----------------------------
-- Table structure for tbl_course
-- ----------------------------
DROP TABLE IF EXISTS `tbl_course`;
CREATE TABLE `tbl_course`  (
  `crs_no` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '2位学院3位编号',
  `crs_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '课程名称',
  `crs_cdt` tinyint(1) NOT NULL COMMENT '学分',
  `crs_hours` tinyint(1) NOT NULL COMMENT '学时',
  `crs_dpt` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '开设学院',
  `crs_type` enum('专业选修','专业必修','通识选修','通识必修') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '课程类型',
  PRIMARY KEY (`crs_no`) USING BTREE,
  INDEX `fk_course_department`(`crs_dpt`) USING BTREE,
  CONSTRAINT `fk_course_department` FOREIGN KEY (`crs_dpt`) REFERENCES `tbl_department` (`dpt_no`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_course
-- ----------------------------
INSERT INTO `tbl_course` VALUES (10001, '数据结构', 4, 64, '11', '专业必修');
INSERT INTO `tbl_course` VALUES (10002, '计算机组成原理', 4, 64, '11', '专业必修');
INSERT INTO `tbl_course` VALUES (10003, '算法设计', 3, 54, '11', '专业选修');
INSERT INTO `tbl_course` VALUES (10004, '网络编程', 3, 54, '11', '专业选修');
INSERT INTO `tbl_course` VALUES (10005, '形式与政策', 1, 32, '11', '通识必修');
INSERT INTO `tbl_course` VALUES (10006, '编程艺术', 1, 32, '11', '通识选修');
INSERT INTO `tbl_course` VALUES (10007, '数据库原理', 4, 64, '11', '专业必修');
INSERT INTO `tbl_course` VALUES (10008, 'C语言', 4, 64, '11', '专业必修');
INSERT INTO `tbl_course` VALUES (10009, 'C++语言', 4, 64, '11', '专业必修');
INSERT INTO `tbl_course` VALUES (10010, 'IOS程序开发', 4, 64, '11', '专业必修');
INSERT INTO `tbl_course` VALUES (10011, '计算机组装与维护', 3, 54, '11', '专业选修');
INSERT INTO `tbl_course` VALUES (10012, '体育教学', 4, 54, '08', '专业必修');
INSERT INTO `tbl_course` VALUES (10013, '网络工程', 4, 54, '11', '专业必修');
INSERT INTO `tbl_course` VALUES (10014, '信息安全', 4, 54, '11', '专业必修');
INSERT INTO `tbl_course` VALUES (10016, '文学院课程1', 2, 42, '01', '专业必修');
INSERT INTO `tbl_course` VALUES (10018, 'Java高级程序设计', 4, 54, '11', '专业必修');

-- ----------------------------
-- Table structure for tbl_department
-- ----------------------------
DROP TABLE IF EXISTS `tbl_department`;
CREATE TABLE `tbl_department`  (
  `dpt_no` char(2) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学院编号',
  `dpt_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学院名称',
  PRIMARY KEY (`dpt_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_department
-- ----------------------------
INSERT INTO `tbl_department` VALUES ('01', '文学院');
INSERT INTO `tbl_department` VALUES ('02', '经管学院');
INSERT INTO `tbl_department` VALUES ('03', '外国语学院');
INSERT INTO `tbl_department` VALUES ('04', '物理与电子学院');
INSERT INTO `tbl_department` VALUES ('05', '化学与材料学院');
INSERT INTO `tbl_department` VALUES ('06', '数学学院');
INSERT INTO `tbl_department` VALUES ('07', '生命科学学院');
INSERT INTO `tbl_department` VALUES ('08', '体育学院');
INSERT INTO `tbl_department` VALUES ('09', '音乐学院');
INSERT INTO `tbl_department` VALUES ('10', '美术学院');
INSERT INTO `tbl_department` VALUES ('11', '计算机科学学院');

-- ----------------------------
-- Table structure for tbl_score
-- ----------------------------
DROP TABLE IF EXISTS `tbl_score`;
CREATE TABLE `tbl_score`  (
  `stu_no` char(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学生学号',
  `tea_crs_no` int(0) NOT NULL COMMENT '确定一次行课的唯一编号',
  `score_pro` int(0) NULL DEFAULT NULL COMMENT '平时成绩',
  `score_end` int(0) NULL DEFAULT NULL COMMENT '期末成绩',
  `score` int(0) NULL DEFAULT NULL COMMENT '综合成绩',
  PRIMARY KEY (`stu_no`, `tea_crs_no`) USING BTREE,
  UNIQUE INDEX `unique_stu_tea_crs`(`stu_no`, `tea_crs_no`) USING BTREE,
  INDEX `fk_score_tea_crs`(`tea_crs_no`) USING BTREE,
  CONSTRAINT `fk_score_student` FOREIGN KEY (`stu_no`) REFERENCES `tbl_student` (`stu_no`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_score_tea_crs` FOREIGN KEY (`tea_crs_no`) REFERENCES `tbl_tea_crs` (`tea_crs_no`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_score
-- ----------------------------
INSERT INTO `tbl_score` VALUES ('2017110302', 7, NULL, NULL, NULL);
INSERT INTO `tbl_score` VALUES ('2017110305', 7, NULL, NULL, 90);
INSERT INTO `tbl_score` VALUES ('2017110318', 7, NULL, NULL, 88);
INSERT INTO `tbl_score` VALUES ('2017110318', 17, NULL, NULL, NULL);
INSERT INTO `tbl_score` VALUES ('2017110323', 1, NULL, NULL, 80);
INSERT INTO `tbl_score` VALUES ('2017110323', 2, NULL, NULL, 81);
INSERT INTO `tbl_score` VALUES ('2017110323', 4, NULL, NULL, NULL);
INSERT INTO `tbl_score` VALUES ('2017110323', 5, NULL, NULL, NULL);
INSERT INTO `tbl_score` VALUES ('2017110323', 6, NULL, NULL, NULL);
INSERT INTO `tbl_score` VALUES ('2017110323', 7, NULL, NULL, NULL);
INSERT INTO `tbl_score` VALUES ('2017110323', 8, NULL, NULL, NULL);
INSERT INTO `tbl_score` VALUES ('2017110401', 26, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tbl_spot
-- ----------------------------
DROP TABLE IF EXISTS `tbl_spot`;
CREATE TABLE `tbl_spot`  (
  `spt_no` char(4) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '2位区域2位教室',
  `spt_area` enum('教学楼A区','教学楼B区','第一实验楼','第一操场') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '区域',
  `spt_room` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '教室',
  PRIMARY KEY (`spt_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_spot
-- ----------------------------
INSERT INTO `tbl_spot` VALUES ('0101', '教学楼A区', 'A101');
INSERT INTO `tbl_spot` VALUES ('0102', '教学楼A区', 'A102');
INSERT INTO `tbl_spot` VALUES ('0103', '教学楼A区', 'A103');
INSERT INTO `tbl_spot` VALUES ('0104', '教学楼A区', 'A201');
INSERT INTO `tbl_spot` VALUES ('0105', '教学楼A区', 'A202');
INSERT INTO `tbl_spot` VALUES ('0106', '教学楼A区', 'A103');
INSERT INTO `tbl_spot` VALUES ('0201', '教学楼B区', 'B101');
INSERT INTO `tbl_spot` VALUES ('0202', '教学楼B区', 'B102');
INSERT INTO `tbl_spot` VALUES ('0301', '第一实验楼', '401');
INSERT INTO `tbl_spot` VALUES ('0302', '第一实验楼', '402');
INSERT INTO `tbl_spot` VALUES ('0303', '第一实验楼', '501');

-- ----------------------------
-- Table structure for tbl_student
-- ----------------------------
DROP TABLE IF EXISTS `tbl_student`;
CREATE TABLE `tbl_student`  (
  `stu_no` char(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学号前4位入学年份2位学院2位班级2位编号',
  `stu_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学生姓名',
  `stu_sex` enum('男','女') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '性别男、女',
  `stu_gdu` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '毕业学校',
  `stu_bth` date NOT NULL COMMENT '出生日期',
  `stu_id` char(18) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '身份证号',
  `stu_tel` char(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `stu_pol` enum('共青团员','共产党员','入党积极分子','其他党派','群众') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '群众' COMMENT '政治面貌',
  `stu_dpt` char(2) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属学院',
  `stu_class` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '4位年级2位学院2位班级',
  `stu_sta` enum('毕业','在校','休学','退学') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '在校' COMMENT '状态',
  `stu_pwd` blob NOT NULL COMMENT '密码',
  PRIMARY KEY (`stu_no`) USING BTREE,
  INDEX `fk_student_department`(`stu_dpt`) USING BTREE,
  INDEX `fk_student_class`(`stu_class`) USING BTREE,
  CONSTRAINT `fk_student_class` FOREIGN KEY (`stu_class`) REFERENCES `tbl_class` (`class_no`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_student_department` FOREIGN KEY (`stu_dpt`) REFERENCES `tbl_department` (`dpt_no`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_student
-- ----------------------------
INSERT INTO `tbl_student` VALUES ('2016110312', '候同学', '女', '第三中学', '1998-12-14', '510722199812147564', '18011108917', '共青团员', '11', '20171103', '在校', 0x6531306164633339343962613539616262653536653035376632306638383365);
INSERT INTO `tbl_student` VALUES ('2017080323', '好同学', '男', '第九中学', '1998-01-01', '510722199810242388', '18971034682', '入党积极分子', '08', '20170803', '在校', 0x6531306164633339343962613539616262653536653035376632306638383365);
INSERT INTO `tbl_student` VALUES ('2017110302', '赵同学', '男', '第六中学', '1998-12-14', '51072219981214146X', '18011108917', '共青团员', '11', '20171103', '在校', 0x6531306164633339343962613539616262653536653035376632306638383365);
INSERT INTO `tbl_student` VALUES ('2017110305', '李同学', '女', '实验中学', '1998-12-14', '510722199812111111', '18011108917', '共青团员', '11', '20171103', '在校', 0x6531306164633339343962613539616262653536653035376632306638383365);
INSERT INTO `tbl_student` VALUES ('2017110318', '雷同学', '女', '第一中学', '1998-12-14', '510722199812147894', '19982031936', '共青团员', '11', '20171103', '在校', 0x6531306164633339343962613539616262653536653035376632306638383365);
INSERT INTO `tbl_student` VALUES ('2017110323', '李同学', '女', '实验中学', '1998-12-14', '510722199812142384', '18011108917', '共青团员', '11', '20171103', '在校', 0x6531306164633339343962613539616262653536653035376632306638383365);
INSERT INTO `tbl_student` VALUES ('2017110401', '张同学', '男', '第四中学', '1998-12-14', '510722199812141234', '18011108917', '共青团员', '01', '20171103', '在校', 0x6531306164633339343962613539616262653536653035376632306638383365);
INSERT INTO `tbl_student` VALUES ('2018090301', '小王同学', '男', '第一中学', '1999-12-01', '527133199912018453', '18011108917', '共青团员', '08', '20180803', '在校', 0x6664323238306164663539393535326431653965643934316662356564303739);
INSERT INTO `tbl_student` VALUES ('2018090323', '小李同学', '女', '第一中学', '2000-10-11', '51104620010117546', '18011108917', '共青团员', '08', '20180803', '在校', 0x3033653532353566653733343830356264303332353165303433643433383330);

-- ----------------------------
-- Table structure for tbl_tea_crs
-- ----------------------------
DROP TABLE IF EXISTS `tbl_tea_crs`;
CREATE TABLE `tbl_tea_crs`  (
  `tea_crs_no` int(0) NOT NULL AUTO_INCREMENT COMMENT '某次授课的唯一编号',
  `tea_no` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '教师编号',
  `crs_no` int(5) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '课程编号',
  `term` int(0) NULL DEFAULT NULL COMMENT '上课学年',
  `time` enum('0','1','3','4','6') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '上课时间',
  `spot` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '上课地点',
  `week` enum('Mon','Tues','Wed','Thur','Fri') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '第几周上课',
  `selected` int(0) NULL DEFAULT 0 COMMENT '已选课人数',
  `total` int(0) NULL DEFAULT NULL COMMENT '最大上课人数',
  `grade` char(4) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '开课年级',
  PRIMARY KEY (`tea_crs_no`) USING BTREE,
  UNIQUE INDEX `constraint_unique`(`term`, `time`, `spot`, `week`) USING BTREE,
  INDEX `fk_tea_crs_spot`(`spot`) USING BTREE,
  INDEX `fk_tea_crs_teacher`(`tea_no`) USING BTREE,
  INDEX `fk_tea_crs_course`(`crs_no`) USING BTREE,
  CONSTRAINT `fk_rea_crs_term` FOREIGN KEY (`term`) REFERENCES `tbl_term` (`term_no`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_tea_crs_course` FOREIGN KEY (`crs_no`) REFERENCES `tbl_course` (`crs_no`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_tea_crs_spot` FOREIGN KEY (`spot`) REFERENCES `tbl_spot` (`spt_no`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_tea_crs_teacher` FOREIGN KEY (`tea_no`) REFERENCES `tbl_teacher` (`tea_no`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_tea_crs
-- ----------------------------
INSERT INTO `tbl_tea_crs` VALUES (1, '20011103', 10001, 20191, '0', '0102', 'Tues', 1, 30, '2018');
INSERT INTO `tbl_tea_crs` VALUES (2, '20051106', 10002, 20191, '3', '0103', 'Wed', 1, 30, '2018');
INSERT INTO `tbl_tea_crs` VALUES (3, '20061104', 10003, 20191, '4', '0102', 'Mon', 0, 40, '2017');
INSERT INTO `tbl_tea_crs` VALUES (4, '20061105', 10005, 20192, '3', '0101', 'Thur', 1, 30, '2017');
INSERT INTO `tbl_tea_crs` VALUES (5, '20061102', 10003, 20201, '4', '0105', 'Tues', 1, 30, '2017');
INSERT INTO `tbl_tea_crs` VALUES (6, '20051106', 10003, 20201, '3', '0106', 'Wed', 1, 40, '2017');
INSERT INTO `tbl_tea_crs` VALUES (7, '20011103', 10002, 20201, '4', '0201', 'Thur', 4, 30, '2017');
INSERT INTO `tbl_tea_crs` VALUES (8, '20011103', 10006, 20201, '0', '0201', 'Tues', 1, 30, '2017');
INSERT INTO `tbl_tea_crs` VALUES (9, '20151107', 10004, 20201, '1', '0106', 'Wed', 0, 30, '2018');
INSERT INTO `tbl_tea_crs` VALUES (17, '20011103', 10004, 20202, '0', '0102', 'Mon', 1, 40, '2019');
INSERT INTO `tbl_tea_crs` VALUES (23, '20061101', 10001, 20202, '1', '0201', 'Mon', 0, 30, '2018');
INSERT INTO `tbl_tea_crs` VALUES (26, '20011103', 10002, 20201, '3', '0201', 'Tues', 1, 30, '2017');

-- ----------------------------
-- Table structure for tbl_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tbl_teacher`;
CREATE TABLE `tbl_teacher`  (
  `tea_no` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '工号4位入职年份2位学院2位编号',
  `tea_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `tea_sex` enum('男','女') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '性别',
  `tea_degree` enum('本科','硕士','博士','其他') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学历',
  `tea_title` enum('教授','副教授','讲师','助教') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '职称',
  `tea_birth` date NOT NULL COMMENT '出生日期',
  `tea_id` char(18) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '身份证号',
  `tea_tel` char(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机号',
  `tea_pol` enum('共产党员','共青团员','其他党派','入党积极分子','群众') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '群众' COMMENT '政治面貌',
  `tea_dpt` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所属学院',
  `tea_wkt` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '在职时间',
  `tea_pwd` blob NOT NULL COMMENT '密码',
  PRIMARY KEY (`tea_no`) USING BTREE,
  INDEX `fk_teacher_department`(`tea_dpt`) USING BTREE,
  CONSTRAINT `fk_teacher_department` FOREIGN KEY (`tea_dpt`) REFERENCES `tbl_department` (`dpt_no`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_teacher
-- ----------------------------
INSERT INTO `tbl_teacher` VALUES ('20011103', '李老师', '男', '博士', '教授', '1976-04-14', '510722197604147894', '13811104563', '共产党员', '11', '2001-09至今', 0x6531306164633339343962613539616262653536653035376632306638383365);
INSERT INTO `tbl_teacher` VALUES ('20051106', '刘老师', '男', '硕士', '助教', '1976-04-14', '510722197604147894', '13511163563', '共产党员', '11', '2005-09至今', 0x6531306164633339343962613539616262653536653035376632306638383365);
INSERT INTO `tbl_teacher` VALUES ('20061101', '张老师', '女', '硕士', '讲师', '1976-04-14', '510722197604147894', '15811104563', '群众', '11', '2006-09至今', 0x6531306164633339343962613539616262653536653035376632306638383365);
INSERT INTO `tbl_teacher` VALUES ('20061102', '张老师', '女', '硕士', '讲师', '1976-03-14', '510722197603147894', '15814504563', '群众', '11', '2006-09至今', 0x6531306164633339343962613539616262653536653035376632306638383365);
INSERT INTO `tbl_teacher` VALUES ('20061104', '王老师', '男', '博士', '副教授', '1976-04-14', '510722197604147894', '15811185563', '共产党员', '11', '2006-09至今', 0x6531306164633339343962613539616262653536653035376632306638383365);
INSERT INTO `tbl_teacher` VALUES ('20061105', '赵老师', '女', '硕士', '讲师', '1976-04-14', '510722197604147894', '18717894563', '共产党员', '11', '2006-09至今', 0x6531306164633339343962613539616262653536653035376632306638383365);
INSERT INTO `tbl_teacher` VALUES ('20070101', '文老师', '女', '硕士', '讲师', '1990-01-01', '546795199001016551', '17865466635', '共产党员', '01', '2008-06至今', 0x6436653632396661373530373738333165316134623831643831343965396466);
INSERT INTO `tbl_teacher` VALUES ('20081101', '81老师', '女', '硕士', '讲师', '1976-10-10', '510722197610109874', '13498745632', '共产党员', '11', '2008-06至今', 0x3833353663666437303638303062363232616632616534643462353763303335);
INSERT INTO `tbl_teacher` VALUES ('20151107', '周老师', '男', '本科', '讲师', '1976-04-14', '510722197604147894', '18011244563', '共产党员', '11', '2015-09至今', 0x6531306164633339343962613539616262653536653035376632306638383365);

-- ----------------------------
-- Table structure for tbl_term
-- ----------------------------
DROP TABLE IF EXISTS `tbl_term`;
CREATE TABLE `tbl_term`  (
  `term_no` int(0) NOT NULL COMMENT '4位年份1位学期 1表示春季 2表示秋季',
  `term_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`term_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_term
-- ----------------------------
INSERT INTO `tbl_term` VALUES (20152, '2015-2016第一学年');
INSERT INTO `tbl_term` VALUES (20161, '2015-2016第二学年');
INSERT INTO `tbl_term` VALUES (20162, '2016-2017第一学年');
INSERT INTO `tbl_term` VALUES (20171, '2016-2017第二学年');
INSERT INTO `tbl_term` VALUES (20172, '2017-2018第一学年');
INSERT INTO `tbl_term` VALUES (20181, '2017-2018第二学年');
INSERT INTO `tbl_term` VALUES (20182, '2018-2019第一学年');
INSERT INTO `tbl_term` VALUES (20191, '2018-2019第二学年');
INSERT INTO `tbl_term` VALUES (20192, '2019-2020第一学年');
INSERT INTO `tbl_term` VALUES (20201, '2019-2020第二学年');
INSERT INTO `tbl_term` VALUES (20202, '2020-2021第一学年');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `uid` varchar(225) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户编号',
  `rid` varchar(225) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色id',
  PRIMARY KEY (`uid`, `rid`) USING BTREE,
  INDEX `fk_role`(`rid`) USING BTREE,
  CONSTRAINT `fk_role` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_role_teacher_uid` FOREIGN KEY (`uid`) REFERENCES `tbl_teacher` (`tea_no`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('20051106', 'admin');
INSERT INTO `user_role` VALUES ('20061104', 'admin');
INSERT INTO `user_role` VALUES ('20061105', 'admin');
INSERT INTO `user_role` VALUES ('20011103', 'super_admin');
INSERT INTO `user_role` VALUES ('20061101', 'super_admin');
INSERT INTO `user_role` VALUES ('20061102', 'super_admin');
INSERT INTO `user_role` VALUES ('20151107', 'super_admin');

-- ----------------------------
-- Triggers structure for table tbl_student
-- ----------------------------
DROP TRIGGER IF EXISTS `tr_pwd_insert`;
delimiter ;;
CREATE TRIGGER `tr_pwd_insert` BEFORE INSERT ON `tbl_student` FOR EACH ROW set new.stu_pwd = md5(RIGHT(new.stu_id,6))
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table tbl_tea_crs
-- ----------------------------
DROP TRIGGER IF EXISTS `tg_insert`;
delimiter ;;
CREATE TRIGGER `tg_insert` BEFORE UPDATE ON `tbl_tea_crs` FOR EACH ROW if (new.selected>old.total or new.selected<0) then
	signal sqlstate 'HY000' set message_text = '退选课已到最大人数' ;
end if
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table tbl_teacher
-- ----------------------------
DROP TRIGGER IF EXISTS `tr_pwd_tea_inset`;
delimiter ;;
CREATE TRIGGER `tr_pwd_tea_inset` BEFORE INSERT ON `tbl_teacher` FOR EACH ROW set new.tea_pwd = md5(RIGHT(new.tea_id,6))
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
