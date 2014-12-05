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
@WebServlet("/GetValidateUserServlet")
public class GetValidateUserServlet extends HttpServlet {
	// private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetValidateUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 4440739483644821986L;

	/**
	 * 确认请求来自微信服务器
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=utf8");
		String ip = request.getRemoteHost();  // note, is it right?
		
		System.out.println("ip = " + ip);
		
//		System.out.println(ip);
		UserDAO userDao = new UserDAO();
		ArrayList<User> users = userDao.getValidateUser(ip);

		// 响应消息
		PrintWriter out = response.getWriter();
		String str = new Gson().toJson(users);
		out.print(str);
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
