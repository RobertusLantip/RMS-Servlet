package com.mitrais.rms.controller;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users/*")
public class UserServlet extends AbstractController
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String path = getTemplatePath(req.getServletPath()+req.getPathInfo());

        if ("/list".equalsIgnoreCase(req.getPathInfo())){
            UserDao userDao = UserDaoImpl.getInstance();
            List<User> users = userDao.findAll();
            req.setAttribute("users", users);
        }
        else if("/update".equalsIgnoreCase(req.getPathInfo())){
            String id = req.getParameter("userid");
            UserDao userDao = UserDaoImpl.getInstance();
            User user = userDao.find(Long.parseLong(id)).get();
            List<User> users = new ArrayList<>();
            users.add(user);
            req.setAttribute("users", users);
            path = "/WEB-INF/jsp/users/updateform.jsp";
        }else if("/delete".equalsIgnoreCase(req.getPathInfo())){
            String id = req.getParameter("userid");
            UserDao userDao = UserDaoImpl.getInstance();
            User user = userDao.find(Long.parseLong(id)).get();
            if(req.getSession().getAttribute("name").equals(user.getUserName()))
            {
                req.setAttribute("message","You can't delete your own account !");
            }else
            if(userDao.delete(user)){
            }

            UserDao userDaolist = UserDaoImpl.getInstance();
            List<User> users = userDaolist.findAll();
            req.setAttribute("users", users);
            path = "/WEB-INF/jsp/users/list.jsp";
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = "";  //getTemplatePath(request.getServletPath()+request.getPathInfo());

        if("/form".equalsIgnoreCase(request.getPathInfo())){
            UserDao userDao = UserDaoImpl.getInstance();
            String un=request.getParameter("username");
            String pw=request.getParameter("password");
            User u = new User(un,pw);
            if(userDao.save(u)){
                path = "/rms-servlet-web/users/list";
            }
        }else if("/update".equalsIgnoreCase(request.getPathInfo())){
            UserDao userDao = UserDaoImpl.getInstance();
            String un=request.getParameter("username");
            String pw=request.getParameter("password");
            String id = request.getParameter("id");
            User user = new User(Long.parseLong(id),un,pw);
            if(userDao.update(user)){
                path = "/rms-servlet-web/users/list";
            }
        }

        response.sendRedirect(path);
    }
}
