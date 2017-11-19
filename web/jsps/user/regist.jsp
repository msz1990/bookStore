<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>Sign up</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	  <script type="text/javascript" src="jslib/jquery-3.2.1.min.js"></script>
	  <script type="text/javascript">
		  function checkUsername() {
		      	var username=document.getElementById("username").value;
				var xhr=createXmlHttpRequest();
				xhr.onreadystatechange=function () {
					if(xhr.readyState == 4){
					    if(xhr.status == 200){
					        var data= xhr.responseText;
					        if( 1 == data){
                                document.getElementById("span1").innerHTML="<font color='green'>Available</font>";
                                document.getElementById("regBut").style.display="block"
							}else if(2 == data){
					            document.getElementById("span1").innerHTML="<font color='red'>Username already exist!</font>";
					            document.getElementById("regBut").style.display="none"
							}
						}
					}
                }
                xhr.open("GET","${pageContext.request.contextPath}/userServlet?method=checkUsername&username="+username+"&time="+new Date().getDate(),true)
			  	xhr.send(null)
          }
          function  createXmlHttpRequest(){
              var xmlHttp;
              try{    //Firefox, Opera 8.0+, Safari
                  xmlHttp=new XMLHttpRequest();
              }catch (e){
                  try{    //Internet Explorer
                      xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
                  }catch (e){
                      try{
                          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
                      }catch (e){}
                  }
              }
              return xmlHttp;
          }
	  </script>

  </head>
  
  <body>
  <h1>Sign up</h1>
<p style="color: red; font-weight: 900">${msg }</p>
<form action="${pageContext.request.contextPath}/userServlet" method="post">
	<input type="hidden" name="method" value="regist"/>
	User name：<input type="text" id="username" name="username" value="" onblur="checkUsername()"/><span id="span1"></span><br/>
	Password：<input type="password" name="password"/><br/>
	Email：<input type="text" name="email" value=""/><br/>
	<input type="submit" id="regBut" value="Sign up"/>
</form>
  </body>
</html>
