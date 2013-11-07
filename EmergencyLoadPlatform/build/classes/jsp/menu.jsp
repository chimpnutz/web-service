<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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

 <div id="left-sidebar">
 	<img src="../css/images/companylogo.png" style="margin-left:12px; margin-top:5px;"/><hr style="margin:6px 10px; color:#cccccc;">
    
    <core:if test="${roleid == 1 || roleid == 2 || roleid == 3 || roleid == 8}">

		  <dl id="menu">
		  <dt onMouseOver="javascript:montre('smenu1');">Access Management</dt>
		  <dd id="smenu1" onMouseOver="javascript:montre('smenu1');" onMouseOut="javascript:montre();">
		   <ul>
		    <li><a href="usermanagement.html">User Management</a></li>
		    <li><a href="rolemanagement.html">Role Management</a></li>
		    <li><a href="privilegesmanagement.html">Privileges Management</a></li>

		   </ul>
		  </dd>
		  
		  <dt onMouseOver="javascript:montre('smenu2');" onMouseOut="javascript:montre();"><a href="holidaymanagement">Holiday Management</a></dt>

		  <dt onMouseOver="javascript:montre('smenu2');" onMouseOut="javascript:montre();"><a href="networkmanagement.html">Network Management</a></dt>
  
		 <dt onMouseOver="javascript:montre('smenu4');"><a href="creditlimitmanagement.html?unpaid">Credit Limit  Management</a></dt>

		  
		  <dt onMouseOver="javascript:montre('smenu5');" onMouseOut="javascript:montre();"><a href="opensimtransactions.html">Open Sim Transactions</a></dt>
		  
		</dl>
		
	
 		</core:if> 
 		
 		
		   <core:if test="${roleid == 4 || roleid == 5 }">
		
		  <dl id="menu">
		  
		  <dt onMouseOver="javascript:montre('smenu1');" onMouseOut="javascript:montre();"><a href="usermanagement.html">User Management</a></dt>
  
  
		  <dt onMouseOver="javascript:montre('smenu2');" onMouseOut="javascript:montre();"><a href="networkmanagement.html">Network Management</a></dt>
	
  
		 		 <dt onMouseOver="javascript:montre('smenu3');">Credit Limit  Management</dt>
		 	  <dd id="smenu3" onMouseOver="javascript:montre('smenu3');" onMouseOut="javascript:montre();">
		   <ul>
		    <li><a href="creditlimitmanagement.html"> Approved Credit Limit  </a></li>
		    <!--<li><a href="creditlimitmanagement.html?unpaid"> Unpaid Credit Limit  </a></li>
		    <li><a href="creditlimitmanagement.html?paid"> Paid Credit Limit  </a></li>-->
		    <li><a href="creditlimitmanagement.html?cancelled"> Cancelled Credit Limit  </a></li>
		   </ul>
		  </dd>
		   
		  	<dt onMouseOver="javascript:montre('smenu4');">Emergency Load Management</dt>
		 	  <dd id="smenu4" onMouseOver="javascript:montre('smenu4');" onMouseOut="javascript:montre();">
		   <ul>
		    <li><a href="emergencyloadmanagement.html?unpaid"> Unpaid Emergency Load</a></li>
		    <li><a href="emergencyloadmanagement.html?paid"> Paid Emergency Load</a></li>

		   </ul>
		  </dd>
		  
		      <dt onMouseOver="javascript:montre('smenu5');" onMouseOut="javascript:montre();"><a href="emergencyloadreport.html">Emergency load Report</a></dt>
  
		
		  
		</dl>
		 
		 
		 </core:if>
		  
   <core:if test="${roleid == 6}">

  <dl id="menu">

  		  		  <dt onMouseOver="javascript:montre('smenu4');">Emergency Load Management</dt>
		 	  <dd id="smenu4" onMouseOver="javascript:montre('smenu4');" onMouseOut="javascript:montre();">
		   <ul>
		    <li><a href="emergencyloadmanagement.html?unpaid"> Unpaid Emergency Load</a></li>
		    <li><a href="emergencyloadmanagement.html?paid"> Paid Emergency Load</a></li>

		   </ul>
		  </dd>
</dl>
 
 
 </core:if>
 
 
   <core:if test="${roleid == 7}">

  <dl id="menu">

 		  		  <dt onMouseOver="javascript:montre('smenu4');">Emergency Load Management</dt>
		 	  <dd id="smenu4" onMouseOver="javascript:montre('smenu4');" onMouseOut="javascript:montre();">
		   <ul>
		    <li><a href="emergencyloadmanagement.html?unpaid"> Unpaid Emergency Load</a></li>
		    <li><a href="emergencyloadmanagement.html?paid"> Paid Emergency Load</a></li>

		   </ul>
		  </dd>
  </dl>
 
 
 </core:if>

   </div>
   
   
   <div id="right-sidebar" class="text12_tungsten"><!----- Right-Sidebar ----->
   <div style="float:right; margin-right:10px; margin-top:5px;"><a href="logout.html"><img src="../css/images/sign-in.gif" hspace="5" align="absmiddle" /></a></div>
   </div> <!----- Right-Sidebar ----->

</html>