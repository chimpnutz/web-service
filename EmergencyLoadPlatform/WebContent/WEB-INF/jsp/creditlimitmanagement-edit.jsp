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
<script type='text/javascript' src='../js/jquery.simplemodal.js'></script>
<script type="text/javascript" src="../js/basic.js"></script>
<script type="text/javascript" src="../js/ajax.js"></script>



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
   
   <core:if test="${roleid == 1 || roleid == 2 || roleid == 3 || roleid == 8}">

	
	<core:if test="${type == 'replenish'}">
	
		<div id="content">
			<core:if test="${success == 'yes'}">
	<div class = "text12_tungsten_bold">${msg}</div>
	</core:if>
	
	<core:if test="${success == 'no'}">
	<div class = "text12_tungsten_bold">${msg}</div>
	</core:if>
	
	<div class = "text12_tungsten_bold" id="eMsg"></div>
	
	<core:forEach var="data" items="${creditlist}">
	<input type="hidden" value="${data.companyid}" id="hdnCompid" class="hdnCompid">
	<input type="hidden" value="${data.creditlimitid}" id="hdnCredid" class="hdnCredid">
	<input type="hidden" value="${data.outstandingbalance}" id="hdnAmt" class="hdnAmt">
    <input type = "hidden" id="hdnOldstatus" class="hdnOldstatus" value="${data.status}">
	  <table width="700" border="0" align="center" cellpadding="3" cellspacing="0">
	    
	    <tr>
	      <td width="150" class="text12_tungsten_bold">Company</td>
	      <td class="text12_tungsten">${data.companyname}</td>
	      
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">Amount</td>
	      <td class="text12_tungsten">${data.outstandingbalance}</td>
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">Payment Status</td>
	      <td class="text12_tungsten">Paid  <input type = "hidden" id="hdnstatus" class="hdnstatus" value="paid"></td>
	      </tr>

	    <tr>
	      <td class="text12_tungsten_bold">&nbsp;</td>
	      <td><input type="image" src="../css/images/submit.png" id="btnUpdate" class="btnUpdate"></td>
        </tr>
      </table>
      </core:forEach>
	</div>
  
		</core:if>
		

</core:if>


   <core:if test="${roleid == 4 || roleid ==5 }">
   
    <core:if test="${txtype == 'edit'}">
   
	<div id="content">
	<core:if test="${success == 'yes'}">
	<div class = "text12_tungsten_bold">${msg}</div>
	</core:if>
	
	<core:if test="${success == 'no'}">
	<div class = "text12_tungsten_bold">${msg}</div>
	</core:if>

	<form:form action=""  commandName="creditlimitmanagementForm" id="creditlimitmanagementForm">
	
	  <table width="700" border="0" align="center" cellpadding="3" cellspacing="0">
	 
	    <tr>
	    
	      <td width="150" class="text12_tungsten_bold">Company Name</td>
	      <core:forEach var="data" items="${creditList}">
	      <td class="text12_tungsten">${data.companyname}</td>
	      <form:hidden path="creditlimitid" name="textfield5"  id="creditlimitid" size="20" value='${data.creditlimitid}'/>
      	   </core:forEach>
        </tr>
      
	    <tr>
	      <td class="text12_tungsten_bold">Amount</td>
	      <td class="text12_tungsten"><form:input path="creditlimit" name="textfield5"  id="amount" size="20" class="amount"/></td>
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">Status</td>
	      <td><form:select path="status"   style="width: 200px; text-align:left;"><form:option value="Select Status" /><form:options id = "status" class ="status" items="${statuslist}" />
				    </form:select></td>
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">&nbsp;</td>
	      <td><input type="image" src="../css/images/submit.png"/></td>
        </tr>
      </table>
 
      </form:form>
          
	</div>
	
	   </core:if>
	
	<core:if test="${txtype == 'pay'}">
	
		<div id="content">
			<core:if test="${success == 'yes'}">
	<div class = "text12_tungsten_bold">${msg}</div>
	</core:if>
	
	<core:if test="${success == 'no'}">
	<div class = "text12_tungsten_bold">${msg}</div>
	</core:if>
	
	<div class = "text12_tungsten_bold" id="eMsg"></div>
	
	<core:forEach var="data" items="${creditlist}">
	
	<input type="hidden" value="${data.companyid}" id="hdnCompid" class="hdnCompid">
	<input type="hidden" value="${data.creditlimitid}" id="hdnCredid" class="hdnCredid">
	<input type="hidden" value="${data.outstandingbalance}" id="hdnAmt" class="hdnAmt">
    <input type = "hidden" id="hdnOldstatus" class="hdnOldstatus" value="${data.status}">
	  <table width="700" border="0" align="center" cellpadding="3" cellspacing="0">
	    
	    <tr>
	      <td width="150" class="text12_tungsten_bold">Company</td>
	      <td class="text12_tungsten">${data.companyname}</td>
	      
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">Amount</td>
	      <td class="text12_tungsten">${data.outstandingbalance}</td>
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">Payment Status</td>
	      <td class="text12_tungsten">Paid  <input type = "hidden" id="hdnstatus" class="hdnstatus" value="paid"></td>
	      </tr>

	    <tr>
	      <td class="text12_tungsten_bold">&nbsp;</td>
	      <td><input type="image" src="../css/images/submit.png" id="btnUpdate" class="btnUpdate"></td>
        </tr>
      </table>
      
      </core:forEach>
	</div>
  
		</core:if>
		
		
		<core:if test="${txtype == 'cancel'}">
		
			<div id="content">

	<div class = "text12_tungsten_bold">${msg}</div>


	
	
	<form:form action=""  commandName="creditlimitmanagementForm" id="creditlimitmanagementForm">
	 <core:forEach var="data" items="${creditList}">
	  <table width="700" border="0" align="center" cellpadding="3" cellspacing="0">
	 
	    <tr>
	    
	      <td width="150" class="text12_tungsten_bold">Company Name</td>
	     
	      <td class="text12_tungsten">${data.companyname}</td>
	      <form:hidden path="creditlimitid" name="textfield5"  id="creditlimitid" size="20" value='${data.creditlimitid}'/>
      	 
        </tr>
      
	    <tr>
	      <td class="text12_tungsten_bold">Credit limit</td>
	      <td class="text12_tungsten">${data.creditlimit}</td>         
        </tr>
         
	    <tr>
	      <td class="text12_tungsten_bold">Status</td>
	      <td class="text12_tungsten"> Cancel<form:hidden path="status" name="textfield5"  id="creditlimitid" size="20" value='cancel'/></td>
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">&nbsp;</td>
	      <td><input type="image" src="../css/images/submit.png"/></td>
        </tr>
      </table>
  </core:forEach>
      </form:form>
          
	</div>
		
		
		
		</core:if>
   
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
