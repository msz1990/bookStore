<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Cart</title>
    
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
		margin:20px;
		border: solid 2px gray;
		width: 150px;
		height: 150px;
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
<h1>Cart</h1>
<c:if test="${fn:length(cart.cartItems)!=0}">
	<table border="1" width="100%" cellspacing="0" background="black">
		<tr>
			<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
				<a href="${pageContext.request.contextPath}/cartServlet?method=clearCart">Clear Cart</a>
			</td>
		</tr>
		<tr>
			<th>Snapshot</th>
			<th>Title</th>
			<th>Author</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Total</th>
			<th></th>
		</tr>

		<c:forEach items="${cart.cartItems}" var="cartItem">
			<tr>
				<td><div><img src="${pageContext.request.contextPath}/${cartItem.book.image}" width="130" height="140"/></div></td>
				<td>${cartItem.book.bname}</td>
				<td>${cartItem.book.author}</td>
				<td>${cartItem.book.price}</td>
				<td>${cartItem.count}</td>
				<td>$${cartItem.subtotal}</td>
				<td><a href="${pageContext.request.contextPath}/cartServlet?method=removeCart&bid=${cartItem.book.bid}">Remove</a></td>
			</tr>
		</c:forEach>

		<tr>
			<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
				Total：$${cart.total}
			</td>
		</tr>
		<tr>
			<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
				<a id="buy" href="${pageContext.request.contextPath}/orderServlet?method=saveOrder"></a>
			</td>
		</tr>
	</table>
</c:if>
<c:if test="${fn:length(cart.cartItems)==0}">
	<h3>Cart is empty! <a href="${pageContext.request.contextPath}/bookServlet?method=findByPage&currPage=1">Buy Now</a>! </h3>
	<img src="${pageContext.request.contextPath}/images/cart.png" width="300" height="350">
</c:if>

  </body>
</html>
