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
<LINK href="../css/demo1.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/ajax.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>

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

<script type="text/javascript" charset="utf-8">
$(document).ready(function()
		{    
			   $('#creditlimitmanagementForm').submit(function(e){ // <<< This selector needs to point to your form.
			        if ($('#dsp').val() == "Select DSP") {
			            alert("Please select valid dsp");
			            e.preventDefault();
			            return false;
			        }else
			        	 if ($('#retailers').val() == "Select Retailers" || $('#retailers').val() == "") {
			 	            alert("Please select valid retailers");
			 	            e.preventDefault();
			 	            return false;
			 	        }
		
			    });
			   
			  $("#creditlimitmanagementForm").validate({
				  
					  	  rules: 
					  	  {
					  	  creditlimit: {
					      required: true,
					      digits: true
				
					      }
						      
					  	   }
			  


			  
			});
		    
		    
		    
			$('#amount').val('');



		});
</script>

<title>Emergency Load</title>
</head>

<body>
<div id="page"><!----- Start of PAGE ----->
<div id="container"><!-----Start of Container ----->

  <core:import url="menu.jsp"></core:import>
	
  <core:if test="${roleid == 1 || roleid == 2 || roleid == 3 || roleid == 4 || roleid ==5 || roleid == 8}">

   <form:form action=""  commandName="creditlimitmanagementForm" id="creditlimitmanagementForm">

	<div id="content">
	<core:if test="${result == 'yes'}">
	<div class = "text12_tungsten_bold">${msg}</div>
	</core:if>

	
	
	  <table width="700" border="0" align="center" cellpadding="3" cellspacing="0">
	  
		<core:if test="${roleid == 1}">
		
		<tr>
	      <td width="150" class="text12_tungsten_bold">PD</td>
	      <td><form:select path="pd"   style="width: 200px; text-align:left;"><form:option value="Select PD" /><form:options id = "dsp" class ="dsp" items="${companylist}" />
				    </form:select></td>
        </tr>
  
		
		</core:if>
		
		<core:if test="${roleid == 2}">
		
		<tr>
	      <td width="150" class="text12_tungsten_bold">PD</td>
	      <td><form:select path="pd"   style="width: 200px; text-align:left;"><form:option value="Select PD" /><form:options id = "dsp" class ="dsp" items="${companylist}" />
				    </form:select></td>
        </tr>
     
		
		</core:if>
		
		<core:if test="${roleid == 3}">
		
		<tr>
	      <td width="150" class="text12_tungsten_bold">PD</td>
	      <td><form:select path="pd"   style="width: 200px; text-align:left;"><form:option value="Select PD" /><form:options id = "dsp" class ="dsp" items="${companylist}" />
				    </form:select></td>
        </tr>
		
		</core:if>
		
		<core:if test="${roleid == 8}">
		
		<tr>
	      <td width="150" class="text12_tungsten_bold">PD</td>
	      <td><form:select path="pd"   style="width: 200px; text-align:left;"><form:option value="Select PD" /><form:options id = "dsp" class ="dsp" items="${companylist}" />
				    </form:select></td>
        </tr>
		
		</core:if>
		
		<core:if test="${roleid == 4 || roleid == 5}">
		
		<tr>
	      <td width="150" class="text12_tungsten_bold">DSP</td>
	      <td><form:select path="dsp"   style="width: 200px; text-align:left;"><form:option value="Select DSP" /><form:options id = "dsp" class ="dsp" items="${companylist}" />
				    </form:select></td>
        </tr>
        <tr>
	      <td width="150" class="text12_tungsten_bold">Retailers</td>
	      <td><form:select path="retailers" id = "retailers" class ="retailers" style="width: 200px; text-align:left; ">
				    </form:select></td>
        </tr>
        
		</core:if>
	

	    <tr>
	      <td class="text12_tungsten_bold">Amount</td>
	      <td class="text12_tungsten"><form:input path="creditlimit" id="amount" class="amount" size="20" /></td>
        </tr>
	

	    <tr>
	      <td class="text12_tungsten_bold">&nbsp;</td>
	      <td><input type="image" src="../css/images/submit.png"/></td>
        </tr>
      </table>
	 
	 
	
	 

 
 

	</div>
     </form:form>

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
