package com.lovebar.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lovebar.dao.TryDAO;
import com.lovebar.dao.UserDAO;

/**
 * Servlet implementation class CoreServlet
 */
@WebServlet("/SetInstalledAppTaskServlet")
public class SetInstalledAppTaskServlet extends HttpServlet {
	// private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetInstalledAppTaskServlet() {
		super();
	}

	private static final long serialVersionUID = 4440739483644821986L;

	/**
	 * 确认请求来自微信服务器
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// To do 还得判断是否已经设置为1，若已为1则提示重复设置
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=utf8");

		String user_id = request.getParameter("user_id");
		String webchat_name = request.getParameter("webchat_name");
		String device_id = request.getParameter("device_id");
		String try_id = request.getParameter("try_id");
		String app_id = request.getParameter("app_id");
		
		// 响应消息
		PrintWriter out = response.getWriter();
		
		UserDAO userdao = new UserDAO();
		if(userdao.existValidatedUser(user_id, webchat_name, device_id)) {
			// update try record
			TryDAO tryDao = new TryDAO();
			int exist = tryDao.setInstalledAppTask(try_id);
			
			if(exist>0)
				out.print("1");
			else 
				out.print("0");
		} else {
			out.print("0");
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
