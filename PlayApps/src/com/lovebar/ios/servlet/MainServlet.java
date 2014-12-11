package com.lovebar.ios.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lovebar.dao.UserDAO;
import com.lovebar.ios.servlet.IosMoneyDAO;
import com.lovebar.ios.servlet.MiDi;
import com.lovebar.util.WxAes;


@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf8");
		response.setContentType("text/json;charset=utf8");
		
		String openid = request.getParameter("openid");
		
		UserDAO userDAO = new UserDAO();
		int user_id = userDAO.getIdByOpenid(openid);
		
		String r = WxAes.getEncryptResult(openid, user_id+"");
		
		System.out.println("openid = " + openid + " user_id = " + user_id);
		
		String redirect_url = "http://ow.miidi.net/ow/wxList.bin?r="+r;
		response.sendRedirect(redirect_url);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private static String md5(String str) throws Exception {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
			byte[] byteArray = messageDigest.digest();
			StringBuffer md5StrBuff = new StringBuffer();
			for (int i = 0; i < byteArray.length; i++) {
				if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
					md5StrBuff.append("0").append(
							Integer.toHexString(0xFF & byteArray[i]));
				else
					md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
			// logger.debug("md5 src:{}, dst:{}", str, md5StrBuff.toString());
			return md5StrBuff.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			// logger.error("NoSuchAlgorithmException caught!");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		throw new Exception("md5 failed!");
	}

}