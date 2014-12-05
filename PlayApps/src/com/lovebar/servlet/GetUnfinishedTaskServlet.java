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
import com.lovebar.dao.TryDAO;
import com.lovebar.pojo.Device;
import com.lovebar.pojo.Try;

/**
 * Servlet implementation class CoreServlet
 */
@WebServlet("/GetUnfinishedTaskServlet")
public class GetUnfinishedTaskServlet extends HttpServlet {

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUnfinishedTaskServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 4440739483644821986L;

	/**
	 * 确认请求来自微信服务器
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=utf8");
		String user_id = request.getParameter("user_id");
		TryDAO tryDao = new TryDAO();
		ArrayList<Try> ts = tryDao.getUnfinishedTask(Integer.parseInt(user_id));
		// 响应消息
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(ts));  
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
