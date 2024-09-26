# MyBatis

[GitHub官网](https://github.com/mybatis/mybatis-3)

# 1. 是什么？优点是什么？

MyBatis 是一个优秀的**持久层框架**，它是一个基于 Java 的持久层框架，用于将**对象**与 **SQL 语句**进行**映射**。MyBatis 提供了强大的数据库操作支持，同时也简化了数据库交互的过程。

MyBatis 的核心思想是将 **SQL 语句从 Java 代码中分离出来**，使得 SQL 语句可以单独进行管理和优化，从而提高了代码的可维护性和灵活性。MyBatis 通过 XML 文件或者注解的方式来进行 SQL 语句的映射，使得开发者可以轻松地进行增删改查等数据库操作。

除了基本的 CRUD 操作外，MyBatis 还提供了一些高级特性，比如**动态 SQL**、**延迟加载**、**缓存机制**等，这些特性使得 MyBatis 在实际项目中的应用更加灵活和高效。

总的来说，MyBatis 是一个轻量级、灵活、强大的持久层框架，适用于各种规模的项目，并且在 Java 开发社区中得到了广泛的应用。



# 1. 大概结构是什么？



1. Controller
2. service
3. Mapper
   - ① mapper接口
   - ② mapper.xml文件

**示意图：**

![](imgs/image_pikdpXSaNV.png)



# 3. 配置方法有什么？

## 3.1 基于XML配置

官方网站：
[mybatis – MyBatis 3 | 配置](https://mybatis.org/mybatis-3/zh_CN/configuration.html)

``` xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <settings>
        <!-- 开启驼峰式映射(表的字段名)-->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
        <!-- 开启logback日志输出-->
        <setting name="logImpl" value="SLF4J"/>
        <!--开启resultMap自动映射 -->
        <setting name="autoMappingBehavior" value="FULL"/>
    </settings>

    <typeAliases>
        <!-- 给实体类起别名(后续的配置类文件可以不用全类名) -->
        <package name="com.atguigu.pojo"/>
    </typeAliases>

    <!-- environments表示配置Mybatis的开发环境，可以配置多个环境，在众多具体环境中，使用default属性指定实际运行时使用的环境。default属性的取值是environment标签的id属性的值。 -->
    <environments default="development">
        <!-- environment表示配置Mybatis的一个具体的环境 -->
        <environment id="development">
            <!-- Mybatis的内置的事务管理器 -->
            <transactionManager type="JDBC"/>
            <!-- 配置数据源 -->
            <dataSource type="POOLED">
                <!-- 建立数据库连接的具体信息 -->
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>	
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis-example"/>
                <property name="username" value="root"/>
                <property name="password" value="admin123"/>
            </dataSource>
        </environment>
    </environments>
	
    <!-- Mapper注册：指定Mybatis映射文件的具体位置 -->
    <!-- mapper标签：配置一个具体的Mapper映射文件 -->
    <!-- 对Maven工程的目录结构来说，resources目录下的内容会直接放入类路径，所以这里我们可以以resources目录为基准 -->
    <mappers>
        <!-- resource属性：指定Mapper映射文件的实际存储位置，这里需要使用一个以类路径根目录为基准的相对路径 -->
        <mapper resource="mappers/UserMapper.xml"/>
        <!-- name属性：指定Mybatis映射文件下的所有xml文件 -->
        <package name="com.atguigu.mapper"/>
    </mappers>
	
    <!-- 配置插件 -->
    <plugins>
        <plugin interceptor="com.github.pagehelper.PageInterceptor">
            <property name="helperDialect" value="mysql"/>
        </plugin>
    </plugins>
    
</configuration>
```



## 3.2 基于SpringBootYML文件

方式一(yaml+配置类)：

``` yaml
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mydatabase
    username: username
    password: password

mybatis:
  mapper-locations: classpath:/mappers/*.xml
  configuration:
    map-underscore-to-camel-case: true		#设置驼峰映射
```

``` java
@Configuration
public class MyBatisConfig {

    @Autowired
    private DataSource dataSource;

    // 配置mapper.xml文件文件位置等参数
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage("com.example.model"); // 设置实体类所在包路径
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:mapper/*.xml"); // 设置mapper.xml文件的位置
        sessionFactory.setMapperLocations(resources);
        return sessionFactory.getObject();
    }
	
    // 开启事务(SpringBoot继承Mybatis事务，不配置springboot会自动进行配置)
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
```


方式二(纯yaml)：

``` yaml
spring:
  datasource:	#数据库配置方式一
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mydatabase
    username: username
    password: password

mybatis:
  configuration:
    map-underscore-to-camel-case: true
    log-impl: SLF4J
    auto-mapping-behavior: FULL
    
  type-aliases-package: com.atguigu.pojo
  
  environment: #数据库配置方式二：很少用
    id: development
    transaction-factory: JDBC
    data-source:
      type: POOLED
      driver: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/mybatis-example
      username: root
      password: admin123
      
  mapper-locations: 
    - classpath:mappers/UserMapper.xml
    - classpath:com/atguigu/mapper/*.xml
    
  plugins:
    - interceptor: com.github.pagehelper.PageInterceptor
      properties:
        helperDialect: mysql
```



# 4. MyBatis#{}和${}区别？

1. Mybatis会将SQL语句中的#{}转换为问号占位符。

2. ${}形式传参，底层Mybatis做的是字符串拼接操作。

3. 一般不用${}进行参数传递，除非是拼接关键字
   ``` java
   @Select("select * from user where ${column} = #{value}")
   User findByColumn(@Param("column") String column, 
                                   @Param("value") String value);
   ```



# 5. Mapper和Mapper.xml参数传递关系？

1. 单个参数传递
   单个简单类型参数，在#{}中可以随意命名，但是没有必要。通常还是使用和接口方法参数同名。

   ``` java
   // 接口定义
   Employee selectEmployee(Integer empId);
   // xml文件
   <select id="selectEmployee" resultType="com.atguigu.mybatis.entity.Employee">
     select emp_id empId,emp_name empName,emp_salary empSalary from t_emp where emp_id=#{empId}
   </select>
   ```

2. 多个参数传递
   零散的多个简单类型参数，如果没有特殊处理，那么Mybatis无法识别自定义名称

   ```java
   // 接口定义
   int updateEmployee(@Param("empId") Integer empId,@Param("empSalary") Double empSalary);
   // xml文件
   <update id="updateEmployee">
     update t_emp set emp_salary=#{empSalary} where emp_id=#{empId}
   </update>
   ```

3. 对象传递
   ``` java
   // 接口定义
   int insertEmployee(Employee employee);
   // xml文件
   <insert id="insertEmployee">
     insert into t_emp(emp_name,emp_salary) values(#{empName},#{empSalary})
   </insert>
   ```

   Mybatis会根据#{}中传入的数据，**加工成getXxx()方法，通过反射在实体类对象中调用这个方法**，从而获取到对应的数据。填充到#{}解析后的问号占位符这个位置。

# 6. 主键回写？

- 自增长
  ```xml
  <!-- int insertEmployee(Employee employee); -->
  <!-- useGeneratedKeys属性字面意思就是“使用生成的主键” -->
  <!-- keyProperty属性可以指定主键在实体类对象中对应的属性名，Mybatis会将拿到的主键值存入这个属性 -->
  <insert id="insertEmployee" useGeneratedKeys="true" keyProperty="empId">
    insert into t_emp(emp_name,emp_salary)
    values(#{empName},#{empSalary})
  </insert>
  ```

-  非自增长
  方式一：使用 selectKey 进行查询赋值。
  
  ``` xml
  <insert id="insertUser" parameterType="com.example.User">
      <!-- 插入操作前生成主键 -->
      <selectKey keyProperty="id" order="BEFORE" resultType="java.lang.Long">
          SELECT IFNULL(MAX(id), 0) + 1 FROM User
      </selectKey>
      
      INSERT INTO User (id, username, email) 
      VALUES (#{id}, #{username}, #{email})
  </insert>
  ```
  
  方式二：在业务逻辑层进行id赋值UUID
  ``` java
  public void saveUser(User user) {
      // 生成 UUID 作为主键
      String uuid = UUID.randomUUID().toString();
      user.setId(uuid);
      
      // 调用 MyBatis 插入方法
      userMapper.insertUser(user);
  }
  ```
  
  

# 7. XML中sql语句及属性


类型分类：

- insert标签。

  | 属性            | 描述                                                         |
  | --------------- | ------------------------------------------------------------ |
  | `id`            | 在命名空间中唯一的标识符，可以被用来引用这条语句。           |
  | `resultType`    | 期望从这条语句中返回结果的类全限定名或别名。 注意，如果返回的是集合，那应该设置为集合包含的类型，而不是集合本身的类型。 resultType 和 resultMap 之间只能同时使用一个。 |
  | `resultMap`     | 对外部 resultMap 的命名引用。结果映射是 MyBatis 最强大的特性，如果你对其理解透彻，许多复杂的映射问题都能迎刃而解。 resultType 和 resultMap 之间只能同时使用一个。 |
  | `timeout`       | 这个设置是在抛出异常之前，驱动程序等待数据库返回请求结果的秒数。默认值为未设置（unset）（依赖数据库驱动）。 |
  | `statementType` | 可选 STATEMENT，PREPARED 或 CALLABLE。这会让 MyBatis 分别使用 Statement，PreparedStatement 或 CallableStatement，默认值：PREPARED。 |

- **insert, update 和 delete标签：**

  | 属性               | 描述                                                         |
  | ------------------ | ------------------------------------------------------------ |
  | `id`               | 在命名空间中唯一的标识符，可以被用来引用这条语句。           |
  | `timeout`          | 这个设置是在抛出异常之前，驱动程序等待数据库返回请求结果的秒数。默认值为未设置（unset）（依赖数据库驱动）。 |
  | `statementType`    | 可选 STATEMENT，PREPARED 或 CALLABLE。这会让 MyBatis 分别使用 Statement，PreparedStatement 或 CallableStatement，默认值：PREPARED。 |
  | `useGeneratedKeys` | （仅适用于 insert 和 update）这会令 MyBatis 使用 JDBC 的 getGeneratedKeys 方法来取出由数据库内部生成的主键（比如：像 MySQL 和 SQL Server 这样的关系型数据库管理系统的自动递增字段），默认值：false。 |
  | `keyProperty`      | （仅适用于 insert 和 update）指定能够唯一识别对象的属性，MyBatis 会使用 getGeneratedKeys 的返回值或 insert 语句的 selectKey 子元素设置它的值，默认值：未设置（`unset`）。如果生成列不止一个，可以用逗号分隔多个属性名称。 |
  | `keyColumn`        | （仅适用于 insert 和 update）设置生成键值在表中的列名，在某些数据库（像 PostgreSQL）中，当主键列不是表中的第一列的时候，是必须设置的。如果生成列不止一个，可以用逗号分隔多个属性名称。 |

`statementType` 是 MyBatis XML 映射文件中 `<select>`, `<insert>`, `<update>`, `<delete>` 等标签的一个属性，用于指定 SQL 语句的类型。

它有三个可能的取值：

1. `STATEMENT`：直接操作 SQL，不进行预编译或存储过程调用。
2. `PREPARED`：SQL 在执行之前被预编译，然后由 JDBC 的 Statement 对象执行。
3. `CALLABLE`：执行存储过程。

默认情况下，MyBatis 使用 `PREPARED`，因为它提供了最好的性能和安全性。但在某些特殊情况下，可能需要使用 `STATEMENT` 或 `CALLABLE`，具体取决于数据库和应用程序的要求。

通常情况下，只有在性能调优或特殊需求时才需要显式地指定 `statementType`。

# 8. MyBatis多表映射

1. 对一

   - 实体类
     ``` java
     public class Customer {
     
       private Integer customerId;
       private String customerName;
     
     }
     
     public class Order {
     
       private Integer orderId;
       private String orderName;
       private Customer customer;// 体现的是对一的关系
     
     }  
     ```

   - mapper接口
     ``` java
     public interface OrderMapper {
       Order selectOrderWithCustomer(Integer orderId);
     }
     ```

   - xml（**association**， **property**， **javaType**）
     ``` java
     <!-- 创建resultMap实现“对一”关联关系映射 -->
     <!-- id属性：通常设置为这个resultMap所服务的那条SQL语句的id加上“ResultMap” -->
     <!-- type属性：要设置为这个resultMap所服务的那条SQL语句最终要返回的类型 -->
     <resultMap id="selectOrderWithCustomerResultMap" type="order">
     
       <!-- 先设置Order自身属性和字段的对应关系 -->
       <id column="order_id" property="orderId"/>
     
       <result column="order_name" property="orderName"/>
     
       <!-- 使用association标签配置“对一”关联关系 -->
       <!-- property属性：在Order类中对一的一端进行引用时使用的属性名 -->
       <!-- javaType属性：一的一端类的全类名 -->
       <association property="customer" javaType="customer">
     
         <!-- 配置Customer类的属性和字段名之间的对应关系 -->
         <id column="customer_id" property="customerId"/>
         <result column="customer_name" property="customerName"/>
     
       </association>
     
     </resultMap>
     
     <!-- Order selectOrderWithCustomer(Integer orderId); -->
     <select id="selectOrderWithCustomer" resultMap="selectOrderWithCustomerResultMap">
     
       SELECT order_id,order_name,c.customer_id,customer_name
       FROM t_order o
       LEFT JOIN t_customer c
       ON o.customer_id=c.customer_id
       WHERE o.order_id=#{orderId}
     
     </select>
     ```

     

2. 对多

   - 实体类
     ``` java
     public class Customer {
     
       private Integer customerId;
       private String customerName;
       private List<Order> orderList;// 体现的是对多的关系
     }
     
     public class Order {
     
       private Integer orderId;
       private String orderName;
       private Customer customer;// 体现的是对一的关系
       
     }
     ```

   - mapper接口
     ``` java
     public interface CustomerMapper {
     
       Customer selectCustomerWithOrderList(Integer customerId);
     
     }
     ```

   - xml文件（**collection**， **property**，**ofType**）

     ``` xml
     <!-- 配置resultMap实现从Customer到OrderList的“对多”关联关系 -->
     <resultMap id="selectCustomerWithOrderListResultMap"
     
       type="customer">
     
       <!-- 映射Customer本身的属性 -->
       <id column="customer_id" property="customerId"/>
     
       <result column="customer_name" property="customerName"/>
     
       <!-- collection标签：映射“对多”的关联关系 -->
       <!-- property属性：在Customer类中，关联“多”的一端的属性名 -->
       <!-- ofType属性：集合属性中元素的类型 -->
       <collection property="orderList" ofType="order">
     
         <!-- 映射Order的属性 -->
         <id column="order_id" property="orderId"/>
     
         <result column="order_name" property="orderName"/>
     
       </collection>
     
     </resultMap>
     
     <!-- Customer selectCustomerWithOrderList(Integer customerId); -->
     <select id="selectCustomerWithOrderList" resultMap="selectCustomerWithOrderListResultMap">
       SELECT c.customer_id,c.customer_name,o.order_id,o.order_name
       FROM t_customer c
       LEFT JOIN t_order o
       ON c.customer_id=o.customer_id
       WHERE c.customer_id=#{customerId}
     </select>
     ```

   优化：

   前提设置：

   | setting属性         | 属性含义                                                     | 可选值              | 默认值  |
   | ------------------- | ------------------------------------------------------------ | ------------------- | ------- |
   | autoMappingBehavior | 指定 MyBatis 应如何自动映射列到字段或属性。 NONE 表示关闭自动映射；PARTIAL 只会自动映射没有定义嵌套结果映射的字段。 FULL 会自动映射任何复杂的结果集（无论是否嵌套）。 | NONE, PARTIAL, FULL | PARTIAL |

   ``` xml
   <!--开启resultMap自动映射 -->
   <setting name="autoMappingBehavior" value="FULL"/>
   ```

   优化后：
   ``` xml
   <resultMap id="teacherMap" type="teacher">
       <id property="tId" column="t_id" />
       <!-- 开启自动映射,并且开启驼峰式支持!可以省略 result!-->
   <!--        <result property="tName" column="t_name" />-->
       <collection property="students" ofType="student" >
           <id property="sId" column="s_id" />
   <!--            <result property="sName" column="s_name" />-->
       </collection>
   </resultMap>
   ```

   

   总结：

   | 关联关系 | 配置项关键词                              | 所在配置文件和具体位置            |
   | -------- | ----------------------------------------- | --------------------------------- |
   | 对一     | association标签/javaType属性/property属性 | Mapper配置文件中的resultMap标签内 |
   | 对多     | collection标签/ofType属性/property属性    | Mapper配置文件中的resultMap标签内 |





# 9. 动态Sql



1. where配合if

   where 配合 if 消除多余的 and/or
   ```xml
   <select id="selectEmployeeByCondition" resultType="employee">
       select emp_id,emp_name,emp_salary from t_emp
       <!-- where标签会自动去掉“标签体内前面多余的and/or” -->
       <where>
           <!-- 使用if标签，让我们可以有选择的加入SQL语句的片段。这个SQL语句片段是否要加入整个SQL语句，就看if标签判断的结果是否为true -->
           <!-- 在if标签的test属性中，可以访问实体类的属性，不可以访问数据库表的字段 -->
           <if test="empName != null">
               <!-- 在if标签内部，需要访问接口的参数时还是正常写#{} -->
               or emp_name=#{empName}
           </if>
           <if test="empSalary &gt; 2000">
               or emp_salary>#{empSalary}
           </if>
           <!--
            第一种情况：所有条件都满足 WHERE emp_name=? or emp_salary>?
            第二种情况：部分条件满足 WHERE emp_salary>?
            第三种情况：所有条件都不满足 没有where子句
            -->
       </where>
   </select>
   ```

2. set

   set消除多余的，
   ``` xml
   <update id="updateEmployeeDynamic">
       update t_emp
       <!-- set emp_name=#{empName},emp_salary=#{empSalary} -->
       <!-- 使用set标签动态管理set子句，并且动态去掉两端多余的逗号 -->
       <set>
           <if test="empName != null">
               emp_name=#{empName},
           </if>
           <if test="empSalary &lt; 3000">
               emp_salary=#{empSalary},
           </if>
       </set>
       where emp_id=#{empId}
       <!--
            第一种情况：所有条件都满足 SET emp_name=?, emp_salary=?
            第二种情况：部分条件满足 SET emp_salary=?
            第三种情况：所有条件都不满足 update t_emp where emp_id=?
               没有set子句的update语句会导致SQL语法错误
        -->
   </update>
   ```

3. trim
   ``` xml
   <select id="selectEmployeeByConditionByTrim" resultType="com.atguigu.mybatis.entity.Employee">
       select emp_id,emp_name,emp_age,emp_salary,emp_gender
       from t_emp
       
       <!-- prefix属性指定要动态添加的前缀 -->
       <!-- suffix属性指定要动态添加的后缀 -->
       <!-- prefixOverrides属性指定要动态去掉的前缀，使用“|”分隔有可能的多个值 -->
       <!-- suffixOverrides属性指定要动态去掉的后缀，使用“|”分隔有可能的多个值 -->
       <!-- 当前例子用where标签实现更简洁，但是trim标签更灵活，可以用在任何有需要的地方 -->
       <trim prefix="where" suffixOverrides="and|or">
           <if test="empName != null">
               emp_name=#{empName} and
           </if>
           <if test="empSalary &gt; 3000">
               emp_salary>#{empSalary} and
           </if>
           <if test="empAge &lt;= 20">
               emp_age=#{empAge} or
           </if>
           <if test="empGender=='male'">
               emp_gender=#{empGender}
           </if>
       </trim>
   </select>
   ```

4. choose/when/otherwise标签

5. 在多个分支条件中，仅执行一个。

   -   从上到下依次执行条件判断
   -   遇到的第一个满足条件的分支会被采纳
   -   被采纳分支后面的分支都将不被考虑
   -   如果所有的when分支都不满足，那么就执行otherwise分支

   ``` xml
   <!-- List<Employee> selectEmployeeByConditionByChoose(Employee employee) -->
   <select id="selectEmployeeByConditionByChoose" resultType="com.atguigu.mybatis.entity.Employee">
       select emp_id,emp_name,emp_salary from t_emp
       where
       <choose>
           <when test="empName != null">emp_name=#{empName}</when>
           <when test="empSalary &lt; 3000">emp_salary &lt; 3000</when>
           <otherwise>1=1</otherwise>
       </choose>
   </select>
   ```

6. foreach标签
   ``` xml
   <!--
       collection属性：要遍历的集合
       item属性：遍历集合的过程中能得到每一个具体对象，在item属性中设置一个名字，将来通过这个名字引用遍历出来的对象
       separator属性：指定当foreach标签的标签体重复拼接字符串时，各个标签体字符串之间的分隔符
       open属性：指定整个循环把字符串拼好后，字符串整体的前面要添加的字符串
       close属性：指定整个循环把字符串拼好后，字符串整体的后面要添加的字符串
       index属性：这里起一个名字，便于后面引用
           遍历List集合，这里能够得到List集合的索引值
           遍历Map集合，这里能够得到Map集合的key
    -->
   <foreach collection="empList" item="emp" separator="," open="values" index="myIndex">
       <!-- 在foreach标签内部如果需要引用遍历得到的具体的一个对象，需要使用item属性声明的名称 -->
       (#{emp.empName},#{myIndex},#{emp.empSalary},#{emp.empGender})
   </foreach>
   
   <!-- 模拟输出
   (Alice, 0, 5000, F),
   (Bob, 1, 6000, M),
   (Charlie, 2, 7000, M)
   <!-->
   ```

   **注意：批量操作设置**
   
   ``` xml
   atguigu.dev.url=jdbc:mysql:///mybatis-example?allowMultiQueries=true
   ```
   
   **批量查询：**
   
   ``` java
   <mapper namespace="com.example.UserMapper">
       <!-- 批量查询用户信息 -->
       <select id="selectUsersByIds" resultType="com.example.User">
           SELECT id, username, email
           FROM User
           WHERE id IN
           <foreach item="id" index="index" collection="list" open="(" separator="," close=")">
               #{id}
           </foreach>
       </select>
   </mapper>
   ```
   
   
   
7. sql片段
   ``` xml
   <sql id="mySelectSql">
       select emp_id,emp_name,emp_age,emp_salary,emp_gender from t_emp
   </sql>
   <!-- 使用include标签引用声明的SQL片段 -->
   <include refid="mySelectSql"/>
   ```



# 10. 高级特性

### 10.1 分页插件PageHelper

- Pom
  ``` xml
  <dependency>
      <groupId>com.github.pagehelper</groupId>
      <artifactId>pagehelper</artifactId>
      <version>5.1.11</version>
  </dependency>
  ```

- xml配置或者yaml配置
  xml:

  ``` xml
  <plugins>
      <plugin interceptor="com.github.pagehelper.PageInterceptor">
          <property name="helperDialect" value="mysql"/>
      </plugin>
  </plugins>
  ```

  yaml:
  ``` yaml
  # PageHelper 配置
  pagehelper:
    helper-dialect: mysql
    reasonable: true
    support-methods-arguments: true
    params: count=countSql
    offset-as-page-num: true
    row-bounds-with-count: true
  ```

- 使用
  ``` java
  @Test
  public void testTeacherRelationshipToMulti() {
  
      TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);
  	
      // 第一步：声明pageNum, pageSize
      PageHelper.startPage(1,2);
  
      List<Teacher> allTeachers = teacherMapper.findAllTeachers();
  	
      // 第二步：导入数据获得pageInfo
      PageInfo<Teacher> pageInfo = new PageInfo<>(allTeachers);
  	
      // 第三步：获取分页信息
      System.out.println("pageInfo = " + pageInfo);
      long total = pageInfo.getTotal(); // 获取总记录数
      System.out.println("total = " + total);
      int pages = pageInfo.getPages();  // 获取总页数
      System.out.println("pages = " + pages);
      int pageNum = pageInfo.getPageNum(); // 获取当前页码
      System.out.println("pageNum = " + pageNum);
      int pageSize = pageInfo.getPageSize(); // 获取每页显示记录数
      System.out.println("pageSize = " + pageSize);
      List<Teacher> teachers = pageInfo.getList(); //获取查询页的数据集合
      System.out.println("teachers = " + teachers);
      teachers.forEach(System.out::println);
  
  }
  ```

### 10.2 逆向工程

&#x20;  MyBatisX 是一个 MyBatis 的代码生成插件，可以通过简单的配置和操作快速生成 MyBatis Mapper、pojo 类和 Mapper.xml 文件。下面是使用 MyBatisX 插件实现逆向工程的步骤：

1. 安装插件：

   在 IntelliJ IDEA 中打开插件市场，搜索 MyBatisX 并安装。

2. 使用 IntelliJ IDEA连接数据库

   - 连接数据库

     ![](E:/学习学习/Java学习资料/SSM/三、MyBatis实践：提高持久层数据处理效率/image/image_yKs2Z2_8sQ.png)

   - 填写信息

     ![](E:/学习学习/Java学习资料/SSM/三、MyBatis实践：提高持久层数据处理效率/image/image_bDboqlZFKD.png)

   - 展示库表

     ![](E:/学习学习/Java学习资料/SSM/三、MyBatis实践：提高持久层数据处理效率/image/image_mCMuBhwZl2.png)

   - 逆向工程使用

     ![](E:/学习学习/Java学习资料/SSM/三、MyBatis实践：提高持久层数据处理效率/image/image_DkwlIx_BM9.png)

     ![](E:/学习学习/Java学习资料/SSM/三、MyBatis实践：提高持久层数据处理效率/image/image_dJvZP76xYm.png)

     ![](E:/学习学习/Java学习资料/SSM/三、MyBatis实践：提高持久层数据处理效率/image/image_KXYfK5CQd-.png)

3. 查看生成结果

   ![](E:/学习学习/Java学习资料/SSM/三、MyBatis实践：提高持久层数据处理效率/image/image_IIK2v1Lk64.png)
