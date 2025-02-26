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
public class RegisterServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        String port = "3310";
        String databaseName = "webapplicationdb";
        String userName = "root";
        String password = "EvansArthur2004";
        String jdbcUrl = "jdbc:mysql://localhost:" + port + "/" + databaseName + "?useSSL=false";
        
        String user= request.getParameter("username");
        String pass = request.getParameter("password");
        
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, userName, password);
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM webapplicationdb.account WHERE user_name='" + user + "'");
            if (rs.next()) {
                response.sendRedirect("errors/login_and_register.jsp?message=User%20already%20exists!");
            }
            
            String insertQuery = "INSERT INTO webapplicationdb.account (user_name, password, user_role) VALUES (?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setString(1, user);
            insertStmt.setString(2, pass);
            insertStmt.setString(3, "user");
            int rowsInserted = insertStmt.executeUpdate();

            if (rowsInserted > 0) {
                stmt.executeUpdate("INSERT INTO webapplicationdb.posts (user_name) VALUES ('" + user + "')");
                stmt.executeUpdate("INSERT INTO webapplicationdb.follows (user_name) VALUES ('" + user + "')");
                response.sendRedirect("login.jsp?message=Registration%20Successful.");
            } else {
                response.sendRedirect("errors/login_and_register.jsp?message=Registration%20Failed.%20Try%20Again.");
            }
            
            rs.close();
            stmt.close();
            insertStmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
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

}
