<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<link rel="stylesheet" href="css/showAdvs.css">
<title></title>
<script type="text/javascript">
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
    // 发送给好友
    WeixinJSBridge.on('menu:share:appmessage', function (argv) {
        WeixinJSBridge.invoke('sendAppMessage', {
            "appid": "123",
            "img_url": "https://mmbiz.qlogo.cn/mmbiz/MYdb1onCeh7xiarIp5MQhxEbU8ssSqs4SHyWicmuFZsBAUJiczk8jtv8YdibLZjU9ib8bcCNzeqFiaO3JcU5oQiay2wJg/0",
            "img_width": "160",
            "img_height": "160",
            "link": "http://115.29.165.234/LoveBar/ShowAdvsServlet?openid=o_gjGszWb7ZPhGECYUj280PCzHnA",
            "desc":  "来玩玩看吧,关注应用体验师，每个月都可以赚几百块钱哦",
            "title": "快来加入吧"
        }, function (res) {
            _report('send_msg', res.err_msg);
        })
    });
 // 分享到朋友圈
    WeixinJSBridge.on('menu:share:timeline', function (argv) {
        WeixinJSBridge.invoke('shareTimeline', {
            "img_url": "https://mmbiz.qlogo.cn/mmbiz/MYdb1onCeh7xiarIp5MQhxEbU8ssSqs4SHyWicmuFZsBAUJiczk8jtv8YdibLZjU9ib8bcCNzeqFiaO3JcU5oQiay2wJg/0",
            "img_width": "160",
            "img_height": "160",
            "link": "http://115.29.165.234/LoveBar/ShowAdvsServlet?openid=o_gjGszWb7ZPhGECYUj280PCzHnA",
            "desc":  "来玩玩看吧,关注应用体验师，每个月都可以赚几百块钱哦",
            "title": "快来加入吧"
        }, function (res) {
            _report('timeline', res.err_msg);
        });
    });
}, false)

</script>

</head>
<body>
	<c:if test="${isAvailable==0}"></c:if>
		<a href="http://mp.weixin.qq.com/mp/redirect?url=http://fir.im/aptest/install" id="link">
	<div id="download">
		<div class="box" id="box1" >
			<div id="rel">
				<img src="img/logo_1.png" alt="card" id="card"/>
				<label id="word1"></label>
				<label id="word2">解锁其他应用</label>
			</div>
			<p id="reward">免费下载</p>
		 </div>
	</div>
	</a>
	<div id="bg">
	</div>
	

<c:forEach var="app" items="${requestScope.arrayList}">
	<!--  
		2014.12.2下面是临时方案，直接将ios下载过来，android的话，由那边处理
	<c:if test="${os_type=='a'}">
		<a href="ShowDetailServlet?app_id=${app.app_id}&user_id=${user_id}&os_type=${os_type}" id="link">
	</c:if>
	<c:if test="${os_type=='i'}">
		<a href="http://mp.weixin.qq.com/mp/redirect?url=${app.url}" id="link">
	</c:if>
	2014.12.2其实都可以直接下载了
	-->
	<a href="http://mp.weixin.qq.com/mp/redirect?url=${app.url}" id="link">
	<div class="box" id="box1" >
		<div id="rel">
			<img src="image/${app.image}" alt="card" id="card"/>
			<label id="word1">${app.keyword}</label>
			<label id="word2">${app.comment}</label>
		</div>
		
		<p id="reward">
		<c:if test="${app.outcome<0.1}">
		免费下载
		</c:if>
		<c:if test="${app.outcome>0.1}">
		奖励${app.outcome}元
		</c:if>
		</p>
	 </div>
	 </a>
  </c:forEach>
</body>
</html>