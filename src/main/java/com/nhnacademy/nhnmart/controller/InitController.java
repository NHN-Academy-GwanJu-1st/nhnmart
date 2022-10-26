package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.domain.Food;
import com.nhnacademy.nhnmart.domain.FoodStand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class InitController implements Command {

    @Override
    public String excute(HttpServletRequest req, HttpServletResponse resp) {
        FoodStand foodStand = new FoodStand();

        ServletContext servletContext = req.getServletContext();

        for (int i = 0; i < 2; i++) {
            foodStand.add(new Food("양파", Integer.parseInt(servletContext.getInitParameter("양파"))));
        }
        for (int i = 0; i < 5; i++) {
            foodStand.add(new Food("계란", Integer.parseInt(servletContext.getInitParameter("계란"))));
        }
        for (int i = 0; i < 10; i++) {
            foodStand.add(new Food("파", Integer.parseInt(servletContext.getInitParameter("파"))));
        }
        for (int i = 0; i < 20; i++) {
            foodStand.add(new Food("사과", Integer.parseInt(servletContext.getInitParameter("사과"))));
        }

        servletContext.setAttribute("foodStand", foodStand.getFoods());
        return "/init.jsp";
    }
}
