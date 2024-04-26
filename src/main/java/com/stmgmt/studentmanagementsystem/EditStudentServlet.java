package com.stmgmt.studentmanagementsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/editstudent")
public class EditStudentServlet extends HttpServlet {
    private final static String QUERY = "SELECT name,email,mobile,gender,qualification,state FROM studentmgmt WHERE id=?";

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

        int id = Integer.parseInt(req.getParameter("id"));
        //get the connection
        try {
            Connection con= DriverManager.getConnection(MYSQL_URL,MYSQL_USER,MYSQL_PASSWORD);
            PreparedStatement ps = con.prepareStatement(QUERY);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String name = rs.getString(1);
            String email = rs.getString(2);
            String mobile = rs.getString(3);
            String gender = rs.getString(4);
            String qualification = rs.getString(5);
            String state = rs.getString(6);

            out.println("<div style='margin:auto;width:1000px;margin-top:100px'>");
            out.println("<form action='edit?id="+id+"' method='post'>");
            out.println("<table class='table table-striped table-hover'>>");
            out.println("<h1 class='text-center bg-danger text-white'>Edit Students information </h1>");
            out.println("<tr>");
            out.println("<td>Name</td>");
            out.println("<td><input type='text' name='name' value='"+name+"'</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Email</td>");
            out.println("<td><input type='email' name='email' value='"+email+"'</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Mobile</td>");
            out.println("<td><input type='text' name='mobile' value='"+mobile+"'</td>");
            out.println("</tr>");
           out.println("<tr>");
            out.println("<td>Gender</td>");
            out.println("<td><input type='radio' name='gender' value='male' required>Male &nbsp;");
            out.println("<input type='radio' name='gender' value='female' required>Female</td>");
        out.println("</tr>");
       out.println("<tr>");
           out.println("<td>Qualification</td>");
           out.println("<td><select id='qualification' name='qualification'>");
               out.println("<option value='M.Tech'>M.Tech</option>");
               out.println("<option value='B.Tech'>B.Tech</option>");
               out.println("<option value='MCA'>MCA</option>");
               out.println("<option value='M.B.A'>M.B.A</option>");
               out.println(" <option value='BA'>BA</option>");
               out.println(" <option value='BBA'>BBA</option>");
               out.println("<option value='B.Sc'>B.Sc</option>");
               out.println("<option value='B.com'>B.com</option>");
               out.println("<option value='other'>Other</option>");
           out.println("</select></td>");
       out.println("</tr>");
        out.println("<tr>");
            out.println("<td>State</td>");
            out.println("<td><select name='state' id='state'>");
                out.println("<option value='Andhra Pradesh'>Andhra Pradesh</option>");
                out.println("<option value='Andaman and Nicobar Islands'>Andaman and Nicobar Islands</option>");
                out.println("<option value='Arunachal Pradesh'>Arunachal Pradesh</option>");
                out.println("<option value='Assam'>Assam</option>");
                out.println("<option value='Bihar'>Bihar</option>");
                out.println("<option value='Chandigarh'>Chandigarh</option>");
                out.println("<option value='Chhattisgarh'>Chhattisgarh</option>");
                out.println("<option value='Dadar and Nagar Haveli'>Dadar and Nagar Haveli</option>");
                out.println("<option value='Daman and Diu'>Daman and Diu</option>");
                out.println("<option value='Delhi'>Delhi</option>");
                out.println("<option value='Lakshadweep'>Lakshadweep</option>");
                out.println("<option value='Puducherry'>Puducherry</option>");
                out.println("<option value='Goa'>Goa</option>");
                out.println("<option value='Gujarat'>Gujarat</option>");
                out.println("<option value='Haryana'>Haryana</option>");
                out.println("<option value='Himachal Pradesh'>Himachal Pradesh</option>");
                out.println("<option value='Jammu and Kashmir'>Jammu and Kashmir</option>");
                out.println("<option value='Jharkhand'>Jharkhand</option>");
                out.println("<option value='Karnataka'>Karnataka</option>");
                out.println("<option value='Kerala'>Kerala</option>");
                out.println("<option value='Madhya Pradesh'>Madhya Pradesh</option>");
                out.println("<option value='Maharashtra'>Maharashtra</option>");
                out.println("<option value='Manipur'>Manipur</option>");
                out.println("<option value='Meghalaya'>Meghalaya</option>");
                out.println("<option value='Mizoram'>Mizoram</option>");
                out.println("<option value='Nagaland'>Nagaland</option>");
                out.println("<option value='Odisha'>Odisha</option>");
                out.println("<option value='Punjab'>Punjab</option>");
                out.println("<option value='Rajasthan'>Rajasthan</option>");
                out.println("<option value='Sikkim'>Sikkim</option>");
                out.println("<option value='Tamil Nadu'>Tamil Nadu</option>");
                out.println("<option value='Telangana'>Telangana</option>");
                out.println("<option value='Tripura'>Tripura</option>");
                out.println("<option value='Uttar Pradesh'>Uttar Pradesh</option>");
                out.println("<option value='Uttarakhand'>Uttarakhand</option>");
                out.println("<option value='West Bengal'>West Bengal</option>");
            out.println("</select></td>");
        out.println("</tr>");
            out.println("<tr>");
            out.println("<td><button type='submit' class='btn btn-outline-success'>Edit</button></td>");
            out.println("<td><button type='reset' class='btn btn-outline-danger'>Cancel</button></td>");
            out.println("</tr>");
            out.println("</table");
            out.println("</form");

        } catch (SQLException e) {
            out.println("<h2 class='bg-danger text-center'>SQLException: " + e.getMessage()+ "</h2>");
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.println("</div>");
        out.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
