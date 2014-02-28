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

<core:if test="${user == 'user' || user == 'superadmin'}">



<div align="center">
 		<ol id="toc">
 		
 		<core:if test="${user == 'superadmin'}">
 		
            <li class="current"><a href="purchaseorder-retailer.html">Retailer</a></li>   
            <li><a href="purchaseorder-history.html">Purchase Order</a></li>   

 		
 		
 		</core:if>
 		
 		<core:if test="${user == 'user'}">
 		
            <li><a href="purchaseorder-ordernow.html">Order Now</a></li>  
            <li class="current"><a href="purchaseorder-history.html">Purchase Order</a></li>

 		
 		
 		</core:if>

      	</ol>
</div>
<p>&nbsp;</p>


		


</core:if>


<div align="center">
<core:if test="${user == 'superadmin' }">
    <form action="purchaseorder-retailer.html">
 		<input type="submit" value="Back" style="margin-left:45px;" class="button"/>
    </form>
</core:if>
<core:if test="${user == 'user' }">
    <form action="purchaseorder-history.html">
 		<input type="submit" value="Back" style="margin-left:45px;" class="button"/>
    </form>
</core:if>
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
							 <td><a href="entryret.html?width=195.5&poid=${data.poid}"  class="jTip"  id="one" name="${data.item}">${data.item}</a></td>												 
				 	  		 <td><a href="entryret.html?width=195.5&poid=${data.poid}"  class="jTip"  id="two" name="${data.item}">${data.quantity}</a></td>
					         <td><a href="entryret.html?width=195.5&poid=${data.poid}"  class="jTip"  id="three" name="${data.item}">${data.face_value_amount}</a></td>
					         <td><a href="entryret.html?width=195.5&poid=${data.poid}"  class="jTip"  id="four" name="${data.item}">${data.discount_amount}</a></td>
					     	 <td><a href="entryret.html?width=195.5&poid=${data.poid}"  class="jTip"  id="five" name="${data.item}">${data.total_amount}</a></td>
					         <td><a href="entryret.html?width=190&poid=${data.poid}"  class="jTip"  id="six" name="${data.item}">${data.wallet}</a></td>
						</tr>
						</core:if>
						
						</core:forEach>	
												
					</table>

<div align="center">
<hr/>
<div class="note">
</div>

<form:form action="" commandName="processorderForm" id="processorderForm">
	<core:if test="${user == 'superadmin'}">
	
	<core:forEach var="data" items="${pomlist}">
	
	<core:if test="${data.payment_status == 'paid' && data.delivery_status != 'delivered'}">
	
			 <input type="submit" value="Process Order" class="button" name="processOrder"></input>
	
	</core:if>
	
	</core:forEach>

	</core:if> 
</form:form>

 <br/>


 </div>
  
</div>
</div>


<div id="container">
<div id="content">
<label class="text12_tungsten_bold"><center>${message}</center></label>
<header><h2 class="text18_tungsten">Payment Section</h2></header>



<div style="margin-left:50px;"> 

</div>

<core:forEach var="data" items="${pomlist}">
<core:if test="${user == 'superadmin' || user == 'user'}">

<div class="adjustspace">

</div>
 
<table width="980" border="0" cellpadding="0" cellspacing="0">


  <tr class="text12_tungsten_bold">

    <td>Payment Date</td>
    <td>Payment Status</td>
    <td>Status</td>
    <td>Total Payment Amount</td>
    <td>Total Order Amount</td>
    <td>Total Fee</td>
    <core:if test="${user == 'superadmin'}">
    
    <core:if test="${data.payment_status != 'paid' && data.delivery_status != 'delivered' && data.payment_status !='cancelled'}">
	
			<td>Actions</td>
	
	</core:if>
	
	   <core:if test="${data.payment_status == 'paid' && data.delivery_status != 'delivered' && data.payment_status !='cancelled'}">
	
			<td>Actions</td>
	
	</core:if>
	
	

  </core:if>

    <td></td>
    </tr>
  <tr>

    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
      <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>

  <tr class="text10_steel">



    <td><a href="entry.html?width=195.5&poid=${data.poid}" class="jTip" id="twentyfive" name="Payment">${data.date_created }</a></td>
     <td><a href="entry.html?width=195.5&poid=${data.poid}" class="jTip" id="twentysix" name="Payment">${data.payment_status}</a></td>
     <td><a href="entry.html?width=195.5&poid=${data.poid}" class="jTip" id="twentyseven" name="Payment">${data.status}</a></td>
    <td><a href="entry.html?width=195.5&poid=${data.poid}" class="jTip" id="twentyeight" name="Payment">${data.total_order }</a></td>
     <td><a href="entry.html?width=195.5&poid=${data.poid}" class="jTip" id="twentynine" name="Payment">${data.total_amount}</a></td>
      <td><a href="entry.html?width=195.5&poid=${data.poid}" class="jTip" id="thirty" name="Payment">${data.total_fee }</a></td>
      <td>
      
      
      
      <form:form action="" commandName="paymentForm" id="paymentForm">
      <core:if test = "${user == 'superadmin' }">
      
	     
	     <core:if test="${data.payment_status != 'paid' && data.payment_status !='cancelled'}">
		
				       <input type="submit" value="Confirm" class="button" name="confirmPayment"></input>
		
		</core:if>
		
		<core:if test="${data.payment_status == 'paid' && data.delivery_status != 'delivered' }">
		
				      <input type="submit" class="button" value="Cancel" name="cancelPayment"></input>
		
		</core:if>
		
 
      </core:if>
      </form:form>
      
      
      </td>


    </tr>


</table>


</core:if>
</core:forEach>



         </div>

</div>






</div>

         </div>

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

