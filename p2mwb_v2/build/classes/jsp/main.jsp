<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>


<html>
<head>
<link rel="shortcut icon" href="../css/../css/images/favicon.ico" >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"><meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=7" /> 
<meta http-equiv="refresh" content="<%= session.getMaxInactiveInterval() %>;URL='logout.html?expire=yes'">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script>
 $(document).ready(function(){

	 $("#loginForm").validate({  				  
		});
	});
</script>

<LINK href="../css/style.css" rel="stylesheet" type="text/css">
<LINK href="../css/form.css" rel="stylesheet" type="text/css">
<title>Pay PhilExchange</title>
<link href="../css/unibar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/header.js"></script>
<script type="text/javascript" src="../js/dropdown.js"></script>
</head>

<body>
<div id="page">
<div id="unibarlogo"><a id="main" href="logout.html?redirect=main"><img src="../css/images/payexchangeinc.gif" alt="Pay PhilExchange" border="0" style="margin-left:-5px;" /></a></div>

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

<div id="wrapper">
<br />
<core:if test="${username != null}">
 <core:import url="menu.jsp"></core:import>
</core:if>    

<core:if test="${user == 'superadmin'}">
<div id="container2">
<div id="content">
 <table width="610" border="0" align="center" cellpadding="5" cellspacing="5">
    <tr>
      <td><a href="loadcustomer.jsp"><img title="Load Customer" onmouseover="this.src='../css/images/icons_on_01.png';" onmouseout="this.src='../css/images/icons_01.png';" src="../css/images/icons_01.png" alt="" width="200" height="140" border="0" /></a></td>
      <td><a href="saleshistory-customer.jsp"><img title="Sales History" onmouseover="this.src='../css/images/icons_on_06.png';" onmouseout="this.src='../css/images/icons_06.png';" src="../css/images/icons_06.png" alt="" width="200" height="141" border="0"></a></td>
      <td><a href="changepassword.jsp"><img title="Change Password" onmouseover="this.src='../css/images/icons_on_09.png';" onmouseout="this.src='../css/images/icons_09.png';" src="../css/images/icons_09.png" alt="" width="200" height="141" border="0"></a></td>
    </tr>
    
    <tr>
     <td><a href="purchaseorder-history.html"><img title="Purchase Order" onmouseover="this.src='../css/images/icons_on_07.png';" onmouseout="this.src='../css/images/icons_07.png';" src="../css/images/icons_07.png" alt="" width="200" height="141" border="0"></a></td>
    </tr>
    
  </table>
  
</div>
</div>

<div style="clear:both"></div>

</core:if>



<core:if test="${user == 'user'}">
<div id="container2">
<div id="content">
 <table width="610" border="0" align="center" cellpadding="5" cellspacing="5">
    <tr>
      <td><a href="loadcustomer.jsp"><img title="Load Customer" onmouseover="this.src='../css/images/icons_on_01.png';" onmouseout="this.src='../css/images/icons_01.png';" src="../css/images/icons_01.png" alt="" width="200" height="140" border="0" /></a></td>
       <td><a href="loadretailer-sim.html"><img title="Load Retailer" onmouseover="this.src='../css/images/icons_on_02.png';" onmouseout="this.src='../css/images/icons_02.png';" src="../css/images/icons_02.png" alt="" width="200" height="140" border="0" /></a></td>
      <td><a href="saleshistory-customer.jsp"><img title="Sales History" onmouseover="this.src='../css/images/icons_on_06.png';" onmouseout="this.src='../css/images/icons_06.png';" src="../css/images/icons_06.png" alt="" width="200" height="141" border="0"></a></td>
	  
    </tr>
    
    <tr>
          <td><a href="changepassword.jsp"><img title="Change Password" onmouseover="this.src='../css/images/icons_on_09.png';" onmouseout="this.src='../css/images/icons_09.png';" src="../css/images/icons_09.png" alt="" width="200" height="141" border="0"></a></td>
     <td><a href="purchaseorder-ordernow.html"><img title="Purchase Order" onmouseover="this.src='../css/images/icons_on_07.png';" onmouseout="this.src='../css/images/icons_07.png';" src="../css/images/icons_07.png" alt="" width="200" height="141" border="0"></a></td>
    </tr>
    
  </table>
  
</div>
</div>

<div style="clear:both"></div>

</core:if>


