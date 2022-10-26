package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.domain.Basket;
import com.nhnacademy.nhnmart.domain.Food;
import com.nhnacademy.nhnmart.exception.AmountException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
public class CartController implements Command {

    private Basket basket = new Basket();

    @Override
    public String excute(HttpServletRequest req, HttpServletResponse resp) throws AmountException {
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
            throw new AmountException("상품 수량이 부족합니다.");
        }

        servletContext.setAttribute("buyMap", buyMap);
        servletContext.setAttribute("totalPrice", totalPrice);
        return "redirect:/cartList.do";
    }
}
