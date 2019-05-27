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

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("userid");
        UserDao userDao = UserDaoImpl.getInstance();
        User user = userDao.find(Long.parseLong(id)).get();
        if(request.getSession().getAttribute("name").equals(user.getUserName()))
        {
            request.setAttribute("message","You can't delete your own account");
        }else
        if(userDao.delete(user)){
//            response.sendRedirect("users/list");
        }

        request.getRequestDispatcher("users/list").forward(request, response);
    }
}
