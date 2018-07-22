package com.work.servlet;

import com.work.bean.User;
import com.work.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserServlet", urlPatterns = {"/login.do", "/loginPrompt.do"})
public class UserServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = null;
        if ("/login.do".equals(request.getServletPath())) {
            name = request.getParameter("username");
            String password = request.getParameter("password");
            User user = userService.getUserByName(name);
            if (user != null && user.getPassword().equals(password)) {
                request.getSession().setAttribute("user", user);
                request.getRequestDispatcher("/CanvasList.do").forward(request, response);
            } else {
                request.setAttribute("msg", "登陆失败");
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
        } else if ("/loginPrompt.do".equals(request.getServletPath())) {
            User user = (User) request.getSession().getAttribute("user");
            if(user!=null){
                request.getRequestDispatcher("/CategoryList.do").forward(request, response);
                return;
            }
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        System.out.println("servlet" + request.getContextPath() + " " + name);
    }
}
