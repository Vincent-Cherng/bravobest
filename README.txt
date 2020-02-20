mysql配置信息
username=root
password=cheng

建表语句
用户表:
    -- auto-generated definition
    create table user
    (
        user_no    varchar(20) not null comment '用户编号'
            primary key,
        user_name  varchar(20) null comment '用户名字',
        password   varchar(20) not null comment '用户登录密码',
        login_name varchar(20) not null comment '用户登录名',
        sex        varchar(4)  null comment '用户性别',
        org_no     varchar(20) null comment '部门编号',
        constraint user_login_name_uindex
            unique (login_name)
    )
        comment '用户表';

部门表：
    -- auto-generated definition
    create table org
    (
        org_no    varchar(20) not null comment '部门编号'
            primary key,
        org_name  varchar(20) null comment '部门编号',
        org_chief varchar(20) null comment '部门经理编号'
    )
        comment '部门表';


--字典项表
-- auto-generated definition
create table dic
(
    enname     varchar(10)   not null,
    cnname     varchar(10)   not null,
    opttype    varchar(100)  null,
    is_enabled varchar(10)   null,
    `order`    varchar(10)   null,
    memo       varchar(1000) null
)
    comment '字典项';

--字典项数据导入
INSERT INTO jian.dic (enname, cnname, opttype, is_enabled, `order`, memo) VALUES ('0', '是', 'yes_no', '0', '', null);
INSERT INTO jian.dic (enname, cnname, opttype, is_enabled, `order`, memo) VALUES ('1', '否', 'yes_no', '0', null, null);
INSERT INTO jian.dic (enname, cnname, opttype, is_enabled, `order`, memo) VALUES ('0', '管理者', 'evalution_type', '0', null, '考评类型');
INSERT INTO jian.dic (enname, cnname, opttype, is_enabled, `order`, memo) VALUES ('1', '员工', 'evalution_type', '0', null, '考评类型');


--绩效考评表
-- auto-generated definition
create table perform_evaluation
(
    pk_id                  varchar(20) default '' not null comment '主键ID'
        primary key,
    user_no                varchar(20)            not null comment '被考评用户编号',
    evalution_period       varchar(20)            not null comment '考评周期',
    evalution_person       varchar(20)            not null comment '考评人',
    evalution_time         varchar(20)            not null comment '考评时间',
    total_evaluation_point varchar(4)             null comment '综合得分',
    evalution_type         varchar(10)            not null comment '考评类型（0管理者，1员工）'
)
    comment '绩效考评表';


--绩效考评明细
-- auto-generated definition
create table perform_evalution_detail
(
    pk_id                 varchar(20) null comment '主键ID',
    perform_id            varchar(20) not null comment '主表id',
    evalution_standard_no varchar(20) not null comment '考评指标编号',
    evalution_point       varchar(20) null comment '考评得分'
)
    comment '绩效考评明细';


--评分标准的配置表
-- auto-generated definition
create table perform_evalution_standard_cfg
(
    standard_no    varchar(100) not null comment '评分标准编号',
    standard_name  varchar(50) not null comment '评分标准名称',
    standard_type  varchar(100) null comment '类型',
    standard_point varchar(10) not null comment '分数定额',
    is_enabled     varchar(4)  null comment '是否启用',
    create_no      varchar(20) null,
    create_time    varchar(20) null,
    evalution_type varchar(10) not null comment '考评类型',
    description    varchar(1000)        comment '描述'
)
    comment '评分标准的配置表';






