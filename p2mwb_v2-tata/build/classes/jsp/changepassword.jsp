<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="../css/images/favicon.ico" >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="refresh" content="<%= session.getMaxInactiveInterval() %>;URL='logout.html?expire=yes'">

<title>Pay PhilExchange</title>


<LINK href="../css/style.css" rel="stylesheet" type="text/css">
<LINK href="../css/form.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui-1.8.18.custom.min.js"></script>

<script src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
 

<link href="../css/unibar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/header.js"></script>
<script type="text/javascript" src="../js/dropdown.js"></script>

</head>

<body>

<div id="page">

<div id="unibarlogo"><a id="main" href="logout.html?redirect=main"><img src="../css/images/payexchangeinc.gif" alt="Pay Phil Exchange" border="0" style="margin-left:-5px;" /></a></div>

<div id="unibar">

<div class="unibarnav" id="payphilexchange">
<ul>
	<li><a href="#" rel="dropmenu1">About</a> |</li>
	<li><a id="prod" href="logout.html?redirect=products">Products & Services</a> |</li>
	<li><a id="sol" href="logout.html?redirect=solutions">Solutions Development</a> |</li>
    <li><a id="part" href="logout.html?redirect=partners">Partners</a></li>

</ul>
</div>

<!--1st drop down menu -->                                                   
<div id="dropmenu1" class="unibar_dropmenu">
<a id="lead" href="logout.html?redirect=leadership">Leadership</a>
<a id="car" href="logout.html?redirect=careers">Careers</a>
<a id="cont" href="logout.html?redirect=contact">Contact us</a>
</div>

<script type="text/javascript">

cssdropdown.startchrome("payphilexchange")

</script>

</div>



<div id="wrapper"><br>

 <core:import url="menu.jsp"></core:import>

<div style="clear:both"></div>
<div id="container">
<div id="content">
<div style="clear:both"></div>

	<core:forEach var="data" items="${records}">			
	
   <core:if test="${changeotherpass == 'yes'}">
   
   
   <core:if test="${user == 'user'}">

	<br> 
	
	<center>Cannot Access Page </center>
		
	<br> <br>
	
	</core:if>
   
   	<core:if test="${user == 'manager'}">
   	
   	<p class="text18_tungsten">Change Password</p>
	
 	 <core:if test="${status == 'fail'}">

	<div class="text12_red">${msg}</div>
	
	</core:if>
	
	<core:if test="${status == 'success'}">

	<div class="text12_red">${msg}</div>
	
	</core:if>
	
	
   	
   	<table width="90%" border="0" cellspacing="3" cellpadding="3">
   	
  	<form:form action=""  commandName="changePassword" id="changepasswordForm">
   	
    <tr>
    
    <td width="150" class="text12_tungsten">Username</td>
    <td><input type="text" style="background-color:white;" size="40"  value="${data.username}" disabled="disabled"/></td>
   	<form:hidden path="username" value="${data.username}"/>
   	
   </tr>	
   
	<tr>
	 
	<td class="text12_tungsten"><label for="pass1">New Password</label></td>
	<td><form:password path="newpass" style="background-color:white;" size="40" id="newpass"/>
	<span id="confirmMessage" class="confirmMessage"></span></td>
	
	</tr>
	
    <tr>
    
    <td class="text12_tungsten"><label for="pass1">Confirm New Password</label></td>
    <td><form:password path="newpass2" style="background-color:white;" size="40" id="newpass2"/></td>   
   
    </tr>
    

    
         <tr>
     <td>&nbsp;</td>
     <td><input type="image" value="Submit" src="../css/images/submit2_button.png" style="margin-left:110px;" name="changeotherpass"/></td>
     </tr>
     
    </form:form>	
	</table>
 	
    
    </core:if>

 	 </core:if>	
 	 
 	 
 	 
 	 
 	 
 	 
 	 <core:if test="${changemypass == 'yes'}">
 	 
 	<span class="text18_tungsten">Change Password</span>
 	
 	<br> <br>
 	 
 	<core:if test="${status == 'fail'}">

	<span class="text12_red">${msg}</span>
	
	</core:if>
	
	<core:if test="${status == 'success'}">

	<span class="text12_red">${msg}</span>
	
	</core:if>
	
	<br> 
	
 	<table width="90%" border="0" cellspacing="3" cellpadding="3">
  	<form:form action=""  commandName="changePassword" id="changepasswordForm">
 	 
 	 <form:hidden path="username" value="${data.username}"/>
 	
 	 <tr>
 	 <td width="150" class="text12_tungsten">Old Password</td>
 	 <td><form:password path="oldpass" style="background-color:white;" size="40" id="oldpass"/></td>
 	 </tr>
 	 
 	 <tr>
 	 <td width="150" class="text12_tungsten">New Password</td>
 	 <td><form:password path="newpass" style="background-color:white;" size="40" id="newpass"/></td>
 	 </tr>
 	 
 	 <tr>
 	 <td width="150" class="text12_tungsten">Confirm New Password</td>
 	 <td><form:password path="newpass2" style="background-color:white;" size="40" id="newpass2"/></td>
 	 </tr>
 	 

 	 
 	    <tr>
     <td>&nbsp;</td>
     <td><input type="image" value="Submit" src="../css/images/submit2_button.png" style="margin-left:200px;" name="changemypass"/></td>
     </tr>
     
     </form:form>	
	 </table>
 	
 	 
     </core:if>	
     
	 </core:forEach>


</div>
</div>

<div style="clear:both"></div>
<div id="footer"></div>
</div><!--wrapper-->
</div><!--page-->

</body>



</html>