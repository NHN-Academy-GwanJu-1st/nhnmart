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
            String processingServletPath = resolveServlet(req.getServletPath());

            /* RequestDispatcher include -> a.jsp -> b.jsp -> a.jsp
             * 즉, 제어를 넘긴 대상 페이지의 처리가 완료된 후 결과와 함께 원래 페이지로 제어가 돌아오게 된다. */

            RequestDispatcher rd = req.getRequestDispatcher(processingServletPath);
            rd.include(req, resp);

            String view = (String) req.getAttribute("view");

            if (view.startsWith(REDIRECT_PREFIX)) {
                // redirect 하는 경우
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()));
            } else {
                // redircet가 아니면 해당 jsp에 처리를 위임하고 include 설정으로 인해 그 결과를 돌려 받음
                rd = req.getRequestDispatcher(view);
                rd.include(req, resp);
            }
        } catch (Exception e) {
            log.error("", e);
            req.setAttribute("exception", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }
    }

    private String resolveServlet(String servletPath) {
        String processingServletPath = null;

        if ("/loginForm.do".equals(servletPath)) {
            processingServletPath = "/loginForm";
        } else if ("/login.do".equals(servletPath)) {
            processingServletPath = "/login";
        } else if ("/logout.do".equals(servletPath)) {
            processingServletPath = "/logout";
        } else if ("/init.do".equals(servletPath)) {
            processingServletPath = "/init";
        } else if ("/foods.do".equals(servletPath)) {
            processingServletPath = "/foods";
        } else if ("/cartList.do".equals(servletPath)) {
            processingServletPath = "/cartList";    // cart get
        } else if ("/cart.do".equals(servletPath)) {
            processingServletPath = "/cart";    // cart post
        }

        return processingServletPath;
    }
}
