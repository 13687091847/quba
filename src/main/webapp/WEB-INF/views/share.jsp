<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,minimum-scale=1.0,maximum-scale=2.0;charset=UTF-8">
<title>攻略分享</title>
<style type="text/css">
.strategy-img {
	width: 100%;
}

.userInfo {
	height: 100px;
	position: relative;
}

.userInfo img {
	margin: 15px 5px 10px 15px;
	width: 60px;
	height: 60px;
	border-radius: 30px;
}

.userInfo h {
	position: absolute;
	top: 35px;
	left: 90px;
	font-size: 18px;
}

.userInfo span {
	position: absolute;
	top: 40px;
	left: 220px;
	font-size: 13px;
}

.strategy-title {
	text-align: center;
	font-size: 23px;
}

.strategy {
	margin: 0px 5px 0px 5px;
}

.strategy p {
	font-size: 14px;
	color: #333333;
}

#download {
	position: fixed;
	top: 0px;
	left: 0px;
	width: 100%;
	height: 45px;
	background-color: lightseagreen;
	box-shadow: 0px 0px 10px #909090;
	text-align: center;
	line-height: 45px;
}

#download a {
	color: white;
	text-decoration: none;
}
</style>
</head>
<body>
	<div>
		<div id="download">
			<a href="http://www.liuhuangming.cn:8083/quba/download">点击下载“去吧”，浏览更多游记</a>
		</div>
		<img alt="" class="strategy-img" src="${strategy.imgSrc }" />
		<div class="userInfo">
			<img alt="" src="${strategy.userInfo.headImg }" id="user-img" />
			<h id="user-name">${strategy.userInfo.nickName }</h>
			<span id="create-time"><fmt:formatDate
					value="${strategy.uploadDate }" pattern="MM-dd HH:MM:SS" /></span>
		</div>
		<div class="strategy-title">
			<h id="title">${strategy.title }</h>
		</div>
		<div class="strategy">
			<p id="content">${strategy.content }</p>
		</div>
	</div>
</body>
</html>