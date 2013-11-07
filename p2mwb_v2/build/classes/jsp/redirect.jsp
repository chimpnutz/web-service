<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<core:if test="${logoutRed == 'yes'}">
<meta http-equiv="refresh" content="5;url=http://www.payexchangeinc.com/partners.html"">
</core:if>
  <SCRIPT language="JavaScript"> 
	<!--
	 function getgoing()
  {
    top.location="http://www.payexchangeinc.com/partners.html";
   }
 

//--> 
</SCRIPT>
</head>


<core:if test="${logout == 'yes'}">
 <core:redirect url="loginpage.html"></core:redirect>
</core:if>

<core:if test="${login == 'yes'}">
<body onload="getgoing()">
</body>
</core:if>




<core:if test="${logoutRed == 'yes'}">
<title>Session Expire</title>

Session Expire.

Redirecting!!


</core:if>






</html>