<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="../css/images/favicon.ico" >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="refresh" content="<%= session.getMaxInactiveInterval() %>;URL='logout.html?expire=yes'">
<title>Pay PhilExchange</title>

<link href="../css/style.css" rel="stylesheet" type="text/css"/>
<link href="../css/tabs.css" rel="stylesheet" type="text/css" />
<link type='../text/css' href='../tabcss/tabcontent.css' rel='stylesheet' media='screen' />
<link type='text/css' href='../tabcss/basic.css' rel='stylesheet' media='screen' />
<link href="../css/wallettransfer.css" rel="stylesheet" type="text/css" />

<script src="../js2/jquery-1.8.2.js"></script>
<script type="text/javascript">
   
    $(document).ready(function() {

	$('.basic-modal').each(function()
	 {
		readymodal('#' + $(this).attr('id'));
		checkamount('#' + $(this).attr('id'));

	 });
	 $('.basic-content').each(function()
	 {
		 $(this).hide();
		
	 }
	 );
    });
	
	function readymodal(basicmodal) 
	{
		$(basicmodal).children('form').children('#divamount').mouseenter(function()
		{
            //  $('#divamount').children().find('#amount').show();
			//$('#amount').show();
			$(this).children('#amount').show();
            
		});
		$(basicmodal).children('form').children('#divamount').children('#amount').mouseout(function ()
		{	
	
			 checkamount('#' + $(this).parent().parent().parent().attr('id')); 
		});
		
		$(basicmodal).children('form').children('#divamount').children('#amount').keypress(function(event){
 
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if(keycode == '13'){

				checkamount('#' + $(this).parent().parent().parent().attr('id'));
				event.preventDefault();
	
     

			}
			return true;
		});
	}
	
	function checkamount(basicmodal)
	{
		
		
		flg = false;

		
		$(basicmodal).children('form').children('#divamount').children('#amount').each(function ()
		{
		   
			if ($(this).val() != '') 
			{
				flg = true;
				
			}
			else
				$(this).hide();
		});


		if (flg)
		    $(basicmodal).children('form').children('.basic').show();
		else
			$(basicmodal).children('form').children('.basic').hide();
			
	}
	

</script>
       <script type="text/javascript">	
       
       $(document).ready(function() {
		
   
         $('#closeModal2').click(function() {
      	   
         	 $('#error2').empty();
                

          	});
         
       
		$('.retailerYes').click(function() {
	   	      
            var pw = $('.retailerPassword').val();
            
            if(pw == '')
            {          	  
              $("div#error2").html("Please input your password.");
              
          	  $(".retailerPassword").focus();
            }
            else
            {
            	doCheckRetaileruser();
            	//$('#error2').empty();
            }
         
     	});
		
	       $('.retailerPassword').keypress(function(e){
	    	    if ( e.which == 13 )
	    	    {
	    	    	
	    	    	return false;

	    	    }
	    	});
       
  
 
  	     
  	     $('.modalclose1').click(function() {
   	    	var url = "loadretailer-sim.html";    
   	    	
 			$.modal.close();
 			
 			$(location).attr('href',url);
 			
   	    	});
  	     

  
       
       });
       
      
        function doRetailerAjaxPost(i) {
        	
        	

            var amount;
			var mobileno;
			var amt;
			var mobno;
			
			$('#loader2').show();
            
        	
    		// do something with `substr[i]`
	   		 	amount =  $("input[name='RetailerAmount["+i+"]']").val();
	   		    mobileno = $("input[name='RetailerMobNo["+i+"]']").val();
	   		             
	            if(amount == "" && mobileno != ""){
	          	            
	            	amt = "";
	            	mobno = mobileno;
	            }
	            else
	           	 
	            	 amt = amount;
	         		 mobno = mobileno;

            $.ajax(
            		
            {
            	
            type: "POST",
            url: "loadretailer-sim.html?mode=transfertoRetailer",
            data: "amount=" + amt + "&mobileno=" + mobno,
            async: false,
            
            success: function(response){
   
        		if(response != ""){
					
					$('<div class="statusmsg-'+i+'" style="padding-bottom: 10px; margin-left:-60px;" > </div>').appendTo('div#data2').append(response);
					
						
				}
        		
        		$('#loader2').hide();
    		
            
    		
    					        	             
            },
        
            
            error: function(e){
            alert('Error: ' + e);
            }
            
            
            });
            
        	
            
            }
        
  
        

        

        
   function doCheckRetaileruser() {
        	
        	

            var pw;

	   	    pw =  $('.retailerPassword').val();
	   		 
	   	    $('#error2').empty();
	   	    $("div#closeModal2").hide();
		    $(".retailerYes").hide();
		    $("p").hide();
		    $(".retailerPassword").hide();
   
		    	    
            $.ajax(
            		
            {
            	
            type: "POST",
            url: "loadretailer-sim.html?mode=validUser",
            data: "pw=" + pw,
            async: false,
            
            success: function(response){
            	
            	$('.retailerPassword').val('');
            	$("div#error2").empty();
   
        		if(response == "SUCCESS"){
        			
        	     	   var txtbox = $("input[name^=RetailerMobNo]").length;
        	     	   
        	       	   $("h3").html("You transferred the following:");
        	            
        	           for (var i = 0; i < txtbox; i++) 
        	           {
        	         	   
        	         	  doRetailerAjaxPost(i);
        	                           
        	           }
        	           
        	          
        	           $("div#error2").empty();
			          // $(".icon2").show();
						
  
				}else{
					
			  	    $("div#closeModal2").show();
				    $(".retailerYes").show();
				    $("p").show();
				    $(".retailerPassword").show();
					$("div#error2").html("Incorrect Password.");
					$(".retailerPassword").focus();
					
					
				}
    		
            
    		
    					        	             
            },
        
            
            error: function(e){
            alert('Error: ' + e);
            }
            
            
            });
            
        	
            
            }
        

        
     
        
        </script>
