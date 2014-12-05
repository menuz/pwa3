package com.lovebar.servlet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

public class Test {
	public static void main(String[] args) throws Exception {
        URL url = new URL("http://115.29.165.234/LoveBar/SetUserInfoServlet?user_id=2");
        Map<String,Object> params = new LinkedHashMap<String, Object>();
        params.put("app", "app");
        params.put("deviceOS", "os");
        params.put("deviceOSVersion", "version");
        params.put("deviceId", "id");
        params.put("deviceModel", "model");
        
        JSONObject obj = new JSONObject(params);
        byte[] postDataBytes = obj.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        for (int c; (c = in.read()) >= 0; System.out.print((char)c));
    }
}


