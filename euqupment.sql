DROP DATABASE IF EXISTS `6_05_coursedesign`;
CREATE DATABASE `6_05_coursedesign`;
USE 6_05_coursedesign;
DROP TABLE IF EXISTS `equipment`;
CREATE TABLE `equipment` (
    `id` char(5) PRIMARY KEY,
    `name` varchar(255) NOT NULL,
    `user` varchar(20) NOT NULL DEFAULT '无',
    `type` char(10) NOT NULL,
    `lent` tinyint(1) NOT NULL DEFAULT 0,
    `scrap` tinyint(1) NOT NULL DEFAULT 0
);
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` char(5) PRIMARY KEY,
    `password` char(20) NOT NULL,
    `name` char(5) NOT NULL,
    `sex` char(1),
    `phoneNumber` char(11),
    `type` char(10) NOT NULL DEFAULT '普通成员'
);
INSERT INTO user(
        `id`,
        `password`,
        `name`,
        `sex`,
        `phoneNumber`,
        `type`
    )
VALUES(
        'u0001',
        '123456',
        '张三',
        '男',
        '18888888888',
        '管理员'
    );
INSERT INTO user(
        `id`,
        `password`,
        `name`,
        `sex`,
        `phoneNumber`,
        `type`
    )
VALUES(
        'u0002',
        '123456',
        '李四',
        '男',
        '18888888888',
        '普通用户'
    );
INSERT INTO user(
        `id`,
        `password`,
        `name`,
        `sex`,
        `phoneNumber`,
        `type`
    )
VALUES(
        'u0003',
        '123456',
        '王五',
        '男',
        '110',
        '普通用户'
    );
INSERT INTO `equipment` (
        `id`,
        `name`,
        `user`,
        `type`,
        `lent`,
        `scrap`
    )
VALUES(
        'e0001',
        '设备1',
        '无',
        '类型1',
        0,
        0
    );
INSERT INTO `equipment` (
        `id`,
        `name`,
        `user`,
        `type`,
        `lent`,
        `scrap`
    )
VALUES(
        'e0002',
        '设备2',
        '李四',
        '类型2',
        1,
        0
    );
INSERT INTO `equipment` (
        `id`,
        `name`,
        `user`,
        `type`,
        `lent`,
        `scrap`
    )
VALUES(
        'e0003',
        '设备3',
        '无',
        '类型1',
        0,
        0
    );
INSERT INTO `equipment` (
        `id`,
        `name`,
        `user`,
        `type`,
        `lent`,
        `scrap`
    )
VALUES(
        'e0004',
        '设备4',
        '无',
        '类型2',
        0,
        1
    );