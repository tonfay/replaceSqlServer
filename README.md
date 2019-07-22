# replaceSqlServer
过滤SQL参数/清洗SQL

单条
[GET/POST]/server/replace
sql=select * from a

批量
[POST]/server/replaces
BODY:["select * from a","select * from b"]


压测批量接口:
50并发
body中为800+条SQL
TPS为:190+-
