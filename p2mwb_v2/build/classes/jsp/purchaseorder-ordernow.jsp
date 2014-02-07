<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="../css/images/favicon.ico" >
<LINK href="../css/style.css" rel="stylesheet" type="text/css">
<LINK href="../css/form.css" rel="stylesheet" type="text/css">
<LINK href="../css/font.css" rel="stylesheet" type="text/css">
<LINK href="../css/tabs.css" rel="stylesheet" type="text/css">
<link href="../css/unibar.css" rel="stylesheet" type="text/css" />
<link href="../css/updatepo.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/header.js"></script>
<script type="text/javascript" src="../js/dropdown.js"></script>
<script src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/POvalidation.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
  <script>
  $(document).ready(function(){
	    $("#purchaseorderForm").validate({
	  	  rules: {
	  	  amount: {
	      required: true,
	      number: true
	      },
	      bank: {
	      required: true
	      },
	      branch: {
		      required: true
		  },
	  },  messages: {
	      amount:
	      {
	    	  required: "Please Input Amount",
	    	  number:	"Please Input Valid Amount"
	      },
	      bank: 
	      {
	  
	    	  required: "Please Input Bank. "
	 
	      },
	      branch: 
	      {
	  
	    	  required: "Please Input Branch. "
	 
	      }   
      }
	});
	    

	    $('#amount').val('');
	    $('#bank').val('');
	    $('#branch').val('');
	    $('#remarks').val('');
	});
  
  </script>
  
  <script type="text/javascript">

  $(document).ready(function() {


  var count1=0;
  var count2 = 1;
  
  //$('#wallet').empty();

  
  $('a#addorder').click(function() {
    count1 +=1;
    count2 +=1;

    $('<p><div style="float:left;width: 100%;"><hr/><div id="ordernew"><p class="text12_tungsten"> &nbsp;Item Code:<select id="ordernew2" name="PO['+count1+'].item" class="selectItem"> <option value="none">Please Choose Item</option><core:forEach var="data" items="${item}">	<option value="${data.itemname}">${data.itemname}</option></core:forEach></select style></p></div>	<input type="hidden" id="PO['+count1+'].amount" /><div id="qantity"><p class="text12_tungsten"> &nbsp;Quantity:<input type="text" name="PO['+count1+'].quantity" id="qty" /></p></div><div id="wallet"><p class="text12_tungsten"> &nbsp; Wallet types:<select id="wallet2" name"PO[0].wallet"><core:forEach var="data" items="${wallet}"><option value="${data.wallet}">${data.wallet}</core:forEach></select style></br><span class="inline"></p></div></option><a href="#" class="remove" id="remove" ><img src="../css/images/cross.png"><div id="adj"></div></a></div></p>').fadeIn("slow").appendTo('#wallet');     

  });
  
  
  $('.remove').live('click', function() {
      $(this).parent().fadeOut(300, function(){ 
        $(this).remove();
        return false;
    });
  });
  
  

  $(".selectItem").live('change', function() {
	  
 		  var name = $(this).attr('name');
 		  
 		  var x = name.indexOf("["); 
		  var y = name.indexOf("]");
		  var pos = name.substring(x+1,y); 
		  
   	      var input = $("select[name='"+name+"']").val();
   	      
   	      if(input == "LOP"){
   	    	  
   	    	$("select[name='PO["+pos+"].wallet']").removeAttr("disabled");
   	      }
   	     else
         {
   	    	$("select[name='PO["+pos+"].wallet']").attr("disabled", "disabled");
         }
           
   

  });
  
  $(".selectItem2").change(function() {
	  
 		  var name = $(this).attr('name');
 		  
 		  var x = name.indexOf("["); 
		  var y = name.indexOf("]");
		  var pos = name.substring(x+1,y); 
		  
   	      var input = $("select[name='"+name+"']").val();
   	      
   	      if(input == "LOP"){
   	    	  
   	    	$("select[name='PO["+pos+"].wallet']").removeAttr("disabled");
   	      }
   	     else
         {
   	    	$("select[name='PO["+pos+"].wallet']").attr("disabled", "disabled");
         }
   	     
	
  });
  
  


  
  



});
</script>
  
<title>Pay PhilExchange</title>
</head>

<body>
<div id="page">
<div id="unibarlogo"><a id="main" href="logout.html?redirect=main"><img src="../css/images/payexchangeinc.gif" alt="Pay Phil Exchange" border="0" style="margin-left:-5px;" /></a></div>

<div id="unibar">

<div class="unibarnav" id="payphilexchange">
<ul>
	<li><a href="#" rel="dropmenu1">About</a> |</li>
	<li><a id="prod" href="logout.html?redirect=products">Products & Services</a> |</li>
	<li><a id="sol" href="logout.html?redirect=solutions">Solutions Development</a> |</li>
    <li><a id="part" href="logout.html?redirect=partners">Partners</a></li>

</ul>
</div>

<!--1st drop down menu -->                                                   
<div id="dropmenu1" class="unibar_dropmenu">
<a id="lead" href="logout.html?redirect=leadership">Leadership</a>
<a id="car" href="logout.html?redirect=careers">Careers</a>
<a id="cont" href="logout.html?redirect=contact">Contact us</a>
</div>

<script type="text/javascript">

cssdropdown.startchrome("payphilexchange")

</script>

</div>

<div id="wrapper"><br />
 <core:import url="menu.jsp"></core:import>
