package com.mitrais.rms.controller;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = UserDaoImpl.getInstance();
        String un=request.getParameter("username");
        String pw=request.getParameter("password");
        String id = request.getParameter("id");
        User user = new User(Long.parseLong(id),un,pw);
        if(userDao.update(user)){
            response.sendRedirect("users/list");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("userid");
        UserDao userDao = UserDaoImpl.getInstance();
        User user = userDao.find(Long.parseLong(id)).get();
        List<User> users = new ArrayList<>();
        users.add(user);
        request.setAttribute("users", users);
        request.getRequestDispatcher("users/updateform").forward(request, response);
    }
}
