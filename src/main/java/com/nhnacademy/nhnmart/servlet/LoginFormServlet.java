package com.nhnacademy.nhnmart.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "loginFormServlet", urlPatterns = "/loginForm")
public class LoginFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (Objects.isNull(session)) {
//            RequestDispatcher rd = req.getRequestDispatcher("/loginForm.jsp");
//            rd.forward(req, resp);
            req.setAttribute("view", "/loginForm.jsp");
        } else {
            req.setAttribute("view", "/index.jsp");
//            RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
//            rd.forward(req,resp);
        }
    }
}
