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


