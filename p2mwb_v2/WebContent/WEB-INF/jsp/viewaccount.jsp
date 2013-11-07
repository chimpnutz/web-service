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

<link rel="stylesheet" href="../css/jquery-ui.css" type="text/css" media="all" />
<link rel="stylesheet" href="../css/ui.theme.css" type="text/css" media="all" />

<script src="../js/jquery.bgiframe-2.1.2.js" type="text/javascript"></script>
<script src="../js/jquery-ui-i18n.min.js" type="text/javascript"></script>

<link href="../css/unibar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/header.js"></script>
<script type="text/javascript" src="../js/dropdown.js"></script>
			

<script type="text/javascript">
			$(function(){

				// Accordion
				$("#accordion").accordion({ header: "h3" });
				$( "#dialog:ui-dialog" ).dialog( "destroy" );
				
				$( "#create-user" )
				.button()
				.click(function() {
					$( "#dialog-form" ).dialog( "open" );
				});
			});
</script> 
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

<core:if test="${type == 'wallettransfer'}">

<core:if test="${user == 'manager'}">

<p class="title">Account Details</p>

<core:forEach var="data" items="${partners}">

<p class="title"><div class="text" style="text-align:center;"><a href="transfertobranch.html?branchId=${data.branchid}">Transfer Wallet to Branch</a> | <a href="transfertomotherpartner?branchId=${data.branchid}">Transfer Wallet to Mother Wallet</a></div><br />
	
			<div id="result">
				<table width="100%" align="left">	
						<tr>				
					         <td>Partner Id</td>
					         <td>Partner Name</td>
					         <td>Partner Wallet</td>
					         <td>Branch id</td>
					         <td>Branch Wallet</td>					     
					   
						</tr>	
						
						<tr>												 
							 <td>${data.partnerid}</td>
					         <td>${data.partnername}</td>
					         <td>${data.partnerwallet}</td>
					         <td>${data.branchid}</td>
							 <td>${data.branchwallet}</td>
					         
						</tr>
						
						  <tr>
						    <td>&nbsp;</td>
						    <td>&nbsp;</td>
						    <td>&nbsp;</td>
						    <td>&nbsp;</td>
						    <td>&nbsp;</td>
						  </tr>
												
				</table>
				
			</div>			
				
</core:forEach>	
 		

</core:if>

</core:if>



<core:if test="${access == 'no'}">


<br>

<center>Cannot Access Page</center>

<br>

<br>

</core:if>


<core:if test="${type == 'toRetailer'}">
		
		<core:forEach var="data" items="${retailer}">
		
			<core:if test="${user == 'manager'}">
			
				  <p class="title"><div class="text" style="text-align:center;"><a href="transfertoretailer.html?mode=frBranch&branchId=${data.branchid}&mobile=${data.msisdn}">Transfer Branch to Retailer</a> | <a href="transfertoretailer.html?mode=frPartner&branchId=${data.branchid}&mobile=${data.msisdn}">Transfer Partner to Retailer</a></div><br />		
			
			</core:if>
				
			<core:if test="${user == 'user'}">
				 
				   <p class="title"><div class="text" style="text-align:center;"><a href="transfertoretailer.html?mode=frBranch&branchId=${data.branchid}&mobile=${data.msisdn}">Transfer Branch to Retailer</a></div>
				 
			</core:if>
								
				
			<div id="result">
				<table width="100%">	
						<tr>				
					         <td>Partner Id</td>
					         <td>Partner Name</td>
					         <td>Partner Wallet</td>
					         <td>Branch id</td>
					         <td>Branch Wallet</td>					     
					   		 <td>MSISDN</td>		
						</tr>	
						
						<tr>												 
							 <td>${data.partnerid}</td>
					         <td>${data.partnername}</td>
					         <td>${data.partnerwallet}</td>
					         <td>${data.branchid}</td>
					         <td>${data.branchwallet}</td>
							 <td>${data.msisdn}</td>
					         
						</tr>
						  <tr>
						    <td>&nbsp;</td>
						    <td>&nbsp;</td>
						    <td>&nbsp;</td>
						    <td>&nbsp;</td>
						    <td>&nbsp;</td>
						    <td>&nbsp;</td>
						  </tr>
						
				</table>
				
			</div>
						
								
</core:forEach>	

</core:if>

</div>
</div>
	
<div style="clear:both"></div>
<div id="footer"></div>
</div><!--wrapper-->
</div><!--page-->
		
</body>

</html>