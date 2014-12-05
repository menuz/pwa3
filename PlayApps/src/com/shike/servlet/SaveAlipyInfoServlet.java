package com.shike.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lovebar.dao.UserDAO;

/**
 * Servlet implementation class SaveAlipyInfoServlet
 */
@WebServlet("/SaveAlipyInfoServlet")
public class SaveAlipyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveAlipyInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=utf8");
//		String account = request.getParameter("account");
		String account = new String(request.getParameter("account").getBytes("ISO-8859-1"),"utf-8");
//		String name = request.getParameter("name");
		String name = new String(request.getParameter("name").getBytes("ISO-8859-1"),"utf-8");
		String openid = request.getParameter("openid");
		System.out.println("account:" + account);
		System.out.println("name:" + name);
		System.out.println("openid:" + openid);
		UserDAO userDao = new UserDAO();
		int res = userDao.saveAlipyInfo(name, account, openid);
		if (res == 1) {
			System.out.println("成功将支付宝信息存入数据库");
			request.getRequestDispatcher("successSetAlipy.html")
					.forward(request, response);
		} else if (res == -1) {
			System.out.println("支付宝信息存入数据库失败。。。");
			request.getRequestDispatcher("fail.html")
					.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
