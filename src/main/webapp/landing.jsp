<%@ page import="java.sql.*" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ include file="authenticator.jsp" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>

    <h2>Welcome, <%= username%>!</h2>

    <div class="nav">
        <a href="users.jsp">Manage Following</a>
        <a href="help.jsp">Help & Support</a>
        <a href="LogoutServlet" style="color: red;">Logout</a>
    </div>

    <h3>Latest Posts from Followed Users</h3>

    <%
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, userName, password);

            // Get the followed users
            String sqlFollow = "SELECT follow1, follow2, follow3 FROM webapplicationdb.follows WHERE user_name = ?";
            PreparedStatement pstmtFollow = conn.prepareStatement(sqlFollow);
            pstmtFollow.setString(1, username);
            ResultSet rsFollow = pstmtFollow.executeQuery();

            String[] followedUsers = new String[3];
            if (rsFollow.next()) {
                followedUsers[0] = rsFollow.getString("follow1");
                followedUsers[1] = rsFollow.getString("follow2");
                followedUsers[2] = rsFollow.getString("follow3");
            }

            rsFollow.close();
            pstmtFollow.close();


            boolean hasFollowedUsers = false;

            for (String followedUser : followedUsers) {
                if (followedUser != null) {
                    hasFollowedUsers = true;

 
                    String sqlPost = "SELECT post5, post4, post3, post2, post1 FROM webapplicationdb.posts WHERE user_name = ?";
                    PreparedStatement pstmtPost = conn.prepareStatement(sqlPost);
                    pstmtPost.setString(1, followedUser);
                    ResultSet rsPost = pstmtPost.executeQuery();

                    String latestPost = "no posts available.";
                    if (rsPost.next()) {

                        for (int i = 1; i <= 5; i++) {
                            latestPost = rsPost.getString("post" + (6 - i));
                            if (latestPost != null && !latestPost.trim().isEmpty()) {
                                break;
                            }
                        }
                    }

                    rsPost.close();
                    pstmtPost.close();
    %>
                    <div>
                        <h4><a href="profile.jsp?user=<%= followedUser %>"><%= followedUser %></a></h4>
                        <p><%= latestPost %></p>
                    </div>
    <%
                }
            }

            conn.close();

            if (!hasFollowedUsers) {
    %>
                <p>You are not following anyone yet.</p>
    <%
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>

</body>
</html>
