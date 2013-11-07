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
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/ajax.js"></script>
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
	    <form:form method="POST" commandName="usermanagementForm">
	  <table width="740"  border="0" align="center" cellpadding="3" cellspacing="0">
	    <tr>
	      <td colspan="6" class="text12_tungsten"><a href="networkmanagement-add.html"><img src="../css/images/add.png" width="28" height="28" hspace="5" border="0" align="absmiddle" /></a><span class="lucida_12_tungsten_b">Company</span></td>
        </tr>
	    <tr>
	      <td width="75" class="text12_tungsten_bold">Disable</td>
	      <td width="150" class="text12_tungsten_bold">Company Name</td>
	      <td width="150" class="text12_tungsten_bold">Parent Company</td>
	      <td width="150" class="text12_tungsten_bold">Level</td>
	      <td width="150" class="text12_tungsten_bold">Contact Person</td>
	      <td width="65">&nbsp;</td>
        </tr>

          <core:forEach var="data" items="${companylist}">
	    <tr>
	      <td class="text10_red"><input type="image" src="../css/images/x.png"  id = "delcomp" class="delcomp"  value ="${data.companyid}"/><input type="hidden"  id = "hiddenroleid" class="hiddencompid" name="hiddencompid" value ="${data.companyid}"/></td>
	      <td class="text12_tungsten">${data.companyname}</td>	   
	      <td class="text12_tungsten">${data.parentcompany}</td>
	      <td class="text12_tungsten">${data.level}</td>
	      <td class="text12_tungsten">${data.contactperson}</td>
	      <td class="text10_red"><a href="networkmanagement-edit.html?cid=${data.companyid}" class="text10_red">edit</a> | <a href="networkmanagement-view.html?cid=${data.companyid}" class="text10_red"> view</a></td>
        </tr>
        </core:forEach>

      </table>
           </form:form>
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
