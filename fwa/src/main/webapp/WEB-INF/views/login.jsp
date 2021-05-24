<%@ page import="java.util.Date" %>
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
<%--<h1>Hello ${name}!</h1>--%>

<div>Current date is <%=date%></div>
</br>
</br>

<p><font color="red">${errorMessage}</font></p>

<form action="/login.do" method="POST">
    Name : <input type="text" name="name"/>
    Password : <input type="password" name="password"/>
    <input type="submit"/>
</form>

</body>
</html>
