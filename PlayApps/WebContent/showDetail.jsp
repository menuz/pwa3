<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<title></title>
<link rel="stylesheet" href="css/a.css">
</head>
<body>
  <div class="box" id="box1">
	
    <img src="image/${app.image}" alt="card" id="card"/>
    <label id="word1">${app.keyword}</label>
    <label id="word2">${app.comment}</label>
    <p id="reward">奖励${app.outcome}元</p>
  </div>



  <div class="box" id="box2">
	<div class="inner">
		<img src="img/step1.jpg" class="step"/>
		<div id="aa">${app.keyword}</div>
		<p class="word">长按虚线框，拷贝关键字</p>
	</div>
	<div class="inner">
		<div>
			<img src="img/step2.jpg" class="step"/>
		</div>
		<div>
		<img src="image/${app.search_image}" id="search_img">
		</div>
		<div>
		<p class="word" id="step2_word">跳转搜索页面，粘贴关键字搜索</p>
		</div>
	</div>
	<div class="inner">
		<div>
			<img src="img/step3.jpg" class="step"/>
		</div>
		<div>
			<img src="image/${app.image}" class="image">
		</div>
		<div>
		<p class="word" id="step2_word">在第${app.rank}个左右，找到图标点击下载</p>
		</div>
	</div>
	<form method="get" action="http://mp.weixin.qq.com/mp/redirect?url=http%3A%2F%2Fitunes.apple.com%2Fus%2Fapp%2Fid399608199%23rd">
		<button class="btn" type="submit">点我去下载</button>
	</form>
  </div>


<!-- 
  <div class="box" id="box3">
  
	<p class="box3_word">注意：<p>
	<li class="box3_li">试玩过程中务必保持【钱眼儿】后台运行</li>
	 
	<li class="box3_li">请就开试玩，否则应用将过期</li>
	<li class="box3_li">ipad用户请在跳转后选择"仅iphone"下载</li>
	<li class="box3_li">唯一微信+唯一设备+唯一苹果账号，不能混乱，否则无法获得奖励</li>
	<p class="box3_word">试玩步骤:<p>
	<p id="lastword">打开应用->微信领取任务->下载应用->试玩5分钟->等待审核->奖金到账</p>

	</ul>
	
  </div>
  -->

</body>
</html>