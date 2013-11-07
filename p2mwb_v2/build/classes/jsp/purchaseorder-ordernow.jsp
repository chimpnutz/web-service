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
<script src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
  <script>
  $(document).ready(function(){
	    $("#purchaseorderForm").validate({
	  	  rules: {
	  	  amount: {
	      required: true,
	      number: true
	      },
	      bank: {
	      required: true
	      },
	      branch: {
		      required: true
		  },
	  },  messages: {
	      amount:
	      {
	    	  required: "Please Input Amount",
	    	  number:	"Please Input Valid Amount"
	      },
	      bank: 
	      {
	  
	    	  required: "Please Input Bank. "
	 
	      },
	      branch: 
	      {
	  
	    	  required: "Please Input Branch. "
	 
	      }   
      }
	});
	    $('#amount').val('');
	    $('#bank').val('');
	    $('#branch').val('');
	    $('#remarks').val('');
	});
  
  </script>
  
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

<core:if test="${user == 'superadmin'}">


<br>

<center>Cannot Access Page</center>

<br><br>



</core:if>

<core:if test="${user == 'user' || user=='manager'}">







<core:if test="${user == 'user'}">

<core:if test="${status == 'success'}">

<div class="text10_red">${msg}</div>

</core:if>

<core:if test="${status == 'fail'}">

<div class="text10_red">${msg}</div>

</core:if>

<div align="center">
 		<ol id="toc">
            <li class="current"><a href="purchaseorder-ordernow.html">Order Now</a></li>   
            <li><a href="purchaseorder-history.html?mypohistory">My PO History</a></li>   
      	</ol>
</div>
<p>&nbsp;</p>

<form:form action=""  commandName="purchaseorderForm" id="purchaseorderForm">

<p class="text12_tungsten">Amount &nbsp; <form:input path="amount" size="30" style="background-color:white; margin-left:60px;" id="amount"/></p>
<p class="text12_tungsten">Bank &nbsp; <form:input path="bank" size="30" style="background-color:white; margin-left:78px;" id="bank"/></p>
<p class="text12_tungsten">Branch &nbsp; <form:input path="branch" size="30" style="background-color:white; margin-left:67px;" id="branch"/></p>
 <form:hidden path="attachment" size="30" style="background-color:white; margin-left:39px; vertical-align:top;" id="attachment"/>
<p class="text12_tungsten">Remarks &nbsp; <form:textarea path="remarks" rows="5" cols="30" style="background-color:white; margin-left:57px; vertical-align:top;" id="remarks"/></p>

<p><input type="image" value="Submit" class="button" style="margin-left:280px;"/></p>


</form:form>



</core:if>




<core:if test="${user == 'manager'}">

<core:if test="${status == 'success'}">

<div class="text10_red">${msg}</div>

</core:if>

<core:if test="${status == 'fail'}">

<div class="text10_red">${msg}</div>

</core:if>

<div align="center">
 		<ol id="toc">
            <li class="current"><a href="purchaseorder-ordernow.html">Order Now</a></li>  
            <li><a href="purchaseorder-retailer.html">Retailer PO Request</a></li>    
            <li><a href="purchaseorder-history.html?mypohistory">My PO History</a></li>  
            <li><a href="purchaseorder-history.html?retailerhistory">Retailer Request History</a></li>    
      	</ol>
</div>
<p>&nbsp;</p>

<form:form action=""  commandName="purchaseorderForm" id="purchaseorderForm"  enctype="multipart/form-data">

<p class="text12_tungsten">Amount &nbsp; <form:input path="amount" size="30" style="background-color:white; margin-left:60px;" id="amount"/></p>
<p class="text12_tungsten">Bank &nbsp; <form:input path="bank" size="30" style="background-color:white; margin-left:78px;" id="bank"/></p>
<p class="text12_tungsten">Branch &nbsp; <form:input path="branch" size="30" style="background-color:white; margin-left:67px;" id="branch"/></p>
<p class="text12_tungsten">Attachment &nbsp; <form:input type="file" path="file" size="30" style="background-color:white; margin-left:39px; vertical-align:top;" id="attachment"/><span class="text10_red">*use jpeg only</span></p>
<p class="text12_tungsten">Remarks &nbsp; <form:textarea path="remarks" rows="5" cols="30" style="background-color:white; margin-left:57px; vertical-align:top;" id="remarks"/></p>

<p><input type="image" value="Submit" class="button" style="margin-left:280px;"/></p>


</form:form>



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
