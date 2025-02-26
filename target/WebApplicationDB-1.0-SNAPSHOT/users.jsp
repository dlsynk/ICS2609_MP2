<%-- 
    Document   : users
    Created on : Feb 24, 2025, 9:54:43 PM
    Author     : ACER
--%>

<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="authenticator.jsp" %>

<%    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(jdbcUrl, userName, password);

        String query = "SELECT follow1, follow2, follow3 FROM follows WHERE user_name = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();
        String follow1 = null, follow2 = null, follow3 = null;
        int followCount = 0;

        while (rs.next()) {
            follow1 = rs.getString("follow1");
            follow2 = rs.getString("follow2");
            follow3 = rs.getString("follow3");

            if (follow1 != null) {
                followCount++;
            }
            if (follow2 != null) {
                followCount++;
            }
            if (follow3 != null) {
                followCount++;
            }
        }


%>

<!DOCTYPE html>
<html>
    <head>
        <title>Followed Users</title>
        <script>
            function validateFollow() {
                var followCount = <%= followCount%>;

                if (followCount >= 3) {
                    alert("You already follow 3 users. You cannot follow more.");
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>

        <h2>Your Followed Users</h2>
        <ul>
            <% if (follow1 != null) {%>
            <li>
                <%= follow1%>
                <form action="ConnectionServlet" method="POST" style="display:inline;">
                    <input type="hidden" name="connection" value="unfollow_user">
                    <input type="hidden" name="unfollowUser" value="<%= follow1%>">
                    <button type="submit">Unfollow</button>
                </form>
            </li>
            <% } %>

            <% if (follow2 != null) {%>
            <li>
                <%= follow2%>
                <form action="ConnectionServlet" method="POST" style="display:inline;">
                    <input type="hidden" name="connection" value="unfollow_user">
                    <input type="hidden" name="unfollowUser" value="<%= follow2%>">
                    <button type="submit">Unfollow</button>
                </form>
            </li>
            <% } %>

            <% if (follow3 != null) {%>
            <li>
                <%= follow3%>
                <form action="ConnectionServlet" method="POST" style="display:inline;">
                    <input type="hidden" name="connection" value="unfollow_user">
                    <input type="hidden" name="unfollowUser" value="<%= follow3%>">
                    <button type="submit">Unfollow</button>
                </form>
            </li>
            <% } %>

            <% if (follow1 == null && follow2 == null && follow3 == null) { %>
            <li>None</li>
                <% } %>
        </ul>


        <h2>Follow a New User</h2>
        <form action="ConnectionServlet" method="POST" onsubmit="return validateFollow();">
            <label for="newFollow">Enter Username to Follow:</label>
            <input type="text" id="newFollow" name="newFollow" required maxlength="30">
            <input type="hidden" name="connection" value="follow_user">
            <button type="submit">Follow</button>
        </form>
        
        <br>
        <form action="landing.jsp">
            <button type="submit">Return</button>
        </form>

    </body>


</html>

<%
        rs.close();
        pstmt.close();
        conn.close();

    } catch (Exception e) {
        e.printStackTrace();
    }


%>