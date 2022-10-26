package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.domain.User;
import com.nhnacademy.nhnmart.exception.NotEnoughMoneyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PayController implements Command {
    @Override
    public String excute(HttpServletRequest req, HttpServletResponse resp) throws NotEnoughMoneyException {

        int totalPrice = Integer.parseInt(req.getParameter("totalPrice"));
        User currentUser = (User) req.getServletContext().getAttribute("user");

        if (totalPrice > 20000 || totalPrice > currentUser.getMoney()) {
            throw new NotEnoughMoneyException("돈이 부족합니다.");
        }

        currentUser.setMoney(currentUser.getMoney() - totalPrice);

        return "redirect:/";
    }
}

