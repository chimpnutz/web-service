<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="refresh" content="<%= session.getMaxInactiveInterval() %>;URL='logout.html?expire=yes'">
<link rel="shortcut icon" href="../css/images/favicon.ico" >
<LINK rel="stylesheet" href="../css/style.css" type="text/css">
<LINK rel="stylesheet" href="../css/font.css" type="text/css">
<link rel="stylesheet" type="text/css" href="../css/verticalmenu.css">
<script type="text/javascript">
<!--
window.onload=montre;
function montre(id) {
var d = document.getElementById(id);
for (var i = 1; i<=10; i++) {
if (document.getElementById('smenu'+i)) {document.getElementById('smenu'+i).style.display='none';}
}
if (d) {d.style.display='block';}
}
//-->
</script>

<style type="text/css">
body {
	margin:0;
}
</style>

<title>Emergency Load</title>
</head>

<body>
<div id="page"><!----- Start of PAGE ----->
<div id="container"><!-----Start of Container ----->

     <core:import url="menu.jsp"></core:import>
    
     <core:if test="${roleid == 1 || roleid == 2 || roleid == 3 || roleid == 4 || roleid ==5 || roleid == 8}">

	<div id="content">
	<core:if test="${success == 'yes'}">
	<div class = "text12_tungsten_bold">Credit Limit Successfully Edited</div>
	</core:if>
	 <core:forEach var="data" items="${creditList}">
	 
		<table width="700" border="0" align="center" cellpadding="3" cellspacing="0">
	   
	    <tr>
	      <td width="150" class="text12_tungsten_bold">Company Name</td>
	      <td class="text12_tungsten">${data.companyname}</td>
        </tr>
 	    <tr>
	      <td class="text12_tungsten_bold">Amount</td>
	      <td class="text12_tungsten">${data.creditlimit}</td>
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">Status</td>
	      <td class="text12_tungsten">${data.status}</td>
        </tr>
        
      </table>
      
      	      <div align="left" style="margin-left:35px;">
<div class="text12_blue_bold">OPEN SIM Replenishment </div>
<table width="700" border="1" cellpadding="3" cellspacing="0" bordercolor="#cccccc">
  
	        <tr>
	          <td width="150" class="text12_tungsten_bold">Date Replenished</td>
	          <td width="150" class="text12_tungsten_bold">Amount Replenished</td>
	          <td width="200" class="text12_tungsten_bold">Starting Transaction Time</td>
	          <td width="150" class="text12_tungsten_bold">End Transaction Time</td>
	          <td width="150" class="text12_tungsten_bold">Transferred Amount</td>
	          <td width="150" class="text12_tungsten_bold">Ending Outstanding Balance of PD</td>
	          <td width="150" class="text12_tungsten_bold">Payment Status</td>
            </tr>
              	   <core:forEach var="data" items="${openlist}">
	        <tr>
	          <td class="text12_tungsten_bold">${data.payment_date}</td>
	          <td class="text12_tungsten">${data.amount_paid}</td>
	          <td class="text12_tungsten">${data.transferfrom_time}</td>
	          <td class="text12_tungsten">${data.transferto_time}</td>   
	          <td class="text12_tungsten">${data.amount_transfered}</td>   
	          <td class="text12_tungsten">${data.outstanding_balance}</td>   
	           <td class="text12_tungsten">${data.payment_status}</td>   
	
        	  
          </tr>
          </core:forEach>
        </table>
<p>&nbsp;</p>
</div> 

      </core:forEach>
	</div>
  
  </core:if>
  
  	  <core:if test="${roleid == 6 || roleid == 7}">
	 
	 <br><br><br><br><br>
	
	<center>	<div class="text12_tungsten_bold"> Access is Denied </div></center>
	
	  </core:if>


</div><!----- End Of Container----->
<div id="footer"></div>
</div><!----- End of PAGE ----->


</body>
</html>
