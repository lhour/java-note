#建表
[toc]
#### 建表语句

```sql
create table(
    字段名 数据类型,
    字段名 数据类型,
    字段名 数据类型,
    ...
);
```

#### 常见数据类型 

|-|-|
|-|-|
|int|整数型|
|bigint| 长整型|
|float| 浮点型|
|char| 字符型|
|varchar| 可变字符串,最多255|
|data| 日期|
|BLOB| 二进制大对象,存储图片视频等|
|CLOB| 字符大对象，存储较大文本，4G字符串|

#### mysql生成的语句

```sql
CREATE TABLE `学生信息表` (
  `学号` varchar(10) NOT NULL,
  `姓名` varchar(10) NOT NULL,
  `性别` varchar(2) DEFAULT NULL,
  `出生日期` datetime DEFAULT NULL,
  `政治面貌` varchar(10) DEFAULT NULL,
  `班级` varchar(6) DEFAULT NULL,
  `毕业学校` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`学号`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```


#### 插入语句

insert into 表名(字段1，字段2...) 
values (v1,v2,...);

这个在附件里全是这样的，单次插入

#### 将查询结果当作新的表
```sql
create table 平均学分绩 as (
    select s.姓名,sum(s.成绩*s.学分)/sum(s.学分) 均分
    from (
    select a.姓名,b.课程编号,b.成绩,c.学分
    from 学生信息表 a
    join 学生选课信息表 b
    join 课程名称 c
    on a.学号 = b.学号 and b.课程编号 = c.课程编号
    order by a.姓名
    ) s
group by s.姓名
order by 均分 desc
);

Query OK, 399 rows affected (0.46 sec)
Records: 399  Duplicates: 0  Warnings: 0
```

#### 数据更新删除 高危操作

```sql
updata 表 set v1 = v1,v2 = v2,.. where 条件;

delete from 表 where 条件;

truncate table 表; 表被截断,永久删除,不可回滚.
没有条件整张表全部更新.
```

#### 修改表结构,一般不修改,可以用工具
1. Alter table 表名 add【column】字段名 列类型 列属性 【first|after 字段名】
2. Alter table 表名 drop【column】字段名;
3. alter table 表名 change 原字段名 新字段名 列类型 列属性;
4. alter table 表名 modify 字段名 列类型 列属性;
5. alter table 表名 rename to 新表名;
6. alter table 表名 表选项；

#### 约束
1. 非空约束
2. 唯一约束
3. 主键约束
4. 外键约束
5. 检查约束,mysql没有
   
```sql
CREATE TABLE `学生信息表` (
  `学号` varchar(10) NOT NULL,
  `姓名` varchar(10) NOT NULL,
  `性别` varchar(2) DEFAULT NULL,
  `出生日期` datetime DEFAULT NULL,
  `政治面貌` varchar(10) DEFAULT NULL,
  `班级` varchar(6) DEFAULT NULL,
  `毕业学校` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`学号`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```
例如
1. not null 非空
2. default 默认值
3. unique 不重
4. primary key 主键
5. foreign key 外键
6. auto_increment 自增


#### 外键

foreign key(cno) references class(cno)

```sql
父表
class
cno(pk)     cname
---------------------------
101        一班
102        二班


子表
student
sno(pk)    sname       cno(fk) 只能填父表里有的
-------------------------------
1          zs           101
2          ls           101
3          ww           102
4          zl           102
5          xe           102
```
先删子表,先删父表