/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ACER
 */
public class ConnectionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession userSession = request.getSession(false);
        String username = (userSession != null) ? (String) userSession.getAttribute("username") : null;

        if (username == null) {
            response.sendRedirect("login.jsp?error=sessionError");
            return;
        }

        String choose = request.getParameter("connection");

        switch (choose) {
            case "update": {
                updateUser();
                break;
            }
            case "delete": {
                deleteUser();
                break;
            }
            case "create_user": {
                createUser();
                break;
            }
            case "create_post": {
                createPost();
                response.sendRedirect("landing.jsp?message=Success!");
                break;
            }
            case "create_message": {
                String message = request.getParameter("contactTextField");
                createMessage(message);
                response.sendRedirect("landing.jsp?message=Success!");
                break;
            }
            case "follow_user": {
                String follow = request.getParameter("newFollow");
                followUser(follow, username);
                response.sendRedirect("users.jsp");
                break;
            }
            case "unfollow_user": {
                String unfollow = request.getParameter("unfollowUser");
                unfollowUser(unfollow, username);
                response.sendRedirect("users.jsp");
                break;
            }
            default: {
                System.out.println("Invalid action: " + choose);
            break;
        }

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    String port = "3310";
    String databaseName = "webapplicationdb";
    String userName = "root";
    String password = "EvansArthur2004";
    String jdbcUrl = "jdbc:mysql://localhost:" + port + "/" + databaseName + "?useSSL=false";

    void createMessage(String newMessage) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, userName, password);

            String query = "SELECT message1, message2, message3, message4, message5 FROM messages WHERE admin_message = 'fixedValue'";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String msg2 = rs.getString("message2");
                String msg3 = rs.getString("message3");
                String msg4 = rs.getString("message4");
                String msg5 = rs.getString("message5");

                String updateQuery = "UPDATE messages SET message1 = ?, message2 = ?, message3 = ?, message4 = ?, message5 = ? WHERE admin_message = 'fixedValue'";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setString(1, msg2);
                updateStmt.setString(2, msg3);
                updateStmt.setString(3, msg4);
                updateStmt.setString(4, msg5);
                updateStmt.setString(5, newMessage);
                updateStmt.executeUpdate();

                rs.close();
                pstmt.close();
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void createPost() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void createUser() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void deleteUser() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void updateUser() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void followUser(String newFollow, String username) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, userName, password);

            // ✅ Use PreparedStatement to prevent SQL injection
            String query = "SELECT user_name FROM webapplicationdb.account WHERE user_name = ?";
            PreparedStatement checkUserStmt = conn.prepareStatement(query);
            checkUserStmt.setString(1, newFollow);
            ResultSet rs = checkUserStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("User does not exist.");
                rs.close();
                checkUserStmt.close();
                conn.close();
                return;
            }

            if (newFollow.equals(username)) {
                System.out.println("Cannot follow yourself.");
                rs.close();
                checkUserStmt.close();
                conn.close();
                return;
            }

            String selectQuery = "SELECT follow1, follow2, follow3 FROM follows WHERE user_name = ?";
            PreparedStatement followStmt = conn.prepareStatement(selectQuery);
            followStmt.setString(1, username);
            ResultSet followRs = followStmt.executeQuery();

            String follow1 = null, follow2 = null, follow3 = null;
            if (followRs.next()) {
                follow1 = followRs.getString("follow1");
                follow2 = followRs.getString("follow2");
                follow3 = followRs.getString("follow3");
            }

            if (newFollow.equals(follow1) || newFollow.equals(follow2) || newFollow.equals(follow3)) {
                System.out.println("You are already following this user.");
                followRs.close();
                followStmt.close();
                conn.close();
                return;
            }

            String updateFollowSQL = "UPDATE follows SET ";
            if (follow1 == null) {
                updateFollowSQL += "follow1 = ? ";
            } else if (follow2 == null) {
                updateFollowSQL += "follow2 = ? ";
            } else {
                updateFollowSQL += "follow3 = ? ";
            }
            updateFollowSQL += "WHERE user_name = ?";


            PreparedStatement updateStmt = conn.prepareStatement(updateFollowSQL);
            updateStmt.setString(1, newFollow);
            updateStmt.setString(2, username);
            updateStmt.executeUpdate();


            rs.close();
            followRs.close();
            checkUserStmt.close();
            followStmt.close();
            updateStmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void unfollowUser(String unfollow, String username) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, userName, password);

            String selectQuery = "SELECT follow1, follow2, follow3 FROM follows WHERE user_name = '" + username + "'";
            Statement stmt = conn.createStatement();
            ResultSet followRs = stmt.executeQuery(selectQuery);

            String follow1 = null, follow2 = null, follow3 = null;
            if (followRs.next()) {
                follow1 = followRs.getString("follow1");
                follow2 = followRs.getString("follow2");
                follow3 = followRs.getString("follow3");
            }

            if (unfollow.equals(follow1)) {
                follow1 = follow2;
                follow2 = follow3;
                follow3 = null;
            } else if (unfollow.equals(follow2)) {
                follow2 = follow3;
                follow3 = null;
            } else if (unfollow.equals(follow3)) {
                follow3 = null;
            }

            String updateFollowSQL = "UPDATE follows SET follow1 = ?, follow2 = ?, follow3 = ? WHERE user_name = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateFollowSQL);
            updateStmt.setString(1, follow1);
            updateStmt.setString(2, follow2);
            updateStmt.setString(3, follow3);
            updateStmt.setString(4, username);
            updateStmt.executeUpdate();

            followRs.close();
            stmt.close();
            updateStmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
