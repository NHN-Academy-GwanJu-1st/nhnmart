package com.nhnacademy.nhnmart.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@Slf4j
@WebServlet(name = "frontController", urlPatterns = "*.do")
public class FrontController extends HttpServlet {

    private static final String REDIRECT_PREFIX = "redirect:";

    private static String locale = "ko";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");


        /* session locale check */
        if (req.getParameter("locale") != null) {
            locale = req.getParameter("locale");
        }
        HttpSession session = req.getSession();
        session.setAttribute("locale", locale);


        try {
            // 실제 처리 요청 servlet
            Command command = resolveServlet(req.getServletPath());

            String view = command.excute(req, resp);

            if (view.startsWith(REDIRECT_PREFIX)) {
                // redirect 하는 경우
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()));
            } else {
                // redircet가 아니면 해당 jsp에 처리를 위임하고 include 설정으로 인해 그 결과를 돌려 받음
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd.include(req, resp);
            }
        } catch (Exception e) {
            log.error("", e);
            req.setAttribute("exception", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }
    }

    private Command resolveServlet(String servletPath) {
        Command command = null;

        if ("/loginForm.do".equals(servletPath)) {
            command = new LoginFormController();
        } else if ("/login.do".equals(servletPath)) {
            command = new LoginController();
        } else if ("/logout.do".equals(servletPath)) {
            command = new LogoutController();
        } else if ("/init.do".equals(servletPath)) {
            command = new InitController();
        } else if ("/foods.do".equals(servletPath)) {
            command = new FoodsController();
        } else if ("/cartList.do".equals(servletPath)) {
            command = new CartListController();
        } else if ("/cart.do".equals(servletPath)) {
            command = new CartController();
        } else if ("/local.do".equals(servletPath)) {
            command = new LocalController();
        }

//        return processingServletPath;
        return command;
    }
}
