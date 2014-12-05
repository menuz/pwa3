package com.lovebar.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lovebar.dao.UserDAO;
import com.lovebar.pojo.Device;

/**
 * Servlet implementation class CoreServlet
 */
@WebServlet("/SetUserInfoServlet")
public class SetUserInfoServlet extends HttpServlet {
	// private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetUserInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 4440739483644821986L;

	/**
	 * 确认请求来自微信服务器
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("get");
		doPost(request, response);
	}

	/**
	 * 处理微信服务器发来的消息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int result = 0;
		
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) { /* report an error */
			
		}

		System.out.println(jb.toString());

		Device d = new Gson().fromJson(jb.toString(), Device.class);
		System.out.println(d);
		
		String user_id = request.getParameter("user_id");
		String device_os = d.getDeviceOS();
		String device_os_version = d.getDeviceOSVersion();
		String device_id = d.getDeviceId();
		String device_model = d.getDeviceModel();
		System.out.println("【SetUserInfoServlet】");
		System.out.println(user_id + " " +  device_os +  " " + device_os_version + " " + device_id + " "
				+ device_model);

		UserDAO userDao = new UserDAO();
		result = userDao.setUserInfo1(device_id, device_os, device_os_version, device_model, user_id);
		
		String app = d.getApp();
		int res = userDao.setInstalled(user_id, app);
		
		// 响应消息
		PrintWriter out = response.getWriter();
		if(res > 0)
			out.print(1);
		else 
			out.print(0);
		out.close();
	}

}