<!-- Load jQuery, SimpleModal and Basic JS files -->

<script type='text/javascript' src='../js2/jquery.simplemodal.js'></script>
<script type='text/javascript' src='../js2/basic.js'></script>
<link href="../css/unibar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/header.js"></script>
<script type="text/javascript" src="../js/dropdown.js"></script>
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
<p class="text18_tungsten">Load Retailer</p>

<core:if test="${user == 'viewer'}">

<br>

<center>Cannot Access Page</center>

<br>
<br>


</core:if>


<core:if test="${user == 'user' || user == 'manager'}">


<core:if test="${user == 'user'}">

				
<!--<div class="smalltext">Your Sub-dealer name is, ${details.partnername}</div>-->
<p class="text12_tungsten">Your Personal Wallet is, PHP ${myWallet}</p>



</core:if>


<core:if test="${user == 'manager'}">

<core:forEach var="details" items="${currentwallet}">
				
<!--<div class="smalltext">Your Sub-dealer name is: ${details.partnername}</div>-->
<div class="text12_tungsten" style="margin-bottom:20px;">Your Sub-dealer Wallet is: <span class="text12_red">PHP  ${partnerwallet}</span></div>
<!--<div class="smalltext">Your Personal Wallet is: PHP ${details.wallet}</div>-->
</core:forEach>	
 
</core:if>

		<core:if test="${user == 'user'}">
		
	<ol id="toc">

	<li class="current"><a href="loadretailer-sim.html">To retailer sim</a></li>

	</ol>
    
    </core:if>  
		
		<core:if test="${user == 'manager'}">

	
    
    <ol id="toc">
		<li><a href="loadretailer-web.html">To web retailer</a></li>
		<li class="current"><a href="loadretailer-sim.html">To retailer sim</a></li>
        <li><a href="loadretailer-retrieve.html">Retrieve</a></li>
	</ol>
	
		
	
	</core:if> 
	
	
<div class="content">

<core:if test="${retailers == 'null'}">

		<br/>
		
		<center>No Available Retailer Sim.</center>
		
		<br/><br/>

</core:if>

<core:if test="${retailers != 'null'}">
	
	<div id="tabcontentcontainer">
