<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="../css/images/favicon.ico" >
<meta http-equiv="refresh" content="<%= session.getMaxInactiveInterval() %>;URL='logout.html?expire=yes'">
<link href="../css/tabs.css" rel="stylesheet" type="text/css" />
<LINK href="../css/style.css" rel="stylesheet" type="text/css">
<link type='text/css' href='../css/basic.css' rel='stylesheet' media='screen' />

<link href="../css/unibar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/header.js"></script>
<script type="text/javascript" src="../js/dropdown.js"></script>

<link href="../css/theme.default.css" rel="stylesheet">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.tablesorter.min.js"></script>
    
<script>
        $(function(){
          $("#myTable").tablesorter(
        		  
          {widgets: ['zebra'],
        headers: { 0: { sorter: 'shortDate'} }
          
          
          
          });
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
<p class="text18_tungsten">Bills Payment History<br />
</p>
<br/>
<p class="text"><div id="tabcontentcontainer" >
<div id="sc1" class="tabcontent">



					<table width="980" border="0" cellpadding="0" cellspacing="0" id="myTable" class="tablesorter">
						<thead> 
						<tr class ="text12_tungsten_bold">				
					         <td>Date Paid</td>
					         <td>Transaction ID</td>
					         <td>Biller</td>
					         <td>Account Number</td>
					         <td>Amount Paid</td>	
					         <td>Status</td>	
			     
					   
						</tr>	
						</thead>
						<tbody>
			
						<core:forEach var="data" items="${bills}">
						<tr class="text10_steel">												 
				 	  		 <td class="text">${data.datepaid}</td>
					         <td>${data.transactionid}</td>
					         <td>${data.companyid}</td>
					     	 <td>${data.accountnumber}</td>
					     	 <td>${data.amount}</td>
					     	 <td>${data.responsemsg}</td>
		
						</tr>
						</core:forEach>	
						</tbody>					
					</table><br>

<!-- modal content -->



</div></div>



</div><!--content-->
</div><!--container-->


<div style="clear:both"></div>



<div id="footer"></div>
</div><!--wrapper-->
</div><!--page-->


</body>
</html>
