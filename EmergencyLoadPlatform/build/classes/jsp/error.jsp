<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

<core:if test="${username == null}">

   <div id="left-sidebar">
 	<img src="../css/images/companylogo.png" style="margin-left:12px; margin-top:5px;"/><hr style="margin:6px 10px; color:#cccccc;">
   </div>

   <div id="content">
 
  <core:if test="${error != null}">

<h2>Internal Server Error.</h2>

<div>The server encountered an internal error or misconfiguration and was 
<br> unable to complete your request</div>

<br>

<div>Please contact the server administrator, info@mobilepayplus.net and
<br> inform them of the time the error occured, and anything you might have
<br> done that may have caused the error.</div>

<br>

<div>More information about this error may be available in the server error log.</div>

</core:if>

	</div>
	
</core:if>



<core:if test="${error == null}">

<div class="text">Page Not Found.</div>

</core:if>
	



</div><!----- End Of Container----->
<div id="footer"></div>
</div><!----- End of PAGE ----->


</body>
</html>
