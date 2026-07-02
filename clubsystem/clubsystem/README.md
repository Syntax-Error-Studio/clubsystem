# 校园社团活动报名与签到管理系统

本项目是基于传统 Java Web 技术栈（JSP + Servlet + JavaBean + DAO 模式）开发的一个校园社团活动报名与签到管理系统原型，实现了用户注册、登录以及系统主页等基础功能，并提供了活动报名与签到的架构设计示例。

## 结构说明

项目目录结构如下：

- **src/**：存放 Java 源代码，按照分层思想划分为 `bean`、`dao`、`service`、`servlet`、`util`等包。
  - **bean**：JavaBean 实体类，对应数据库表，例如 `User`、`Activity` 等。
  - **dao**：数据访问层，负责对数据库进行增删改查，例如 `UserDao` 提供用户的持久化操作。
  - **service**：业务逻辑层，封装业务规则，例如 `UserService` 处理注册和登录逻辑。
  - **servlet**：控制器层，负责接收请求、调用业务层并进行页面跳转，例如 `LoginServlet`、`RegisterServlet`。
  - **util**：工具类包，例如数据库连接工具 `DBUtil`。
- **WebContent/**：存放 JSP 页面和 Web 资源。
  - 静态页面如 `login.jsp`、`register.jsp`、`index.jsp` 提供用户界面。
  - `WEB-INF/web.xml`：Web 应用的配置文件，用于配置 Servlet 映射。
- **README.md**：项目说明文档（当前文件）。

> 注意：由于作业重点在系统分析和设计，本示例仅实现了用户注册和登录功能，其余功能如活动管理、报名管理、签到管理仅在代码中给出接口或注释，可以在此基础上扩展实现。

## 编译和运行

1. 使用支持 JSP/Servlet 的 Web 服务器，如 **Apache Tomcat**。建议版本 8.x 或以上。
2. 将 `clubsystem` 目录作为一个 Eclipse/IDEA Web 项目或手动部署到 Tomcat 中。
3. 配置数据库连接：
   - 修改 `src/com/clubsystem/util/DBUtil.java` 中的数据库 URL、用户名和密码，使其能够连接到你的 MySQL 数据库。
   - 在数据库中创建相应的数据表（示例在代码注释中给出）。
4. 部署后通过浏览器访问 `http://localhost:8080/clubsystem/login.jsp` 即可进入系统登录页面。

## 版权声明

本项目仅用于教学演示，代码中所有注释均采用中文书写，符合中国开发者的习惯。你可以自由修改和扩展本项目，以完成完整的校园社团活动报名与签到管理系统。