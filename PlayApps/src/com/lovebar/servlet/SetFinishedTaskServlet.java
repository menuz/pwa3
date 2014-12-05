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
@WebServlet("/SetFinishedTaskServlet")
public class SetFinishedTaskServlet extends HttpServlet {
	// private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetFinishedTaskServlet() {
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
//		response.sendRedirect("ShowAdvsServlet"); // 20141128改正，原20141110添加，疑误操作
		PrintWriter out = response.getWriter();
		String query = request.getQueryString();
		System.out.println(query);
		
		// To do 还得判断是否已经设置为1，若已为1则提示重复设置
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=utf8");

		String user_id = request.getParameter("user_id");
		String webchat_name = request.getParameter("webchat_name");
		String device_id = request.getParameter("device_id");
		String try_id = request.getParameter("try_id");
		String app_id = request.getParameter("app_id");
		String app_name = request.getParameter("app_name");
		app_name = new String(app_name.getBytes("iso8859-1"),"UTF-8");  // 解决了url中有中文乱码的问题
		System.out.println(app_name);
		
		UserDAO userDao = new UserDAO();
		System.out.println(user_id);
		User user = userDao.getUser(user_id);
		String openid = null;
		if (user == null) {
			System.out.println("sql调用出错啦");
		} else {
			openid = user.getOpenid();
			System.out.println("openid:"+ openid); // mason 到这里测试过
		}
		final String APPID = Global.appId;
		final String SECRET = Global.appSecret;
		
		UserDAO userdao = new UserDAO();
		if (userdao.existValidatedUser(user_id, webchat_name, device_id)) {
			// update try record
			TryDAO tryDao = new TryDAO();
			int exist = tryDao.setFinishedTask(try_id);
			if (exist > 0) {
				// to do save income to db
				RewardDAO rewardDAO = new RewardDAO();
				rewardDAO.finishApp(user_id, try_id, app_id);
				
				// mason 修改，微信端提示，用户已经试玩成功，下面的功能已经测试了，那就只剩中间的部分没有测试了
				final String ACCESS_TOKEN = WeiXinUtil.getAccessToken(APPID, SECRET).getToken();
				String json1 = "{\"touser\": \"" + openid + "\",\"msgtype\": \"text\"" + ",\"text\":{\"content\":\"" + app_name + "试玩成功\"}}";
				System.out.println("json:" + json1);
				String sr = URLUtil.sendPost("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + ACCESS_TOKEN, json1);
				System.out.println(sr);
				
				out.print("1");
			}
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
