package com.nhnacademy.nhnmart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class LogoutController implements Command {

    @Override
    public String excute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);

        if (Objects.nonNull(session)) {
            session.invalidate();
            return "redirect:/index.jsp";
        } else {
            return "redirect:/login.jsp";
        }
    }
}
