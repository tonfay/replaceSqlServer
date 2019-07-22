# replaceSqlServer
过滤SQL参数/清洗SQL

单条
[GET/POST]/server/replace
参数:sql=select * from a where c = 1
response:select * from a where c = ?

批量
[POST]/server/replaces
参数:BODY:["select * from a where c = 1","select * from b where c = 1"]
response:select * from a where c = ?,select * from b where c = ?

压测批量接口:
50并发
body中为800+条SQL
TPS为:190+-


wiki:
rest 入口在哪里?
com.gofun.ms.feign.rest.ServerRest
