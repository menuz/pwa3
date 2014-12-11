<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.shike.util.Global" %>

<html>
<head>
    <meta charset="utf-8" />
    <title>我的信息</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
        name="viewport" />
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta content="telephone=no" name="format-detection" />
    <link href="css/common.css" rel="stylesheet" type="text/css" />
</head>
<body class="info">
    <div class="info-user box p18 mt12">
        <img class="iu-icon" src="http://wx.qlogo.cn/mmopen/Qzr0ItOW9C4FHpt1U1picm9pibG29RzbUmoPFP93zn3Te5chboMmLRaeQmlKAX68DBALuyMy5jDIIwqT6aL6TDN2j3BrsZoicf9/0" width="74px"
            height="74px;">
        <div class="ml24">
            <ul class="info-detail g6 fs-xl unstyled ml12">
                <li class="mt5 nowrap">ID号: </li> 
                <li class="mt5 nowrap">昵称: </li> 
                <li class="mt5 nowrap">地区: </li> 
                <li class="mt5 nowrap">性别: </li>
            </ul>
        </div>
    </div>
    <div class="box p12 mt8">
        <table class="info-data table table-bordered g6 tac">
            <tr align="center">
                <td>
                    赚取收入
                </td>
                <td>
                   
                </td>
                <td>
                    <a href="recode.aspx?state=downin&oid=">详情</a> 
                </td>
            </tr>
             <tr align="center">
                <td>
                    邀请收入
                </td>
                <td>
                    
                </td>
                 <td>
                    <a href="recode.aspx?state=tuiguang&oid=">详情</a> 
                </td>
            </tr>
             <tr align="center">
                <td>
                    邀请人数
                </td>
                <td>
                    
                </td>
                 <td>
                    <a href="recode.aspx?state=person&oid=">详情</a> 
                </td>
            </tr>
             <tr align="center">
                <td>
                    已兑积分
                </td>
                <td>
                   
                </td>
                 <td>
                    <a href="recode.aspx?state=gift&oid=">详情</a> 
                </td>
            </tr>
             <tr align="center">
                <td>
                    账户余额
                </td>
                <td>
                   
                </td>
                 <td>
                   
                </td>
            </tr>
        </table>
    </div>
</body>
</html>