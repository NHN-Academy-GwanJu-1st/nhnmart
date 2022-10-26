package com.nhnacademy.nhnmart.servlet;

import com.nhnacademy.nhnmart.domain.Food;
import com.nhnacademy.nhnmart.domain.FoodStand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "foodServlet", urlPatterns = "/foods")
public class FoodsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = req.getServletContext();
        ArrayList<Food> foodStand = (ArrayList<Food>) servletContext.getAttribute("foodStand");

        /* 중복을 제거한 리스트를 뷰에 보내기 */
        List<Food> foodList = foodStand.stream()
                .distinct()
                .collect(Collectors.toList());

        req.setAttribute("foodList", foodList);
        req.setAttribute("foodStand", foodStand);

//        RequestDispatcher rd = req.getRequestDispatcher("/foodStand.jsp");
//        rd.forward(req, resp);

        req.setAttribute("view", "/foodStand.jsp");
    }
}
