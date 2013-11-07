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

    <core:if test="${roleid == 4 || roleid ==5}">


   <!----- Right-Sidebar ----->
	<div id="content">
	
	<core:if test="${emergencylist != 'null'}">
	
	
	<form:form action=""  commandName="reportForm" id="reportForm">
	
	
	<table width="90%" border="0" cellspacing="3" cellpadding="3">
	
	<tr>
		    
		        <td style="float:left;"><form:select path="dsp" style="width: 150px; text-align:left; vertical-align:top;"><form:options items="${fillbox}" />
				    </form:select> &nbsp; &nbsp; <input type="image" src="../css/images/submit.png" value="Search"  /> </td>
				    
	</tr>

	
	
	</table>
	
	
	</form:form>
	
	
	
	
	
	  <table width="740"  border="0" align="center" cellpadding="3" cellspacing="0">

	    <tr>

	      <td class="text12_tungsten_bold">Company Name</td>
	      <td class="text12_tungsten_bold">DSP</td>
	      <td class="text12_tungsten_bold">Status</td>
	      <td class="text12_tungsten_bold">Delivery Status</td>
	      <td class="text12_tungsten_bold">Amount</td>
	      <td class="text12_tungsten_bold">Date Availed</td>
	   
	    
        </tr>
	
        
        <core:forEach var="data" items="${emergencylist}">
	    <tr >

	      <td class="text12_tungsten">${data.companyname}</td>
	      <td class="text12_tungsten">${data.parentcompanyname}</td>
	      <td class="text12_tungsten">${data.status}</td>
	      <td class="text12_tungsten">${data.delivery_status}</td>
	      <td class="text12_tungsten">${data.amount}</td>
	      <td class="text12_tungsten">${data.dateavailed}</td>
	     
     
        </tr>
        </core:forEach>
	
      </table>
        
        
        <div  class="text12_tungsten" id = "downloadlink">
		<core:url value="/jsp/download/export/xls" var="downloadXls"/>
		<a href="${downloadXls}"><img src="../css/images/excel_icon.png" align="absmiddle">Click the icon to download in excel</a>
		</div>
		
        
        </core:if>
        
        <core:if test="${emergencylist == 'null'}">
        
        
        	<center>	<div class="text12_tungsten_bold"> No Record Found. </div></center>
        	
        	
        </core:if>

	</div>
	
	</core:if>
	
	  <core:if test="${roleid == 1 || roleid ==2 || roleid == 3 || roleid == 6 || roleid == 7 || roleid == 8}">
	 
	 <br><br><br><br><br>
	
	<center>	<div class="text12_tungsten_bold"> Access is Denied </div></center>
	
	  </core:if>

	
  


</div><!----- End Of Container----->
<div id="footer"></div>
</div><!----- End of PAGE ----->


</body>
</html>
