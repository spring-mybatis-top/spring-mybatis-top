官网： [http://spring-mybatis.top](http://spring-mybatis.top)

开始**免费**生成第一个项目 [http://free.spring-mybatis.top](http://free.spring-mybatis.top)

一句话，Spring-Mybatis-Vue 源代码生成器是充分使用**ER图**信息生成了**所有**能生成的**源代码**（应用框架和前后端增删改查/CRUD），避免了程序员没有提升的**重复**劳动，而更加关注于**业务逻辑开发**和自身的**技术进步**。

## 主要功能描述

本软件(Spring-Mybatis-Vue 源代码生成器)是一个**服务于Web软件开发人员**的在线工具，是一个面向**Spring Boot** + **Mybatis** + **Vue** (**前端** + **后端**) 源代码生成器，生成的源代码可以直接运行且通过浏览器使用、并且单元测试通过。

**使用人群**：Web程序员（前端和后端）、学习软件开发的大学生、产品经理、项目经理、测试人员。

用户以文本格式描述系统的**ER图**作为输入（包括任意多个 **一对一**、**一对多**、**多对多** 关系），本软件会生成:

- **后端部分**：
  - 标准**RESTful** Controller**源代码**，包括**增删改查**、**分页**、**排序**以及Blob支持。
  - 提供针对上述Controller的完整**单元测试**案例**源代码**。
  - 基于Maven的完整的Spring Boot框架（根目录输入./mvnw就可以启动可以使用的前后端程序）。
  - 包括数据库的完整定义以及约束关系的源代码（支持**Mysql**，**H2内存数据库**等）。
  - 使用MyBatis Generator生成的Mapper、Example源码以及测试数据。
  - 集成了**Swagger** OpenAPI支持。
  - 基于角色的基本用户权限管理源代码（使用**Jwt**或**Session**）。
  - 包含**Docker-Compose**文件以及**CI-CD**支持。

- **前端部分**：
  - 包含完整的**Vue**（或**Angular**）**源代码**，与后端匹配，支持**增删改查**功能。

- **管理部分**：
  - 提供资源监控，包括内存、请求数等的源代码。
  - 包含Spring配置管理的源代码。
  - 提供服务状态管理的源代码。
  - 包含日志管理的源代码。
  - 如果选择H2内存数据库，提供H2自带的一个客户端。

## 开发背景

本软件是在JHipster基础上进行二次开发的，Jhipster是商业友好的开源许可证（Apache-2.0 license）,github 20k星，4k fork [https://github.com/jhipster/generator-jhipster](https://github.com/jhipster/generator-jhipster)。

本软件还使用了JDL Studio，JDL Studio同样是商业友好开源许可证（Apache-2.0 license） [https://github.com/jhipster/jdl-studio](https://github.com/jhipster/jdl-studio)。

## 开发使用等请参考官网文档

官网： [http://spring-mybatis.top](http://spring-mybatis.top)
