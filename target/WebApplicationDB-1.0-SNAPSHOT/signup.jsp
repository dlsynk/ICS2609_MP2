<%-- 
    Document   : signup
    Created on : Feb 24, 2025, 9:55:45 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Signup</title>
</head>
<body>
    <h2>Signup Page</h2>
    <form action="RegisterServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <label for="confirmPassword">Confirm Password:</label>
        <input type="confirmPassword" id="confirmPassword" name="confirmPassword" required>
        <br>
        <button type="submit">Signup</button>
    </form>
</body>

</html>
