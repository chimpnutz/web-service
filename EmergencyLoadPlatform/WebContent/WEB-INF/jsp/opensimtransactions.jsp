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
<LINK rel="stylesheet" href="../css/basic.css" type="text/css">
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

<!--  <script type="text/javascript">
$(document).ready(function showSim(str){
	if (str=="")
	  {
	  document.getElementById("opensimnum").innerHTML="";
	  return;
	  } 
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
	    document.getElementById("opensimnum").innerHTML=xmlhttp.responseText;
	    }
	  }
	xmlhttp.open("GET","OpensimDao.java?q="+str,true);
	xmlhttp.send();
});
</script>
-->
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
	
	<core:if test="${roleid == 1 || roleid == 2 || roleid == 3 ||  roleid == 8}">
	
		<form:form action="" commandName="opensimtransactionsForm" id="opensimtransactionsForm">
		
		<form:select  path="opensim" id="opensim" class="opensim" name="opensim" style="width: 180px; text-align:left;"><form:option value="Select Open Sim Number" /><form:options items="${opensimlist}" />
				    </form:select>
	  
	  
	  <div id ="testdiv" class="testdiv"></div>
	  
	  <table width="740"  border="0" align="center" cellpadding="3" cellspacing="0" id="test" class="test">	  

				    <tr id="header" class ="header">
			
				      <td class="text12_tungsten_bold">Time</td>
				      <td class="text12_tungsten_bold">Open Sim</td>
				      <td class="text12_tungsten_bold">Balance</td>
				      <td width="65">&nbsp;</td>
			        </tr>
				
		
				   
					      	<tr>
					      		<td class="text12_tungsten"><div class="datetime" id="datetime"></div></td>
				          		<td class="text12_tungsten"><div class="opensimnum" id="opensimnum"></div></td>
							    <td class="text12_tungsten"><div class="balancenum" id="balancenum"></div></td>
				           </tr>
		         	
			        <core:forEach var="data" items="${opensimlogs}">
					  <tr class="testdata" id="testdata">
				
					      <td class="text12_tungsten">${data.date}</td>
					      <td class="text12_tungsten">${data.opensim}</td>
					      <td class="text12_tungsten">${data.balance}</td>
				
					      	           
				       </tr>
			        </core:forEach>
			       
					 
					
			</table>			
      	
      	
      	
      
      </form:form>
      
	</core:if>
	
	<core:if test="${roleid == 4 || roleid == 5 || roleid ==6 ||  roleid == 7}">
	
		 <br><br><br><br><br>
	
	<center>	<div class="text12_tungsten_bold"> Access is Denied </div></center>
	
	
	
	
	</core:if>

	
	
	
	
	
	  
		 
	</div>
  


</div><!----- End Of Container----->
<div id="footer"></div>
</div><!----- End of PAGE ----->


</body>
</html>

