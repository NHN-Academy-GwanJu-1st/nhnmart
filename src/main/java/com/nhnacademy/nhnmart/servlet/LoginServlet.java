package com.nhnacademy.nhnmart.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "loginServlet", urlPatterns = "/login", initParams = {
        @WebInitParam(name = "id", value = "admin"),
        @WebInitParam(name = "password", value = "1234")
})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (Objects.isNull(session)) {
            RequestDispatcher rd = req.getRequestDispatcher("/loginForm.jsp");
            rd.forward(req, resp);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
            rd.forward(req,resp);
//            resp.sendRedirect("/index.jsp");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String initId = getServletConfig().getInitParameter("id");
        String initPassword = getServletConfig().getInitParameter("password");

        String id = req.getParameter("id");
        String password = req.getParameter("password");

        if (initId.equals(id) && initPassword.equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("id", id);
            resp.sendRedirect("/login");
        } else {
            resp.sendRedirect("/login");
        }
    }


}
