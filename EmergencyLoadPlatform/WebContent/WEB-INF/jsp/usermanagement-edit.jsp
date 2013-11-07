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
<script type="text/javascript" src="../js/jquery.validate.js"></script>

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
	   $('#usermanagementform').submit(function(e){ // <<< This selector needs to point to your form.
	        if ($('#status').val() == "Select Status") {
	            alert("Please select valid status");
	            e.preventDefault();
	            return false;
	        }else
	        	 if ($('#companyname').val() == "Select Company") {
	 	            alert("Please select valid company");
	 	            e.preventDefault();
	 	            return false;
	 	        }else
	        if ($('#rolename').val() == "Select Rolename") {
	            alert("Please select valid rolename");
	            e.preventDefault();
	            return false;
	        }else if ($('#pass').val() != $('#pass2').val()) {
	            alert("Please select valid rolename");
	            e.preventDefault();
	            return false;
	        }
	    });
	   
	  $("#usermanagementform").validate({
		  
		  
			  	  rules: {
			  		name: {
			      required: true,
		
			      },
			      username: {
			      required: true
		
			      },
			      password: {
				      required: true
				      
			
				  },
				  password2: {
				      required: true,
				      equalTo: "#pass"
			
				  },
				  status: {
					  required: true
				
				  },
				  email_address: {
					  required: true
					
				  },
				  mobile: {
					  required: true,
					  digits: true
						
				  },
				  roleid: {
					   required: true
							
					},
					companyname: {
					   required: true
								
				  }
				      
			  }
	  


	  
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
   
    <core:if test="${roleid == 4 || roleid == 5 || roleid == 1 || roleid == 2 || roleid == 3 ||  roleid == 8}">

   <div id="content">
   
			<core:if test="${success == 'yes'}">
		<div class = "text12_tungsten_bold">New User Added Successfully</div>
		</core:if>
		
		<core:if test="${success == 'no'}">
		<div class = "text12_tungsten_bold">Adding of new user failed</div>
		</core:if>
		
		 <form:form action=""  commandName="usermanagementForm" id="usermanagementform">
 
  	    <core:forEach var="data" items="${user}">   
  	    
       <table width="700" border="0" align="center" cellpadding="3" cellspacing="0">
           <tr>
             <td width="150" class="text12_tungsten_bold">Name</td>
             <td>
               <form:input path="name" name="name"  id="name" size="50" value = "${data.name}"/>
            </td>
           </tr>
           <tr>
             <td class="text12_tungsten_bold">Username</td>
             <td><form:input path="username"  name="textfield2" id="uname" value = "${data.username}" /></td>
           </tr>
           <tr>
             <td class="text12_tungsten_bold">Password</td>
             <td><form:password path="password" name="textfield3" id="pass" class="pass"/></td>
           </tr>
           <tr>
             <td class="text12_tungsten_bold">Confirm Password</td>
             <td><form:password path="password2"  name="textfield4" id="pass2" class="pass2"/></td>
           </tr>
           <tr>
             <td class="text12_tungsten_bold">Status</td>
             <td><form:select path="status" style="width: 130px; text-align:left;"><form:options items="${statuslist}" />
				    </form:select></td>
           </tr>
           <tr>
             <td class="text12_tungsten_bold">Email</td>
             <td><form:input path="email_address" name="textfield5"  id="email" size="50" value = "${data.email_address}"/></td>
           </tr>
           <tr>
             <td class="text12_tungsten_bold">Company</td>
             <td><form:select path="companyname" style="width: 130px; text-align:left;"><form:options items="${companylist}" />
				    </form:select></td>
           </tr>
           <tr>
             <td class="text12_tungsten_bold">Mobile no</td>
             <td><form:input path="mobile" name="textfield7" id="mobile" value = "${data.mobile}"/></td>
           </tr>
           <tr>
             <td class="text12_tungsten_bold">Role</td>
             <td><form:select path="rolename" style="width: 130px; text-align:left;"><form:options items="${rolelist}" />
				    </form:select> </td>
           </tr>
           <tr>
             <td class="text12_tungsten_bold">&nbsp;</td>
             <td>&nbsp;</td>
           </tr>
       </table> 
           <div align="center"><input type="image" src="../css/images/submit.png"/></div>
       </core:forEach>
     </form:form>

   
    

    
    </div>
    
    </core:if>
    
      	  <core:if test="${roleid == 4 || roleid == 5 || roleid == 6 || roleid == 7}">
	 
	 <br><br><br><br><br>
	
	<center>	<div class="text12_tungsten_bold"> Access is Denied </div></center>
	
	  </core:if>
    
   </div> 
   </div>
  


</div><!----- End Of Container----->
<div id="footer"></div>
</div><!----- End of PAGE ----->
</body>
</html>
