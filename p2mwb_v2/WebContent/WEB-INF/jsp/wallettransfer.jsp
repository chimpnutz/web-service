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
<div align="center"><table align="center" style="width:900px; ">
	<tr>
		<td width="300" class="smalltext" style="float:left; text-align: left;"><img src="../css/images/user2.png" width="17" height="17" align="absmiddle" /> Hi <span class="smalltext_orange"><%= session.getAttribute( "USER" ) %>, </span> you're logged in as 
		
		<core:if test="${user == 'user'}">
		
		<font color="silver"><%= session.getAttribute( "USERTYPE" ) %></font>
		
		</core:if>  
		
		<core:if test="${user == 'manager'}">
		
		<font color="gold"><%= session.getAttribute( "USERTYPE" ) %></font>
	
		</core:if>  
		
		<core:if test="${usertype == 'user'}">
		
		<font color="silver"><%= session.getAttribute( "USERTYPE" ) %></font>
		
		</core:if>  
		
		<core:if test="${usertype == 'manager'}">
		
		<font color="#2b6991"><%= session.getAttribute( "USERTYPE" ) %></font>
	
		</core:if>  
		
	
		<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= new java.text.SimpleDateFormat("MMMM dd,yyyy").format(new java.util.Date()) %></td>
        <td width="300" style="text-align:center;"><a href="main.jsp"><img title="main" onmouseover="this.src='../css/images/main-icon-on.png';" onmouseout="this.src='../css/images/main-icon-off.png';" src="../css/images/main-icon-off.png" alt="" width="37" height="38" border="0"></a></td>
        <td width="300" class="smalltext" valign="top" style="text-align:right;"><a href="logout.html"><img title="logout" onmouseover="this.src='../css/images/logout-on.png';" onmouseout="this.src='../css/images/logout.png';" src="../css/images/logout.png" alt="" width="32" height="32" border="0"></a></td>
	</tr>
    
</table>
</div>

<div style="clear:both"></div>

<div id="container">
<div id="content">

<core:if test="${user == 'manager'}">

<p class="title">Wallet Transfer</p>

<table width="100%" align="center">
  <tr>
	  <th>Partner ID</th>
	  <th>Partner Name</th>
	  <th>Partner Wallet</th>
	  <th>Branch ID</th>
	  <th>Branch Wallet</th>
  </tr>
  
  <core:forEach var="data" items="${partners}">
  <tr class="text">												 
		<td><a href="viewaccount.html?branchId=${data.branchid}">${data.partnerid}</a> 
		<td>${data.partnername}</td>
		<td>${data.partnerwallet}</td>
		<td>${data.branchid}</td>
		<td>${data.branchwallet}</td>				         
  </tr>
  </core:forEach>	
  
  </table><br />

				
</core:if>

<core:if test="${user == 'user'}">
<br>

<center>Cannot Access Page</center>

<br>
<br>


</core:if>


<core:if test="${type == 'toRetailer'}">



<p class="title">Wallet Transfer</p>

<table width="100%" align="center">
  <tr>
	  <th>Partner ID</th>
	  <th>Partner Name</th>
	  <th>Partner Wallet</th>
	  <th>Branch ID</th>
	  <th>Branch Wallet</th>
  </tr>
  
  <core:forEach var="data" items="${partners}">
  <tr class="text">												 
		<td><a href="viewretailersim.html?branchId=${data.branchid}">${data.partnerid}</a> 
		<td>${data.partnername}</td>
	    <td>${data.partnerwallet}</td>
		<td>${data.branchid}</td>
		<td>${data.branchwallet}</td>				         
  </tr>
  </core:forEach>	
  
  </table><br />
  
  
  

	
</core:if>

<core:if test="${retailer == 'null'}">

<br>

<center>No Records Found. </center>

<br>
<br>



</core:if>

</div>
</div>

<div id="footer"></div>
</div><!--wrapper-->
</div><!--page-->

</body>
</html>