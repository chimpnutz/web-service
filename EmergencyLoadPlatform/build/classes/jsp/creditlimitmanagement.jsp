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


   <!----- Right-Sidebar ----->
	<div id="content">
	  <table width="740"  border="0" align="center" cellpadding="3" cellspacing="0">
	    <tr>
	      <td colspan="7" class="text12_tungsten"><a href="creditlimitmanagement-add.html"><img src="../css/images/add.png" width="28" height="28" hspace="5" border="0" align="absmiddle" /></a><span class="lucida_12_tungsten_b">Credit Limit</span></td>
        </tr>
          <core:if test="${roleid == 4 || roleid ==5}">
               <tr>
	      <td colspan="7" class="text12_tungsten">Your Remaining Limit is :${balance}</td>
        </tr>
          </core:if>
    
	    <tr>

	      <td class="text12_tungsten_bold">Company Name</td>
	      <td class="text12_tungsten_bold">Credit Limit</td>
	      <td class="text12_tungsten_bold">Outstanding Balance</td>
	      <td class="text12_tungsten_bold">Remaining Balance</td>
	      <td class="text12_tungsten_bold">Status</td>
	      <core:if test="${ roleid ==4|| roleid ==5}">
	          
	            <core:if test="${type == 'unpaid' || type == 'paid' || type == 'cancelled'}">
	            	      <td class="text12_tungsten_bold">Payment Status</td>
	            </core:if>

	      </core:if>
	      <td>&nbsp;</td>
	      <td>&nbsp;</td>
        </tr>
	
        
        <core:forEach var="data" items="${creditList}">
	    <tr >

	      <td class="text12_tungsten"><a href="creditlimitmanagement-view.html?cid=${data.creditlimitid}" class="text12_red_bold">${data.companyname}</a></td>
	      <td class="text12_tungsten">${data.creditlimit}</td>
	      <td class="text12_tungsten">${data.outstandingbalance}</td>
	      <td class="text12_tungsten">${data.remaininglimit - data.outstandingbalance}</td>
	      <td class="text10_red">${data.status}</td>

	      
	         <core:if test="${roleid ==4|| roleid ==5}">
	       <td class="text10_red">${data.paymentstatus}</td>
	       	      <td class="text10_red"> <a href="creditlimitmanagement-edit.html?edit&cid=${data.creditlimitid}" class="text10_red">edit</a></td>
	       </core:if>
	      	      
	      <core:if test="${roleid == 1 || roleid == 2 || roleid == 3 ||  roleid == 8}">
	      
	      <core:if test="${type == 'unpaid'}">
	      
	       <td class="text10_red"><a href="creditlimitmanagement-edit.html?replenish&cid=${data.creditlimitid}" class="text10_red">replenish</a>  </td>
	    
	      </core:if>
	      
	      
	      </core:if>
	      
	      <core:if test="${ roleid == 4 || roleid ==5  }">
	      
	      <core:if test="${type == 'unpaid'}">
	       <core:if test="${data.outstandingbalance == 0}">
	        	<td class="text10_red"> <a href="creditlimitmanagement-edit.html?edit&cid=${data.creditlimitid}" class="text10_red">edit</a></td>
	       </core:if>
	         <core:if test="${data.outstandingbalance > 0}">
	         	 <td class="text10_red"><a href="creditlimitmanagement-edit.html?pay&cid=${data.creditlimitid}" class="text10_red">pay</a>  </td>
	        	<td class="text10_red"> <a href="creditlimitmanagement-edit.html?edit&cid=${data.creditlimitid}" class="text10_red">edit</a></td>
	        
	       </core:if>
	  
	   
	      </core:if>
	      
	      <core:if test="${type == 'paid'}">
	       <td class="text10_red"><a href="creditlimitmanagement-edit.html?cancel&cid=${data.creditlimitid}" class="text10_red">cancel</a> </td>
	    
	      </core:if>
	      
	      
	      </core:if>
	      
	     
        </tr>
        </core:forEach>
	
      </table>
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
