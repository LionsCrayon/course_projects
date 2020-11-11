INSERT INTO `course`.`chapter` (`id`, `course_id`, `name`) VALUES ('1', '00001', '大章1');
INSERT INTO `course`.`chapter` (`id`, `course_id`, `name`) VALUES ('2', '00002', '大章2');
INSERT INTO `course`.`chapter` (`id`, `course_id`, `name`) VALUES ('3', '00003', '大章3');
INSERT INTO `course`.`chapter` (`id`, `course_id`, `name`) VALUES ('4', '00004', '大章4');
INSERT INTO `course`.`chapter` (`id`, `course_id`, `name`) VALUES ('5', '00005', '大章5');
INSERT INTO `course`.`chapter` (`id`, `course_id`, `name`) VALUES ('6', '00006', '大章6');
INSERT INTO `course`.`chapter` (`id`, `course_id`, `name`) VALUES ('7', '00007', '大章7');
INSERT INTO `course`.`chapter` (`id`, `course_id`, `name`) VALUES ('8', '00008', '大章8');
INSERT INTO `course`.`chapter` (`id`, `course_id`, `name`) VALUES ('9', '00009', '大章9');
INSERT INTO `course`.`chapter` (`id`, `course_id`, `name`) VALUES ('10', '000010', '大章10');
INSERT INTO `course`.`chapter` (`id`, `course_id`, `name`) VALUES ('11', '000011', '大章11');
INSERT INTO `course`.`chapter` (`id`, `course_id`, `name`) VALUES ('12', '000012', '大章12');
INSERT INTO `course`.`chapter` (`id`, `course_id`, `name`) VALUES ('13', '000013', '大章13');
INSERT INTO `course`.`chapter` (`id`, `course_id`, `name`) VALUES ('14', '000014', '大章14');
INSERT INTO `course`.`chapter` (`id`, `course_id`, `name`) VALUES ('15', '000015', '大章15');
INSERT INTO `course`.`chapter` (`id`, `course_id`, `name`) VALUES ('16', '000016', '大章16');
INSERT INTO `course`.`chapter` (`id`, `course_id`, `name`) VALUES ('17', '000017', '大章17');
INSERT INTO `course`.`chapter` (`id`, `course_id`, `name`) VALUES ('18', '000018', '大章18');
INSERT INTO `course`.`chapter` (`id`, `course_id`, `name`) VALUES ('19', '000019', '大章19');
INSERT INTO `course`.`chapter` (`id`, `course_id`, `name`) VALUES ('20', '000020', '大章20');


/*小节*/
DROP  table if exists `section`;
CREATE TABLE `section`(
    `id` CHAR (8) NOT NULL DEFAULT '' COMMENT 'ID',
    `title` VARCHAR (50) NOT NULL  COMMENT '标题',
    `course_id` CHAR (8)  COMMENT '课程|course.id',
    `chapter_id` CHAR (8) COMMENT '大章|chapter.id',
    `video` VARCHAR (200) COMMENT '视频',
    `time` INT  COMMENT '时长|单位秒',
    `charge` CHAR (1)  COMMENT '收费|c 收费;F 免费',
    `sort` INT COMMENT '顺序' ,
    `created_at` DATETIME COMMENT '创建时间',
    `updated_at` DATETIME COMMENT '修改时间',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '小节';

INSERT INTO `course`.`section` (
    `id`,
    `title`,
    `course_id`,
    `chapter_id`,
    `video`,
    `time`,
    `charge`,
    `sort`,
    `created_at`,
    `updated_at`
)
VALUES('00001','小节01','00001','00001','',500,'F',1,now(),now());

/*课程*/
drop table if exists course.course;
create table course(
    id char(8) not null default '' comment 'id',
    name varchar(50) not null comment '名称',
    summary varchar(2000) comment '概述',
    time int default 0 comment '时长|单位秒',
    price decimal(8,2) default 0.00 comment '价格(元)',
    image varchar(100) comment '封面',
    level char(1) comment '级别|枚举[CourseLevelEnum]:ONE("1", "初级"),TWO("2", "中级"),THREE("3", "高级")',
    charge char(1) comment '收费|枚举[CourseChargeEnum]:CHARGE("C", "收费"),FREE("F", "免费")',
    status char(1) comment '状态|枚举[CourseStatusEnum]:PUBLISH("P", "发布"),DRAFT("D", "草稿")',
    enroll integer default 0 comment '报名数',
    sort int comment '顺序',
    created_at datetime comment '创建时间',
    updated_at datetime comment '修改时间',
    primary key (id)
)engine=innodb default charset=utf8mb4 comment='课程';
INSERT INTO `course`.`course` (`id`,`name`,`summary`,`time`,`price`,`image`,`level`,`charge`,`status`,`enroll`,`sort`,`created_at`,
                               `updated_at`
)
VALUES
('00001','测试课程1','这是一门测试课',7200,9.9,'','0','C','D','100','0',now(),now());


/* 分类*/
drop table if exists `category`;
create table `category`(
    `id` char(8) not null default '' comment 'id',
    `parent` char(8) not null default '' comment '父id',
    `name` varchar(50) not null comment '名称',
    `sort` int comment '顺序',
    primary key (`id`)
)engine=innodb default charset=utf8mb4 comment '分类';



insert into `category` (id, parent, name, sort) values ('0000100','0000000','前端技术',100);
insert into `category` (id, parent, name, sort) values ('0000101','0000100','HTML/CSS',101);
insert into `category` (id, parent, name, sort) values ('0000102','0000100','JavaScript',102);
insert into `category` (id, parent, name, sort) values ('0000103','0000100','Vue.js',103);
insert into `category` (id, parent, name, sort) values ('0000104','0000100','React.JS',104);
insert into `category` (id, parent, name, sort) values ('0000105','0000100','Angular',105);
insert into `category` (id, parent, name, sort) values ('0000106','0000100','Node.js',106);
insert into `category` (id, parent, name, sort) values ('0000107','0000100','jQuery',107);
insert into `category` (id, parent, name, sort) values ('0000108','0000100','Bootstrap',108);

insert into `category` (id, parent, name, sort) values ('0000200','0000000','后端技术',200);
insert into `category` (id, parent, name, sort) values ('0000201','0000200','Java',201);
insert into `category` (id, parent, name, sort) values ('0000202','0000200','SpringBoot',202);
insert into `category` (id, parent, name, sort) values ('0000203','0000200','Spring Cloud',203);
insert into `category` (id, parent, name, sort) values ('0000204','0000200','SSM',204);
insert into `category` (id, parent, name, sort) values ('0000205','0000200','PHP',205);
insert into `category` (id, parent, name, sort) values ('0000206','0000200','Python',206);
insert into `category` (id, parent, name, sort) values ('0000207','0000200','Django',207);
insert into `category` (id, parent, name, sort) values ('0000208','0000200','Tornado',208);