<core:if test="${user == 'manager'}">
<div id="container2">
<div id="content">
  <table width="610" border="0" align="center" cellpadding="5" cellspacing="5">

    <tr>
      <td><a href="loadcustomer.html"><img title="Load Customer" onmouseover="this.src='../css/images/icons_on_01.png';" onmouseout="this.src='../css/images/icons_01.png';" src="../css/images/icons_01.png" alt="" width="200" height="140" border="0" /></a></td>
      <td><a href="loadretailer-web.html"><img title="Load Retailer" onmouseover="this.src='../css/images/icons_on_02.png';" onmouseout="this.src='../css/images/icons_02.png';" src="../css/images/icons_02.png" alt="" width="200" height="140" border="0" /></a></td>
      <td><a href="webwallethistory.html"><img title="Wallet History "onmouseover="this.src='../css/images/icons_on_03.png';" onmouseout="this.src='../css/images/icons_03.png';" src="../css/images/icons_03.png" alt="" width="200" height="140" border="0" /></a></td>
    </tr>
    <tr>
      <td><a href="simtransactionhistory.html"><img title="SIM Transaction History" onmouseover="this.src='../css/images/icons_on_04.png';" onmouseout="this.src='../css/images/icons_04.png';" src="../css/images/icons_04.png" alt="" width="200" height="141" border="0"></a></td>
      <td><a href="transactioninquiry.html"><img title="Transaction Inquiry" onmouseover="this.src='../css/images/icons_on_05.png';" onmouseout="this.src='../css/images/icons_05.png';" src="../css/images/icons_05.png" alt="" width="200" height="141" border="0"></a></td>
      <td><a href="saleshistory-customer.html"><img title="Sales History" onmouseover="this.src='../css/images/icons_on_06.png';" onmouseout="this.src='../css/images/icons_06.png';" src="../css/images/icons_06.png" alt="" width="200" height="141" border="0"></a></td>
    </tr>
    <tr>
      <td><a href="purchaseorder-ordernow.html"><img title="Purchase Order" onmouseover="this.src='../css/images/icons_on_07.png';" onmouseout="this.src='../css/images/icons_07.png';" src="../css/images/icons_07.png" alt="" width="200" height="141" border="0"></a></td>
      <td><a href="manageretailer.html"><img title="Manage Retailer" onmouseover="this.src='../css/images/icons_on_08.png';" onmouseout="this.src='../css/images/icons_08.png';" src="../css/images/icons_08.png" alt="" width="200" height="141" border="0"></a></td>
      <td><a href="changepassword.html"><img title="Change Password" onmouseover="this.src='../css/images/icons_on_09.png';" onmouseout="this.src='../css/images/icons_09.png';" src="../css/images/icons_09.png" alt="" width="200" height="141" border="0"></a></td>
    </tr>
 	 <tr>
	  <td><a href="managebranches.html"><img title="Manage Branches "onmouseover="this.src='../css/images/icons_on_14.png';" onmouseout="this.src='../css/images/icons_off_14.png';" src="../css/images/icons_on_14.png" alt="" width="200" height="140" border="0" /></a></td> 	
    </tr>

    
  </table>

</div>
</div>

  	
<div style="clear:both"></div>
</core:if>


<core:if test="${user == 'viewer'}">
<div id="container2">
<div id="content">
  <table width="610" border="0" align="center" cellpadding="5" cellspacing="5">
    <tr>
      <td><a href="webwallethistory.html"><img title="Wallet History "onmouseover="this.src='../css/images/icons_on_03.png';" onmouseout="this.src='../css/images/icons_03.png';" src="../css/images/icons_03.png" alt="" width="200" height="140" border="0" /></a></td>
      <td><a href="simtransactionhistory.html"><img title="SIM Transaction History" onmouseover="this.src='../css/images/icons_on_04.png';" onmouseout="this.src='../css/images/icons_04.png';" src="../css/images/icons_04.png" alt="" width="200" height="141" border="0"></a></td>
      <td><a href="transactioninquiry.html"><img title="Transaction Inquiry" onmouseover="this.src='../css/images/icons_on_05.png';" onmouseout="this.src='../css/images/icons_05.png';" src="../css/images/icons_05.png" alt="" width="200" height="141" border="0"></a></td>
    </tr>
    <tr>
     
      <td><a href="saleshistory-customer.html"><img title="Sales History" onmouseover="this.src='../css/images/icons_on_06.png';" onmouseout="this.src='../css/images/icons_06.png';" src="../css/images/icons_06.png" alt="" width="200" height="141" border="0"></a></td>
   	  <td><a href="purchaseorder-history.html"><img title="Purchase History" onmouseover="this.src='../css/images/icons_on_07.png';" onmouseout="this.src='../css/images/icons_07.png';" src="../css/images/icons_07.png" alt="" width="200" height="141" border="0"></a></td>
      <td><a href="changepassword.html"><img title="Change Password" onmouseover="this.src='../css/images/icons_on_09.png';" onmouseout="this.src='../css/images/icons_09.png';" src="../css/images/icons_09.png" alt="" width="200" height="141" border="0"></a></td>
    </tr>
   

  </table>

</div>
</div>

  
<div style="clear:both"></div>
</core:if>




<core:if test="${hide != 'yes'}">


 <div id="login-box"><p class="title" style="margin-left:15px;">Sign in to your account</p>
<div id="login-bg">
<form:form  commandName="loginForm" id="loginForm">
<div class="text" style="margin-left:50px; padding-top:30px">Username: <form:input path="userName" size="30" style="background-color:white;" id="username" width="100px"/>
</div> 
<div class="text" style="margin-left:50px; margin-top:10px;">Password: &nbsp;<form:password path="password" size="30" style="background-color:white;" id="password"/>
</div>

<div style="float:right; margin-right:30px; margin-top:10px"><input type="image" src="../css/images/submit_button.png" value="Sign in"  /></div>
</form:form>
<br><br>
<core:if test="${success == 'no'}">
<div id="error">
<div class="text-error" style="margin-top:10px; margin-left:40px;">${msg}</div>
</div>
</core:if>
<core:if test="${login == 'no'}">
<div id="error">
<div class="text-error" style="margin-top:10px; margin-left:40px;">Please login.</div>
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

</div><!--login-box-->
</div><!--login-bg-->
<div style="clear:both"></div>


</core:if>




<div id="footer"></div>
</div><!--wrapper-->
</div><!--page-->


</body>
</html>
