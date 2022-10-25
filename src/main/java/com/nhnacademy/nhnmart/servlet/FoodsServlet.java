package com.nhnacademy.nhnmart.servlet;

import com.nhnacademy.nhnmart.domain.Food;
import com.nhnacademy.nhnmart.domain.FoodStand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FoodsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = req.getServletContext();
        ArrayList<Food> foodStand = (ArrayList<Food>) servletContext.getAttribute("foodStand");

        /* 여기서 Map으로 가공해서 보내버릴까? 각 Map<Food, int> Food 갯수 */

        List<Food> foodList = foodStand.stream()
                .distinct()
                .collect(Collectors.toList());

        req.setAttribute("foodList", foodList);

        RequestDispatcher rd = req.getRequestDispatcher("/foodStand.jsp");
        rd.forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
//        FoodStand foodStand = new FoodStand();
//        fillFoodStand(foodStand);


    }
}
