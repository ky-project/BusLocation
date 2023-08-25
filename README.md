# 理工科艺基于GPS的班车实时定位系统开发

## 1. 各阶段任务

1. 2019.03.25-2019.04.01

   完成系统开发的需求分析说明文档，将包含功能模块图、用例图、数据流图。详细见：/doc/需求分析/需求分析文档.docx。

2. 2019.04.02-2019.04.09

   学习系统开发所需的知识，主要包括多线程、bootstrap、网络编程。

3. 2019.04.10-2019.04.16

   数据库设计收尾，开始系统开发，主要包括用户的登录、路线查询、管理员登录、管理员指定路线对应的校车。

4. 2019.04.17-2019.04.30

   出系统Demo。

## 2. 开发进度

### 2.1 需求分析-已完成

需求分析文档一份

### 2.2 数据库设计-已完成

E-R图、概念模型、逻辑模型、物理模型、数据库创建SQL文件

1. 问题总结：

   1. 用户职工编号登录，非校内人员，自己分配。
   2. 根据权限判断是否能进行该操作
   3. 路线不显示线、实时查询显示经过的路线

### 2.3 系统开发

1. 已完成

   教师登录、管理员登录、教师查询校车定位

   用户管理、用户角色/角色权限相关接口前后端对接完成

2. 待完成

   后台各表的管理，管理员指定路线对应的校车

3. 待修改

   

## 3. 本阶段学习任务和开发任务

### 3. 1需学习内容（4月10日前学习并掌握）

1. 多线程：开发小组
2. 微信导入系统接口(url)：开发小组
3. 服务器拦截报文：开发小组

### 3.2 开发任务（4月30日前出Demo）

1. 数据库设计：开发小组
2. 报文解析存入数据库：开发小组
3. 多线程接收/处理报文：开发小组
4. 教师登录、教师查看指定路线的校车定位；管理员登录、管理员指定对应路线的校车（管理员部分页面可先简化）：开发小组

## 4. 项目重点知识

1. GPS信号发送/接收形式

   GPS信号以TCP/UDP协议报文形式发送到指定服务器IP，因此需要监控服务器指定端口，获取并解析GPS信号。GPS信号中带有的信息主要包含：经纬度（具体的坐标系标准暂定）、方向角、时间等等

2. 多线程（线程池）

   由于班车数量较多，因此需要考虑多线程问题。采用线程池的方式，用一个线程抓取监听端口获取到的GPS报文进行解析。

3. 高并发

   由于查看班车信息的大多为教师，且访问时间较为固定，大致在6:00~8:00这个个时间段存在多用户访问，因此需要考虑高并发问题。

## 5. 隐含问题

1. 校车行驶时间段不统一
2. 路线行驶校车不确定
3. GPS报文发送协议未定

