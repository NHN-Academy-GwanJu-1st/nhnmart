package com.nhnacademy.nhnmart.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "frontController", urlPatterns = "*.do")
public class FrontController extends HttpServlet {

    private static final String REDIRECT_PREFIX = "redirect:";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

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
//        String processingServletPath = null;

        Command command = null;

        if ("/loginForm.do".equals(servletPath)) {
//            processingServletPath = "/loginForm";
            command = new LoginFormController();
        } else if ("/login.do".equals(servletPath)) {
//            processingServletPath = "/login";
            command = new LoginController();
        } else if ("/logout.do".equals(servletPath)) {
//            processingServletPath = "/logout";
            command = new LogoutController();
        } else if ("/init.do".equals(servletPath)) {
//            processingServletPath = "/init";
            command = new InitController();
        } else if ("/foods.do".equals(servletPath)) {
//            processingServletPath = "/foods";
            command = new FoodsController();
        } else if ("/cartList.do".equals(servletPath)) {
//            processingServletPath = "/cartList";    // cart get
            command = new CartListController();
        } else if ("/cart.do".equals(servletPath)) {
//            processingServletPath = "/cart";    // cart post
            command = new CartController();
        }

//        return processingServletPath;
        return command;
    }
}
