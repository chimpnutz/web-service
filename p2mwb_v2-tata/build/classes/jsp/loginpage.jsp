<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>


<html>

<title>Pay PhilExchange</title>


<!-- [if lt IE 8]>  
< link rel='stylesheet' type='text/css' href='ie.css'/>  
<!  [endif] -->
<LINK href="../css/style.css" rel="stylesheet" type="text/css">
<LINK href="../css/form.css" rel="stylesheet" type="text/css">

 
<script type="text/javascript" src="../js/jquery.min.js"></script>

<script src="../js/jquery-latest.js"></script>

<script type="text/javascript" src="../js/jquery.validate.js"></script>
 
  <script>
  $(document).ready(function(){
	    $("#loginForm").validate({
		});
	});
  

  </script>

<form:form  commandName="loginForm" id="loginForm" target="_parent" style="background:#f9f9f9;" >

<div class="text12_tungsten" style="margin-left:50px; padding-top:30px">Username: <form:input path="userName" size="30" style="background-color:white;" id="username" width="100px"/>
</div> 
<div class="text12_tungsten" style="margin-left:50px; margin-top:10px;">Password: &nbsp;<form:password path="password" size="30" style="background-color:white;" id="password"/>
</div>

<div style="float:left; margin-right:270px; margin-top:10px;"><input type="image" src="../css/images/login.png" value="Sign in"  /></div>

</form:form>

<br><br>
<core:if test="${success == 'no'}">
<div id="error">
<div class="text" style="margin-top:10px; margin-left:40px;">${msg}</div>
</div>
</core:if>
<core:if test="${login == 'no'}">
<div id="error">
<div class="text" style="margin-top:10px; margin-left:40px;">Please login.</div>
</div>
</core:if>
<core:if test="${blankUser == 'yes'}">
<div id="error">
<div class="text-error" style="margin-top:10px; margin-left:40px;">${msg}</div>
</div>
</core:if>
<core:if test="${blankPass == 'yes'}">
<div id="error">
<div class="text-error" style="margin-top:10px; margin-left:40px;">${msg}</div>
</div>
</core:if>

</html>
