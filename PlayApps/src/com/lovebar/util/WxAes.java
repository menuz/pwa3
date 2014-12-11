package com.lovebar.util;

import java.net.URLEncoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class WxAes {
	
	public static void main(String[] args) {
//		Integer appId = 110; // 开发者在米迪后台注册的应用的：发布ID，假设是： 110
		String openId = "abcdefghijklmnop"; // 微信用户的openid，假设是： abcdefghijklmnop
//		String appName = "应用名称"; // 开发者的公众号名称，也是开发者在米迪后台注册的应用
//		的：应用名称，假设是： 应用名称
//		String encryptKey = "abcdefghijklmnop";// 开发者在米迪后台注册的应用的：应用密码，假设
//		是： abcdefghijklmnop
		String param0 = "abcdefghijklmnop"; //开发者用来判断唯一用户的标准，可以用 openid，米
//		迪回调开发者的时候会原样返回，假设是： abcdefghijklmnop
		
		System.out.println(getEncryptResult(openId, param0));
//		
//		110_xVtF5av0hW08WzhMSzfT%2BjKzQG1HBhRvzx0LawvaGrIT4rX%2B32aA0ORq6BkgNMt1rEJ9lRdILFOoh8684B3yVsf8hjjhl4n2xQT1o2in1jE%3D
//		
//		110_xVtF5av0hW08WzhMSzfT%2BjKzQG1HBhRvzx0LawvaGrIT4rX%2B32aA0ORq6BkgNMt1rEJ9lRdILFOoh8684B3yVsf8hjjhl4n2xQT1o2in1jE%3D

		
	}
	
	
	public static String getEncryptResult(String openid, String user_id) {
		Integer appId = Constants.MIDI_APP_ID; // 开发者在米迪后台注册的应用的：发布ID
		String openId = openid; // 微信用户的openid
		String appName =  Constants.MIDI_APP_NAME;// 开发者的公众号，也是开发者在米迪后台
		// 注册的应用的：应用名称
		String encryptKey = Constants.MIDI_APP_SECRET;// 开发者在米迪后台注册的应用的：
		
		// 应用密码，16位的
		String param0 = user_id; // 开发者用来判断唯一用户的标准，可以用openId
		// 作为值，也可以是开发者自己定义的用户id，米迪回调开发者的时候会原样返回，开发者需
		// 要用这个值区分用户
		try {
			String encryptString = "openId=" + openId + "&appName=" + appName
					+ "&param0=" + param0;// 三个参数缺一不可
			String encryptResult = appId + "_"
					+ encrptAesBase64(encryptString, encryptKey);// appId+ "_"
																	// 也必不可缺
			return encryptResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}

	private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6 };
	
	public static String encrptAesBase64(String encryptString, String encryptKey)
			throws Exception {
		if (encryptKey == null) {
			return null;
		}
		if (encryptString == null) {
			return null;
		}
		if (encryptKey.length() != 16) {
			return null;
		}

		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		byte[] keys = encryptKey.getBytes("UTF8");
		SecretKeySpec key = new SecretKeySpec(keys, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 算法/模式/
																	// //补码方式
		cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
		byte[] encryptedData = cipher.doFinal(encryptString.getBytes("UTF8"));
		String base64Str = new String(Base64.encodeBase64(encryptedData),
				"UTF8");
		return URLEncoder.encode(base64Str, "utf-8");
	}
}
