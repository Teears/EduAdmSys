
--行政管理表
create table tbl_adm_class(
pk_adm_class_no char(6) primary key not null, --学号
dpt char(4) check (dpt in ('计科','美院','物电')) not null  --系别
)
-----------------------------------------------------------------------------------

--课程表
create table tbl_course(
  pk_crs_no int check(pk_crs_no = 4) primary key not null,    --课程编号
  crs_name varchar(20) check (crs_name <=10) not null,             --课程名
  crs_cdt smallint check(crs_cdt>=1 and crs_hour<=5)not null,                 --学分
  crs_hour smallint check(crs_hour in ('16','32','64')) not null,                --学时
  crs_type char(1) check(crs_type in ('专业必修课','专业选修课','公共必修课','公共选修课'))   --课程类别 
);
-------------------------------------------------------------------------------------

--学生表
create table tbl_student(
  pk_stu_no int primary key check(pk_stu_no = 6) not null,   --学号
  stu_name varchar(50) not null,                              --姓名
  stu_sex char(1)   check(stu_sex in ('女','男')) not null，  --性别
  stu_gdu varchar(30) not null,                               --毕业学校
  stu_bth date not null,                                      --出生日期
  stu_id char(18) not null,                                   --身份证号
  stu_tel int check(stu_tel=11) not null,                               --联系电话
  stu_pol char(1) check(stu_pol in ('群众','共青团委','入党积极分子','党员')),--政治面貌
  fk_adm_class_no int check(fk_adm_class_no = 6) not null,                           --行政班级
  stu_reg varchar(50) not null,                               --户籍
  ck_stu_sta char(1) check(ck_stu_sta in ('已完成','未完成')) not null,  --学业情况
  ck_stu_pwd varchar(30) check(ck_stu_pwd>=2 and ck_stu_pwd<=8) not null,--密码？？？                      
  constraint fk_student_admclass foreign key(fk_adm_class_no)
  references tbl_adm_class(pk_adm_class_no)    
   );    
----------------------------------------------------------------------------------

--教师表
create table tbl_teacher (
  pk_tea_no int primary key check (pk_tea_no = 4) not null,                                          --工号
  tea_name varchar(50) not null,                                               --姓名
  tea_sex char(1) check(tea_sex in ('女','男')),                               --性别
  tea_acd char(1) check(tea_acd in ('本科','硕士','博士')),  --学历
  tea_title char(1) check(tea_title in ('讲师','副教授','教授')),     --职称
  tea_bth date not null,                                                        --出生日期
  tea_id char(18) unique not null,                                              --身份证号
  tea_tel int check(tea_tel = 11) not null,                                                 --联系电话
  tea_pol char(1) check(tea_pol in ('党员','共青团员')),                        --政治面貌
  fk_dpt char(4) not null,                                                      --系别
  tea_reg varchar(50) not null,                                                 --户籍
  tea_wkt date not null,                                                        --工作时间
  ck_tea_pwd varchar(30) check (ck_tea_pwd>=2 and ck_tea_pwd<=8) not null    --密码???
);
------------------------------------------------------------------------------------------

--管理员表
create table tbl_admin(
  pk_account varchar(10) primary key not null，  --管理员账号
  fk_tea_no int unique not null,                 --工号
  entitled int not null,                         --超级管理员权限
  ck_password varchar(30) not null 
  check (ck_password >= 2 and ck_password <=8), --密码???
  constraint fk_admin_teacher foreign key (fk_tea_no)
  references tbl_teacher(pk_tea_no)); 
-------------------------------------------------------------------------------------------

--行课表
create table tbl_crs_class(
   pk_crs_class_no int primary key not null
   check(pk_crs_class_no = 8),    --行课班级编号
   fk_crs_no char(4) not null,                   --课程编号
   fk_tea_no int  not null,                      --工号
   class_term char(1) check(class_term in ('2018-2019年第一学年度','2018-2019年第二学年度','2019-2020年第一学年度','2019-2020年第二学年度'))not null,    --学期
   class_time varchar(30) not null,             --授课时间
   class_place varchar(20) not null,            --授课地点
  constraint fk_crsclass_course foreign key(fk_crs_no)
  references tbl_course(pk_crs_no),
  constraint fk_crsclass_teacher foreign key(fk_tea_no)
  references tbl_teacher(pk_tea_no)
);
----------------------------------------------------------------------------------------------

--行课班级群表
create table tbl_stu_class(
pk_stu_no int primary key not null, --学号
fk_crs_class_no int check(fk_crs_class_no = 8) not null,      --行课班级编号       
grade smallint check(grade>=0 and grade <= 100) not null,                  --成绩  
constraint fk_stuclass_crsclass foreign key(fk_crs_class_no)  
references tbl_crs_class(pk_crs_class_no)
);
------------------------------------------------------------------------------------------------

