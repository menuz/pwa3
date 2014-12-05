package com.lovebar.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lovebar.dao.UserDAO;
import com.lovebar.pojo.User;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Servlet implementation class WithdrawServlet
 */
@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WithdrawServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String openid = null;

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=utf8");
		System.out.println("withdraw");
		// openid = (String) request.getAttribute("openid");
		openid = request.getParameter("openid");
		System.out.println("Withdraw....openid:" + openid);
		request.setAttribute("openid", openid); // 不会吧，取出来就没了？？
		UserDAO userDao = new UserDAO();
		User user = userDao.getIncome(openid);
		String alipy_name = user.getAlipy_name();
		int user_id = user.getUser_id();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String ct = df.format(new Date());// new Date()为获取当前系统时间

		if (alipy_name == null || "".equals(alipy_name)) {
			System.out.println("还没设置支付宝哦");
			request.getRequestDispatcher("user.jsp").forward(
					request, response);
		} else {
			System.out.println("支付宝已经设置啦，查看余额中。。。");
			// 取出该用户可提款的金额
			float available_income = user.getAvailable_income();
			// 判断该用户可提款的金额是否大于0元
			if (available_income > 0) {
				// 若大于0元，提示提款成功，已成功提款多少元，做两件事情，用户表里数据减少
//				int rr1 = userDao.reduceMoney(available_income, openid);
//				if (rr1 == 1) {
//					System.out.println("user表中减少" + available_income + "元" + "，applyMoney增加了");
//				} else {
//					System.out.println("user表available_income减少和applyMoney增加失败了。。。");
//				}
//				// 提款记录里面数据增多
//				int rr2 = userDao.addWithdraw(user_id, available_income, ct);
//				if (rr2 == 1) {
//					System.out.println("withdraw增加了" + available_income + "元");
//				} else {
//					System.out.println("withdraw增加了失败了。。。");
//				}
				userDao.applyMoney(available_income, openid, user_id, ct);
				request.getRequestDispatcher("successWithdraw.html").forward(request,
						response);
			} else {
				// 若小于0元，提示余额不足
//				PrintWriter out = response.getWriter();
				System.out.println("余额不足，不能提款");
				request.getRequestDispatcher("noMoney.html").forward(request,
						response);

			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
