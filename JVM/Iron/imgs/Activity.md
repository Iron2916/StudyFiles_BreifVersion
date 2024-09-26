# 一. Activity 详细操作

## 1. 对应工具的下载与配置

<a href = "http://www.activiti.org/before-you-start">官网进行跳转下载</a>
<img src = "./imgs/03_对应的war包放到tomcat里面中"> </img>

**默认登陆账号：kermit kermit**

## 2. Activity 使用的七大流程

- <span style = "color: red"> 第一步：引入依赖并初始化数据库 </span>
  ① 添加依赖

   ``` XML
   <!--引入activiti的springboot启动器 -->
   <dependency>
       <groupId>org.activiti</groupId>
       <artifactId>activiti-spring-boot-starter</artifactId>
       <version>7.1.0.M6</version>
       <exclusions>
           <exclusion>
               <artifactId>mybatis</artifactId>
               <groupId>org.mybatis</groupId>
           </exclusion>
       </exclusions>
   </dependency>
   ```

  ② 添加配置
  ``` yaml
  spring:    
  	activiti:
        #    false:默认，数据库表不变，但是如果版本不对或者缺失表会抛出异常（生产使用）
        #    true:表不存在，自动创建（开发使用）
        #    create_drop: 启动时创建，关闭时删除表（测试使用）
        #    drop_create: 启动时删除表,在创建表 （不需要手动关闭引擎）
        database-schema-update: true
        #监测历史表是否存在，activities7默认不开启历史表
        db-history-used: true
        #none：不保存任何历史数据，流程中这是最高效的
        #activity：只保存流程实例和流程行为
        #audit：除了activity，还保存全部的流程任务以及其属性，audit为history默认值
        #full：除了audit、还保存其他全部流程相关的细节数据，包括一些流程参数
        history-level: full
        #校验流程文件，默认校验resources下的process 文件夹的流程文件
        check-process-definitions: true
  ```

  ③ 启动项目：生成对应的表（一共生成了25张）
  <img src="./imgs/02_activity生成的对应的表.png"> </img>

  <span style = "color: pink"> 
  注意：activity 整合了spring-security: </span>
  <img src="./imgs/01_Activity整合了spriting-security.png"> </img>

  <span style="color:red">同时:</span>因为我们设置的 Spring-security 验证的是 sysUser 即用户表。所以Activity 框架会检查用户是否存在，否则会出现异常。

- 