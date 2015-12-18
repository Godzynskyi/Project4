<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Login</title>
</head>
<body>
<form action="/main" method="POST">
  Enter username : <input type="text" name="username"> <BR>
  Enter password : <input type="password" name="password"> <BR>
  <input type="submit" />
</form>
<a href="/register">Register</a>
</body>
</html>