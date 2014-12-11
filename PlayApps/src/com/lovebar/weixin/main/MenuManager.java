package com.lovebar.weixin.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lovebar.pojo.AccessToken;
import com.lovebar.weixin.pojo.Button;
import com.lovebar.weixin.pojo.CommonButton;
import com.lovebar.weixin.pojo.ComplexButton;
import com.lovebar.weixin.pojo.Menu;
import com.lovebar.weixin.pojo.ViewButton;
import com.lovebar.weixin.util.WeiXinUtil;
import com.shike.util.Global;

/**
 * 菜单管理器类
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = new Global().appId;
		// 第三方用户唯一凭证密钥
		String appSecret = new Global().appSecret;

		// 调用接口获取access_token
		AccessToken at = WeiXinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// 调用接口创建菜单
			int result = WeiXinUtil.createMenu(getMenu(), at.getToken());

			// 判断菜单创建结果
			if (0 == result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败，错误码：" + result);
		}
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("开始赚钱");
		btn11.setType("click");
		btn11.setKey("11");
	
		CommonButton btn12 = new CommonButton();
		btn12.setName("邀请好友");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("排行榜");
		btn13.setType("click");
		btn13.setKey("13");

		CommonButton btn14 = new CommonButton();
		btn14.setName("收入查询");
		btn14.setType("click");
		btn14.setKey("14");

		CommonButton btn21 = new CommonButton();
		btn21.setName("我的信息");
		btn21.setType("click");
		btn21.setKey("21");

		ViewButton btn22 = new ViewButton();
		btn22.setName("我要提款");
		btn22.setType("view");
		btn22.setUrl("http://54.254.161.96/LoveBar/WithdrawServlet");

		CommonButton btn23 = new CommonButton();
		btn23.setName("邀請好友");
		btn23.setType("click");
		btn23.setKey("23");

		CommonButton btn24 = new CommonButton();
		btn24.setName("试客社区");
		btn24.setType("click");
		btn24.setKey("24");

		// CommonButton btn25 = new CommonButton();
		// btn25.setName("聊天唠嗑");
		// btn25.setType("click");
		// btn25.setKey("25");

		CommonButton btn31 = new CommonButton();
		btn31.setName("使用帮助");
		btn31.setType("click");
		btn31.setKey("31");

		CommonButton btn32 = new CommonButton();
		btn32.setName("捡钱应用");
		btn32.setType("click");
		btn32.setKey("32");

		CommonButton btn33 = new CommonButton();
		btn33.setName("关于我们");
		btn33.setType("click");
		btn33.setKey("33");

		// ViewButton btn34 = new ViewButton();
		CommonButton btn34 = new CommonButton();
		btn34.setName("商务合作");
		btn34.setType("click");
		btn34.setKey("34");
		// btn34.setType("view");
		// btn34.setUrl("http://www.qq.com/");

		CommonButton btn35 = new CommonButton();
		btn35.setName("联系客服");
		btn35.setType("click");
		btn35.setKey("35");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("开始赚钱");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13, btn14 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("用戶中心");
		mainBtn2.setSub_button(new Button[] { btn21, btn23});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("更多");
		mainBtn3.setSub_button(new Button[] {btn32, btn34});

		/**
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
//		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });
		menu.setButton(new Button[] { btn11, mainBtn2, btn31 });

		return menu;
	}
}
