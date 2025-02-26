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
        <h4 style="color: green;">
            <%= request.getParameter("message") != null ? request.getParameter("message") : ""%>
        </h4>
        <% String error = request.getParameter("error"); %>
        <% if ("invalid_credentials".equals(error)) { %>
        <p style="color: red;">Invalid username or password. Please try again.</p>
        <% }%>
        <% if ("sessionError".equals(error)) { %>
        <p style="color: red;">Invalid session. Please Login again.</p>
        <% }%>

        <form action="LoginServlet" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required maxlength="30">
            <br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required maxlength="30">
            <br>
            <button type="submit">Login</button>
        </form>
        <a href="signup.jsp">Signup</a>
    </body>

</html>



