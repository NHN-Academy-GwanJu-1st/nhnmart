package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController implements Command {

    @Override
    public String excute(HttpServletRequest req, HttpServletResponse resp) {

        String initId = req.getServletContext().getInitParameter("id");
        String initPassword = req.getServletContext().getInitParameter("password");

        String id = req.getParameter("id");
        String password = req.getParameter("password");

        if (initId.equals(id) && initPassword.equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("id", id);
            User user = new User(id, password);
            req.getServletContext().setAttribute("user", user);

            return "redirect:/";
        } else {
            return "redirect:/loginForm.do";
        }
    }
}
