package com.shike.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lovebar.dao.InviteDAO;
import com.lovebar.pojo.Invite;

/**
 * Servlet implementation class getInviteRecordsServlet
 */
@WebServlet("/getInviteRecordsServlet")
public class getInviteRecordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getInviteRecordsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=utf8");
		String user_id = request.getParameter("user_id");
		InviteDAO invitedDAO = new InviteDAO();
		ArrayList<Invite> ts = invitedDAO.getInviteRecords(user_id);
		// 响应消息
		PrintWriter out = response.getWriter();
		if (ts.size() == 0)
			out.print("-1");
		else
			out.print(new Gson().toJson(ts));  
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
