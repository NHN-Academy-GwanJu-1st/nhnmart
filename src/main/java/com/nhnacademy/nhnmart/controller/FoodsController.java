package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.domain.Food;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

public class FoodsController implements Command {

    @Override
    public String excute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();
        ArrayList<Food> foodStand = (ArrayList<Food>) servletContext.getAttribute("foodStand");

        /* 중복을 제거한 리스트를 뷰에 보내기 */
        List<Food> foodList = foodStand.stream()
                .distinct()
                .collect(Collectors.toList());

        req.setAttribute("foodList", foodList);

        return "/foodStand.jsp";
    }
}
