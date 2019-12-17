# 教师档案信息管理平台

部署Linux服务器:
```sql
scp /home/soldier/SOLDIER/IDE_project/idea_project/teacher/target/teacher.war 116.62.48.112:/usr/local/tomcat
ssh 116.62.48.112
cd /usr/local/tomcat/webapps
rm -rf teacher.war
rm -rf teacher
cd ..
mv teacher.war webapps/
```

####清空表数据并重新排序:
```sql
truncate table xxx;
```

####建表：
```sql
create table competition_annex
(
  competition_annex_id int auto_increment
    primary key,
  competition_id       int          null,
  file_name            varchar(255) null,
  file_path            varchar(255) null,
  file_type            varchar(255) null
)
  comment '竞赛项目附件' charset = utf8;
```
