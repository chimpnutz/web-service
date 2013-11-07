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
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/ajax.js"></script>
<script type="text/javascript" src="../js/basic.js"></script>
<script type='text/javascript' src='../js/jquery.simplemodal.js'></script>
<LINK rel="stylesheet" href="../css/basic.css" type="text/css">
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
    
	<div id="content">
	  <table width="740"  border="0" align="center" cellpadding="3" cellspacing="0">

	  <core:if test="${roleid == 7}">
	  
	  <core:if test="${isCreditLimit == 'true'}">
	  
	  	 <tr>
	      <td colspan="7" class="text12_tungsten"><input type="image" id="apply" src="../css/images/apply.png" width="48" height="25"/> 
	        For Emergency Load</td>
        </tr>
        	 <tr>
	      <td colspan="7" class="text12_tungsten">Your Available Credit Limit is :${balance}</td>
        </tr>
	  
	  </core:if>
	  

        
        
	  </core:if>


        
       <core:if test="${roleid == 1 || roleid == 2 || roleid == 3 || roleid ==8 }">
       
       	    <tr>

	      <td class="text12_tungsten_bold">Company </td>
	      <td class="text12_tungsten_bold">Payment Status</td>
	      <td class="text12_tungsten_bold">Amount</td>
	      <td width="80">&nbsp;</td>
        </tr>

       
    	 <core:forEach var="data" items="${creditlist}">
    
	    <tr>
	
	      <td class="text12_tungsten">${data.companyname}</td>
	      <td class="text12_tungsten">${data.status}</td>
	      <td class="text12_tungsten">${data.amount}</td>

	     
	     <core:if test="${type == 'unpaid'}">
	     
	     <td class="text10_red"><a href="emergencyloadmanagement-edit.html?unpaid&cid=${data.creditid}" class="text10_red">Pay</a> | <a href="emergencyloadmanagement-view.html?unpaid&cid=${data.creditid}" class="text10_red">view</a></td>
	     
	     </core:if>
	     
	     <core:if test="${type == 'paid'}">
	     
	     <td class="text10_red"><a href="emergencyloadmanagement-edit.html?paid&cid=${data.creditid}" class="text10_red">edit</a> | <a href="emergencyloadmanagement-view.html?paid&cid=${data.creditid}" class="text10_red">view</a></td>
	     
	     </core:if>
	     
		 <core:if test="${type == 'cancelled'}">
	     
	     <td class="text10_red"><a href="emergencyloadmanagement-edit.html?cancelled&cid=${data.creditid}" class="text10_red">edit</a> | <a href="emergencyloadmanagement-view.html?cancelled&cid=${data.creditid}" class="text10_red">view</a></td>
	     
	     </core:if>
	       
       
       
        </tr>
      
			</core:forEach>  
			
			 </core:if>	 
			 
			 
			 
			 	<core:if test="${roleid == 4 || roleid == 5}">
       
    	
    	 
    	 	    <tr>

	      <td class="text12_tungsten_bold">Company </td>
	      <td class="text12_tungsten_bold">Payment Status</td>
	      <td class="text12_tungsten_bold">Delivery Status</td>
	      <td class="text12_tungsten_bold">Amount</td>
	      <td class="text12_tungsten_bold">Availed Date</td>
	      <td width="80">&nbsp;</td>
        </tr>

     <core:forEach var="data" items="${creditlist}">
	    <tr>

	      <td class="text12_tungsten">${data.companyname}</td>
	      <td class="text12_tungsten">${data.status}</td>
	      <td class="text12_tungsten">${data.delivery_status}</td>
	      <td class="text12_tungsten">${data.amount}</td>
	      <td><span class="text12_tungsten">${data.date_borrowed}</span></td>
	     
	     <core:if test="${type == 'unpaid'}">
	      <td class="text10_red">
	     <core:if test="${data.delivery_status == 'completed'}">
	      <a href="emergencyloadmanagement-edit.html?unpaid&cid=${data.creditid}" class="text10_red">Pay</a> | 
	     </core:if>
	   	<a href="emergencyloadmanagement-view.html?unpaid&cid=${data.creditid}" class="text10_red">View</a>
	     </td>
	     </core:if>
	     
	     <core:if test="${type == 'paid'}">
	     
	     <td class="text10_red"><a href="emergencyloadmanagement-edit.html?paid&cid=${data.creditid}" class="text10_red">edit</a> | <a href="emergencyloadmanagement-view.html?paid&cid=${data.creditid}" class="text10_red">view</a></td>
	     
	     </core:if>
	     
		 <core:if test="${type == 'cancelled'}">
	     
	     <td class="text10_red"><a href="emergencyloadmanagement-edit.html?cancelled&cid=${data.creditid}" class="text10_red">edit</a> | <a href="emergencyloadmanagement-view.html?cancelled&cid=${data.creditid}" class="text10_red">view</a></td>
	     
	     </core:if>
        </tr>
      
			</core:forEach>  
			
			 </core:if>	
			 
			 
			 
			 	<core:if test="${roleid == 6}">
       
    	 
    	 	    <tr>

	      <td class="text12_tungsten_bold">Company </td>
	      <td class="text12_tungsten_bold">Payment Status</td>
	      <td class="text12_tungsten_bold">Delivery Status</td>
	      <td class="text12_tungsten_bold">Amount</td>
	      <td class="text12_tungsten_bold">Availed Date</td>
	      <td width="80">&nbsp;</td>
        </tr>

    

     <core:forEach var="data" items="${creditlist}">
	    <tr>

	      <td class="text12_tungsten">${data.companyname}</td>
	      <td class="text12_tungsten">${data.status}</td>
	      <td class="text12_tungsten">${data.delivery_status}</td>
	      <td class="text12_tungsten">${data.amount}</td>
	      <td><span class="text12_tungsten">${data.date_borrowed}</span></td>
	     
	     <core:if test="${type == 'unpaid'}">
	      <td class="text10_red">
	     <core:if test="${data.delivery_status == 'completed'}">
	      <a href="emergencyloadmanagement-edit.html?unpaid&cid=${data.creditid}" class="text10_red">Pay</a> | 
	     </core:if>
	   	<a href="emergencyloadmanagement-view.html?unpaid&cid=${data.creditid}" class="text10_red">View</a>
	     </td>
	     </core:if>
	     
	     <core:if test="${type == 'paid'}">
	     
	     <td class="text10_red"><a href="emergencyloadmanagement-edit.html?paid&cid=${data.creditid}" class="text10_red">edit</a> | <a href="emergencyloadmanagement-view.html?paid&cid=${data.creditid}" class="text10_red">view</a></td>
	     
	     </core:if>
	     
		 <core:if test="${type == 'cancelled'}">
	     
	     <td class="text10_red"><a href="emergencyloadmanagement-edit.html?cancelled&cid=${data.creditid}" class="text10_red">edit</a> | <a href="emergencyloadmanagement-view.html?cancelled&cid=${data.creditid}" class="text10_red">view</a></td>
	     
	     </core:if>
        </tr>
      
			</core:forEach>  
      
  
			
			 </core:if>	
			 
	<core:if test="${roleid == 7}">
	
		    <tr>

	      <td class="text12_tungsten_bold">Company </td>
	      <td class="text12_tungsten_bold">Payment Status</td>
	      <td class="text12_tungsten_bold">Delivery Status</td>
	      <td class="text12_tungsten_bold">Amount</td>
	      <td class="text12_tungsten_bold">Availed Date</td>
	      <td width="80">&nbsp;</td>
        </tr>

       
    	 <core:forEach var="data" items="${creditlist}">
    
	    <tr>

	      <td class="text12_tungsten">${data.companyname}</td>
	      <td class="text12_tungsten">${data.status}</td>
	      <td class="text12_tungsten">${data.delivery_status}</td>
	      <td class="text12_tungsten">${data.amount}</td>
	      <td><span class="text12_tungsten">${data.date_borrowed}</span></td>
	     
	     <core:if test="${type == 'unpaid'}">
	     
	     <td class="text10_red"><a href="emergencyloadmanagement-view.html?unpaid&cid=${data.creditid}" class="text10_red">view</a></td>
	     
	     </core:if>
	     
	     <core:if test="${type == 'paid'}">
	     
	     <td class="text10_red"><a href="emergencyloadmanagement-view.html?paid&cid=${data.creditid}" class="text10_red">view</a></td>
	     
	     </core:if>
	     
		 <core:if test="${type == 'cancelled'}">
	     
	     <td class="text10_red"><a href="emergencyloadmanagement-view.html?cancelled&cid=${data.creditid}" class="text10_red">view</a></td>
	     
	     </core:if>
        </tr>
      
			</core:forEach>  
			
			 </core:if>	 
			 
       

      </table>
          <div id='basic-modal-content'>      
		 </div>
		 
	</div>
  


</div><!----- End Of Container----->
<div id="footer"></div>
</div><!----- End of PAGE ----->


</body>
</html>
