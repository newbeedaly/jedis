### NOSQL概述

1. NOSQL:not only sql:非关系型数据库。
2. 为什么需要NOSQL:
   - High performance - 高并发读写
   - Huge Storage - 海量数据的高效率存储和访问
   - High Scalability && High Availability - 高可扩展性和高可用性
3. NOSql 产品分四类：NOSQL(键值对存储) MongoDB(文档数据库) HBase（列存储） 图形数据库(InfoGrid)

![NOSql四种数据库特点](https://i.imgur.com/IbejnML.png)

4. NOSql的特点
   - 易扩展(数据之间没有关系)
   - 灵活的数据模型
   - 大数据量，高性能
   - 高可用
   
5. redis的由来
   - 对mysql的数据库不满意，c语言开发的键值对数据库
   - 键值对类型：字符串类型，散列类型，列表类型，集合类型，有序集合类型
6. redis的应用场景
   - 缓存
   - 任务队列
   - 应用排行榜
   - 网站访问统计
   - 数据过期处理
   - 分布式集群架构中的session分离
# ubuntu安装redis
### 一. 安装gcc-c++开发环境
	1. 先安装 ：sudo apt-get install build-essential
	2. 查看 gcc 版本 然后安装 统一版本的 g++ 
		>gcc --version
		gcc (Ubuntu/Linaro 4.4.4-14ubuntu5) 4.4.5...
	3. 安装 g++
	sudo apt-get install g++-4.4
	4. 查看安装版本 g++ --version
### 二. 安装redis服务端
	sudo apt-get update 
	1. sudo apt-get install redis-server
	2. 检查redis服务器系统进程和状态
		ps -aux|grep redis
		netstat -nlt|grep 6379
	3. 通过启动命令检查redis服务器状态
		sudo /etc/init.d/redis-server status
### 三. 文件配置
	1. redis允许远程访问
		sudo vim /etc/redis/redis.conf
		#注释bind
		#bind 127.0.0.1 
	2. 设置密码（修改redis.conf）
		# requirepass foobared
		requirepass pass
	3. 设置密码(重启没有了)
		>redis-cli 进入客户端
		>config set requirepass pass
		再次进入
		>redis-cli
		>auth pass
		>shutdown save
		显示ok进入成功
### 四. 重启redis服务器 
	1. 适合apt-get/yum安装
	/etc/init.d/redis-server stop
	/etc/init.d/redis-server start
	/etc/init.d/redis-server restart
	service redis restart
	kill -9 进程号(不是端口号)

### 五. redis数据结构
	
	1. 字符串：set/get
	例子：set name java / get name /getset name open/
	incr n(默认是0)/decr n/incrby n 3/decrby n 6/append name 1/ keys * /del name
	参考资料：http://blog.java1234.com/blog/articles/316.html
	2. 哈希：hset/hget/hlen/hgetall/hexists
	例子：hset h1 username user/hset h1 password pass/hget h1 username/hmset h2 username user2 password pass2/hgetall h2/hdel h1 username/hdel h2 usernaem password/hset h1 age 20/hget h1 age/hincrby h1 age 5/hexists h1 age/hgetall h1/hkeyall h1
	参考资料：http://blog.java1234.com/blog/articles/317.html
	3. List：lpush/lrange/
	例子：lpush l1 a b c d/lpush l1 1 2 3 4/lrange l1 0 -1/
	rpush l2 a b c d/rpush l2 1 2 3 4/lrange l2 0 6/rpop l2/lpop l2/llen l2/lpushx l2 xx/lrange l2 0 -1/rpushx l2 yy/lrange l2 0 -1/lrem l3 2 1/lrem l3 0 2/
	参考资料：http://blog.java1234.com/blog/articles/318.html
	4. Set(不重复无序的元素)
	例子：sadd set1 a b c /smembers set1/sdd set1 a d e/
	srem set1 a d/sismember set1 a(是否存在)/sdiff set2 set3（计算差集）/scard set4/srandmenber set1/sdiffstore r1 set2 set3/sinterstore r2 set2 set3/sunionstore r3 set2 set3/差集交集并集/
	参考资料：http://blog.java1234.com/blog/articles/319.html
	5. Sorted-set(有序)
	例子：zass sort1 5 a 4 b 6 c/zrange sort1 0 -1/zadd sort1 7 d/zscore sort1 b/zrem sort1 b/zcard sort1/zrange sort1 0 -1 withscores/zrevrange sort1 0 -1 withscores/zremragnebyrank sort1 0 2/...
	参考资料：http://blog.java1234.com/blog/articles/320.html
	6. type s1/expire s1 120(设置存在多少秒)/llt s1
	六. Redis持久化
	持久化：数据从内存写入硬盘。两种方式：rdb方式和aof方式
	rdb：指定时间写入硬盘。优势：只有一个文件。宕机：数据丢失多
	aof：将以日志、操作写入硬盘 dump.rdb/appendonly.aof文件默认在 /var/lib/redis/文件目录中

	第六讲：集群
