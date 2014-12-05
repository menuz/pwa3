package com.lovebar.servlet;

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
import com.lovebar.pojo.Try;

/**
 * Servlet implementation class CoreServlet
 */
@WebServlet("/GetFinishedTaskServlet")
public class GetFinishedTaskServlet extends HttpServlet {
	// private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetFinishedTaskServlet() {
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
		PrintWriter out = response.getWriter();
		String query = request.getQueryString();
		System.out.println(query);

		String user_id = request.getParameter("user_id");
		TryDAO trydao = new TryDAO();
		ArrayList<Try> ts = trydao.getFinishedApp(Integer.parseInt(user_id));
		// 响应消息
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
