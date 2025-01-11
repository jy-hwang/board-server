CREATE TABLE `user`
(
    `no`          int NOT NULL AUTO_INCREMENT ,
    `user_id`     varchar(50) NOT NULL ,
    `password`    varchar(200) NOT NULL ,
    `nickname`    varchar(50) NOT NULL ,
    `is_admin`    tinyint NOT NULL ,
    `is_withdraw` tinyint NOT NULL ,
    `status`      varchar(20) NOT NULL ,
    `create_time` datetime NOT NULL ,
    `update_time` datetime NULL ,
    PRIMARY KEY (`no`)
);

CREATE TABLE `category`
(
    `no`   int NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL ,

    PRIMARY KEY (`no`)
);


CREATE TABLE `post`
(
    `no`          int NOT NULL AUTO_INCREMENT,
    `title`       varchar(50) NOT NULL ,
    `is_admin`    tinyint NOT NULL ,
    `contents`    varchar(500) NOT NULL ,
    `views`       int NOT NULL ,
    `user_no`     int NOT NULL ,
    `category_no` int NOT NULL ,
    `create_time` datetime NOT NULL ,
    `update_time` datetime NULL ,
    `delete_time` datetime NULL ,

    PRIMARY KEY (`no`),
    KEY `FK_1` (`user_no`),
    CONSTRAINT `FK_1` FOREIGN KEY `FK_1` (`user_no`) REFERENCES `user` (`no`),
    KEY `FK_2` (`category_no`),
    CONSTRAINT `FK_2` FOREIGN KEY `FK_2` (`category_no`) REFERENCES `category` (`no`)
);

DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`
(
    `no`        int NOT NULL AUTO_INCREMENT,
    `path`      varchar(200) NOT NULL ,
    `name`      varchar(50) NOT NULL ,
    `extension` varchar(50) NOT NULL ,
    `post_no`   int NOT NULL ,

    PRIMARY KEY (`no`),
    KEY `FK_1` (`post_no`),
    CONSTRAINT `FK_3` FOREIGN KEY `FK_1` (`post_no`) REFERENCES `post` (`no`)
);

CREATE TABLE `tag`
(
    `no`   int NOT NULL ,
    `name` varchar(50) NOT NULL ,

    PRIMARY KEY (`no`)
);

CREATE TABLE `post_tag`
(
    `no`      int NOT NULL AUTO_INCREMENT,
    `post_no` int NOT NULL ,
    `tag_no`  int NOT NULL ,

    PRIMARY KEY (`no`),
    KEY `FK_1` (`post_no`),
    CONSTRAINT `FK_6` FOREIGN KEY `FK_1` (`post_no`) REFERENCES `post` (`no`),
    KEY `FK_2` (`tag_no`),
    CONSTRAINT `FK_7` FOREIGN KEY `FK_2` (`tag_no`) REFERENCES `tag` (`no`)
);

CREATE TABLE `comment`
(
    `no`             int NOT NULL AUTO_INCREMENT,
    `post_no`        int NOT NULL ,
    `contents`       varchar(300) NOT NULL ,
    `sub_comment_no` int NOT NULL ,
    `user_no`        int NOT NULL ,
    `create_time`    datetime NOT NULL ,
    `update_time`    datetime NULL ,
    `delete_time`    datetime NULL ,

    PRIMARY KEY (`no`),
    KEY `FK_1` (`post_no`),
    CONSTRAINT `FK_4` FOREIGN KEY `FK_1` (`post_no`) REFERENCES `post` (`no`),
    KEY `FK_2` (`sub_comment_no`),
    CONSTRAINT `FK_5` FOREIGN KEY `FK_2` (`sub_comment_no`) REFERENCES `comment` (`no`),
    KEY `FK_3` (`user_no`),
    CONSTRAINT `FK_8` FOREIGN KEY `FK_3` (`user_no`) REFERENCES `user` (`no`)
);
