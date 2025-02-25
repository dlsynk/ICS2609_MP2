<%-- 
    Document   : help
    Created on : Feb 24, 2025, 9:54:52 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <textarea id="contactTextField" name="contactTextField" rows="20" cols="10"></textarea>
        <br>
        <button type="submit">Submit</button>
        <input type="hidden" id="contactAdmin" name="contactAdmin" value="contact">
    </form>
</body>

</html>
