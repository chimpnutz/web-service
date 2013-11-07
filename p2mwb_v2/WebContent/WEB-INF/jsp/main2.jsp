<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>

<html>

<head>
<link rel="shortcut icon" href="../css/images/favicon.ico" >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"><meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />

  
<meta http-equiv="refresh" content="<%= session.getMaxInactiveInterval() %>;URL='logout.html?expire=yes'">

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


<div id="wrapper">
<br />

<core:if test="${username != null}">
<div align="center">
<table align="center" style="width:900px; ">
	<tr>
		<td width="300" class="smalltext" style="float:left; text-align: left;">Hi <span class="smalltext_orange">${username}, </span> you're logged in as 
		
		<core:if test="${user == 'user'}">
		
		<font color="silver">${usertype}</font>
		
		</core:if>  
		
		<core:if test="${user == 'manager'}">
		
		<font color="#2b6991">${usertype}</font>
	
		</core:if>  
		
		</td>
        <td width="300" class="smalltext" valign="top" style="text-align:right;"><a href="logout.html"><img title="logout" onmouseover="this.src='../css/images/logout-on.png';" onmouseout="this.src='../css/images/logout.png';" src="../css/images/logout.png" alt="" width="32" height="32" border="0"></a></td>
	</tr>
    
</table>
<div class="smalltext" style="margin-top: -20px; margin-left: 60px; float:left;"><%= new java.text.SimpleDateFormat("MMMM dd,yyyy").format(new java.util.Date()) %></div>
<div><img style="margin-top: -29px; margin-left: 33px; float:left;" src="../css/images/user2.png" width="22" height="25" /></div>
</div>
</core:if>    




<core:if test="${user == 'user'}">


<div id="container2">
<div id="content">

  <table width="610" border="0" align="center" cellpadding="5" cellspacing="5">
    <tr>
      <td><a href="topup.html"><img title="Top up" onmouseover="this.src='../css/images/account-on_01.png';" onmouseout="this.src='../css/images/account-off_01.png';" src="../css/images/account-off_01.png" alt="" width="204" height="148" border="0"></a></td>
      <td><a href="transfercredits.html"><img title="Wallet Transfer to Retailer"onmouseover="this.src='../css/images/wallet-on_01.png';" onmouseout="this.src='../css/images/wallet-off_01.png';" src="../css/images/wallet-off_01.png" alt="" width="203" height="145" border="0"></a></td>
	  <td><a href="transactionhistory.html"><img title="Transaction History" onmouseover="this.src='../css/images/transaction-on_05.png';" onmouseout="this.src='../css/images/transaction-off_05.png';" src="../css/images/transaction-off_05.png" alt="" width="203" height="144" border="0"></a></td>
    </tr>
    <tr>
      <td><a href="changepassword.html"><img title="Change Password" onmouseover="this.src='../css/images/account-on_05.png';" onmouseout="this.src='../css/images/account-off_05.png';" src="../css/images/account-off_05.png" alt="" width="203" height="148" border="0"></a></td>     
   		<td><a href="emergencyloadhistory.html"><img title="Emergency Load "onmouseover="this.src='../css/images/emergencyload2.png';" onmouseout="this.src='../css/images/emergencyload1.png';" src="../css/images/emergencyload1.png" alt="" width="203" height="145" border="0"></a></td>		
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
      <td><a href="topup.html"><img title="Top up" onmouseover="this.src='../css/images/account-on_01.png';" onmouseout="this.src='../css/images/account-off_01.png';" src="../css/images/account-off_01.png" alt="" width="204" height="148" border="0"></a></td>
      <td><a href="transfercredits.html"><img title="Wallet Transfer" onmouseover="this.src='../css/images/wallet-on_01.png';" onmouseout="this.src='../css/images/wallet-off_01.png';" src="../css/images/wallet-off_01.png" alt="" width="204" height="145" border="0"></a></td>    
      <td><a href="emergencyload.html"><img title="Emergency Load "onmouseover="this.src='../css/images/emergencyload2.png';" onmouseout="this.src='../css/images/emergencyload1.png';" src="../css/images/emergencyload1.png" alt="" width="203" height="145" border="0"></a></td>
    </tr>
    <tr>
      <td><a href="transactioninquiry.html"><img title="Transaction Inquiry" onmouseover="this.src='../css/images/transaction-on_01.png';" onmouseout="this.src='../css/images/transaction-off_01.png';" src="../css/images/transaction-off_01.png" alt="" width="204" height="144" border="0"></a></td>
      <td><a href="transactionreports.html"><img title="Transaction Report" onmouseover="this.src='../css/images/transaction-on_03.png';" onmouseout="this.src='../css/images/transaction-off_03.png';" src="../css/images/transaction-off_03.png" alt="" width="203" height="144" border="0"></a></td>
      <td><a href="transactionhistory.html"><img title="Transaction History" onmouseover="this.src='../css/images/transaction-on_05.png';" onmouseout="this.src='../css/images/transaction-off_05.png';" src="../css/images/transaction-off_05.png" alt="" width="203" height="144" border="0"></a></td>
    </tr>
    <tr>
      <td><a href="wallettransferhistory.html"><img title="Wallet History "onmouseover="this.src='../css/images/wallet-on_05.png';" onmouseout="this.src='../css/images/wallet-off_05.png';" src="../css/images/wallet-off_05.png" alt="" width="203" height="145" border="0"></a></td>
      <td><a href="usermanagement.html"><img title="Manage Retailer" onmouseover="this.src='../css/images/account-on_03.png';" onmouseout="this.src='../css/images/account-off_03.png';" src="../css/images/account-off_03.png" alt="" width="203" height="148" border="0"></a></td>
      <td><a href="changepassword.html"><img title="Change Password" onmouseover="this.src='../css/images/account-on_05.png';" onmouseout="this.src='../css/images/account-off_05.png';" src="../css/images/account-off_05.png" alt="" width="203" height="148" border="0"></a></td>
    </tr>
    
     <tr>
      <td><a href="emergencyloadmanagement.html"><img title="Emergency Load "onmouseover="this.src='../css/images/emergencyload2.png';" onmouseout="this.src='../css/images/emergencyload1.png';" src="../css/images/emergencyload1.png" alt="" width="203" height="145" border="0"></a></td>
	  <td><a href="emergencyloadhistory.html"><img title="Emergency Load "onmouseover="this.src='../css/images/emergencyload2.png';" onmouseout="this.src='../css/images/emergencyload1.png';" src="../css/images/emergencyload1.png" alt="" width="203" height="145" border="0"></a></td>		
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
<div class="text-error" style="margin-top:10px; margin-left:40px;">* Incorrect Username or Password.</div>
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