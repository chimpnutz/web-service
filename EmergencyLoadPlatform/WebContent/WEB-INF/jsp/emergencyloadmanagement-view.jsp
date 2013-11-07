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


<LINK href="../css/demo1.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/jquery.datePicker.js"></script>
<script type="text/javascript" src="../js/date.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="../css/datePicker.css">
<script type="text/javascript" src="../js/basic.js"></script>
<script type='text/javascript' src='../js/jquery.simplemodal.js'></script>
<LINK rel="stylesheet" href="../css/basic2.css" type="text/css">
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
<script type="text/javascript" charset="utf-8">
			
			$(document).ready(function()
			{    
				$('#amount').val('');
				$('#date1').val('');
				$('#date2').val('');

				
				var dateToday = new Date();
				Date.firstDayOfWeek = 0;
				Date.format = 'mm/dd/yyyy';
				var dateSelected = false;
				$('.date-pick').datePicker({				
					minDate: dateToday,
					onSelect: function(dateText, inst) 
					{
				
					},

					
				
				});	
			    			   			    

            });
</script>
<title>Emergency Load</title>
</head>

<body>
<div id="page"><!----- Start of PAGE ----->
<div id="container"><!-----Start of Container ----->

 <core:import url="menu.jsp"></core:import>
   
     <core:if test="${roleid == 1 || roleid == 2 || roleid == 3 || roleid == 4 || roleid ==5 || roleid ==6 || roleid ==7 || roleid == 8}">
          
	<div id="content">
	

          <br />
          
          		  <core:if test="${roleid == 1 || roleid == 2 || roleid == 3 || roleid == 8}">
          		  
         <core:forEach var="data" items="${creditlist}">

		  <table width="700" border="0" align="center" cellpadding="3" cellspacing="0">
	   
	    <tr>
	      <td width="150" class="text12_tungsten_bold">Company Name</td>
	      <td class="text12_tungsten">${data.companyname}</td>
        </tr>
        <tr>
	      <td width="150" class="text12_tungsten_bold">Amount</td>
	      <td class="text12_tungsten">${data.amount}</td>
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">Payment Status</td>
	      <td class="text12_tungsten">${data.status}</td>
        </tr>

      </table>
      
      
      	</core:forEach>
		  
		<div align="left" style="margin-left:35px;">
        <div class="text12_blue_bold">Payment Listing</div>
     
      
        <table width="600" border="1" align="left" cellpadding="3" cellspacing="0" bordercolor="#CCCCCC">
          <tr>
            <td colspan="3" class="text12_tungsten_bold"><a href="emergencyloadmanagement-edit.html"><img src="../css/images/add.png" width="28" height="28" border="0" align="absmiddle" /></a> Update Payment</td>
          </tr>
          <tr>
            <td width="150" class="text12_tungsten_bold">Payment Date</td>
            <td width="200" class="text12_tungsten_bold">New Status</td>
          </tr>
              <core:forEach var="data" items="${paymenthistory}">
          <tr>
       
            <td class="text12_tungsten_bold"><span class="text12_tungsten">${data.changedate}</span></td>
            <td class="text12_tungsten">${data.newstatus}</td>
         
          </tr>
          </core:forEach>
        </table>
       
		</div>
	
	</core:if>

	      <core:if test="${roleid == 4 || roleid == 5}">
	      
	      	   <core:forEach var="data" items="${creditlist}">

		  <table width="700" border="0" align="center" cellpadding="3" cellspacing="0">
	   
	    <tr>
	      <td width="150" class="text12_tungsten_bold">Company Name</td>
	      <td class="text12_tungsten">${data.companyname}</td>
        </tr>
        <tr>
	      <td width="150" class="text12_tungsten_bold">Amount</td>
	      <td class="text12_tungsten">${data.amount}</td>
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">Date Applied</td>
	      <td class="text12_tungsten">${data.date_borrowed}</td>
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">Payment Status</td>
	      <td class="text12_tungsten">${data.status}</td>
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">Delivery Status</td>
	      <td class="text12_tungsten">${data.delivery_status}</td>
        </tr>
        


      </table>
      
      
      	</core:forEach>
	      
	      <div align="left" style="margin-left:35px;">
