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

<script type="text/javascript">

    $(document).ready(function()
    {
        $('#btnUpdate').click(function()
        {
            $(this).attr('disabled',true);

            return false;
        });
    });

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
       
	<core:if test="${roleid == 1 || roleid == 2 || roleid == 3 || roleid == 4 || roleid ==5 || roleid == 6 || roleid == 8}">
 
	<div id="content">
	
	<div class = "text12_tungsten_bold" id="eMsg"></div>
	
	<core:forEach var="data" items="${creditlist}">
	<input type="hidden" value="${data.companyid}" id="hdnCompid" class="hdnCompid">
	<input type="hidden" value="${data.creditid}" id="hdnCredid" class="hdnCredid">
	<input type="hidden" value="${data.amount}" id="hdnAmt" class="hdnAmt">
    <input type = "hidden" id="hdnOldstatus" class="hdnOldstatus" value="${data.status}">
	  <table width="700" border="0" align="center" cellpadding="3" cellspacing="0">
	    
	    <tr>
	      <td width="150" class="text12_tungsten_bold">Company</td>
	      <td class="text12_tungsten">${data.companyname}</td>
	      
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">Amount</td>
	      <td class="text12_tungsten">${data.amount}</td>
        </tr>
	    <tr>
	      <td class="text12_tungsten_bold">Payment Status</td>
	      
	       <core:if test="${type == 'unpaid'}">
	    	<td class="text12_tungsten">Paid  <input type = "hidden" id="hdnstatus" class="hdnstatus" value="paid"></td>
          
	      </core:if>
	      
	      <core:if test="${type == 'paid'}">
	 
          	<td class="text12_tungsten">Unpaid  <input type = "hidden" id="hdnstatus" class="hdnstatus" value="unpaid"></td>
          
	      </core:if>
	      
	      <core:if test="${type == 'cancelled'}">
	      
	       	<td class="text12_tungsten">Paid  <input type = "hidden" id="hdnstatus" class="hdnstatus" value="paid"></td>
	       	          
	      </core:if>
	      </tr>

	    <tr>
	      <td class="text12_tungsten_bold">&nbsp;</td>
	      <td><input type="image" src="../css/images/submit.png" id="btnUpdate" class="btnUpdate"></td>
        </tr>
      </table>
      </core:forEach>
	</div>
  

   </core:if>



</div><!----- End Of Container----->
<div id="footer"></div>
</div><!----- End of PAGE ----->


</body>
</html>
