<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>

<html>
<head>



<!--META-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Welcome to Circles</title>

<!--STYLESHEETS-->
<link href="../css2/style.css" rel="stylesheet" type="text/css" />

<!--SCRIPTS-->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
<!--Slider-in icons-->
<script type="text/javascript">
$(document).ready(function() {
	$(".username").focus(function() {
		$(".user-icon").css("left","-48px");
	});
	$(".username").blur(function() {
		$(".user-icon").css("left","0px");
	});
	
	$(".password").focus(function() {
		$(".pass-icon").css("left","-48px");
	});
	$(".password").blur(function() {
		$(".pass-icon").css("left","0px");
	});
});
</script>

</head>
<body>

<!--WRAPPER-->
<div id="wrapper">

	<!--SLIDE-IN ICONS-->
    <div class="user-icon"></div>
    <div class="pass-icon"></div>
    <!--END SLIDE-IN ICONS-->

<!--LOGIN FORM-->
<form:form commandName="loginForm" id="loginForm" class="login-form">

	<!--HEADER-->
    <div class="header">
    <!--TITLE--><h1>Circles</h1><!--END TITLE-->
 
    </div>
    <!--END HEADER-->
	
	<!--CONTENT-->
    <div class="content">
	<!--USERNAME--><form:input path="username" id="username" class="input username" placeholder="Username"/><!--END USERNAME-->
    <!--PASSWORD--><form:password path="password" id="password" class="input password" placeholder="Password"/><!--END PASSWORD-->
    
    <core:if test="${blankUser == 'yes' }">
    <label class="font" style="color:red">${msg }</label>
    </core:if>
    
    <core:if test="${blankPass == 'yes' }">
     <label class="font" style="color:red">${msg }</label>
    </core:if>
    
    <core:if test="${login == 'no' }">
     <label class="font" style="color:red">Please Login</label>
    </core:if>
    
    </div>
    <!--END CONTENT-->
    
    <!--FOOTER-->
    <div class="footer">
    
    <!--LOGIN BUTTON--><input type="submit" name="submit" value="Login" class="button" /><!--END LOGIN BUTTON-->
 	
    </div>
    <!--END FOOTER-->

</form:form>
<!--END LOGIN FORM-->

</div>
<!--END WRAPPER-->

<!--GRADIENT--><div class="gradient"></div><!--END GRADIENT-->

</body>
</html>
