需求：前端折线图到后台数据库的一条完整链路
思路：
1:先改造前台，看看前端需要什么样的数据。再来构造数据库
2：该网站定位：完全可以作为一个脚手架来用。

3:Shiro简介
- 一个轻量级的安全框架。没有SpringSecurity这么大。
- 主要包括三个部分：
- 1：Subject**:**主体，应用代码直接交互的对象是Subject,也就是说Shiro的对外API核心就是Subject，代表了当前用户，
这个用户不一定是具体的人，与当前应用交互的任何东西都是Subject，如网络爬虫，机器人等；是一个抽象的概念；所有
Subject都绑定到SecurityManager,与Subject的所有交互都会委托给SecurityManager；可以把Security看成一个门面；
SecurityManager才是实际的执行者。
- 2：SecurityManager**：**安全管理器；即所有与安全相关的操作都会与SecurityManager交互；且它管理着所有
Subject;可以看出它是Shiro的核心（抓住核心），它负责与后边介绍的其它组件进行交互，如果学习过SpringMVC,可以
把它看成前端的DispatcherServlet前端控制器。
- 3：Realm**：**域，Shiro从Realm获取安全数据(如角色，用户，权限)，也就是说SecurityManager要验证用户身份，
那么它需要从Realm获取相应的用户进行比较以确定用户身份是否合法；也需要从Realm得到用户相应的角色/权限进行验证
用户是否能进行操作；可以把Realm看成DataSource。即安全数据源。