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

<core:if test="${user == 'user'}">

<p class="text18_tungsten">Purchase Order</p>

<div align="center">
 		<ol id="toc">
            <li><a href="purchaseorder-ordernow.html">Order Now</a></li>  
            <li class="current"><a href="purchaseorder-history.html">Purchase Order</a></li>
          </ol>
 		<p>&nbsp;</p>
</div>

				<table width="100%" align="center">		
				
						<tr class ="text12_tungsten_bold">	
							 <td >PO ID</td>			
					         <td >Purchase Order Date</td>
					         <td>Total Amount</td>
					         <td>Payment Status</td>
					         <td>Delivery Status</td>	
					         <td>PO Status</td>	
						     
					   
						</tr>	
						<core:forEach var="data" items="${polist}">
						<tr class="text10_steel">	
							<td>${data.poid}</td>										 
				 	  		<td>${data.podate}</td>
					         <td>${data.order_amount}</td>
					         <td>${data.payment_status}</td>
					     	 <td>${data.delivery_status}</td>
					         <td>${data.po_status}</td>
					
					         
					         
						</tr>
						</core:forEach>	
													
					</table>


</core:if>

<core:if test="${user == 'manager'}">

<p class="text18_tungsten">Purchase Order</p>

<div align="center">

 		<ol id="toc">
            <li><a href="purchaseorder-ordernow.html">Order Now</a></li>  
            <li><a href="purchaseorder-retailer.html">Retailer</a></li>    
            <li class="current"><a href="purchaseorder-history.html">Purchase Order</a></li>  
      	</ol>

</div>

<p>&nbsp;</p>
				<table width="100%" align="center">		
				
						<tr class ="text12_tungsten_bold">	
							 <td >PO ID</td>			
					         <td >Purchase Order Date</td>
					         <td>Total Amount</td>
					         <td>Payment Status</td>
					         <td>Delivery Status</td>	
					         <td>PO Status</td>	
					         

						     
					   
						</tr>	
						<core:forEach var="data" items="${polist}">
						<tr class="text10_steel">	
							  <td><a href="purchaseorder-view.html?poid=${data.poid}">${data.poid}</a></td>										 
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

<p class="text18_tungsten">Purchase Order</p>

<div align="center">

 		<ol id="toc">
            <li class="current"><a href="purchaseorder-history.html">Purchase Order</a></li>
          </ol>
          
<p>&nbsp;</p>

</div>

				<table width="100%" align="center">		
				
						<tr class ="text12_tungsten_bold">	
							 <td >PO ID</td>			
					         <td >Requested Date</td>
					         <td>Agent</td>
					         <td>Partner ID</td>
					         <td>Amount</td>	
					         <td>Branch</td>		
					         <td>Status</td>	
						     <td>Payment Status</td>	
					   		 <td>Delivery Status</td>
					   		 <td>Attachement</td>	
						</tr>	
						
						
						<core:forEach var="data" items="${polist}">
						<tr class="text10_steel">	
							  <td><a href="purchaseorder-view.html?poid=${data.poid}">${data.poid}</a></td>										 
				 	  		 <td>${data.date_created}</td>
					         <td>${data.partnername}</td>
					         <td>${data.partnerid}</td>
					     	 <td>${data.total_amount}</td>
					     	 <td>${data.branch}</td>
					         <td>${data.status}</td>
					         <td>${data.payment_status}</td>
					         <td>${data.delivery_status}</td>					  
 							 <td><a href="../css/uploadedimages/${data.file }" >See Attachments</a></td>	
 					 
						</tr>
						</core:forEach>	
								 
						
					</table>

</core:if>


<core:if test="${user == 'viewer'}">

<p class="text18_tungsten">Purchase Order</p>

<div align="center">
 		<ol id="toc">
            <li class="current"><a href="purchaseorder-history.html?subdealerhistory">Sub Dealer Request History</a></li>
          </ol>
 		<p>&nbsp;</p>
</div>

				<table width="100%" align="center">		
				
						<tr class ="text12_tungsten_bold">	
							 <td >PO ID</td>			
					         <td >Requested Date</td>
					         <td>Agent</td>
					         <td>Partner ID</td>
					         <td>Amount</td>	
					         <td>Bank</td>	
					         <td>Branch</td>		
					         <td>Status</td>	
						     
					   
						</tr>	
						<core:forEach var="data" items="${polist}">
						<tr class="text10_steel">	
							 <td>${data.id}</td>												 
				 	  		 <td>${data.date}</td>
					         <td>${data.username}</td>
					         <td>${data.partnername}</td>
					     	 <td>${data.amount}</td>
					         <td>${data.bank}</td>
					         <td>${data.branch}</td>
					         <td>${data.status}</td>
					
					         
					         
						</tr>
						</core:forEach>	
													
					</table>

</core:if>


</div>
</div>


<div style="clear:both"></div>
<div id="footer"></div>
</div><!--wrapper-->
</div><!--page-->


</body>
</html>
