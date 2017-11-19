<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>图书列表</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <style type="text/css">
        body {
            font-size: 10pt;
        }

        .icon {
            margin: 10px;
            border: solid 2px gray;
            width: 160px;
            height: 180px;
            text-align: center;
            float: left;
        }
    </style>
</head>

<body>
<c:forEach var="b" items="${pageBean.list}">
    <div class="icon">
        <a href="${pageContext.request.contextPath}/bookServlet?method=findByBid&bid=${b.bid}"><img src="${pageContext.request.contextPath}/${b.image}"
                                                            width="130" height="140" border="0"/></a>
        <br/>
        <a href="${pageContext.request.contextPath}/bookServlet?method=findByBid&bid=${b.bid}">${b.bname}</a>
    </div>
</c:forEach>
<div style="position:absolute;left:0px;top:430px;">
    Page ${pageBean.currPage}/${pageBean.totalPage}
    <c:if test="${pageBean.totalPage ne 1}">
        <a href="${pageContext.request.contextPath}/bookServlet?method=findByPage&currPage=1">Fist page</a>
        <c:if test="${pageBean.currPage ne 1}">
            <a href="${pageContext.request.contextPath}/bookServlet?method=findByPage&currPage=${pageBean.currPage-1}">Previous</a>
        </c:if>
        <c:if test="${pageBean.currPage ne pageBean.totalPage}">
            <a href="${pageContext.request.contextPath}/bookServlet?method=findByPage&currPage=${pageBean.currPage+1}">Next</a>
        </c:if>
        <a href="${pageContext.request.contextPath}/bookServlet?method=findByPage&currPage=${pageBean.totalPage}">Last page</a>
    </c:if>
</div>


</body>

</html>

