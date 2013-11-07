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
<LINK href="../css/font.css" rel="stylesheet" type="text/css">
<link href="../css/unibar.css" rel="stylesheet" type="text/css" />
 

<script src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script src="../js/bulkoffers.js"></script>
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
<div id="container3">
<div id="content">
<p class="text18_tungsten">LOP</p>

<core:if test="${user == 'viewer'}">

<br>

<center>Cannot Access Page</center>

<br>
<br>


</core:if>


<core:if test="${user == 'manager' || user == 'user'}">


<p class="text12_tungsten">Your current balance is: <span class="text15_red"> PHP ${currentwallet} </span></p>

<form:form action=""  commandName="lopForm" id="lopForm">


<div class="text10_red">${msg}</div>



<table width="70%" border="0" cellspacing="3" cellpadding="3">

  <tr>
    <td class="text12_tungsten" style="text-align: left;">Amount</td>
    <td style="float:left;"><form:input path="amount" size="30" style="background-color:white;" id="amount" class="amount" /></td>
    
    				<form:hidden path="pid" value='<%= session.getAttribute( "PID" ) %>' />
					<form:hidden path="bid" value='<%= session.getAttribute( "BID" ) %>'  />
    </tr>
	
  <tr>
    <td>&nbsp;</td>
    <td style="float:left;"><input type="image" value="Submit" src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif" align="left" style="margin-right:7px;" style="margin-left:140px;"/></td>
    </tr>
</table>

</form:form>

<br><br /><br />



</core:if>



</div>

</div>

<div style="clear:both"></div>

<div id="footer"></div>

</div><!--wrapper-->

</div><!--page-->

</body>

</html>