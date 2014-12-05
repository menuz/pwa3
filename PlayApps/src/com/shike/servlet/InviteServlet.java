package com.shike.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lovebar.dao.InviteDAO;
import com.lovebar.dao.UserDAO;

/**
 * Servlet implementation class InviteServlet
 */
@WebServlet("/InviteServlet")
public class InviteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Log log = LogFactory.getLog(InviteServlet.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InviteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=utf8");
		String openid = request.getParameter("openid");
		// 获取ip
		String ip = request.getRemoteHost();
		log.info("openid" + openid + ",ip:" + ip);
		// 将邀请人的openid以及相应的ip插入数据库中，当然要保证数据库中该记录没有（这个还没弄，要写在到里面诶）
		UserDAO userDao = new UserDAO();
		int userId = userDao.getIdByOpenid(openid);
		InviteDAO inviteDao = new InviteDAO();
		if (inviteDao.isIpExist(userId, ip) == 0) {
			if (inviteDao.addIp(userId, ip) == 1) {
				log.info("成功在invite表中插入一条记录，记录为openid：" + openid + ",ip:" + ip);
			} else {
				log.info("invite表中插入记录失败，记录为openid：" + openid + ",ip:" + ip);
			}
		} else {
			log.info("该记录已经存在，记录为openid：" + openid + ",ip:" + ip);
		}
		
		
		//无论如何都要往jsp进行跳转的
		request.getRequestDispatcher("invite.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
