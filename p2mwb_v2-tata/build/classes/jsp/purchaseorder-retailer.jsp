<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="../css/images/favicon.ico" >
<LINK href="../css/style.css" rel="stylesheet" type="text/css">
<LINK href="../css/form.css" rel="stylesheet" type="text/css">
<LINK href="../css/font.css" rel="stylesheet" type="text/css">
<LINK href="../css/tabs.css" rel="stylesheet" type="text/css">
<link href="../css/unibar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/header.js"></script>
<script type="text/javascript" src="../js/dropdown.js"></script>
<title>Pay PhilExchange</title>
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

<div id="wrapper"><br />
 <core:import url="menu.jsp"></core:import>
<div style="clear:both"></div>
<div id="container">
<div id="content">

<p class="text18_tungsten">Purchase Order</p>

<core:if test="${user == 'viewer'}">

<br>

<center>Cannot Access Page</center>

<br>
<br>


</core:if>

<core:if test="${user == 'user'}">

<br>

<center>Cannot Access Page</center>

<br><br>

</core:if>

<core:if test="${user == 'manager' || user == 'superadmin'}">



<div align="center">
 		<ol id="toc">
 		<core:if test="${user == 'superadmin'}">
 		

            <li class="current"><a href="purchaseorder-retailer.html">Retailer</a></li>   
  		    <li><a href="purchaseorder-history.html">Purchase Order</a></li>   
 		
 		
 		</core:if>
 		
 		<core:if test="${user == 'manager'}">
 		
 		 	<li><a href="purchaseorder-ordernow.html">Order Now</a></li>  
            <li class="current"><a href="purchaseorder-retailer.html">Retailer</a></li>      
            <li><a href="purchaseorder-history.html">Purchase Order</a></li>   
 		
 		
 		</core:if>

      	</ol>
</div>
<p>&nbsp;</p>

<core:if test="${user == 'manager'}">
					<table width="100%" align="center">		
				
						<tr class ="text12_tungsten_bold">	
							 <td >PO ID</td>			
					         <td >Retailer Branch</td>
					         <td>Purchase Order Date</td>
					         <td>Total Amount</td>
					         <td>Payment Status</td>	
					         <td>Delivery Status</td>	
					         <td>PO Status</td>		
			
					   
						</tr>	
						<core:forEach var="data" items="${retailer}">
						<tr class="text10_steel">	
							<td><a href="purchaseorder-viewretailer.html?poid=${data.poid}">${data.poid}</a></td>	
							 <td>${data.retailer}</td>									 
				 	  		 <td>${data.podate}</td>
					         <td>${data.order_amount}</td>
					         <td>${data.payment_status}</td>
					     	 <td>${data.delivery_status}</td>
					         <td>${data.po_status}</td>
 
						</tr>
						</core:forEach>	
													
					</table>
</core:if>

<core:if test="${user == 'superadmin'}">
					<table width="100%" align="center">		
				
						<tr class ="text12_tungsten_bold">	
							 <td >PO ID</td>			
					         <td >Retailer Branch</td>
					         <td>Purchase Order Date</td>
					         <td>Total Amount</td>
					         <td>Status</td>
					         <td>Payment Status</td>	
					         <td>Delivery Status</td>	
					         <td>PO Status</td>
					         <td>Attachements</td>			
			
					   
						</tr>	
						<core:forEach var="data" items="${ghpretailer}">
						<tr class="text10_steel">	
							<td><a href="purchaseorder-view-retailer-payment.html?poid=${data.poid}">${data.poid}</a></td>		
							 <td>${data.retailer}</td>									 
				 	  		 <td>${data.podate}</td>
					         <td>${data.order_amount}</td>
					         <td>${data.status}</td>
					         <td>${data.payment_status}</td>
					     	 <td>${data.delivery_status}</td>
					         <td>${data.po_status}</td>
					         <core:choose>
							    <core:when test="${empty data.file}">
							        <td>No Attachment</td>
							    </core:when>
							    <core:otherwise>
							        <td><a href="../css/uploadedimages/${data.file }">See Attachment</a></td>
							    </core:otherwise>
							</core:choose>
 
						</tr>
						</core:forEach>	
													
					</table>
</core:if>		



</core:if>


</div>
</div>


<div style="clear:both"></div>
<div id="footer"></div>
</div><!--wrapper-->
</div><!--page-->


</body>
</html>
