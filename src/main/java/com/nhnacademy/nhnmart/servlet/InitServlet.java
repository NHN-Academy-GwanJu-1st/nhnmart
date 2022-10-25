package com.nhnacademy.nhnmart.servlet;

import com.nhnacademy.nhnmart.domain.Food;
import com.nhnacademy.nhnmart.domain.FoodStand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "initServlet", urlPatterns = "/init")
public class InitServlet extends HttpServlet {
    private static FoodStand foodStand = new FoodStand();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        fillFoodStand(foodStand);
        req.getServletContext().setAttribute("foodStand", foodStand.getFoods());
        RequestDispatcher rd = req.getRequestDispatcher("/init.jsp");
        rd.forward(req, resp);
    }

    private void fillFoodStand(FoodStand foodStand) {

        for (int i = 0; i < 2; i++) {
            foodStand.add(new Food("양파", 1_000));
        }
        for (int i = 0; i < 5; i++) {
            foodStand.add(new Food("계란(30개)", 5_000));
        }
        for (int i = 0; i < 10; i++) {
            foodStand.add(new Food("파", 500));
        }
        for (int i = 0; i < 20; i++) {
            foodStand.add(new Food("사과", 2_000));
        }
    }
}
