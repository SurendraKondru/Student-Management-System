package com.stmgmt.studentmanagementsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/edit")
public class UpdateStudentServlet extends HttpServlet {
    private final static String QUERY = "UPDATE studentmgmt SET name=?,email=?,mobile=?,gender=?,qualification=?,state=? WHERE id = ?";

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        out.println("<link rel='stylesheet' href='css/bootstrap.css'>");

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String gender = req.getParameter("gender");
        String qualification = req.getParameter("qualification");
        String state = req.getParameter("state");

        final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String MYSQL_URL = "jdbc:mysql://localhost:3306/myworkdb";
        final String MYSQL_USER = "root";
        final String MYSQL_PASSWORD = "root";
        // register the driver
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //get the connection
        try {
            Connection con= DriverManager.getConnection(MYSQL_URL,MYSQL_USER,MYSQL_PASSWORD);
            // create statement object
            PreparedStatement ps = con.prepareStatement(QUERY);
            ps.setString(1,name);
            ps.setString(2,email);
            ps.setString(3,mobile);
            ps.setString(4,gender);
            ps.setString(5,qualification);
            ps.setString(6,state);
            ps.setInt(7,id);
            out.println("<div class='card' style='margin:auto;width:500px;margin-top:100px'>");
            int count = ps.executeUpdate();
            if (count!=0) {
                out.println("<h1 class='bg-danger text-center'>Record Edited Successfully</h1>");
            }else  {
                out.println("<h1 class='bg-danger text-center'>Record not Edited</h1>");
            }
        } catch (SQLException e) {
            out.println("<h2 class='bg-danger text-center'>SQLException: " + e.getMessage()+ "</h2>");
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
        out.println("&nbsp;&nbsp;&nbsp;&nbsp;");
        out.println("<a href='studentdata'><button class='btn btn-outline-success'>Student Data</button></a>");
        out.println("</div>");

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
