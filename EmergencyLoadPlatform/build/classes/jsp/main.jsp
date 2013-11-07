<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="refresh" content="<%= session.getMaxInactiveInterval() %>;URL='logout.html?expire=yes'">
<link rel="shortcut icon" href="../css/images/favicon.ico" >
<LINK rel="stylesheet" href="../css/style.css" type="text/css">
<LINK rel="stylesheet" href="../css/font.css" type="text/css">
<link rel="stylesheet" type="text/css" href="../css/verticalmenu.css">
<script type="text/javascript">
<!--
window.onload=montre;
function montre(id) {
var d = document.getElementById(id);
for (var i = 1; i<=10; i++) {
if (document.getElementById('smenu'+i)) {document.getElementById('smenu'+i).style.display='none';}
}
if (d) {d.style.display='block';}
}
//-->
</script>

<style type="text/css">
body {
	margin:0;
}
</style>

<title>Emergency Load</title>
</head>

<body>
<div id="page"><!----- Start of PAGE ----->
<div id="container"><!-----Start of Container ----->

<core:if test="${username != null}">
 
  <core:import url="menu.jsp"></core:import>
   
</core:if>   

	
<core:if test="${username == null}">

   <div id="left-sidebar">
 	<img src="../css/images/companylogo.png" style="margin-left:12px; margin-top:5px;"/><hr style="margin:6px 10px; color:#cccccc;">
   </div>

   <div id="content">
      <div id="login-box" class="text12_tungsten">
      
         <core:if test="${success == 'no'}">
		<div id="error">
		<div class="text10_red " style="margin-top:10px; margin-left:40px;">* Incorrect Username or Password.</div>
		</div>
		</core:if>
		<core:if test="${login == 'no'}">
		<div id="error">
		<div class="text10_red " style="margin-top:10px; margin-left:40px;">Please login.</div>
		</div>
		</core:if>
		<core:if test="${blankUser == 'yes'}">
		<div id="error">
		<div class="text10_red " style="margin-top:10px; margin-left:40px;">${msg}</div>
		</div>
		</core:if>
		<core:if test="${blankPass == 'yes'}">
		<div id="error">
		<div class="text10_red " style="margin-top:10px; margin-left:40px;">${msg}</div>
		</div>
		</core:if>

        <form:form  commandName="loginForm" id="loginForm" target="_parent" style="background:#f9f9f9;" >
  		<table width="300" border="0" cellspacing="0" cellpadding="3">
  		<tr>
    	<td>Username</td>
    	<td><form:input path="username" size="30" style="background-color:white;" id="username" width="100px"/></td>
  		</tr>
  		<tr>
    	<td>Password</td>
    	<td><form:password path="password" size="30" style="background-color:white;" id="password"/></td>
  		</tr>
  		<tr>
    	<td>&nbsp;</td>
    	<td style="float:right; margin-right:60px;"><input type="image" src="../css/images/submit_button.png" value="Sign in"  /></td>
    	</tr>
		</table><br /><br />
        </form:form>
   	</div>
  
</div>
	
</core:if>   
	



</div><!----- End Of Container----->
<div id="footer"></div>
</div><!----- End of PAGE ----->


</body>
</html>
