package com.lovebar.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lovebar.dao.UserDAO;
import com.lovebar.message.resp.Article;
import com.lovebar.message.resp.NewsMessage;
import com.lovebar.message.resp.TextMessage;
import com.lovebar.pojo.User;
import com.lovebar.util.MessageUtil;
import com.shike.util.Global;

/**
 * 核心服务类
 * 
 * @author liufeng
 * @date 2013-05-20
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	static Log log = LogFactory.getLog(CoreService.class);
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "";
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			
			System.out.println("------------------ Receive From Client ------------------");
			for (Object o : requestMap.keySet()) {
			   System.out.println("key=" + o + " value=" + requestMap.get(o));
			}

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			//textMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				//respContent = "您发送的是文本消息！";
				respContent = handleTextMessage(requestMap.get("Content"));
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "欢迎来到试玩天地，啥也不多说啦，试玩来看看效果吧";
					//respContent = "subscribe";
//					String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0f319efc8d73bfd7&redirect_uri=http%3a%2f%2f54.254.161.96%2fLoveBar%2fGetWeChatInfoServlet&response_type=code&scope=snsapi_userinfo&state=12#wechat_redirect";
					// https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
					
					UserDAO userDao = new UserDAO();
					userDao.getUserInfo(fromUserName);
					// 2014.12.2 下面这个需要返回么？我注释掉勒
					//return respContent;
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
					// 2014.12.2在数据库中备注一下，可以统计哪一些用户目前关注
					UserDAO userDao = new UserDAO();
					int cancelRes = userDao.cancel(fromUserName);
					if (cancelRes == 1) {
						log.info("用户" + fromUserName + "取消关注");
					} else {
						log.info("用户" + fromUserName + "取消关注,数据更新失败");
					}
					
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");
					System.out.println(eventKey);
					if (eventKey.equals("11")) {
						System.out.println("**************test*****************");
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						
//						textMessage.setCoarntent(respContent);
						int articleCount = 1;
						newsMessage.setArticleCount(articleCount);
						
						Article article1 = new Article();
						String title = "一起玩";
//						String url = "http://mp.weixin.qq.com/s?__biz=MzA4ODIxNDcxMw==&mid=200038816&idx=1&sn=6bf622411fc4d0bce911d7263baa6f3e#rd";
//						String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0f319efc8d73bfd7&redirect_uri=http%3a%2f%2f54.254.161.96%2fLoveBar%2fGetWeChatInfoServlet&response_type=code&scope=snsapi_base&state=12#wechat_redirect";
						String url = "http://" + Global.ip +"/"+Global.project_name + "/MainServlet?openid=" + fromUserName; // To do 等一下，这里user_id还得弄一下
//						String url = "http://54.254.161.96/LoveBar/SetFinishedTaskServlet?user_id=7&app_name=大众点评"; // To do 等一下，这里user_id还得弄一下
						String picUrl = "http://ww3.sinaimg.cn/mw690/a761af96gw1en5i5ibfnfj20p00dw76k.jpg";
						String description = "跟伟哥一起玩应用!!";
					 	article1.setTitle(title);
						article1.setUrl(url);
						article1.setPicUrl(picUrl);
						article1.setDescription(description);
						
						List<Article> articles = new ArrayList<Article>();
						articles.add(article1);
						
						newsMessage.setArticles(articles);
						
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
						
						System.out.println("------------------ Send To Client  Xml Format ------------------");
						System.out.println(respMessage);
						System.out.println("\n");
						
						return respMessage;
						
					} else if (eventKey.equals("12")) {
						respContent = "公交查询1111菜单项被点击！";
					} else if (eventKey.equals("13")) {
						respContent = "周边搜索菜单项被点击！";
					} else if (eventKey.equals("14")) {
						respContent = "历史上的今天菜单项被点击！";
					} else if (eventKey.equals("21")) {
//						respContent = "歌曲点播菜单项被点击！";
						String openid = fromUserName;
						UserDAO userDao = new UserDAO();
						User user = userDao.getIncome(openid);
						System.out.println(user);
						String name = user.getWebchat_name();
						float totalIncome = user.getTotal_income();
						float available_income = user.getAvailable_income();
						String account = user.getAccount();
						String alipyInfo = "";
						respContent = name + "，您好\n" + "总收入：" + totalIncome + "\n可提款：" + available_income;
						String link = "<a href=\"http://" + Global.ip +"/LoveBar/WithdrawServlet?openid=" + openid + "\">我要提款</a>";
						// 如果用户提款了，则显示正在提款的金额
						float applyMoney = user.getApplyMoney();
						if (applyMoney != 0) {
							respContent += "\n" + "正提款：" + applyMoney;
						}
						
						if (account != null && account.length() != 0) {
							respContent += "\n" + "支付宝账号：" + account;	
						}
						respContent += "\n\n"+ link;
//						respContent = "nihao";
//						System.out.println(respContent);
//						return respContent;  nimei
					} else if (eventKey.equals("22")) {
						respContent = "我要提款";
					} else if (eventKey.equals("23")) {
						respContent = "邀请好友！";
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						int articleCount = 1;
						newsMessage.setArticleCount(articleCount);
						Article article1 = new Article();
						String title = "邀请好友";
						String url = "http://" + Global.ip +"/LoveBar/InviteServlet?openid=" + fromUserName;
						String picUrl = "http://" + Global.ip +"/LoveBar/img/invite.jpg";
						String description = "每邀请一个好友，都可以获得现金奖励，行动起来吧!阅读原文，查看详情^.^";
					 	article1.setTitle(title);
						article1.setUrl(url);
						article1.setPicUrl(picUrl);
						article1.setDescription(description);
						List<Article> articles = new ArrayList<Article>();
						articles.add(article1);
						newsMessage.setArticles(articles);
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
						log.info("------------------ Send To Client  Xml Format ------------------");
						log.info(respMessage);
						return respMessage;
					} else if (eventKey.equals("24")) {
						respContent = "人脸识别菜单项被点击！";
					} else if (eventKey.equals("25")) {
						respContent = "聊天唠嗑菜单项被点击！";
					} else if (eventKey.equals("31")) {
						respContent = "使用帮助";
//						respContent = "第一步：下载应用[" + "<a href=\"http://fir.im/aptest\" class=\"pure-button pure-button-primary\">捡钱</a>" + "]并打开\n"
//						respContent = "第一步：下载应用[" + "<a href=\"http://54.254.161.96/LoveBar/test.html\">捡钱</a>" + "]并打开\n"
						respContent = "第一步：下载应用[" + "<a href=\"http://mp.weixin.qq.com/mp/redirect?url=http://fir.im/aptest/install\">应用体验师</a>" + "]并打开，放后台运行\n"
//						respContent = "第一步：下载应用[" + "<a href=\"http://mp.weixin.qq.com/mp/redirect?url=http://fir.im/shimei/install#weixin.qq.com#wechat_redirect\">应用体验师</a>" + "]并打开，放后台运行\n"
								+ "第二步：点击[开始赚钱]\n"
								+ "第三步：按照要求下载，试玩5分钟\n"
								+ "第四步：获得奖励";
					} else if (eventKey.equals("32")) {
						respContent = "捡钱应用";
					} else if (eventKey.equals("33")) {
						respContent = "幽默笑话菜单项被点击！";
					}
					
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_VIEW)) {
//					request.setParameter("openid", fromUserName);
					request.setAttribute("open", fromUserName);
				} else if (eventType.equals("LOCATION")) {
					String openid = fromUserName;
					UserDAO userDao = new UserDAO();
					// Dao已经写好了，现在就是获取经纬度，然后放进去
					if (requestMap.equals(null)) {
						System.out.println("用户没有提供GPS地理位置。。。");
					} else {
						double precision = Double.valueOf(requestMap.get("Precision"));
						double latitude = Double.valueOf(requestMap.get("Latitude"));
						double longitude = Double.valueOf(requestMap.get("Longitude"));
						int GPSRes = userDao.updateGPS(longitude, latitude, precision, openid);
						System.out.println("precision:" + precision + ",latitude:" + latitude + ",longitude:" + longitude);
						if (GPSRes == 1) {
//							System.out.println("GPS 更新成功");
							log.info("GPS 更新成功");
						} else {
//							System.out.println("GPS 更新失败");
							log.info("GPS 更新失败");
						}
					}
					
				}
			}

			textMessage.setContent(respContent);
			
			respMessage = MessageUtil.textMessageToXml(textMessage);
			
			System.out.println("------------------ Send To Client ------------------");
			System.out.println(textMessage.toString());
			System.out.println("------------------ Send To Client  Xml Format ------------------");
			System.out.println(respMessage);
			System.out.println("\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}
	
	/**
	 * xiaoqrobot的主菜单
	 * 
	 * @return
	 */
	public static String getMainMenu() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("您好，有什么问题请直接留言，我们会尽快回复您^.^");
//		buffer.append("您好，我是恋爱助手，请回复数字选择服务：").append("\n\n");
//		buffer.append("1  获取外链").append("\n");
//		buffer.append("2  获取图文消息").append("\n\n");
//		buffer.append("回复“?”显示此帮助菜单");
		return buffer.toString();
	}
	
	public static String handleTextMessage(String requestMsg) {
		//2014.12.2 目前没有根据数字来回复，所以注释掉了
//		try {
//			int requestCode = Integer.parseInt(requestMsg);
//			return ReqTextService.getContentById(requestCode);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		if(isQqFace(requestMsg)) {
//			return requestMsg;
//		}
		
		return getMainMenu(); 
	}
	
	
	/**
	 * 判断是否是QQ表情
	 * 
	 * @param content
	 * @return
	 */
	public static boolean isQqFace(String content) {
		boolean result = false;

		// 判断QQ表情的正则表达式
		String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";
		Pattern p = Pattern.compile(qqfaceRegex);
		Matcher m = p.matcher(content);
		if (m.matches()) {
			result = true;
		}
		return result;
	}
}

