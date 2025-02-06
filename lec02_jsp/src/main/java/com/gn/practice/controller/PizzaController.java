package com.gn.practice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/order")
public class PizzaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PizzaController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String userName = req.getParameter("user_name");
		String userPhone = req.getParameter("user_phone");
		String userEmail = req.getParameter("user_email");
		String size = req.getParameter("size");
		String[] adds = req.getParameterValues("add");
		String deliveryTime = req.getParameter("delivery_time");

		int price = 0;
		// 1. size 기준
		// Small : 15900
		// Medium : 21000
		// Large : 27900
		int sizePrice = 0;
		switch (size) {
		case "1":
			sizePrice = 15900;
			break;
		case "2":
			sizePrice = 21000;
			break;
		case "3":
			sizePrice = 27900;
			break;
		}

		// 2. 토핑추가
		// 새우 +2000
		// 고구마, 감자, 파인애플 +1000
		int toppingPrice = 0;
		if (adds != null) {
			for (int i = 0; i < adds.length; i++) {
				switch (adds[i]) {
				case "1":
					toppingPrice += 2000;
					break;
				default:
					toppingPrice += 1000;
				}
			}
		}

		price = sizePrice + toppingPrice;
		System.out.println("피자값 : " + price);

		System.out.printf("이름 : %s\n전화번호 : %s\n이메일 : %s\n사이즈  %s\n토핑 : %s\n희망 배달 시간 : %s", userName, userPhone,
				userEmail, size, adds.toString(), deliveryTime);

		RequestDispatcher view = req.getRequestDispatcher("views/pizzaPayment.jsp");
		req.setAttribute("name", userName);
		req.setAttribute("phone", userPhone);
		req.setAttribute("email", userEmail);
		req.setAttribute("size", size);
		req.setAttribute("toppings", adds);
		req.setAttribute("price", price);
		req.setAttribute("time", deliveryTime);

		view.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
