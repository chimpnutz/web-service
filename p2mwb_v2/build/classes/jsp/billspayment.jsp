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

<LINK href="../css/demo1.css" rel="stylesheet" type="text/css">
<LINK href="../css/style.css" rel="stylesheet" type="text/css">
<LINK href="../css/form.css" rel="stylesheet" type="text/css">
<LINK href="../css/font.css" rel="stylesheet" type="text/css">

<script src="../js/jquery-latest.js"></script>

<script type="text/javascript" src="../js/jquery.validate.js"></script>

<script src="../js/billspayment.js"></script>

<script type='text/javascript' src='../js2/jquery.simplemodal.js'></script>

<script type="text/javascript" src="../js/date.js"></script>


<script type="text/javascript" src="../js/jquery.datePicker.js"></script>

<link rel="stylesheet" type="text/css" media="screen" href="../css/datePicker.css">
  
<link href="../css/unibar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/header.js"></script>
<script type="text/javascript" src="../js/dropdown.js"></script>



<style type="text/css">

#basic-modal-content {display:none;}

/* Overlay */
#simplemodal-overlay {background-color:#000; cursor:wait;}

/* Container */
#simplemodal-container {height:auto;  width:850px; color:#cccccc;}
#simplemodal-container .simplemodal-data {padding:8px;}
#simplemodal-container code {background:#141414; border-left:3px solid #65B43D; color:#bbb; display:block; font-size:12px; margin-bottom:12px; padding:4px 6px 6px;}
#simplemodal-container a {color:#ddd;}
#simplemodal-container a.modalCloseImg {background:url(../images/x.png) no-repeat; width:25px; height:29px; display:inline; z-index:3200; position:absolute; top:-15px; right:-16px; cursor:pointer;}
#simplemodal-container h3 {color:#84b8d9;}

</style>


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
<p class="text18_tungsten">Bills Payment</p>

<core:if test="${user == 'viewer'}">

<br>

<center>Cannot Access Page</center>

<br>
<br>


</core:if>


<core:if test="${user == 'manager' || user == 'user'}">


<p class="text12_tungsten">Your current balance is: <span class="text15_red"> PHP ${currentwallet} </span></p>



<div class="text10_red" id="result">${result}</div>



<div class=text12_tungsten style='float left; margin-top: 27px;'>Biller</div><div style = 'float: left; margin-top:-17px; margin-left:191px;'><select style="width: 206px; text-align:left;" id=biller class=biller></select></div>

<div id = "billerfield" class = "billerfield"></div>


<div id="basic-modal-content" class="basic-content">


		 <center><img src="../css/images/loader.gif" width="37" height="38" border="0"/></center>


</div>



<br>




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