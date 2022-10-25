package com.nhnacademy.nhnmart.servlet;

import com.nhnacademy.nhnmart.domain.Basket;
import com.nhnacademy.nhnmart.domain.Food;
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
@WebServlet(name = "cartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {

    private Basket basket = new Basket();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/cart.jsp");
        rd.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = req.getServletContext();

        int totalPrice = 0;
        Map<Food, Integer> buyMap = new HashMap<>();
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String param = parameterNames.nextElement();
            String pickNumber = req.getParameter(param);
            buyMap.put(new Food(param, Integer.parseInt(servletContext.getInitParameter(param))), Integer.parseInt(pickNumber));
            totalPrice += Integer.parseInt(servletContext.getInitParameter(param)) * Integer.parseInt(pickNumber);

            for (int i = 0; i < Integer.parseInt(pickNumber); i++) {
                basket.add(new Food(param, Integer.parseInt(servletContext.getInitParameter(param))));
            }

        }

        ArrayList<Food> basketFoods= basket.getFoods();
        int basketCount = basketFoods.size();

        ArrayList<Food> foodStand = (ArrayList<Food>) servletContext.getAttribute("foodStand");

        for (int i = basketCount; i > 0; i--) {
            for (int j = 0; j < foodStand.size(); j++) {
                if (foodStand.get(j).equals(basketFoods.get(i-1))) {
                    foodStand.remove(j);
                    basketCount--;
                    break;
                }
            }
        }

        if (basketCount != 0) {
            throw new NoSuchElementException("식품매대에 재고가 부족할 때");
        }

        servletContext.setAttribute("buyMap", buyMap);
        servletContext.setAttribute("totalPrice", totalPrice);
        resp.sendRedirect("/cart");
    }
}
