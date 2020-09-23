<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%--	静态包含头 base标签，css样式，jqery文件--%>
	<%@include file="/pages/common/header.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
			<a href="index.jsp"><img class="logo_img" alt="" src="static/img/logo.gif" ></a>

				<%--静态包含，登录成功以后的菜单--%>
				<%@include file="/pages/common/login_success_common.jsp" %>
		</div>
		
		<div id="main">
		
			<h1>欢迎回来 <a href="index.jsp">转到主页</a></h1>
	
		</div>
		

</body>
</html>