<div class="text12_blue_bold">Transfer Load to Retailer List</div>
<table width="500" border="1" cellpadding="3" cellspacing="0" bordercolor="#cccccc">
  
	        <tr>
	          <td width="150" class="text12_tungsten_bold">Created Date</td>
	          <td width="150" class="text12_tungsten_bold">Transfer Date</td>
	          <td width="200" class="text12_tungsten"><span class="text12_tungsten_bold">Status</span></td>
	          <td width="150" class="text12_tungsten_bold">Amount</td>
	          <td width="150" class="text12_tungsten_bold">Action</td>
            </tr>
              	   <core:forEach var="data" items="${transferlist}">
	        <tr>
	          <td class="text12_tungsten_bold">${data.createddate}</td>
	          <td class="text12_tungsten">${data.transferdate}</td>
	          <td class="text12_tungsten">${data.transfer_status}</td>
	          <td class="text12_tungsten">${data.amount}</td>   
	           <td class="text12_tungsten">    
	          <core:if test="${data.transfer_status == 'initial'}">
	         <a href="emergencyloadmanagement-transfer.html?cid=${data.creditid}" class="text10_red">transfer</a>
        	  </core:if>
        	  </td>
          </tr>
          </core:forEach>
        </table>
<p>&nbsp;</p>
</div>           

<core:forEach var="data" items="${paymenthistory}">
		<div align="left" style="margin-left:35px;">
        <div class="text12_blue_bold">Payment Listing</div>
     
      
        <table width="600" border="1" align="left" cellpadding="3" cellspacing="0" bordercolor="#CCCCCC">

          <tr>
            <td width="150" class="text12_tungsten_bold">Payment Date</td>
                 <td width="150" class="text12_tungsten_bold">Amount</td>
            <td width="200" class="text12_tungsten_bold">New Status</td>
          </tr>
              
          <tr>
       
            <td class="text12_tungsten_bold"><span class="text12_tungsten">${data.changedate}</span></td>
                <td class="text12_tungsten">${data.amount}</td>
            <td class="text12_tungsten">${data.newstatus}</td>
         
          </tr>
       
        </table>
       
		</div>
	   </core:forEach>	
	      </core:if>
	  

	      
	            <core:if test="${roleid == 6}">
	      
	      	   <core:forEach var="data" items="${creditlist}">

		  <table width="700" border="0" align="center" cellpadding="3" cellspacing="0">
	   
	    <tr>
	      <td width="150" class="text12_tungsten_bold">Company Name</td>
	      <td class="text12_tungsten">${data.companyname}</td>
        </tr>
        <tr>
	      <td width="150" class="text12_tungsten_bold">Amount</td>
	      <td class="text12_tungsten">${data.amount}</td>
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">Date Applied</td>
	      <td class="text12_tungsten">${data.date_borrowed}</td>
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">Payment Status</td>
	      <td class="text12_tungsten">${data.status}</td>
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">Delivery Status</td>
	      <td class="text12_tungsten">${data.delivery_status}</td>
        </tr>

      </table>
      
      
      	</core:forEach>

	      
	      </core:if>

	      
	        <core:if test="${roleid == 7	}">
	      
	      	<core:forEach var="data" items="${creditlist}">

		  <table width="700" border="0" align="center" cellpadding="3" cellspacing="0">
	   
	    <tr>
	      <td width="150" class="text12_tungsten_bold">Company Name</td>
	      <td class="text12_tungsten">${data.companyname}</td>
        </tr>
        <tr>
	      <td width="150" class="text12_tungsten_bold">Amount</td>
	      <td class="text12_tungsten">${data.amount}</td>
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">Date Applied</td>
	      <td class="text12_tungsten">${data.date_borrowed}</td>
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">Payment Status</td>
	      <td class="text12_tungsten">${data.status}</td>
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">Delivery Status</td>
	      <td class="text12_tungsten">${data.delivery_status}</td>
        </tr>

      </table>
      
      
      	</core:forEach>
	             
	  
	      </core:if>
	      
	      
	      
	      
	      <core:if test="${roleid == 8}">
	      
	      	   <core:forEach var="data" items="${creditlist}">

		  <table width="700" border="0" align="center" cellpadding="3" cellspacing="0">
	   
	    <tr>
	      <td width="150" class="text12_tungsten_bold">Company Name</td>
	      <td class="text12_tungsten">${data.companyname}</td>
        </tr>
        <tr>
	      <td width="150" class="text12_tungsten_bold">Amount</td>
	      <td class="text12_tungsten">${data.amount}</td>
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">Date Applied</td>
	      <td class="text12_tungsten">${data.date_borrowed}</td>
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">Payment Status</td>
	      <td class="text12_tungsten">${data.status}</td>
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">Delivery Status</td>
	      <td class="text12_tungsten">${data.delivery_status}</td>
        </tr>

      </table>
      
      
      	</core:forEach>
	      
	      <div align="left" style="margin-left:35px;">
