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
 		

            <li class="current"><a href="purchaseorder-retailer.html">Sub Dealer PO Request</a></li>   
            <li><a href="purchaseorder-history.html?mypohistory">Sub Dealer Request History</a></li>   
 		
 		
 		</core:if>
 		
 		<core:if test="${user == 'manager'}">
 		
 		 	<li><a href="purchaseorder-ordernow.html">Order Now</a></li>  
            <li class="current"><a href="purchaseorder-retailer.html">Retailer PO Request</a></li>   
            <li><a href="purchaseorder-history.html?mypohistory">My PO History</a></li>   
            <li><a href="purchaseorder-history.html?retailerhistory">Retailer Request History</a></li>   
 		
 		
 		</core:if>

      	</ol>
</div>
<p>&nbsp;</p>

	<core:if test="${type == 'requestlist'}">
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
					         <td>Attachment</td>	
						     
					   
						</tr>	
						<core:forEach var="data" items="${polist}">
						<tr class="text10_steel">	
							 <td><a href="purchaseorder-retailer.html?view&poid=${data.id}">${data.id}</a></td>												 
				 	  		 <td>${data.date}</td>
					         <td>${data.username}</td>
					         <td>${data.partnername}</td>
					     	 <td>${data.amount}</td>
					         <td>${data.bank}</td>
					         <td>${data.branch}</td>
					         <td>${data.status}</td>
					  
					         
					         <core:if test="${data.attachment == null}">
					         <td>Not Available</td>
					         </core:if>
					         
					          <core:if test="${data.attachment != null}">
					         <td><a href="../css/uploadedimages/${data.attachment}">See Attachment</a></td>
					         </core:if>
					        
					
					         
					         
						</tr>
						</core:forEach>	
													
					</table>
					
		</core:if>
		
			<core:if test="${type == 'poid'}">
			
					<core:forEach var="data" items="${polist}">

					<form:form action=""  commandName="purchaseorderForm" id="purchaseorderForm">
					
					<p class="text12_tungsten">Retailer &nbsp; <form:input path="username" name="" type="text" size="32" style="margin-left:60px;" value='${data.username}' readonly='true'  /></p>
					
					<p class="text12_tungsten">ID &nbsp; <form:input path="id" name="" type="text" size="32" style="margin-left:90px;" value='${data.id}' readonly='true'/></p>
					<p class="text12_tungsten">Amount Order  &nbsp; <form:input path="amount" name="" type="text" size="32" style="margin-left:20px;" value='${data.amount}' readonly='true'/>
					<p class="text12_tungsten">Status  &nbsp; <form:select path="status" style="width: 150px; text-align:left; margin-left:70px;"> <form:options items="${statuslist}" />
				    </form:select>
					<p class="text12_tungsten">Remarks &nbsp; <form:textarea path="remarks" rows="5" cols="30" style="background-color:white; margin-left:57px; vertical-align:top;" id="remarks"/></p>
					
					
					
					<p><input type="image" value="Submit" class="button" style="margin-left:315px;"/></p>
								
								
					</form:form>
					
						
					</core:forEach>
									
			</core:if>
				
				<core:if test="${type == 'result'}">


					<core:forEach var="data" items="${finishPO}">

					<div style="border:1px; border-style:solid; border-color:#CCC; width:400px; height: 300px; margin-top:50px; margin-left:30px; padding:20px; 
					-moz-border-radius: 10px; -webkit-border-radius: 10px; -khtml-border-radius: 10px; border-radius: 10px;"> 
					
									
					<core:if test="${status == 'success'}">

					<div class="text10_red">${msg}</div>
					
					</core:if>
					
					<core:if test="${status == 'fail'}">
					
					<div class="text10_red">${msg}</div>
					
					</core:if>
					
                     <br /><p class="text12_tungsten">Retailer: &nbsp; <span class="text12_steel"> ${data.username}</span> <br>
                     <br>
                     ID: &nbsp; <span class="text12_steel">  ${data.id} </span>   <br>
                     <br>
                     Amount: Order  &nbsp; <span class="text12_steel">  ${data.amount} </span>  <br>
                     <br>
                     Status:  &nbsp; <span class="text12_steel"> ${data.status} </span> <br>
                     <br>
                     Remarks: &nbsp; <span class="text12_steel"> ${data.remarks} </span> </p>
</div>

					
					
				
						
					</core:forEach>
					
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