<div style="clear:both"></div>
<div id="container">
<div id="content">

<p class="text18_tungsten">Purchase Order</p>




<core:if test="${user == 'viewer'}">

<br>

<center>Cannot Access Page</center>

<br>
<br>


</core:if>

<core:if test="${user == 'superadmin'}">


<br>

<center>Cannot Access Page</center>

<br><br>



</core:if>

<core:if test="${user == 'user' || user=='manager'}">







<core:if test="${user == 'user'}">

<core:if test="${status == 'success'}">

<div class="text10_red">${msg}</div>

</core:if>

<core:if test="${status == 'fail'}">

<div class="text10_red">${msg}</div>

</core:if>

<div align="center">
 		<ol id="toc">
            <li class="current"><a href="purchaseorder-ordernow.html">Order Now</a></li>   
            <li><a href="purchaseorder-history.html">Purchase Order</a></li>   
      	</ol>
</div>
<p>&nbsp;</p>

<form:form action=""  commandName="purchaseorderForm" id="purchaseorderForm">
		
		   <label class="text12_tungsten_bold">
		   	<core:if test="${msg=='success' }">
		   		${message}
		   	</core:if>
		   </label>
		
              <div id="ordernew">
	        <p class="text12_tungsten"> &nbsp;Item Code:
	       <select id="ordernew2" name="PO[0].item" class="selectItem2">
	       
	       <option value="none">Please Choose Item</option>
		   <core:forEach var="data" items="${item}">
																				 
			<option value="${data.itemname}">${data.itemname}</option>
						
			</core:forEach>
	      </select style>
	      <core:if test="${valid=='fails'}">
			<label class="test12_tungsten_bold">${message}</p></label>
			</core:if>
	      </p>
	      </div>
	
		<input type="hidden" name="PO[0].amount" value="" />
	
        <div id="qantity">
        <p class="text12_tungsten"> &nbsp;Quantity:
        <input type="text" name="PO[0].quantity" id="qty"/>
        <core:if test="${valid=='fail'}">
		${message}
		</core:if>
        <br/> <span class="inline"></p>       
        </div>





        <div id="wallet" > 
        <p class="text12_tungsten"> &nbsp;Wallet types:
        <select id="wallet2" name="PO[0].wallet">
	   <core:forEach var="data" items="${wallet}">
																			 
		<option value="${data.wallet}">${data.wallet}</option>
				
					         
						
		</core:forEach>
        </select style>
          <br/>
           <span class="inline"></p>
        </div>
      
  <p><a href="#" id="addorder" ><input type="button" value="Add New Order" style="margin-left:30px;" class="addnewbtn addnewbtn-primary"/></a>
  <input type="image" value="Submit" style="margin-left:45px;" class="button" id="btnprocess"/></p>

  	
  </form:form>      
     

</core:if>




<core:if test="${user == 'manager'}">

<core:if test="${status == 'success'}">

<div class="text10_red">${msg}</div>

</core:if>

<core:if test="${status == 'fail'}">

<div class="text10_red">${msg}</div>

</core:if>

<div align="center">
 		<ol id="toc">
            <li class="current"><a href="purchaseorder-ordernow.html">Order Now</a></li>  
            <li><a href="purchaseorder-retailer.html">Retailer</a></li>    
            <li><a href="purchaseorder-history.html">Purchase Order</a></li>  
      	</ol>
</div>
<div>

</div>
<p>&nbsp;</p>

<form:form action=""  commandName="purchaseorderForm" id="purchaseorderForm">
		
		   <label class="text12_tungsten_bold">
		   	<core:if test="${msg=='success' }">
		   		${message}
		   	</core:if>
		   </label>
		
              <div id="ordernew">
	        <p class="text12_tungsten"> &nbsp;Item Code:
	       <select id="ordernew2" name="PO[0].item" class="selectItem2">
	       
	       <option value="none">Please Choose Item</option>
		   <core:forEach var="data" items="${item}">
																				 
			<option value="${data.itemname}">${data.itemname}</option>
						
			</core:forEach>
	      </select style>
	      <core:if test="${valid=='fails'}">
			${message}
			</core:if>
	      </p>
	      </div>
	
		<input type="hidden" name="PO[0].amount" value="" />
	
        <div id="qantity">
        <p class="text12_tungsten"> &nbsp;Quantity:
        <input type="text" name="PO[0].quantity" id="qty"/>
        <core:if test="${valid=='fail'}">
		${message}
		</core:if>
        <br/> <span class="inline"></p>       
        </div>





        <div id="wallet" > 
        <p class="text12_tungsten"> &nbsp;Wallet types:
        <select id="wallet2" name="PO[0].wallet">
	   <core:forEach var="data" items="${wallet}">
																			 
		<option value="${data.wallet}">${data.wallet}</option>
				
					         
						
		</core:forEach>
        </select style>
          <br/>
           <span class="inline"></p>
        </div>
      
  <p><a href="#" id="addorder" ><input type="button" value="Add New Order" style="margin-left:30px;" class="addnewbtn addnewbtn-primary"/></a>
  <input type="image" value="Submit" style="margin-left:45px;" class="button" id="btnprocess"/></p>

  	
  </form:form>
  

</core:if>

</core:if>

</div>
</div>


<div style="clear:both"></div>
<div id="footer"></div>
</div><!--wrapper-->
</div><!--page-->


</body>
</html>
