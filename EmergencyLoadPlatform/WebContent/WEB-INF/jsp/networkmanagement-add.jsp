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

<script type="text/javascript">
$(document).ready(function()
{    
	   $('#networkmanagementForm').submit(function(e)
			   { // <<< This selector needs to point to your form.
	        if ($('#status').val() == "Select Status") {
	            alert("Please select valid status");
	            e.preventDefault();
	            return false;
	        }else
	        	 if ($('#parentcompany').val() == "Select Parent Company") {
	 	            alert("Please select valid company");
	 	            e.preventDefault();
	 	            return false;
	 	        }
	   
	   });
	    
	   
	  $("#networkmanagementForm").validate({
		  
		  
			  	  rules: {
			  		companyname: {
			      required: true,
		
			      },
			      companyaddress: {
			      required: true
		
			      },
			      retailersim: {
				      required: true
			
				  },
				  mobile: {
					      required: true
				
					  },
				  contactperson: {
				      required: true
			
				  },
				  status: {
					  required: true
				
				  },
				  parentcompany: {
					  required: true
					
				  }
				      
			  }
	  


	  
	});
    
    
    
	$('#companyname').val('');
	$('#companyaddress').val('');
	$('#retailersim').val('');
	$('#contactperson').val('');
	$('#status').val('');
	$('#parentcompany').val('');
	$('#mobile').val('');


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

    <core:if test="${roleid == 1 || roleid == 2 || roleid == 3 || roleid == 4 || roleid ==5 || roleid == 8}">
   
   
	<div id="content">
	
	  <core:if test="${roleid == 1 || roleid == 2 || roleid == 3 || roleid == 8}">
	  
	  	 <form:form action=""  commandName="networkmanagementForm" id="networkmanagementForm" class="networkmanagementForm">
	
		<core:if test="${success == 'yes'}">
		<div class = "text12_tungsten_bold">Company Added Successfully</div>
		</core:if>
		
		<core:if test="${success == 'no'}">
		<div class = "text12_tungsten_bold">Adding of Company Failed</div>
		</core:if>
       <table width="700" border="0" align="center" cellpadding="3" cellspacing="0">

			 <tr>
              <td colspan="5" class="text12_tungsten"><span class="lucida_12_tungsten_b">Add PD</span></td>
            </tr>
            
           <tr>
             <td width="150" class="text12_tungsten_bold">Company Name</td>
             <td>
               <form:input path="companyname" name="companyname"  id="companyname" class="companyname" size="50" />
            </td>
           </tr>
           <tr>
             <td class="text12_tungsten_bold">Company Address</td>
             <td><form:input path="companyaddress"  name="companyaddress" id="companyaddress" class="companyaddress" /></td>
           </tr>
            <tr>
             <td class="text12_tungsten_bold">Mobile Number Sim No.</td>
             <td><form:input path="mobile" name="mobile" id="mobile" /></td>
           </tr>
           <tr>
             <td class="text12_tungsten_bold">Retailer Sim No.</td>
             <td><form:input path="retailersim" name="retailersim" id="retailersim" /></td>
           </tr>
           <tr>
             <td class="text12_tungsten_bold">Contact Person</td>
             <td><form:input path="contactperson"  name="contactperson" id="contactperson" class="contactperson"  /></td>
           </tr>
           <tr>
             <td class="text12_tungsten_bold">Status</td>
             <td><form:select path="status" id="status" class="status" contactpersonstyle="width: 130px; text-align:left;"><form:option value="Select Status" /><form:options items="${statuslist}" />
				    </form:select></td>
           </tr>

     
           <tr>
             <td class="text12_tungsten_bold">&nbsp;</td>
             <td>&nbsp;</td>
           </tr>
           
       </table> 
    <div align="center"><input type="image" src="../css/images/submit.png"/></div>
     </form:form>
	  
	  
	  </core:if>
	

	
	<core:if test="${roleid == 4 || roleid == 5 }">
	
	
	
		  	 <form:form action=""  commandName="networkmanagementForm" id="networkmanagementForm" class="networkmanagementForm">
	
		<core:if test="${success == 'yes'}">
		<div class = "text12_tungsten_bold">Company Added Successfully</div>
		</core:if>
		
		<core:if test="${success == 'no'}">
		<div class = "text12_tungsten_bold">Adding of Company Failed</div>
		</core:if>
       <table width="700" border="0" align="center" cellpadding="3" cellspacing="0">

			 <tr>
              <td colspan="5" class="text12_tungsten"><span class="lucida_12_tungsten_b">Add Company</span></td>
            </tr>
            
           <tr>
             <td width="150" class="text12_tungsten_bold">Company Name</td>
             <td>
               <form:input path="companyname" name="companyname"  id="companyname" class="companyname" size="50" />
            </td>
           </tr>
           <tr>
             <td class="text12_tungsten_bold">Company Address</td>
             <td><form:input path="companyaddress"  name="companyaddress" id="companyaddress" class="companyaddress" /></td>
           </tr>
            <tr>
             <td class="text12_tungsten_bold">Mobile Number Sim No.</td>
             <td><form:input path="mobile" name="mobile" id="mobile" /></td>
           </tr>
           <tr>
             <td class="text12_tungsten_bold">Retailer Sim No.</td>
             <td><form:input path="retailersim" name="retailersim" id="retailersim" /></td>
           </tr>
           <tr>
             <td class="text12_tungsten_bold">Contact Person</td>
             <td><form:input path="contactperson"  name="contactperson" id="contactperson" class="contactperson"  /></td>
           </tr>
           <tr>
             <td class="text12_tungsten_bold">Status</td>
             <td><form:select path="status" id="status" class="status" contactpersonstyle="width: 130px; text-align:left;"><form:option value="Select Status" /><form:options items="${statuslist}" />
				    </form:select></td>
           </tr>
           <tr>
             <td class="text12_tungsten_bold">Parent Company</td>
             <td><form:select path="parentcompany" id = "parentcompany" class ="parentcompany" style="width: 130px; text-align:left;">
				   <form:option value="Select Parent Company" /><form:options items="${companylist}" /> </form:select></td>
           </tr>
     
           <tr>
             <td class="text12_tungsten_bold">&nbsp;</td>
             <td>&nbsp;</td>
           </tr>
           
       </table> 
    <div align="center"><input type="image" src="../css/images/submit.png"/></div>
     </form:form>
	
	
	</core:if>
		

        

    
    
    </div>
    
    </core:if>
    
    
	  <core:if test="${roleid == 6 || roleid == 7}">
	 
	 <br><br><br><br><br>
	
	<center>	<div class="text12_tungsten_bold"> Access is Denied </div></center>
	
	  </core:if>
    

	    <div align="center"></div>
         <br /><br />
    
	</div>
 


</div><!----- End Of Container----->
<div id="footer"></div>
</div><!----- End of PAGE ----->


</body>
</html>
