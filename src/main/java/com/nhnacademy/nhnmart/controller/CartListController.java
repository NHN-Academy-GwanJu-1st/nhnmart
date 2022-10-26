package com.nhnacademy.nhnmart.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class CartListController implements Command {

    @Override
    public String excute(HttpServletRequest req, HttpServletResponse resp) {
        return "/cart.jsp";
    }
}
