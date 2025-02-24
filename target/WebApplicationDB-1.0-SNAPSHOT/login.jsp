<%-- 
    Document   : login
    Created on : Feb 24, 2025, 9:55:38 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login Page</h2>
    <form action="LoginServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <button type="submit">Login</button>
    </form>
    <a href="signup.jsp">Signup</a>
</body>

</html>



<!-- C:\ProgramData\MySQL\MySQL Server 8.0\Data\webapplicationdb--!>