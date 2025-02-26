<%-- 
    Document   : help
    Created on : Feb 24, 2025, 9:54:52 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="authenticator.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contact Admin</title>
</head>
<body>
    <h2>Contact Admin</h2>
    <form action="ConnectionServlet" method="post">
        <label for="Contact">Insert information down below</label>
        <br>
        <textarea id="contactTextField" name="contactTextField" rows="5" cols="50" maxlength="200"></textarea>
        <br>
        <button type="submit">Submit</button>
        <input type="hidden" id="contactAdmin" name="connection" value="create_message">
    </form>
    <form action="landing.jsp" method="post">
        <button type="submit">Return</button>
    </form>
</body>

</html>