<div class="text12_blue_bold">Transfer Load to Retailer</div>
<table width="500" border="1" cellpadding="3" cellspacing="0" bordercolor="#cccccc">
        <tr>
	          <td colspan="3" class="text12_tungsten_bold"><img src="../css/images/add.png" width="28" height="28" align="absmiddle" /> Transfer Load to Retailer</td>
          </tr>
	        <tr>
	          <td width="150" class="text12_tungsten_bold">Transfer Date</td>
	          <td width="200" class="text12_tungsten"><span class="text12_tungsten_bold">Status</span></td>
	          <td width="150" class="text12_tungsten_bold">Amount</td>
            </tr>
	        <tr>

            </tr>
	        <tr>
	          <td class="text12_tungsten_bold"><span class="text12_tungsten">
	            <input name="textfield8" type="text" id="textfield8" size="10" />
              <img src="../css/images/calendar.png" width="28" height="28" align="absmiddle" /></span></td>
	          <td class="text12_tungsten"><select name="select3" class="text12_tungsten" id="select3">
	            <option>Initial</option>
	            <option>Processed</option>
              </select></td>
	          <td class="text12_tungsten">&nbsp;</td>
          </tr>
        </table>
<p>&nbsp;</p>
</div>           
		<div align="left" style="margin-left:35px;">
        <div class="text12_blue_bold">Payment Listing</div>
      
        <table width="600" border="1" align="left" cellpadding="3" cellspacing="0" bordercolor="#CCCCCC">
          <tr>
            <td colspan="3" class="text12_tungsten_bold"><a href="emergencyloadmanagement-edit.html"><img src="../css/images/add.png" width="28" height="28" border="0" align="absmiddle" /></a> Update Payment</td>
          </tr>
          <tr>
            <td width="150" class="text12_tungsten_bold">Payment Date</td>
            <td width="200" class="text12_tungsten_bold">Level</td>
            <td width="200" class="text12_tungsten_bold">New Status</td>
          </tr>
          <tr>
            <td class="text12_tungsten_bold"><span class="text12_tungsten">20,000</span></td>
            <td class="text12_tungsten">02-14-12</td>
            <td class="text12_tungsten">02-15-12</td>
          </tr>
        </table>
		</div>
	      
	      </core:if>
	      
<p>&nbsp;</p>
	    
   
	<p>&nbsp;</p>
	<p>&nbsp;</p>
    </div>
    
    
    </core:if>

    
    
<br />
</div><!----- End Of Container----->
<div id="footer"></div>
</div><!----- End of PAGE ----->


</body>
</html>
