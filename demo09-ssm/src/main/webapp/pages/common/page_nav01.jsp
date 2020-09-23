<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ng
  Date: 2020/5/23
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="page_nav">

<%--当前页码没在第一页时 显示首页和上一页按钮   --%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>&nbsp;
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1}">上一页</a>
    </c:if>

    <c:choose>
        <%-- 总页数小于等于5页 全部同时显示 --%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>

        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
<%--                当前页码位于前3页 则显示1-5页       --%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
<%--                当前页码位于后3页 则显示最后5页       --%>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal - 4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>
<%--                其他情况 显示当前页码的后2页         --%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo - 2}"/>
                    <c:set var="end" value="${requestScope.page.pageNo + 2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    &nbsp;当前
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i != requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}"> ${i}</a>&nbsp;
        </c:if>
    </c:forEach>
    页&nbsp;
<%--    当前页面不是最后一页时 显示最后一页和下一页按钮    --%>
    <c:if test="${requestScope.page.pageNo != requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}">下一页</a>&nbsp;
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">最后一页</a>&nbsp;
    </c:if>

    共${requestScope.page.pageTotal}页 ${requestScope.page.totalCount}条记录&nbsp;
    跳转第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input">页
    <input type="submit" id="searchPageBtn" value="确定">

    <script type="text/javascript">
        $(function(){
            $("#searchPageBtn").click(function () {
                var pageNo = $("#pn_input").val();
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
            })
        })
    </script>

</div>

