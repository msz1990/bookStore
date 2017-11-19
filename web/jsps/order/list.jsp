<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Order List</title>
    
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
	* {
		font-size: 11pt;
	}
	div {
		border: solid 2px gray;
		width: 75px;
		height: 75px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	
	#buy {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -902px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#buy:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -938px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>
  </head>
  
  <body>
<h1>Order</h1>

<table border="1" width="100%" cellspacing="0" background="black">
<c:forEach items="${list}" var="order">
	<tr bgcolor="gray" bordercolor="gray">
		<td colspan="6">
			Order Id：${order.oid}　Transaction Time：${order.ordertime}　Price：<font color="red"><b>${order.total}</b></font>　
			<c:if test="${order.state ==1}">
					<a href="${pageContext.request.contextPath}/orderServlet?method=findByOid&oid=${order.oid}">Make payment</a>
			</c:if>
			<c:if test="${order.state ==2}">
					Delivering
			</c:if>
			<c:if test="${order.state ==3}">
					<a href="javascript:alert('已确认收货！');">Receive Item</a>
			</c:if>
			<c:if test="${order.state ==4}">
					Complete
			</c:if>
		</td>
	</tr>
	<c:forEach items="${order.orderItems}" var="orderItem">
	<tr bordercolor="gray" align="center">
		<td width="15%">
			<div><img src="${pageContext.request.contextPath}/${orderItem.book.image}" width="65" height="70"/></div>
		</td>
		<td>Book Title：${orderItem.book.bname}</td>
		<td>Price：${orderItem.book.price}</td>
		<td>Author：${orderItem.book.author}</td>
		<td>Items：${orderItem.count}</td>
		<td>Total：$${orderItem.subtotal}</td>
	</tr>
	</c:forEach>
</c:forEach>

</table>
  </body>
</html>
