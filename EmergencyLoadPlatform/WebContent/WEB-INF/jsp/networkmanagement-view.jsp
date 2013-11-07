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
	
      <form id="form1" name="form1" method="post" action="">
      	<core:forEach var="data" items="${company}">
	      <table width="700" border="0" align="center" cellpadding="3" cellspacing="0">

            <tr>
	          <td width="150" class="text12_tungsten_bold">Company Name</td>
	          <td class="text12_tungsten">${data.companyname}</td>
            </tr>
	        <tr>
	          <td class="text12_tungsten_bold">Company address</td>
	          <td class="text12_tungsten">${data.companyaddress}</td>
            </tr>
	        <tr>
	          <td class="text12_tungsten_bold">Retailer Sim No.</td>
	          <td class="text12_tungsten">${data.retailersim}</td>
            </tr>
	        <tr>
	          <td class="text12_tungsten_bold">Contact person</td>
	          <td class="text12_tungsten">${data.contactperson}</td>
            </tr>
	        <tr>
	          <td class="text12_tungsten_bold">Status</td>
	          <td class="text12_tungsten">${data.status}</td>
            </tr>
	        <tr>
	          <td class="text12_tungsten_bold">Level</td>
	          <td class="text12_tungsten">${data.level}</td>
            </tr>
	        <tr>
	          <td class="text12_tungsten_bold">Parent Company</td>
	          <td class="text12_tungsten">${data.parentcompany}</td>
            </tr>
             <tr>
	          <td class="text12_tungsten_bold">OpenSIM</td>
	          <td class="text12_tungsten">${data.opensim}</td>
            </tr>
  </table>
  </core:forEach>
	      <br />

<div align="left" style="margin-left:35px;">
<div class="text12_blue_bold">User List</div>
<table width="500" border="1" cellpadding="3" cellspacing="0" bordercolor="#cccccc">
        <tr>
	          <td colspan="3" class="text12_tungsten_bold"><img src="images/add.png" width="28" height="28" align="absmiddle" /> Add User</td>
            </tr>
	        <tr>
	          <td width="150" class="text12_tungsten_bold">Username</td>
	          <td width="200" class="text12_tungsten"><span class="text12_tungsten_bold">Role</span></td>
	
            </tr>
            <core:forEach var="data" items="${userlist}">
	        <tr>
		        
	          
	          <td class="text12_tungsten_bold"><span class="text12_tungsten">${data.username}</span></td>
	          <td class="text12_tungsten">${data.rolename}</td>

	          	
            </tr>
            </core:forEach>
          </table>
<p>&nbsp;</p>
</div>           
		<div align="left" style="margin-left:35px;">
        <div class="text12_blue_bold">Credit Limit List</div>
      
        <table width="600" border="1" align="left" cellpadding="3" cellspacing="0" bordercolor="#CCCCCC">
          <tr>
            <td colspan="4" class="text12_tungsten_bold"><img src="images/add.png" width="28" height="28" align="absmiddle" /> Credit Limit</td>
            </tr>
          <tr>
          	
            <td width="150" class="text12_tungsten_bold">Amount</td>
            <td width="150" class="text12_tungsten_bold">Status</td>
            <td width="150" class="text12_tungsten_bold">Created Date</td>
          </tr>
           <core:forEach var="data" items="${creditlimitlist}">
          <tr>
         
            <td class="text12_tungsten_bold"><span class="text12_tungsten">${data.creditlimit}</span></td>
            <td class="text12_tungsten">${data.status}</td>
            <td class="text12_tungsten">${data.date}</td>
           
          </tr>
 		 </core:forEach>
        </table>
		</div>
      </form>
	   
      <p>&nbsp;</p>
	    
    </div>
 
 </core:if>
 
	  <core:if test="${roleid == 4 || roleid == 5 || roleid == 6 || roleid == 7}">
	 
	 <br><br><br><br><br>
	
	<center>	<div class="text12_tungsten_bold"> Access is Denied </div></center>
	
	  </core:if>


</div><!----- End Of Container----->
<div id="footer"></div>
</div><!----- End of PAGE ----->


</body>
</html>
