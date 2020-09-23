<%--
  Created by IntelliJ IDEA.
  User: 11356
  Date: 2020-03-25
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
//  通过request域对象，动态获取base标签
    String basePath = request.getScheme() //获取请求的协议
                        + "://"
                        + request.getServerName()  //获取请求的服务器 ip 或域名
                        + ":"
                        + request.getServerPort()  //获取请求的服务器端口号
                        + request.getContextPath() + "/";  //获取当前工程路径
%>
<!--写 base 标签， 永远固定相对路径跳转的结果-->
<base href="<%=basePath%>">
<b><%=basePath%></b>
<%--将公共的css和jqery标签抽取出来--%>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>