package com.shike.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lovebar.dao.AppDAO;
import com.lovebar.dao.InviteDAO;
import com.lovebar.dao.UserDAO;
import com.lovebar.pojo.App;
import com.lovebar.pojo.User;
import com.shike.util.JudgeOS;

/**
 * Servlet implementation class ShowAdvsServlet
 */
@WebServlet("/ShowAdvsServlet")
public class ShowAdvsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Log log = LogFactory.getLog(ShowAdvsServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowAdvsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=utf8");

		String openid = request.getParameter("openid");
		System.out.println(openid);
		UserDAO userDao = new UserDAO();
		// getUserInfo中会判断该用户是否已经关注
		userDao.getUserInfo(openid);

		User user = userDao.getIncome(openid);// 名字取得不好而已啦，其实就是根据openid获取user对象啦
		System.out.println(user);
		int user_id = user.getUser_id();
		request.setAttribute("user_id", user_id);

		// ip
		String ip = request.getRemoteHost();
		System.out.println("ip11:" + ip);
		int st = -1;
		st = userDao.updateIp(user_id + "", ip);
		System.out.println("user_id:" + user_id);
		System.out.println("ip:" + ip);
		if (st == 1) {
			log.info("ip插入成功");
		} else {
			log.info("ip插入失败");
		}
		// 2014.12.4 判断ip是否出现在invite中，若出现，则表示邀请成功
		InviteDAO inviteDao = new InviteDAO();
		if (inviteDao.isInvited(ip) == 1) {
			if (inviteDao.addInvitee(user_id, ip) == 1) {
				log.info("该用户" + user_id + "是被邀请的，ip为" + ip);
			} else {
				log.info("该用户" + user_id + "是被邀请的，ip为" + ip + "，但插入失败");
			}
			
		} else {
			log.info("该用户" + user_id + "是自己关注的");
		}
		
		
		AppDAO appDao = new AppDAO();
		ArrayList<App> ts = appDao.showAdvs(user_id);
		ArrayList<App> result = new ArrayList<App>();
		// 响应消息
		// 2014.11.30 判断是android还是ios
		JudgeOS judgeOS = new JudgeOS(request);
		int isAvailable = user.getIs_available();
		if (judgeOS.JudgeIsAndroid()) {
			// 2014.12.2 若为android，直接显示应用
			log.info("openid: " + openid + ", os: android");
			Iterator<App> app = ts.iterator();
			while (app.hasNext()) {
				App next = app.next();
				//if (!"a".equals(next.getOs_type())) {
				if ("a".equals(next.getOs_type())) {
//					app.remove();
					result.add(next);
				}
			}
			request.setAttribute("os_type", "a");
			// 默认android用户已经验证通过了，------------临时方案
			isAvailable = 2;
		} else if (judgeOS.JudgeIsIos()) {
			// 2014.12.2若为ios，则判定该用户是否已经验证，若没有验证，则遮掩并提示用户安装
			log.info("openid: " + openid + ", os: ios");
			Iterator<App> app = ts.iterator();
			while (app.hasNext()) {
				App next = app.next();
//				if (!"i".equals(next.getOs_type())) {
				if ("i".equals(next.getOs_type())) {
//					app.remove();
					result.add(next);
				}
			}
			request.setAttribute("os_type", "i");
		} else {
			log.info("openid: " + openid + ", os: 不是android也不是ios");
		}
		request.setAttribute("isAvailable", isAvailable);
//		if (re.size() == 0) {
		if (result == null || result.size() == 0) {
			request.getRequestDispatcher("noApp.html").forward(request, response);
		} else {
			request.setAttribute("arrayList", result);
			request.getRequestDispatcher("showAdvs.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
