<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 11356
  Date: 2020-03-28
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="page_nav">

    <%--判断当前页码是否大于1，大于则显示首页和上一页这两个按钮，如果等于1则不显示。--%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1}">上一页</a>
    </c:if>


    <%--页码输出的开始--%>
    <c:choose>
        <%--情况 1： 如果总页码小于等于 5 的情况， 页码的范围是： 1-总页码--%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:set var="begin" value="1" />
            <c:set var="end" value="${requestScope.page.pageTotal}" />
        </c:when>
        <%--情况 2： 总页码大于 5 的情况--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%--小情况 1： 当前页码为前面 3 个： 1， 2， 3 的情况， 页码范围是： 1-5.--%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1" />
                    <c:set var="end" value="5" />
                </c:when>
                <%--小情况 2： 当前页码为最后 3 个， 8， 9， 10， 页码范围是： 总页码减 4 - 总页码--%>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3 }">
                    <c:set var="begin" value="${requestScope.page.pageTotal - 4}" />
                    <c:set var="end" value="${requestScope.page.pageTotal}" />
                </c:when>
                <%--小情况 3： 4， 5， 6， 7， 页码范围是： 当前页码减 2 - 当前页码加 2--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo - 2}" />
                    <c:set var="end" value="${requestScope.page.pageNo + 2}" />
                </c:otherwise>

            </c:choose>

        </c:when>

    </c:choose>

    <%--    获取上方所赋值的begin和end--%>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <%-- 如果当前页码正好是request域中page对象的pageNo那么就显示【】--%>
        <c:if test="${i == requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <%-- 如果不是，则显示正常的页码--%>
        <c:if test="${i != requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <%--判断当前页码是否小于最大页码，小于则显示末页和下一页这两个按钮，如果等于最大页数则不显示。--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

     <%--抽取Session域中的pageTotal和totalCount显示到前台--%>
    共${ requestScope.page.pageTotal }页， ${ requestScope.page.totalCount }条记录

    到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
    <input type="button" id="searchPageBtn" value="确定">
    <script type="text/javascript">
        $(function () {
            $("#searchPageBtn").click(function () {
                //获取文本输入框中的内容
                var pageNo = $("#pn_input").val();
                //请求page方法。basePath是我们写好的工程路径，这个不细说。看之前的笔记
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
            });
        });
    </script>


</div>