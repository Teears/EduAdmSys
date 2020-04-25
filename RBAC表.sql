
--组织表
create table tbl_organzation(
       pk_org_id number primary key not null,       --组id
       org_parent_id number not null,               --父组
       org_name varchar(64) not null,               --组织名称
       org_create_time date not null,               --创建时间
       org_desription varchar(1024) not null        --组织描述
)
-----------------------------------------------------------------

--用户表
create table tbl_user(
       pk_user_id number primary key not null,    --记录标识id
       fk_org_id number not null,                 --所属组织id
       constraint fk_user_organzation foreign key(fk_org_id)
       references tbl_organzation(pk_org_id), 
       login_name varchar(64) not null,           --登陆账号
       user_password varchar(64) not null,        --用户密码
       tel_number number not null,                --用户姓名
       email varchar(64) not null,                --电话号码
       user_create_time date not null,            --电子邮件
       login_time date not null,                  --登陆时间
       last_login_time date not null,             --上次登陆时间
       login_count number not null                --登陆次数
)
-------------------------------------------------------------
--角色表
create table tbl_role(
       pk_role_id number primary key not null,    --角色id
       parent_role_id number not null,            --父级角色id
       role_name varchar(64) not null,            --角色名称
       role_create_time date not null,            --创建时间
       role_description varchar(1024)             --角色描述
)
  
----------------------------------------------------------------

--权限表
create table tbl_right(
       pk_right_id number primary key not null,   --权限id
       parent_right_id varchar(64) not null,      --父权限id
       right_name varchar(64) not null,           --权限名称
       right_description varchar(1024)            --权限描述
)
------------------------------------------------------------------

--组表
create table tbl_group(
       pk_group_id number primary key not null,   --组id
       group_name varchar(64) not null,           --组名称
       parent_group_id number not null,           --父组id
       group_create_time date not null,           --创建时间
       group_decription varchar(1024)             --组描述
)
------------------------------------------------------------------

--角色权限表
create table tbl_role_right(
       pk_rri_id number primary key not null,     --记录id
       fk_role_id number not null,                --角色id
       constraint fk_roleright_role foreign key(fk_role_id)
       references tbl_role(pk_role_id),
       fk_right_id number not null,               --权限id
       constraint fk_rolerright_right foreign key(fk_right_id)
       references tbl_right(pk_right_id),
       right_type int not null                    --创建类型
       ) 
-------------------------------------------------------------------

--组权限表
create table tbl_group_right(
       pk_gri_id number primary key not null,     --记录标识
       fk_group_id number not null,               --组
       constraint fk_groupright_group foreign key(fk_group_id)
       references tbl_group(pk_group_id),
       fk_right_id number not null,               --权限
       constraint fk_groupright_right foreign key(fk_right_id)
       references tbl_right(pk_right_id),
       ck_right_type char(1) not null check(ck_right_type in ('可访问','可授权'))              --权限类型
       
)
--------------------------------------------------------------------

--组角色表
create table tbl_group_role(
       pk_gro_id number primary key not null,     --记录标识
       fk_group_id number not null,               --组
       constraint fk_grouprole_group foreign key(fk_group_id)
       references tbl_group(pk_group_id),
       fk_role_id number not null                 --角色
)
-----------------------------------------------------------------

--用户权限表
create table tbl_user_right(
       pk_uri_id number primary key not null,     --记录标识id
       fk_user_id number not null,                --用户
       constraint fk_userright_user foreign key(fk_user_id)
       references tbl_user(pk_user_id),            
       fk_right_id number not null,               --权限
       constraint fk_userright_right foreign key(fk_right_id)
       references tbl_right(pk_right_id),
       ck_right_type char(1) not null check(ck_right_type in ('可访问','可授权')) --权限类型
) 
-------------------------------------------------------------------

--用户角色表
create table tbl_user_role(
       pk_uro_id number primary key not null,       --记录标识id
       fk_user_id number not null,                  --用户
       constraint fk_userrole_user foreign key(fk_user_id)
       references tbl_user(pk_user_id),            
       fk_role_id number not null,                   --角色
       constraint fk_userrole_role foreign key(fk_role_id)
       references tbl_role(pk_role_id)
)
---------------------------------------------------------------

--用户组表
create table tbl_user_group(
       pk_urg_id number primary key not null,       --记录标识id
       fk_user_id number not null,                  --用户
       constraint fk_usergroup_user foreign key(fk_user_id)
       references tbl_user(pk_user_id),
       fk_group_id number not null,                  --组
       constraint fk_usergroup_group foreign key(fk_group_id)
       references tbl_group(pk_group_id)
)
-------------------------------------------------------------------



--操作日志表
create table tbl_log(
       pk_log_id number primary key not null,       --记录标识id
       log_op_type int not null,                    --操作类型
       fk_role_id number not null,                  --角色
       constraint fk_log_role foreign key(fk_role_id)
       references tbl_role(pk_role_id),
       op_content varchar(1024) not null,           --操作内容
       fk_org_id number not null,                   --操作人
       constraint fk_rogorg_organzation foreign key(fk_org_id)
       references tbl_organzation(pk_org_id),
       op_time date not null                        --操作时间
  )
------------------------------------------------------------------------------    
