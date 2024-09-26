# 什么是Request 和 Response

在JavaWeb开发中，`request`（请求）和`response`（响应）是两个核心的概念，它们分别代表了客户端向服务器发送请求和服务器向客户端返回响应的过程。以下是它们的详细解释：

### HttpServletRequest（请求）

`HttpServletRequest`对象代表了客户端的HTTP请求，它包含了客户端发送给服务器的所有信息，包括请求的URL、HTTP方法（GET、POST等）、请求参数、请求头、以及客户端的其他相关信息。

主要特性和用途包括：

1. **获取请求信息**：可以通过该对象获取请求的URL、参数、HTTP方法等信息，从而处理不同的请求。
2. **获取请求参数**：通过该对象可以获取GET请求的参数（在URL中），以及POST请求的参数（在请求体中）。
3. **获取请求头**：可以获取HTTP请求的头部信息，如User-Agent、Referer等。
4. **处理会话**：支持会话管理，可以获取和设置会话相关的信息。
5. **处理Cookie**：支持获取和设置Cookie信息。

### HttpServletResponse（响应）

`HttpServletResponse`对象代表了服务器向客户端发送的HTTP响应，它包含了要发送给客户端的所有信息，包括响应状态码、响应头、响应体（即实际的响应内容）等。

主要特性和用途包括：

1. **设置响应状态码**：可以设置HTTP响应的状态码，如200（成功）、404（未找到）、500（服务器内部错误）等。
2. **设置响应头**：可以设置HTTP响应的头部信息，如Content-Type、Cache-Control等。
3. **设置响应内容**：可以向客户端输出具体的响应内容，例如HTML页面、JSON数据等。
4. **设置Cookie**：可以向客户端设置Cookie，以便在客户端和服务器之间传递状态信息。
5. **重定向**：支持将客户端重定向到另一个URL，实现页面跳转。

### HttpServletRequest 方法

1. **获取请求信息**
   - `String getMethod()`：获取请求的 HTTP 方法，如 GET、POST 等。
   - `String getRequestURI()`：获取请求的 URI（不包括主机部分）。
   - `StringBuffer getRequestURL()`：获取请求的 URL（包括主机部分）。
   - `String getQueryString()`：获取请求的查询字符串部分。
   - `String getProtocol()`：获取请求的协议名称和版本。
   - `String getRemoteAddr()`：获取客户端的 IP 地址。
2. **获取请求头信息**
   - `String getHeader(String name)`：获取指定名称的请求头的值。
   - `Enumeration<String> getHeaderNames()`：获取所有请求头的名称。
3. **获取请求参数**
   - `String getParameter(String name)`：获取指定名称的请求参数的值。
   - `Map<String, String[]> getParameterMap()`：获取所有请求参数的键值对。
4. **处理会话**
   - `HttpSession getSession()`：获取与请求关联的会话对象，如果不存在则创建一个新的会话。
   - `HttpSession getSession(boolean create)`：获取与请求关联的会话对象，根据参数决定是否创建新的会话。
5. **处理Cookie**
   - `Cookie[] getCookies()`：获取请求中包含的所有 Cookie 对象数组。
6. **其他方法**
   - `Object getAttribute(String name)`：获取指定名称的请求属性值。
   - `Enumeration<String> getAttributeNames()`：获取所有请求属性的名称。

### HttpServletResponse 方法

1. **设置响应状态和头信息**
   - `void setStatus(int sc)`：设置响应的状态码，如 200、404 等。
   - `void sendError(int sc, String msg)`：发送错误状态码和对应的消息。
   - `void setHeader(String name, String value)`：设置响应头的指定名称和值。
   - `void addHeader(String name, String value)`：添加响应头的指定名称和值，可以添加多个相同名称的头。
2. **设置响应内容**
   - `void setContentType(String type)`：设置响应的 MIME 类型，如 "text/html"、"application/json" 等。
   - `PrintWriter getWriter()`：获取用于向客户端发送字符文本的 PrintWriter 对象。
   - `ServletOutputStream getOutputStream()`：获取用于向客户端发送二进制数据的 ServletOutputStream 对象。
   - `void setContentLength(int len)`：设置响应内容的长度。
3. **重定向和发送内容**
   - `void sendRedirect(String location)`：重定向到指定的 URL。
   - `void sendError(int sc)`：发送错误状态码。
   - `void setStatus(int sc, String sm)`：设置响应的状态码和描述。
4. **设置Cookie**
   - `void addCookie(Cookie cookie)`：添加一个 Cookie 到响应中。
5. **其他方法**
   - `void setCharacterEncoding(String charset)`：设置响应字符编码。
   - `void setBufferSize(int size)`：设置响应缓冲区大小。