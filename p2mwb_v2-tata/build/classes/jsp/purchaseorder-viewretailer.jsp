<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="height=device-height,width=device-width,initial-scale=1">
<link rel="shortcut icon" href="../css/images/favicon.ico" >
<LINK href="../css/style.css" rel="stylesheet" type="text/css">
<LINK href="../css/form.css" rel="stylesheet" type="text/css">
<LINK href="../css/font.css" rel="stylesheet" type="text/css">
<LINK href="../css/tabs.css" rel="stylesheet" type="text/css">
<link href="../css/unibar.css" rel="stylesheet" type="text/css" />
<link href="../css/purchasehistory.css" rel="stylesheet" type="text/css" />
<link href="../css/podetails.css" rel="stylesheet" type="text/css" />
<link href="../css/updatepo.css" rel="stylesheet" type="text/css" />
<link href="../css/modal.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/header.js"></script>
<script type="text/javascript" src="../js/dropdown.js"></script>
<script type="text/javascript" src="../js/jtip.js"></script>

<style type="text/css" media="all">
@import "../css/global.css";
</style>
<script src="../js/jtip.js" type="text/javascript"></script>

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
 		
 		<core:if test="${user == 'manager'}">
 		
 		 	<li><a href="purchaseorder-ordernow.html">Order Now</a></li>  
            <li class="current"><a href="purchaseorder-retailer.html">Retailer</a></li>   
            <li><a href="purchaseorder-history.html">Purchase Order</a></li>   

 		
 		
 		</core:if>

      	</ol>
</div>
<p>&nbsp;</p>


		


</core:if>


<div align="center">
    <form action="purchaseorder-retailer.html">
 		<input type="submit" value="Back" style="margin-left:45px;" class="button"/>
    </form>
</div>

  <div class="addextraheight"></div>
  
   		 
      		
  			
  <div class="addextraheight"></div>
  
  
  				<table width="100%" align="center">		
				
						<tr class ="text12_tungsten_bold">	
						
							 <td >Item Code</td>			
					         <td >Quantity</td>
					         <td>Face Value Amount</td>
					         <td>Discount Amount</td>
					         <td>Total Amount for Item</td>	
					         <td>Wallet</td>
						     
					   
						</tr>	
						
						<core:forEach var="data" items="${otlist}">
			
						<core:if test="${data.item == data.item }">
						<tr class="text10_steel">	
							 <td><a href="entrylop.html?width=195.5&poid=${data.poid}"  class="jTip"  id="one" name="${data.item}">${data.item}</a></td>												 
				 	  		 <td><a href="entrylop.html?width=195.5&poid=${data.poid}"  class="jTip"  id="two" name="${data.item}">${data.quantity}</a></td>
					         <td><a href="entrylop.html?width=195.5&poid=${data.poid}"  class="jTip"  id="three" name="${data.item}">${data.face_value_amount}</a></td>
					         <td><a href="entrylop.html?width=195.5&poid=${data.poid}"  class="jTip"  id="four" name="${data.item}">${data.discount_amount}</a></td>
					     	 <td><a href="entrylop.html?width=195.5&poid=${data.poid}"  class="jTip"  id="five" name="${data.item}">${data.total_amount}</a></td>
					         <td><a href="entrylop.html?width=190&poid=${data.poid}"  class="jTip"  id="six" name="${data.item}">${data.wallet}</a></td>
						</tr>
						</core:if>					
					
						</core:forEach>	
												
					</table>

<div align="center">
<hr/>
<div class="note">
<core:if test="${status=='decline'}">
			<label class="text12_tungsten_bold">${message}</label>
</core:if>
<core:if test="${status=='approve'}">
			<label class="text12_tungsten_bold">${message}</label>
</core:if>
<core:if test="${status=='blank'}">
			<label class="text12_tungsten_bold">${message}</label>
</core:if>
</div>



 <br/>


 </div>
  
</div>
</div>




<core:forEach var="data" items="${otlist}">

		


	     <core:if test="${data.payment_status != 'paid' && data.po_status == 'active'  &&  data.delivery_status != 'delivered' &&  data.delivery_status != 'cancelled'}">
		
		<div id="container">
		<div id="content">
		<div style="margin-left:50px;"> 
				       		<form:form action="" commandName = "retailerForm" id="processorderForm">
							<center>
								 <select name="type">
								    <option value ="">------</option>
								 	<option value = "approve">Approve</option>
								 	<option value = "decline"> Decline</option>	 
								 </select>
								 
								 <input type=submit name="update" value="submit" class="button">
							</center>
							</form:form>
							
		</div>
		</div>
		</div>
		
		</core:if>
	


</core:forEach>






</div>

<div class="addextraheight">
             </div>





<div style="clear:both"></div>
<div id="footer"></div>
</div><!--wrapper-->
</div><!--page-->

<div class="viewpobot">
</div>

</body>
</html>
