package com.lovebar.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lovebar.dao.UserDAO;
import com.lovebar.pojo.Device;
import com.lovebar.pojo.Try;
import com.lovebar.pojo.User;

/**
 * Servlet implementation class CoreServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	// private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 4440739483644821986L;

	/**
	 * 确认请求来自微信服务器
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("LoginServlet");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=utf8");

		String device_id = request.getParameter("device_id");
		String user_id = request.getParameter("user_id");
		String webchat_name = new String(request.getParameter("webchat_name").getBytes(
				"ISO-8859-1"), "UTF-8");

		// get user by device_id or return error info
		PrintWriter out = response.getWriter();
		UserDAO userDao = new UserDAO();
		
		System.out.println(device_id + "  " +  user_id + "  " + webchat_name);
		
		boolean exist = userDao.existValidatedUser(user_id, webchat_name, device_id);
		if (exist) {
			out.print(1);
		} else {
			out.print(0);
		}
		out.close();
	}

	/**
	 * 处理微信服务器发来的消息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
