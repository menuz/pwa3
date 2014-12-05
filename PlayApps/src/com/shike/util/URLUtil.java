package com.shike.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

public class URLUtil {
	public static String getJson(String urlString) {
	    try {
	      StringBuffer html = new StringBuffer();
	      URL url = new URL(urlString);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      InputStreamReader isr = new InputStreamReader(conn.getInputStream());
	      BufferedReader br = new BufferedReader(isr);
	      String temp;
	      while ((temp = br.readLine()) != null) {
	        html.append(temp).append("\n");
	      }
	      br.close();
	      isr.close();
	      return html.toString();
	    } catch (Exception e) {
	      e.printStackTrace();
	      return null;
	    }
	}
	public static Map Json2Map(String json) {
		Map result = new HashMap();
		String key = null;
		Object value = null;
		try {
			JSONObject jsonObject = new JSONObject(json);
			Iterator iterator = jsonObject.keys();
			while (iterator.hasNext()) {
				key = (String) iterator.next();
				value = jsonObject.get(key);
				result.put(key, value);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<String> keySet = result.keySet();
//		 遍历key集合，获取value
		for (String key1 : keySet) {
			Object value1 = result.get(key1);
			System.out.println("key:" + key1 + ",value:" + value1);
		}
		return result;
	}
	
	/**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    } 
}
