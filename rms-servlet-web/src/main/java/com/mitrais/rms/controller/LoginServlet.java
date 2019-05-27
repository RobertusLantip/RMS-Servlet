package com.mitrais.rms.controller;

import com.mitrais.rms.dao.DataSourceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends AbstractController
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String path = getTemplatePath(req.getServletPath());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
//        super.doPost(req, resp);
        String un=req.getParameter("username");
        String pw=req.getParameter("password");
        try {
            Connection conn = DataSourceFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("select user_name,password from user where user_name=? and password=?");
            ps.setString(1, un);
            ps.setString(2, pw);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                req.getSession().setAttribute("name", un);
                resp.sendRedirect("index.jsp");
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
