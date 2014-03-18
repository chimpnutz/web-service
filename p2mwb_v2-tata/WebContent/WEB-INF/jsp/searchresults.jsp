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

<script>
  $(document).ready(function(){
	  
	  $("#main").click(function() 
			  {
				  var answer = confirm('Are you sure you want to leave this page?');
				  if (answer)
				  {
					return true;
				  }
				  else
				  {
				    return false;
				  }
			  });
	  
	  
	  $("#prod").click(function() 
	  {
		  var answer = confirm('Are you sure you want to leave this page?');
		  if (answer)
		  {
			return true;
		  }
		  else
		  {
		    return false;
		  }
	  });
	  
	  $("#sol").click(function() 
	  {
		  var answer = confirm('Are you sure you want to leave this page?');
		  if (answer)
		  {
			return true;
		  }
		  else
		  {
		    return false;
		  }
	  });
	  
	  $("#part").click(function() 
	  {
		  var answer = confirm('Are you sure you want to leave this page?');
		  if (answer)
		  {
			return true;
		  }
		  else
		  {
		    return false;
		  }
	  });
	  $("#lead").click(function() 
	  {
		  var answer = confirm('Are you sure you want to leave this page?');
		  if (answer)
		  {
			return true;
		  }
		  else
		  {
		    return false;
		  }
	  });
	  $("#car").click(function() 
	  {
		  var answer = confirm('Are you sure you want to leave this page?');
		  if (answer)
		  {
			return true;
		  }
		  else
		  {
		    return false;
		  }
	  });
	  $("#cont").click(function() 
	  {
		  var answer = confirm('Are you sure you want to leave this page?');
		  if (answer)
		  {
			return true;
		  }
		  else
		  {
		    return false;
		  }
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

<core:import url="menu.jsp"></core:import>

<div style="clear:both"></div>
   

<div id="container">
<div id="content">

<p class="text18_tungsten">Search Results</p>

<core:if test="${txinquiry == 'null'}">

<center><p class="text12_tungsten">No Records Found!</p>
</center>

<br><br>

</core:if>


<core:if test="${wallethistory == 'null'}">

<center><p class="text12_tungsten">No Records Found!</p>

</center>

<br><br>

</core:if>

<core:if test="${txreport == 'null'}">

<center><p class="text12_tungsten">No Records Found!</p>

</center>

<br><br>

</core:if>


<core:if test="${user == 'user'}">

<center><p class="text12_tungsten">No Records Found!</p>

<br> <br> <br>

</center>

</core:if>

					<core:if test="${type == 'txinquiry'}">
					<table width="100%" align="center" id="myTable" class="tablesorter">		
					<thead>
						<tr class ="text12_tungsten_bold">				
					         <td >Transaction Date</td>
					         <td>Partner Id</td>
					         <td>Partner Name</td>
					         <td>Agent</td>	
					         <td>Amount</td>	
					         <td>Target Mobile No.</td>		
					         <td>Status</td>	
					         <td>Type</td>			     
					   
						</tr>	
						</thead>
						<tbody>
						<core:forEach var="data" items="${results}">
						<tr class="text10_steel">													 
				 	  		 <td>${data.transactiondate}</td>
					         <td>${data.partnerid}</td>
					         <td>${data.partnername}</td>
					     	 <td>${data.username}</td>
					         <td>${data.amount}</td>
					         <td>${data.targetmsisdn}</td>
					         <td>${data.status}</td>
					         <td>${data.type}</td>
					         
					         
						</tr>
						</core:forEach>	
								</tbody>				
					</table>
					</core:if>	
					
						<core:if test="${type == 'tx'}">
					<table width="100%" align="center" id="myTable" class="tablesorter">	
					<thead>	
						<tr class ="text12_tungsten_bold">				
					         <td>Transaction Date</td>
					         <td>Partner Id</td>
					         <td>Partner Name</td>
					         <td>Username</td>	
					         <td>Amount</td>	
					         <td>Target Mobile No.</td>		
					         <td>Status</td>	
					         <td>Type</td>					     
					   
						</tr>	
						
						</thead>
						<tbody>
						<core:forEach var="data" items="${results}">
						<tr class="text10_steel">													 
				 	 	  		 <td>${data.transactiondate}</td>
					         <td>${data.partnerid}</td>
					         <td>${data.partnername}</td>
					     	 <td>${data.username}</td>
					         <td>${data.amount}</td>
					         <td>${data.targetmsisdn}</td>
					         <td>${data.status}</td>
					         <td>${data.type}</td>
					         
						</tr>
						</core:forEach>	
						</tbody>							
					</table>
					</core:if>	
					
					<core:if test="${type == 'wallethistory'}">
					<table width="100%" align="center" id="myTable" class="tablesorter">	
	
						<thead>
						<tr class ="text12_tungsten_bold" >				
					         <td>Date</td>
					         <td>Transaction ID</td>
					         <td>Source ID</td>
					         <td>Beginning Balance</td>
					         <td>Amount</td>
					         <td>Ending Balance</td>	
					         <td>Recipient  ID </td>	
					         <td>Beginning Balance </td>		
					         <td>Ending Balance</td>		
					        	     
					   
						</tr>
						</thead>
						
						<tbody>
						<core:forEach var="data" items="${wallethistory}">
						<tr class="text10_steel">
																			 
					         <td>${data.transdate}</td>
					         <td>${data.transid}</td>
					         <td>${data.sender}</td>			         
					     	 <td>${data.sender_start_bal}</td>
					     	 <td>${data.amount}</td>
					         <td>${data.sender_end_bal}</td>
					         <td>${data.receiver}</td>
					         <td>${data.receiver_start_bal}</td>
					         <td>${data.receiver_end_bal}</td>
					         
						</tr>
						
			
						</core:forEach>	
						</tbody>	
					</table>
					</core:if>	
				
		
</div>
</div>

<core:if test="${type == 'tx'}">
<div class="text10_steel" id = "downloadlink">
<core:url value="/jsp/download/export/xls" var="downloadXls"/>
<a href="${downloadXls}"><img src="../css/images/excel_icon.png" align="absmiddle">Click the icon to download in excel</a>
</div>
</core:if>

<core:if test="${type == 'wallethistory'}">
<div class="text10_steel" id = "downloadlink">
<core:url value="/jsp/download/export/xls/walletHistory" var="downloadXls"/>
<a href="${downloadXls}"><img src="../css/images/excel_icon.png" align="absmiddle">Click the icon to download in excel</a>
</div>
</core:if>


<div id="footer"></div>
</div><!--wrapper-->
</div><!--page-->

</body>
</html>