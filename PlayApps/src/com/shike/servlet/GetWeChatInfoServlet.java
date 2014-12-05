package com.shike.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shike.util.Global;
import com.shike.util.URLUtil;

/**
 * Servlet implementation class GetWeChatInfoServlet
 */
@WebServlet("/GetWeChatInfoServlet")
public class GetWeChatInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetWeChatInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=utf8");
		System.out.println("GetWeChatInfoServlet...");
		String code = request.getParameter("code");
		String openid = request.getParameter("openid");
		final String APPID = Global.appId;
		final String SECRET = Global.appSecret;
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ APPID
				+ "&secret="
				+ SECRET
				+ "&code="
				+ code
				+ "&grant_type=authorization_code";
		System.out.println("code = " + code);
		System.out.println("openid = " + openid);
		System.out.println("requestUrl = " + requestUrl);

		String json = URLUtil.getJson(requestUrl);
		Map resMap = URLUtil.Json2Map(json);
		
		final String ACCESS_TOKEN = (String) resMap.get("access_token");
		final String OPENID = (String) resMap.get("openid");
		String lang = "zh_CN";
		System.out.println(openid);
		System.out.println(OPENID);
		String getUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + ACCESS_TOKEN + "&openid=" + OPENID + "&lang=" + lang;
		System.out.println(getUserInfoUrl);
		String userInfoJson = URLUtil.getJson(getUserInfoUrl);
		Map userInfoMap = URLUtil.Json2Map(userInfoJson);
		
		response.sendRedirect("ShowAdvsServlet");
		PrintWriter out = response.getWriter();
		out.print("hello world");
		out.close();
		
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
