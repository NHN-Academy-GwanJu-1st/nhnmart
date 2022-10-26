package com.nhnacademy.nhnmart.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

@Slf4j
public class LocalController implements Command {
    @Override
    public String excute(HttpServletRequest req, HttpServletResponse resp) {
//        String locale = req.getParameter("locale");
//
//        log.info("-------- param locale ------- " + locale);
//
//        HttpSession session = req.getSession();
//        session.setAttribute("locale", locale);

        return "redirect:/";
    }
}
