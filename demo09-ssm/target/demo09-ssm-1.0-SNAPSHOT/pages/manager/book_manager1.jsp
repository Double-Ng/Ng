<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%--	静态包含头 base标签，css样式，jqery文件--%>
    <%@include file="/pages/common/header.jsp" %>

    <script type="text/javascript">

        $(function () {
            $("a.deleteClass").click(function () {
                /**
                 * 点击确认删除图书 返回true
                 */
                return confirm("你真的要删除【" + $(this).parent().parent().find("td:first").text() + "】这本图书吗？")


            })
        });
    </script>


</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <%--静态包含manager管理模块的菜单--%>
    <%@ include file="/pages/common/manager_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${requestScope.page.items}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stocks}</td>
                <td><a href="manager/book/getBook.do?id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
                <td><a class="deleteClass" href="manager/book/delete.do?id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
            </tr>
        </c:forEach>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>

            <td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
        </tr>
    </table>

    <%--		&lt;%&ndash;静态包含分页条模块&ndash;%&gt;--%>
    <%@include file="/pages/common/page_nav01.jsp" %>

</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>