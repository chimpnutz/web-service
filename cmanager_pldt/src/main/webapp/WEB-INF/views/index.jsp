<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>



<!--META-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Welcome to Circles</title>

<!--STYLESHEETS-->
<link href="resources/css2/style.css" rel="stylesheet" type="text/css" />

<!--SCRIPTS-->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/jquery2.min.js"></script>
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
<form:form name="login-form" class="login-form" modelAttribute="loginForm" action="auth" method="post">

	<!--HEADER-->
    <div class="header">
    <!--TITLE--><h1>Circles</h1><!--END TITLE-->
 	
    </div>
    <!--END HEADER-->
	
	<!--CONTENT-->
    <div class="content">
	<!--USERNAME--><form:input path="username" type="text" class="input username" placeholder="Username" /><!--END USERNAME-->
    <!--PASSWORD--><form:input path="password" type="password" class="input password" placeholder="Password" /><!--END PASSWORD-->
   	<br></br>
   	<c:if test="${validate == 'invalid' }">
 	<span style="color:red; font-size:12px">${error}</span>
 	</c:if>
 	<c:if test="${blankUser == 'yes' }">
 	<span style="color:red; font-size:12px">${msg}</span>
 	</c:if>
 	<c:if test="${blankPass == 'yes' }">
 	<span style="color:red; font-size:12px">${msg}</span>
 	</c:if>
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