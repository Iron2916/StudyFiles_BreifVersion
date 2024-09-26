# 一、什么是javaweb过滤器

JavaWeb过滤器（Filter）是Servlet规范中的一种特殊组件，用于在Servlet处理请求之前预处理请求，或在Servlet将响应发送到客户端之前后处理响应。过滤器可以截取请求和响应，允许开发者对它们进行修改、增强或者拦截，从而实现一些与业务逻辑无关但是对请求和响应有重要影响的功能。

具体来说，过滤器的主要作用包括：

1. **预处理请求**：过滤器可以在请求到达Servlet之前对请求进行预处理，例如验证用户身份、检查请求参数、修改请求头等操作。
2. **后处理响应**：过滤器可以在Servlet处理完请求并生成响应后对响应进行后处理，例如添加响应头、压缩响应数据、对响应内容进行过滤或修改等。
3. **拦截器**：过滤器可以拦截请求，根据特定条件决定是否继续传递给下一个过滤器或目标Servlet，或者直接结束请求并返回响应。
4. **复用性**：过滤器可以在多个Servlet之间共享，提高代码的复用性。例如，可以编写一个通用的日志记录过滤器，应用于整个Web应用程序。
5. **配置灵活**：通过在`web.xml`文件中或者使用注解（`@WebFilter`）配置过滤器，可以灵活地指定过滤器应用的URL模式或者Servlet名称，以及过滤器的顺序。



# 二、Filter的两种声明方式

## 1. 注解声明：**@WebFilter("路径")**

``` java
package com.itheima.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


@WebFilter("/filter1")
public class FilterDemo implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //1. 放行前，对 request数据进行处理
        System.out.println("FilterDemo 过滤器启动");

        //放行
        chain.doFilter(request,response);

        //2. 放行后，对Response 数据进行处理
        System.out.println("FilterDemo 过滤器进行放行");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }



    @Override
    public void destroy() {

    }
}
```



## 2. web.xml文件声明

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">

    <!-- 通过xml注解进行配置过滤器 -->
    <filter>
        <filter-name>AuthenticationFilter</filter-name>
        <filter-class>com.itheima.web.filter.FilterDemoByXML</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>AuthenticationFilter</filter-name>
        <url-pattern>/*</url-pattern> <!-- 所有请求都经过该Filter -->
    </filter-mapping>

</web-app>
```

# 三、Filter生命周期

Filter的生命周期包括三个主要阶段：初始化、请求处理和销毁。

1. **初始化阶段**：
   - 当容器（如Tomcat）启动时，会初始化Web应用程序中配置的所有Filter。初始化阶段调用Filter的`init()`方法。
   - `init(FilterConfig filterConfig)`方法用于初始化Filter实例，可以在这里进行一些初始化操作，如读取配置文件、建立数据库连接等。
   - `FilterConfig`对象提供了访问Servlet容器传递给Filter的初始化参数的方法。
2. **请求处理阶段**：
   - 对于每个请求，Servlet容器在调用目标Servlet之前会调用所有匹配请求URL模式的Filter的`doFilter()`方法。
   - `doFilter(ServletRequest request, ServletResponse response, FilterChain chain)`方法是Filter的主要工作方法，负责处理请求和响应，以及将请求传递给下一个Filter或目标Servlet。
   - 在`doFilter()`方法中，可以对请求和响应进行修改、记录日志、验证身份、处理异常等操作。调用`chain.doFilter(request, response)`将控制权传递给下一个Filter或目标Servlet，或者结束请求处理流程。
3. **销毁阶段**：
   - 当Servlet容器关闭时，会销毁Web应用程序中所有的Filter实例。销毁阶段调用Filter的`destroy()`方法。
   - `destroy()`方法用于执行一些清理工作，例如关闭数据库连接、释放资源等。

关于生命周期的一些注意事项：

- Filter的`init()`方法只会在第一次创建Filter实例时调用，之后的请求不会再次调用`init()`方法。
- `doFilter()`方法在每次匹配请求URL模式时都会被调用。
- `destroy()`方法只会在应用程序关闭时调用一次。



# 四、Filter的注意事项

Filter 是 Java Web 应用程序中的一个强大工具，用于对请求和响应进行预处理或后处理。它们通常用于实现日志记录、安全检查和数据压缩等功能。以下是使用 Filter 时需要注意的一些事项：



1. **Filter 链配置顺序**：
   - Filter 是按配置顺序执行的，因此要确保正确的执行顺序。可以在 `web.xml` 文件中通过 `<filter-mapping>` 标签配置顺序，或者在使用框架（如Spring）时通过注解指定顺序。
2. **Filter 的生命周期**：
   - Filter 的生命周期方法包括 `init()`、`doFilter()` 和 `destroy()`。确保在 `init()` 方法中进行必要的初始化操作，在 `destroy()` 方法中进行清理工作。
3. **Filter 的线程安全**：
   - 由于 Filter 可能会被多个线程同时访问，确保在 Filter 中使用的任何共享资源（如类变量）是线程安全的。
4. **Exception 处理**：
   - 在 `doFilter()` 方法中处理可能抛出的异常，避免未捕获的异常导致请求中断。可以通过 `try-catch` 块捕获异常并进行处理。
5. **Filter 的性能**：
   - 尽量避免在 Filter 中进行耗时操作，以免降低系统性能。可以考虑将耗时操作放在异步线程中处理。
6. **Filter 的链传递**：
   - 在 `doFilter()` 方法中，确保调用 `chain.doFilter(request, response)` 方法，以将请求传递给链中的下一个 Filter 或目标资源（如Servlet）。否则，请求将不会继续处理。

```java
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
           throws IOException, ServletException {
       // Pre-processing
       // ...

       // Ensure the request continues down the filter chain
       chain.doFilter(request, response);

       // Post-processing
       // ...
   }
```



1. **Filter 的作用范围**：
   - 确保正确配置 Filter 的作用范围（url-pattern），以便它仅对需要处理的请求生效，避免不必要的性能开销。
2. **Filter 的可重用性**：
   - 尽量编写通用和可重用的 Filter，以便在多个Web应用中使用。避免将业务逻辑直接嵌入到 Filter 中。
3. **使用注解配置 Filter**：
   - 在现代的 Java EE 和 Spring 应用中，可以使用注解来配置 Filter，简化配置过程。例如：

```java
   @WebFilter(urlPatterns = "/*")
   public class MyFilter implements Filter {
       // Filter implementation
   }
```



1. 清理资源：
   - 确保在 `destroy()` 方法中释放任何在 `init()` 方法中分配的资源，以避免资源泄漏。