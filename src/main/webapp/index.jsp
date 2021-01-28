<%@ page import="jvm.chapter9.JavaclassExecuter" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.InputStream" %>


<html>
<body>
<h2>Hello World!</h2>

<%
    InputStream is = new FileInputStream("E:/TestClass.class");
    byte[] b = new byte[is.available()];
    is.read(b);
    is.close();
    out.println("<textarea style='width:1000;height=800'>");
    out.println(JavaclassExecuter.execute(b));
    out.println("</textarea>");
%>

</body>
</html>
