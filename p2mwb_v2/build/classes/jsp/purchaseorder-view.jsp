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
 		 	<core:if test="${user == 'superadmin'}">
 		

            <li class="current"><a href="purchaseorder-retailer.html">Sub Dealer PO Request</a></li>   
            <li><a href="purchaseorder-history.html?">Sub Dealer Request History</a></li>   
 		
 		
 		</core:if>
 		
 		<core:if test="${user == 'manager'}">
 		
 		 	<li><a href="purchaseorder-ordernow.html">Order Now</a></li>  
            <li ><a href="purchaseorder-retailer.html">Retailer</a></li>   
            <li class="current"><a href="purchaseorder-history.html">Purchase Order</a></li>   

 		
 		
 		</core:if>

      	</ol>
</div>
<p>&nbsp;</p>


		


</core:if>


<div align="center">
    <form action="purchaseorder-history.html">
 		<input type="submit" value="Back" style="margin-left:45px;" class="button"/>
    </form>
</div>

  <div class="addextraheight"></div>
  


    
        <core:forEach var="data" items="${polist}">
       <div id="pod" >
       <label class ="text12_tungsten_bold">PO Date:</label>
       
       <label class = "text12_tungsten">${data.podate}</label>
      
        </div>
        <div id="pn" >
        <label class ="text12_tungsten_bold">Partner Name:</label>
      	<label class = "text12_tungsten">${data.partner_name}</label>
        </div>




        <div id="ps">
        <label class ="text12_tungsten_bold">Payment Status:</label>
        <label class = "text12_tungsten">${data.payment_status}</label>
        </div>

        <div id="ds">
        <label class ="text12_tungsten_bold">Delivery Status:</label>
        <label class = "text12_tungsten">${data.delivery_status}</label>
        </div>
    

           <div id="pos">
        <label class ="text12_tungsten_bold">PO Status:</label>
        <label class = "text12_tungsten">${data.po_status }</label>
        </div>


              <div id="toa">
        <label class ="text12_tungsten_bold">Total Order Amount:</label>
        <label class ="text12_tungsten">${data.total_amount }</label>
        </div>


            <div id="ap">
        <label class ="text12_tungsten_bold">Amount Paid:</label>
       <label class ="text12_tungsten">${data.amount_paid}</label>
        </div>

        <div id="c">
        <label class ="text12_tungsten_bold">Cancelled Date:</label>
        <label class ="text12_tungsten">${data.cancel_date }</label>
        </div>
         </core:forEach>

  
  
  
       		 
      		
  			
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
						<core:forEach var="data" items="${polist}">
						<tr class="text10_steel">	
							 <td>${data.item}</td>												 
				 	  		 <td>${data.quantity}</td>
					         <td>${data.face_value_amount}</td>
					         <td>${data.discount_amount}</td>
					     	 <td>${data.total_amount}</td>
					         <td>${data.wallet}</td>
       
						</tr>
						</core:forEach>	
													
					</table>

<div align="center">
<hr/>
 <input type="button" value="Process Order" class="button"></input>
 <input type="button" value="Delivered Order" class="button"></input>
 <br/>


 </div>
  
</div>
</div>

<div id="container">
<div id="content">




<header><h2 class="text18_tungsten">Payment Section <a href="#modal">  <input type="button" id="button1" class="button" value="Add"></input></a></h2></header>
<div style="margin-left:50px;"> 

</div>
<core:forEach var="data" items="${polist}">
<core:if test="${data.payment_status != 'initial'}">

<div class="adjustspace">

</div>

<table width="980" border="0" cellpadding="0" cellspacing="0">


  <tr class="text12_tungsten_bold">
 
    <td>Payment Date</td>
      <td>Status</td>
   <td>Total Payment Amount</td>
    <td>Total Order Amount</td>
   <td>Total Fee</td>
<td>Actions</td>
  

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
  

 
    <td><a href="entry.html?poid=${data.poid}"  class="jTip"  id="one" name="item code">${data.podate}</a></td>
      <td><a href="entry.html?poid=${data.poid}" class="jTip" id="two" name="item code">${data.payment_status }</a></td>
    <td><a href="entry.html?poid=${data.poid}" class="jTip" id="three" name="item code">${data.amount_paid}</a></td>
       <td><a href="entry.html?poid=${data.poid}" class="jTip" id="four" name="item code">${data.total_amount }</a></td>
      <td><a href="entry.html?poid=${data.poid}" class="jTip" id="five" name="item code">insert here</a></td>
      <td><input type="button" value="Confirm" title="confirm" class="button"></input> <input type="button" class="button" value="Cancel" title="cancel"></input></td>
   
     <td></td>
    </tr>


</table>







   <div>

     <div >
  </core:if>
</core:forEach>



  <div id="modal">
    <div class="modal-content">
      <div class="header">
        <h2>Pay Through</h2>
      </div>
      <div class="copy">
        

<fieldset style="border:0">
  <label>Select</label>
  <select class="branch">
  <option value="1">------------</option>
    <option value="2">Manual</option>
    <option value="3">Bancnet</option>
 
  </select>

</fieldset>


<article>
  <div id="insider">
     <div class="1">
      <h1></h1>
    </div>
    <div class="2">
     <div id="space1"></div>
      <form action="" method="post">

    
        
         <div id="amnt">
        <label>Amount</label>
        <input type="text" id="amnt1"/><br/> <span class="inline">
        </div>


        <div id="bank">
        <label>Bank</label>
        <input type="text" id="bank1"/><br/> <span class="inline">
        </div>



        <div id="branch">
        <label>Branch</label>
        <input type="text" id="branch1"/><br/> <span class="inline">
        </div>


        <div id="remarks">
            <label for="textarea">Remarks</label>
    <textarea id="textarea" cols="60" rows="5" style="resize: none;" placeholder="Leave a message..." required></textarea>

        
        </div>


  </form>



    </div>
    <div class="3">
      <h1>Bancnet</h1>
    </div>
  
  </div>
</article>




      </div>
      <div class="cf footer">
        <a href="#" class="btn" id="moveme">Close</a>  <a href="#" class="btn" id="moveme">Submit</a>
      </div>
    </div>
    <div class="overlay"></div>
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

<script>
$(document).ready(function(){
  $('#insider').children().hide();
 
  
  $('.branch').change(function(){
    var branchVal = $(this).val();
    
    $(this).parent().next().children().children().hide();
    $(this).parent().next().children().children('div:nth-child('+branchVal+')').show();
  });
});
</script>
</body>
</html>
