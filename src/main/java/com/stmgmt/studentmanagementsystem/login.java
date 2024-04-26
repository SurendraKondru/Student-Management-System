package com.stmgmt.studentmanagementsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class login extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


        String username = req.getParameter("username");
        String password = req.getParameter("password");

        PrintWriter out = res.getWriter();
        res.setContentType("text/html");

        out.println("<link rel='stylesheet' href='css/bootstrap.css'>");

        if (username.equals("Surendra") && password.equals("Surendra@123")) {
           res.sendRedirect("home.html");

        }else {
            out.println("<div class='container text-center'>");
            out.println("<h2>invalid User name or password</h2>");
            out.println("<a href='login.html'><button class='btn btn-outline-success'>Login</button></a>");
            out.println("</div>");
        }


    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }
}
