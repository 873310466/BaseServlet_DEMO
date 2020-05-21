package com.lemonfish.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserServlet/*")
public class UserServlet extends BaseServlet {

    /**
     * 登录
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("我来转发啦");
        request.getRequestDispatcher("/hello.jsp").forward(request, response);
    }

    /**
     * 注册
     */
    public void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("Sign Up");
    }
}
