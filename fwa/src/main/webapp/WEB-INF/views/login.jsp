<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: milko
  Date: 23.05.21
  Time: 8:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>From JSP</title>
</head>
<body>
<%
    System.out.println("Name = " + request.getParameter("name"));
    Date date = new Date();
%>
<h1>Hello ${name}!</h1>
<div>Current date is <%=date%></div>
</body>
</html>
