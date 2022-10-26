package com.nhnacademy.nhnmart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class LoginFormController implements Command {

    @Override
    public String excute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (Objects.isNull(session)) {
            req.setAttribute("view", "/loginForm.jsp");
            return "/loginForm.jsp";
        } else {
            req.setAttribute("view", "/index.jsp");
            return "/index.jsp";
        }
    }
}
