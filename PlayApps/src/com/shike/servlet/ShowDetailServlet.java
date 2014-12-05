package com.shike.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lovebar.dao.AppDAO;
import com.lovebar.dao.TryDAO;
import com.lovebar.dao.UserDAO;
import com.lovebar.pojo.App;

/**
 * Servlet implementation class ShowDetailServlet
 */
@WebServlet("/ShowDetailServlet")
public class ShowDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowDetailServlet() {
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
		PrintWriter out = response.getWriter();
		
		//2014.11.30 若为android，则直接下载嘿！！
		String os_type = request.getParameter("os_type");
		System.out.println("a".equals(os_type));
		if ("a".equals(os_type)) {
			String href = "http://mp.weixin.qq.com/mp/redirect?url=http://fir.im/shimei/install";
//			href = "http://www.baidu.com";
			System.out.println(href);
			response.sendRedirect(href);
//			request.getRequestDispatcher(href).forward(request, response);
		} else if ("i".equals(os_type)) {
			String app_id = request.getParameter("app_id");
			AppDAO appDao = new AppDAO();
			App app = appDao.showDetail(app_id);
			if (null != app) {
				request.setAttribute("app", app);
//				System.out.println("##############" + request.getParameter("openid"));
				// 将该记录插入到try表中中 2014-11-07
				TryDAO tryDao = new TryDAO();
				String user_id = request.getParameter("user_id");
				int res = tryDao.insert2Try(user_id, app_id);
				if (res == 1) {
					System.out.println("插入try表成功"); //应该写入日志的！！
				} else if (res == -1) {
					System.out.println("插入try表失败"); //应该写入日志的！！
				}
//				String ip = request.getRemoteHost();
//				System.out.println("ip:" + ip);
//				int st = -1;
//				UserDAO userDao = new UserDAO();
//				st = userDao.updateIp(user_id, ip);
//				System.out.println("user_id:" + user_id);
//				System.out.println("ip:" + ip);
//				if (st == 1) {
//					System.out.println("ip插入成功");
//				} else {
//					System.out.println("ip插入失败");
//				}
				request.getRequestDispatcher("showDetail.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("fail.jsp").forward(request, response);
			}
			out.print(new Gson().toJson(app)); 
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
