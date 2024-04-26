package com.stmgmt.studentmanagementsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/studentdata")
public class ShowStudentServlet extends HttpServlet {
    private final static String QUERY = "SELECT id,name,email,mobile,gender,qualification,state FROM studentmgmt";

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        out.println("<link rel='stylesheet' href='css/bootstrap.css'>");

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
            PreparedStatement ps = con.prepareStatement(QUERY);
            ResultSet rs = ps.executeQuery();

            out.println("<div style='margin:auto;width:1000px;margin-top:100px'>");
            out.println("<h1 class='text-center bg-danger text-white'>Students information </h1>");
            out.println("<table class='table table-striped table-bordered'>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Email</th>");
            out.println("<th>Mobile</th>");
            out.println("<th>Gender</th>");
            out.println("<th>Qualification</th>");
            out.println("<th>State</th>");
            out.println("<th>Edit</th>");
            out.println("<th>Delete</th>");
            out.println("</tr>");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String mobile = rs.getString("mobile");
                String gender = rs.getString("gender");
                String qualification = rs.getString("qualification");
                String state = rs.getString("state");
                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + email + "</td>");
                out.println("<td>" + mobile + "</td>");
                out.println("<td>" + gender + "</td>");
                out.println("<td>" + qualification + "</td>");
                out.println("<td>" + state + "</td>");
                out.println("<td><a href='editstudent?id="+id+"'><button class='btn btn-outline-success'>Edit</button></a></td>");
                out.println("<td><a href='deletestudent?id="+id+"'><button class='btn btn-outline-success'>Delete</button></a></td>");
                out.println("</tr>");
            }
            out.println("</table");

        } catch (SQLException e) {
            out.println("<h2 class='bg-danger text-center'>SQLException: " + e.getMessage()+ "</h2>");
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.println("<div class='container text-center'>");
        out.println("<td><a href='home.html'><button class='btn btn-outline-success' d-block>Home</button></a></td>");
        out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        out.println("<td><a href='index.html'><button class='btn btn-outline-danger' d-block>logout</button></a></td>");
        out.println("</div>");
        out.println("</div>");

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
