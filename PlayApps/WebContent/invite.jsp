<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.shike.util.Global" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<style type="text/css">
div#bg {
    background-color: rgb(255, 248, 178);
	width: 100%;
	height: 100%;
}
img {
	width: 100%;
}
div#next {
	background: white;
	width: 30%;
	margin: 39px auto;
	position: relative;
	font-size: large;
	border-radius: 30px;
	padding: 25px 15px;
}
p#word {
	background: white;
	margin: 0px auto;
	position: relative;
	font-size: large;
}
</style>
<script type="text/javascript">
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
    // 发送给好友
    WeixinJSBridge.on('menu:share:appmessage', function (argv) {
        WeixinJSBridge.invoke('sendAppMessage', {
            "appid": "123",
            "img_url": "http://115.29.165.234/LoveBar/img/invite.jpg",
            "img_width": "160",
            "img_height": "160",
            "link": "http://115.29.165.234/LoveBar/invite.jsp",
            "desc":  "关注应用体验师，下载手机应用还可以赚钱，一个月赚几百元妥妥的!^.^",
            "title": "快来加入吧！"
        }, function (res) {
            _report('send_msg', res.err_msg);
        })
    });

    // 分享到朋友圈
    WeixinJSBridge.on('menu:share:timeline', function (argv) {
        WeixinJSBridge.invoke('shareTimeline', {
            "img_url": "http://115.29.165.234/LoveBar/img/invite.jpg",
            "img_width": "160",
            "img_height": "160",
            "link": "http://115.29.165.234/LoveBar/invite.jsp",
            "desc":  "关注应用体验师，下载手机应用还可以赚钱，一个月赚几百元妥妥的!^.^",
            "title": "快来加入吧！"
        }, function (res) {
            _report('timeline', res.err_msg);
        });
    });
}, false)
</script>
</head>
<body>
<div id="bg">
<img src="img/invite.jpg", alt="invite friend"/>
<div id="next">
	<p id="word">
		快来加入吧，搜索“itryapp”，关注“应用体验师”，每个月可以赚取几百块钱零花钱
	</p>
</div>
</div>
</body>
</html>