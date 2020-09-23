<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑图书</title>
    <%--	静态包含头 base标签，css样式，jqery文件--%>
    <%@include file="/pages/common/header.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }

        input {
            text-align: center;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif">
    <span class="wel_word">编辑图书</span>
    <%--静态包含manager管理模块的菜单--%>
    <%@ include file="/pages/common/manager_menu.jsp" %>
</div>

<div id="main">
    <c:if test="${ empty requestScope.book.id}">
    	<form action="manager/book/add.do" method="post">
    </c:if>
    <c:if test="${ !empty requestScope.book.id}">
        <form action="manager/book/edit.do" method="post">
    </c:if>

            <input type="hidden" name="pageNo" value="${param.pageNo}">
            <input type="hidden" name="pageTotal" value="${param.pageNo}">
<%--            <input type="hidden" name="action" value="${ empty requestScope.book.id  ? "add":"update"}">--%>
            <input type="hidden" name="id" value="${requestScope.book.id }">
            <table>
                <tr>
                    <td>名称</td>
                    <td>价格</td>
                    <td>作者</td>
                    <td>销量</td>
                    <td>库存</td>
                    <td colspan="2">操作</td>
                </tr>
                <tr>
                    <td><input name="name" type="text" value="${requestScope.book.name}" placeholder="请输入书名"/></td>
                    <td><input name="price" type="text" value="${requestScope.book.price}" placeholder="请输入价格"/></td>
                    <td><input name="author" type="text" value="${requestScope.book.author}" placeholder="请输入作者"/></td>
                    <td><input name="sales" type="text" value="${requestScope.book.sales}" placeholder="请输入销量"/></td>
                    <td><input name="stocks" type="text" value="${requestScope.book.stocks}" placeholder="请输入库存"/></td>
                    <td><input type="submit" value="提交"/></td>
                </tr>
            </table>
        </form>


</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>