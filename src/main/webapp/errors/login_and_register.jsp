<%-- 
    Document   : error
    Created on : Feb 26, 2025, 2:22:23 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>

        <h2 style="color: red;">
            <%= request.getParameter("message") != null ? request.getParameter("message") : "An unknown error occurred."%>
        </h2>
        <a href="login.jsp">Go back to Login</a>

    </body>
</html>
