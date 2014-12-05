<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Document</title>
<link rel="stylesheet" href="css/user.css">
</head>
<body>
	<form action="SaveAlipyInfoServlet" method="get">
		<div class="box">
			<div class="input-wrapper">
				<label class="icon">支付宝账号</label>
				<p class="input-box">
				<input type="text" id="account" name="account" placeholder="请填写实名认证账号"></input>
				</p>
			</div>
			<div class="input-wrapper">
				<label class="icon">收款人姓名</label>
				<p class="input-box">
				<input type="text" id="name" name="name" placeholder="请填写真实姓名"></input>
				</p>
			</div>
			<input type="hidden" name="openid" value=${openid}></input>
		</div>
		<li id="note">支付宝需实名认证，否则无法提现成功</li>
		<input type="submit" name="button" id="button" value="提交"/>
	</form>
</body>
</html>