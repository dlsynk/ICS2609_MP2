<%-- 
    Document   : authenticator
    Created on : Feb 26, 2025, 4:29:19 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    HttpSession userSession = request.getSession(false);
    String username = (userSession != null) ? (String) userSession.getAttribute("username") : null;

    if (username == null) {
        response.sendRedirect("login.jsp?error=sessionError");
        return;
    }
    
    String port = "3310";
    String databaseName = "webapplicationdb";
    String userName = "root";
    String password = "EvansArthur2004";
    String jdbcUrl = "jdbc:mysql://localhost:" + port + "/" + databaseName + "?useSSL=false";
%>