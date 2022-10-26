package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.exception.AmountException;
import com.nhnacademy.nhnmart.exception.NotEnoughMoneyException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    String excute(HttpServletRequest req, HttpServletResponse resp) throws NotEnoughMoneyException, AmountException;
}