<div id="sc2" class="tabcontent">
<div id="basic-modal-2"  class="basic-modal">
   	
     <form id="topupform-b2" action="" onSubmit="return false">     
 		 <div class="branch_class"  style="margin-left:10px;">Branch Name</div>
 		 <div class="branchid_class"  style="margin-left:0px;">Branch ID</div>
 		 <div class="mobile_no" style="margin-left:61px;">Mobile no</div>
  		 <div class="amount_class"  style="margin-left:57px;">Amount</div>
  		 
  		 <div style="clear:both;"></div>
    	
    	<core:forEach var="data" items="${retailers}" varStatus="status">

      	 <div id="divamount" >
      	 
         <div id="branchname" class="branch_class" style="margin-left:7px;">${data.partnername}</div>
         <div id="branchid" class="branchid_class" style="margin-left:2px;">${data.partnerid}</div>
		 <div id="mobile" class="mobile_no" style="margin-left:61px;">${data.msisdn}</div>
		 
       	 <input type="text" name="RetailerAmount[${status.index}]" id="amount" style="margin-left:57px; background: #fff;"/>
          <input type="hidden" name="RetailerMobNo[${status.index}]" id="mobno" value= "${data.msisdn}"/>
         
         
         <div style="clear:both;"></div>
         
         </div>
         
       
</core:forEach>


 <input type="image"  src="../css/images/button.png"  name="basic"  id="basic-2" class="basic" value="Confirm"/> 
</form>


</div><!--basic modal-->


<!-- modal content -->

<div id="basic-2-content" class="basic-content">

<div class="modalclose1" style="width:25px; height:29px; display:inline; z-index:3200; position:absolute; top:-15px; right:-16px; cursor:pointer;"><img src="../css/images/x.png" alt="" width="25" height="29" border="0"></div>

<table class="icon2" align="center" style="width:50%; display:none; ">
	<tr>
        <td width="300" style="text-align:center;"><a href="main.jsp"><img title="main" onmouseover="this.src='../css/images/main-icon-on.png';" onmouseout="this.src='../css/images/main-icon-off.png';" src="../css/images/main-icon-off.png" alt="" width="37" height="38" border="0"></a></td>
	</tr>

</table>
		 <h3>You're transferring the following:</h3>
		 
		 <form id="topupform-2" action="" onSubmit="return false">
		 
 		 <div class="branch_class">Partner Name</div>
 		 <div class="branchid_class">Partner ID</div>
 		 <div class="mobile_no" style="margin-left: 2px;">Mobile no</div>
  		 <div class="amount_class"style="margin-left: 26px; width: 10%;">Amount</div>
         <div class="status" style="margin-top:10px; margin-left:30px;">Result</div>
         
         <div style="clear:both"></div>
         
 		 <div id="data"></div>
 		
 		 <div id="data2"></div>
         
         <div style="clear:both"></div>
         
         <div align="center" style="margin-top:50px; margin-bottom:20px;">
         
         <div id="loader2" style='display:none'>
		 <img src="../css/images/loader.gif" width="37" height="38" border="0"/>
		 </div>
         
         <p class="text">Please enter your password:</p>
                
		 <input type="password" name="password" class="retailerPassword"  size="20" style="background:#FFF;">
		
		 <div class = "text" id="error2"></div>	
		 
  		 </div>

<!--
		<div class='no simplemodal-close' id="closeModal2" style="float:left; margin-left:260px; margin-top:30px;">
			<input type="image" src="../css/images/cancel.png" value="cancel">
		</div>
 -->
 
		<div class="retailerYes" style="float:right; margin-right:380px; margin-top:30px;">
			<input type="image" src="../css/images/ok.png" value="ok">
		</div>
	
		<p>&nbsp;</p>
	
	
		<!-- preload the images -->
				<div style='display:none'>
					<img src='../css/images/x.png' alt='' />
				</div>
				
          </form>
          
</div><!--basic modal content-->
</div><!--sc2-->

</div>
		    
   </core:if>


</div>

</core:if>

</div><!--content-->
</div><!--container-->


<div style="clear:both"></div>



<div id="footer"></div>
</div><!--wrapper-->
</div><!--page-->


</body>
</html>
