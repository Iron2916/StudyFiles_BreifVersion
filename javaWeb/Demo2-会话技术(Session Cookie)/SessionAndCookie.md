# 什么是会话追踪技术

1. **会话**:用户打开浏览器，访问web服务器的资源，会话建立，直到有一方断开连接，会话结束。在一次会话中可以包含**多次请求**和响应。

   - 从浏览器发出请求到服务端响应数据给前端之后，一次会话(在浏览器和服务器之间)就被建立了

   - 会话被建立后，如果浏览器或服务端都没有被关闭，则会话就会持续建立着

   - 浏览器和服务器就可以继续使

   - 用该会话进行请求发送和响应，上述的整个过程就被称之为**会话**。

2. **会话跟踪**:一种维护浏览器状态的方法，服务器需要识别多次请求是否来自于同一浏览器，以便在同一次会话的多次请求间**共享数据**。

   * 服务器会收到多个请求，这多个请求可能来自多个浏览器。
   * 服务器需要用来识别请求是否来自同一个浏览器
   * 服务器用来识别浏览器的过程，这个过程就是会话跟踪
   * 服务器识别浏览器后就可以在同一个会话中多次请求之间来共享数据

# 设置和获取Session的方法：

## 1. **设置Session**

在Servlet中设置Session可以通过`HttpServletRequest`对象获取`HttpSession`对象，并使用`setAttribute()`方法存储数据。

```
HttpSession session = request.getSession();
session.setAttribute("username", "john_doe");
```

上述代码将用户名"john_doe"存储在名为"username"的Session属性中。

## 2. **获取Session**

在Servlet中获取Session数据可以通过`HttpServletRequest`对象获取`HttpSession`对象，并使用`getAttribute()`方法获取存储的数据。

```
java复制代码HttpSession session = request.getSession();
String username = (String) session.getAttribute("username");
```

上述代码将获取之前存储的用户名数据。

## 3. **设置Session的超时时间**

第一种方法：可以使用`setMaxInactiveInterval()`方法设置Session的最大空闲时间（单位为秒），超过这个时间Session将失效。

```
java复制代码HttpSession session = request.getSession();
session.setMaxInactiveInterval(30 * 60); // 设置为30分钟
```

第二种方法：

``` xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >
<web-app>
  <!-- 设置 session 的过期时间 -->
    <session-config>
      <session-timeout>100</session-timeout>
    </session-config>
</web-app>

```

## 4. **Session销毁**

``` java
session.invalidate();
```

## 5. 设置中文

``` java
// 设置请求和响应的字符编码为UTF-8
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
```

# 设置和获取Cookie的方法：

## 1. **设置Cookie**：

在Servlet中设置Cookie需要使用`HttpServletResponse`对象创建`Cookie`对象，并通过`addCookie()`方法添加到响应中。

```
java复制代码Cookie cookie = new Cookie("username", "john_doe");
cookie.setMaxAge(30 * 24 * 60 * 60); // 设置为30天
response.addCookie(cookie);
```

上述代码创建了一个名为"username"的Cookie，设置了它的值为"john_doe"，并且设置了它的最大生存时间为30天。

## 2. **获取Cookie**：

在Servlet中获取Cookie需要使用`HttpServletRequest`对象的`getCookies()`方法获取所有Cookie，然后根据名字找到特定的Cookie。

```
java复制代码Cookie[] cookies = request.getCookies();
if (cookies != null) {
    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("username")) {
            String username = cookie.getValue();
            // 处理Cookie中的值
            break;
        }
    }
}
```

上述代码遍历所有Cookie，找到名为"username"的Cookie，并获取它的值。

1. **注意事项**：
   - 在设置Cookie时，要确保Cookie的名称和值符合要求，并且根据需要设置Cookie的过期时间。
   - 在获取Cookie时，要注意处理可能为空的情况（`getCookies()`方法返回null）。
   - 使用Cookie时要注意安全性，避免存储敏感信息。

## 3. **设置中文**

Cookie不能直接存储中文

Cookie不能存储中文，但是如果有这方面的需求，这个时候该如何解决呢?

这个时候，我们可以使用之前学过的一个知识点叫`URL编码`，所以如果需要存储中文，就需要进行转码，具体的实现思路为:

> 1.在AServlet中对中文进行URL编码，采用URLEncoder.encode()，将编码后的值存入Cookie中
>
> 2.在BServlet中获取Cookie中的值,获取的值为URL编码后的值
>
> 3.将获取的值在进行URL解码,采用URLDecoder.decode()，就可以获取到对应的中文值

(1)在AServlet中对中文进行URL编码

```java
@WebServlet("/aServlet")
public class AServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //发送Cookie
        String value = "张三";
        //对中文进行URL编码
        value = URLEncoder.encode(value, "UTF-8");
        System.out.println("存储数据："+value);
        //将编码后的值存入Cookie中
        Cookie cookie = new Cookie("username",value);
        //设置存活时间   ，1周 7天
        cookie.setMaxAge(60*60*24*7);
        //2. 发送Cookie，response
        response.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
```

(2)在BServlet中获取值，并对值进行解码

```java
@WebServlet("/bServlet")
public class BServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Cookie
        //1. 获取Cookie数组
        Cookie[] cookies = request.getCookies();
        //2. 遍历数组
        for (Cookie cookie : cookies) {
            //3. 获取数据
            String name = cookie.getName();
            if("username".equals(name)){
                String value = cookie.getValue();//获取的是URL编码后的值 %E5%BC%A0%E4%B8%89
                //URL解码
                value = URLDecoder.decode(value,"UTF-8");
                System.out.println(name+":"+value);//value解码后为 张三
                break;
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
```



# Cookie 和 Session 之间的差异

Cookie 和 Session 都是来完成一次会话内多次请求间**数据共享**的。

所需两个对象放在一块，就需要思考:

Cookie和Session的区别是什么?

Cookie和Session的应用场景分别是什么?

* 区别:
  * 存储位置：Cookie 是将数据存储在客户端，Session 将数据存储在服务端
  * 安全性：Cookie不安全，Session安全
  * 数据大小：Cookie最大3KB，Session无大小限制
  * 存储时间：Cookie可以通过setMaxAge()长期存储，Session默认30分钟
  * 服务器性能：Cookie不占服务器资源，Session占用服务器资源
* 应用场景:
  * 购物车:使用Cookie来存储
  * 以登录用户的名称展示:使用Session来存储
  * 记住我功能:使用Cookie来存储
  * 验证码:使用session来存储
* 结论
  * Cookie是用来保证用户在未登录情况下的身份识别
  * Session是用来保存用户登录后的数据

