package servlets;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ACER
 */
public class LoginServlet extends HttpServlet {

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
        String port = "3310";
        String databaseName = "webapplicationdb";
        String userName = "root";
        String password = "EvansArthur2004";
        String jdbcUrl = "jdbc:mysql://localhost:" + port + "/" + databaseName + "?useSSL=false";
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, userName, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * FROM webapplicationdb.account WHERE user_name='" + name + "'");
            
            boolean userFound = false;
            
            while(rs.next()) {
                String confirmName = rs.getString("user_name");
                String confirmPass = rs.getString("password");
                String role = rs.getString("user_role");
                
                if (confirmName.equals(name) && confirmPass.equals(pass)) {
                    userFound = true;
                    if (role.equals("user")) {
                        response.sendRedirect("landing.jsp");
                    }
                    else if (role.equals("admin")) {
                        response.sendRedirect("admin/landing_admin.jsp");
                    }
                    else if (role.equals("super_admin")) {
                        
                    }
                    HttpSession session = request.getSession();
                    session.setAttribute("username", name);
                    break;
                }
                if (!userFound) {
                    response.sendRedirect("login.jsp?error=invalid_credentials");
                }
            }
            
            
            rs.close();
            stmt.close();
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
