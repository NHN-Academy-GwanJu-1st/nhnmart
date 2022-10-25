package com.nhnacademy.nhnmart.servlet;

import com.nhnacademy.nhnmart.domain.Food;
import com.nhnacademy.nhnmart.domain.FoodStand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Slf4j
@WebServlet(name = "initServlet", urlPatterns = "/init")
public class InitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        FoodStand foodStand = new FoodStand();

        ServletContext servletContext = req.getServletContext();

        Enumeration<String> initParameterNames = servletContext.getInitParameterNames();
        ArrayList<String> paramList = new ArrayList<>();

        log.info(String.valueOf(Objects.isNull(initParameterNames)));

        while (initParameterNames.hasMoreElements()) {

            paramList.add(initParameterNames.nextElement());
        }

        for (int i = 0; i < 2; i++) {
            foodStand.add(new Food(paramList.get(0), Integer.parseInt(servletContext.getInitParameter(paramList.get(0)))));
        }
        for (int i = 0; i < 5; i++) {
            foodStand.add(new Food(paramList.get(1), Integer.parseInt(servletContext.getInitParameter(paramList.get(1)))));
        }
        for (int i = 0; i < 10; i++) {
            foodStand.add(new Food(paramList.get(2), Integer.parseInt(servletContext.getInitParameter(paramList.get(2)))));
        }
        for (int i = 0; i < 20; i++) {
            foodStand.add(new Food(paramList.get(3), Integer.parseInt(servletContext.getInitParameter(paramList.get(3)))));
        }


        servletContext.setAttribute("foodStand", foodStand.getFoods());


        RequestDispatcher rd = req.getRequestDispatcher("/init.jsp");
        rd.forward(req, resp);
    }
}
