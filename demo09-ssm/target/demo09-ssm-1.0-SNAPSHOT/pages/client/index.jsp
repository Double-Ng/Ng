<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>哈利路亚首页</title>
    <%--	静态包含头 base标签，css样式，jqery文件--%>
    <%@include file="/pages/common/header.jsp" %>
    <script type="text/javascript">
        $(function () {
            //给购物车绑定单击事件
            $("button.addToCart").click(function () {
                //获取自定义属性bookId的值
                var bookId = $(this).attr("bookId");
                location.href = "<%=basePath%>cart/addItem.do?id=" + bookId;

                // 发Ajax请求，添加商品到购物车中
                <%--$.getJSON(--%>
                <%--    "<%=basePath%>cart/ajaxAddItem.do",--%>
                <%--    "id=" + bookId,--%>
                <%--    function (data) {--%>
                <%--        $("#cartTotalCount").text("您的购物车中有" + data.totalCount + "件商品");--%>
                <%--        // $("#cartLastName1").text("您刚刚将" + data.lastName + "加到了购物车");--%>
                <%--        $("#cartLastName").text(data.lastName);--%>
                <%--        // $("#cartLastName1").text(data.lastName);--%>
                <%--    }--%>
                <%--);--%>
            });
        });
    </script>

</head>
<body>

<div id="header">
    <a href="index.jsp"><img class="logo_img" alt="" src="static/img/logo.gif"></a>
    <span class="wel_word">网上书城</span>
    <div>
        <%--        如果用户没有登录，显示 登录和注册的菜单--%>
        <c:if test="${empty sessionScope.user}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
            <a href="pages/cart/cart.jsp">购物车</a>
            <a href="pages/manager/manager.jsp">后台管理</a>
        </c:if>

        <%-- 如果已经登录，则显示登录成功之后的用户信息--%>
        <c:if test="${not empty sessionScope.user}">
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临哈利路亚！</span>
            <%--如果是管理员，就不显示这个我的订单--%>
            <c:if test="${sessionScope.user.username !='admin'}">
                <a href="orderServlet?action=showAllOrders">我的订单</a>

            </c:if>
            <a href="pages/cart/cart.jsp">购物车</a>
            <a href="user/logout.do">注销</a>&nbsp; &nbsp;
            <c:if test="${sessionScope.user.username == 'admin'}">
                <a href="pages/manager/manager.jsp">后台管理</a>
            </c:if>
        </c:if>

    </div>
</div>

<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/page.do" method="get">
                <input type="hidden" name="action" value="page">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max == 0 ?  "":param.max}"> 元
                <input type="submit" value="查询"/>
            </form>
        </div>

        <div style="text-align: center">

<%--            购物车中没有商品，前台不再显示购物车近期信息--%>
            <c:if test="${empty sessionScope.cart.items}">
                <span id="cartTotalCount"> </span>
                <div>
                    <span style="color: red" id="cartLastName">您的购物车中没有商品</span>
                </div>

            </c:if>

            <%--             购物车中有商品，前台显示购物车近期信息--%>
            <c:if test="${not empty sessionScope.cart.items}">
                <span id="cartTotalCount">您的购物车中有${sessionScope.cart.totalCount}件商品</span>
                <div >
                    您刚刚将<span style="color: red" id="cartLastName1">${sessionScope.lastName}</span>加入到了购物车中
                </div>
            </c:if>
        </div>

        <c:forEach items="${requestScope.page.items}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${book.imgPath}"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stocks}</span>
                    </div>
                    <div class="book_add">
                        <button bookId="${book.id}" class="addToCart">加入购物车</button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <%--静态包含分页条模块--%>
    <%@include file="/pages/common/page_nav01.jsp" %>

</div>

<div id="bottom">
		<span>
			哈利路亚.Copyright &copy;2020
		</span>
</div>
</body>
</html>