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

import com.lovebar.ios.servlet.IosMoneyDAO;
import com.lovebar.ios.servlet.MiDi;


// test url
// snuid=100000001&device_id=864400001136123&app_id=072cb4d9d9d5dfd23ed2981e5e33fe59&currency=100&app_ratio=1&time_stamp=1326183509234&token=7872AAEC78382CE083992168BA7571AB
@WebServlet("/IMidiServlet")
public class IMidiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IMidiServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String user_id = request.getParameter("param0");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String ct = sdf.format(new Date());

			request.setCharacterEncoding("utf8");
			response.setContentType("text/json;charset=utf8");

			String queryString = request.getQueryString();
			System.out.println("/IMidiServlet?" + queryString);

			String ad_id = request.getParameter("id");
			String trand_no = request.getParameter("trand_no");
			String cash = request.getParameter("cash");
			String imei = request.getParameter("imei");
			String bundle_id = request.getParameter("bundleId");
			String app_name = new String(request.getParameter("appName") 
					.getBytes("ISO-8859-1"), "UTF-8");
			String sign = request.getParameter("sign");

			String key = "rr7c6jxu8rcbdblfak4kx8p2lmklgc";

			StringBuffer sb = new StringBuffer();
			sb.append(ad_id).append(trand_no).append(cash)
					.append(user_id == null ? "" : user_id).append(key);

			MiDi md = new MiDi(ad_id, trand_no, Integer.parseInt(cash), imei,
					bundle_id, user_id, app_name, sign);

			PrintWriter out = response.getWriter();

			if (sign != null
					&& (sign.equals(md5(sb.toString())) || sign.equals(md5(sb
							.toString())))) { // 验证合法
				if (new IosMoneyDAO().miDiCheck(trand_no)) {
					new IosMoneyDAO().addMiDi(md);
					out.print(200);
				} else {
					System.out.println("trand_no exist");
					out.print(404);
				}

			} else {
				out.print(404);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("IMidiServlet Exception");
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