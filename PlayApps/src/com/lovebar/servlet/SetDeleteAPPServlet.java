package com.lovebar.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lovebar.dao.RewardDAO;
import com.lovebar.dao.TryDAO;
import com.lovebar.dao.UserDAO;
import com.lovebar.pojo.User;
import com.lovebar.weixin.util.WeiXinUtil;
import com.shike.util.Global;
import com.shike.util.URLUtil;

/**
 * Servlet implementation class CoreServlet
 */
@WebServlet("/SetDeleteAPPServlet")
public class SetDeleteAPPServlet extends HttpServlet {
	// private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetDeleteAPPServlet() {
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
		
		// To do 还得判断是否已经设置为1，若已为1则提示重复设置
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=utf8");

		String try_id = request.getParameter("try_id");
		String user_id = request.getParameter("user_id");
		String app_id = request.getParameter("app_id");
		
		TryDAO userdao = new TryDAO();
		int res = userdao.deleteAPP(try_id, user_id, app_id);
		if(res >0) out.print("1");
		else out.print("0");

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
