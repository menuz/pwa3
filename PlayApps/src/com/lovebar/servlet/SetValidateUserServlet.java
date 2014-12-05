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
@WebServlet("/SetValidateUserServlet")
public class SetValidateUserServlet extends HttpServlet {
	// private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetValidateUserServlet() {
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
		System.out.println("SetValidateUserServlet");

		synchronized (this) {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/json;charset=utf8");

			String user_id = request.getParameter("user_id");
			
			String webchat_name = new String(request.getParameter("webchat_name")
					.getBytes("ISO-8859-1"), "UTF-8");
//			String webchat_name = request.getParameter("webchat_name");
			String device_id = request.getParameter("device_id");
			String ip = request.getRemoteHost();

			// 响应消息
			PrintWriter out = response.getWriter();
			UserDAO userDao = new UserDAO();
			boolean exist = userDao.existUnvalidatedUser(user_id, webchat_name,
					ip);
			if (exist) {
				int res = userDao.setValidateUser(user_id, webchat_name, ip,
						device_id);
				if (res > 0)
					out.print(1);
				else
					out.print(0);
			} else {
				out.print(0);
			}
			out.close();
		}
	}

	/**
	 * 处理微信服务器发来的消息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		
		doGet(request, response);
	}

}
