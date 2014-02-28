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


<LINK href="../css/style.css" rel="stylesheet" type="text/css">
<LINK href="../css/form.css" rel="stylesheet" type="text/css">
<link type='text/css' href='../tabcss/tabcontent.css' rel='stylesheet' media='screen' />
<link type='text/css' href='../tabcss/basic.css' rel='stylesheet' media='screen' />

<!--[if IE]>
<link type='text/css' href='../tabcss/basic_ie.css' rel='stylesheet' media='screen' />
<![endif]-->

<style type="text/css">

#basic-modal {
	width:100%;
	height:auto;
}
#divamount {
	padding:5px;
	
}

.branch_class {
		float:left;
		width:25%;
		margin-top:10px;
		font-size:12px;
		font-family:"Lucida Grande", "Lucida Sans Unicode", Helvetica, Arial, Verdana, sans-serif;
		color:#666;
		


	       }
.branchid_class {
		float:left;
		width:15%;
		margin-top:10px;
		font-size:12px;
		font-family:"Lucida Grande", "Lucida Sans Unicode", Helvetica, Arial, Verdana, sans-serif;
		color:#666;
		


		}
.balance_class {
		float:left;
		width:15%;
		margin-top:10px;
		font-size:12px;
		font-family:"Lucida Grande", "Lucida Sans Unicode", Helvetica, Arial, Verdana, sans-serif;
		color:#666;
		
}
.mobile_no{ 
		float:left;
		width:15%;
		margin-top:10px;
		font-size:12px;
		font-family:"Lucida Grande", "Lucida Sans Unicode", Helvetica, Arial, Verdana, sans-serif;
		color:#666;
		}

.amount_class {
		float:left;
		width:20%;
		margin-top:10px;
		font-size:12px;
		font-family:"Lucida Grande", "Lucida Sans Unicode", Helvetica, Arial, Verdana, sans-serif;
		color:#666;
}

.status  {
	float:left;
	width:15%;
	font-size:12px;
	font-family:"Lucida Grande", "Lucida Sans Unicode", Helvetica, Arial, Verdana, sans-serif;
	color:#666;	
}

.statusmsg  {
	float:left;
	width:15%;
	font-size:12px;
	font-family:"Lucida Grande", "Lucida Sans Unicode", Helvetica, Arial, Verdana, sans-serif;
	color:#666;	
}

.basic  {
	margin-top:50px;
	margin-bottom:20px;
	margin-left:300px;
	
}


#data {

	width: 600px;
	margin-left : 0px;
	float: left;
}

#data2 {

	width: 20%;
	float: right;
	margin-top: 10px;
	margin-right: 5px;
	font-size:12px;
	font-family:"Lucida Grande", "Lucida Sans Unicode", Helvetica, Arial, Verdana, sans-serif;
	color:#666
}

input[type="string"], input[type="text"], input[type="password"]
{
padding:3px;
background:#eff0f0;
border-top-width:thin solid#535454;
border-bottom-width:thin solid#eff0f0;
outline:none;
}


</style>

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
		
         $('#closeModal1').click(function() {
        	   
        	 $('#error1').empty();

         	});
         
         $('#closeModal2').click(function() {
      	   
         	 $('#error2').empty();
                

          	});
         
         $('#closeModal3').click(function() {
      	   
         	 $('#error3').empty();
                

          	});
    
    	   
       $('.yes').click(function() {
    	   
          var pw = $('.password').val();
          
          if(pw == '')
          {

        	  $("div#error1").html("Please input your password.");
        	  
        	  $(".password").focus();
          }
          else
          {
        	  doCheckuser();
        	 // $('#error1').empty();
          }

          

    	});
       
       $('.password').keypress(function(e){
    	    if ( e.which == 13 )
    	    {
    	    	
    	    	return false;

    	    }
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
       
  		$('.retrieveYes').click(function() {
 		   	   
       	   var pw = $('.retrievePassword').val();
       	   
             if(pw == '')
             {
           	
              $("div#error3").html("Please input your password.");
              
           	  $(".retrievePassword").focus();
           	  
             }
             else
             {
            	 doCheckRetrieveuser();
            	// $('#error3').empty();
             }
             
             $('.retrievePassword').keypress(function(e){
 	    	    if ( e.which == 13 )
 	    	    {
 	    	    	
 	    	    	return false;

 	    	    }
 	    	});
           
           
        	   

    	});
  		
  	     $('.modalclose').click(function() {
  	    	var url = "transfercredits.html";    
  	    	
			$.modal.close();
			
			$(location).attr('href',url);
			
  	    	});
  	     
  	     $('.modalclose1').click(function() {
   	    	var url = "transfercredits.html";    
   	    	
 			$.modal.close();
 			
 			$(location).attr('href',url);
 			
   	    	});
  	     
  	   		$('.modalclose2').click(function() {
  	    	var url = "transfercredits.html";    
  	    	
			$.modal.close();
			
			$(location).attr('href',url);
			
  	    	});
  		
  
       
       });
       
       
       
       
        function doAjaxPost(i) {
        	
     
        var amount;
        var branchid;
        var amt;
        var bid;
        
        $('#loader1').show();

    		// do something with `substr[i]`
    		 amount =  $("input[name='amount["+i+"]']").val();
             branchid = $("input[name='branchid["+i+"]']").val();
             
             
             
             
             if(amount == "" && branchid != ""){
           	            
            	 amt = "";
        	     bid = branchid;
            	
             }
             else
            	 
             	 amt = amount;
         	     bid = branchid;

        $.ajax(
        		
        {
        	
        type: "POST",
        url: "transfercredits.html",
        data: "amount=" + amt + "&branchid=" + bid,
     	async: false,

        success: 
        function(response){
 		

						if(response != ""){
							
							$('<div class="statusmsg-'+i+'" style="padding-bottom: 10px; margin-left: -29px" > </div>').appendTo('div#data2').append(response);
													
						}
						
						$('#loader1').hide();

        },
    
        
        error: function(e){
        alert('Error: ' + e);
        }
        
        
        });
        
        
        }
        
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
            url: "transfercredits.html?mode=transfertoRetailer",
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
        
        function doRetrieveAjaxPost(i) {
        	
    
            var amount;
            var branchid;
			var amt;
            var bid;
            
            $('#loader3').show();
        	
    		// do something with `substr[i]`
	   		 	amount =  $("input[name='RetrieveAmount["+i+"]']").val();
	            branchid = $("input[name='RetrieveBranchid["+i+"]']").val();
	            
	            if(amount == "" && branchid != ""){
	          	            
		           	 amt = "";
	        	     bid = branchid;
	           	
	            }
	            else
	           	 
	            	 amt = amount;
	        	     bid = branchid;

            $.ajax(
            		
            {
            	
            type: "POST",
            url: "transfercredits.html?mode=retrievefrBranch",
            data: "amount=" + amt + "&branchid=" + bid,
            async: false,
            
            success: function(response){
   
        		if(response != ""){
					
					$('<div class="statusmsg-'+i+'" style="padding-bottom: 10px;" > </div>').appendTo('div#data2').append(response);
		
				}
        		
        		$('#loader3').hide();
	        	             
            },
        
            
            error: function(e){
            alert('Error: ' + e);
            }
            
            
            });
            
        	
            
            }
        

        
        function doCheckuser() {
        	
            var pw;

    		// do something with `substr[i]`
	   		pw =  $('.password').val();  
    		
	   		$('#error1').empty();
	        $("div#closeModal1").hide();
		    $(".yes").hide();
		    $("p").hide();
		    $(".password").hide();


            $.ajax(
            		
            {
            	
            type: "POST",
            url: "transfercredits.html?mode=validUser",
            data: "pw=" + pw,
            async: false,
            
            success: function(response){
            	
            	$('.password').val('');
            	$("div#error1").empty();
            	
   
        		if(response == "SUCCESS")
        		{

        			    	   var txtbox = $("input[name^=branchid]").length;
        			           
        			    	   $("h3").html("You transferred the following:");
        			    	      
        			           for (var i = 0; i < txtbox; i++) {
        			        	   
        			        		doAjaxPost(i);
        			                          
        			           }
        			           
        			
        				      $('#error1').empty();
        			         // $(".icon1").show();
        			    
        			           
        			           
        			           

				}else{
						
			        $("div#closeModal1").show();
				    $(".yes").show();
				    $("p").show();
				    $(".password").show();
					$("div#error1").html("Incorrect Password.");
					$(".password").focus();
					
				}
    		
            
    		
    					        	             
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
            url: "transfercredits.html?mode=validUser",
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
        
        function doCheckRetrieveuser() {
        	
        	

            var pw;

    		// do something with `substr[i]`
	   		 pw =  $('.retrievePassword').val();
    		
	   		 $('#error3').empty();
	   	     $("div#closeModal3").hide();
		     $(".retrieveYes").hide();
		     $("p").hide();
		     $(".retrievePassword").hide();	
		    
            $.ajax(
            		
            {
            	
            type: "POST",
            url: "transfercredits.html?mode=validUser",
            data: "pw=" + pw,
            async: false,
            
            success: function(response){
            	
            	$('.retrievePassword').val('');
            	$("div#error3").empty();
   
        		if(response == "SUCCESS"){
        			
        			
        	    	  	   var txtbox = $("input[name^=RetrieveBranchid]").length;
        	    	  	   
        	    	  	  $("h3").html("You transferred the following:");
        	           
        		           for (var i = 0; i < txtbox; i++) {
        		        	   
        		        	   doRetrieveAjaxPost(i);
        		                          
        		           }
        		           
     			   
    			           
        		           $("div#error3").empty();
    			          // $(".icon3").show();

  
        			        	   
				
				}else{
					
					$("div#closeModal3").show();
				    $(".retrieveYes").show();
				    $("p").show();
				    $(".retrievePassword").show();	
					$("div#error3").html("Incorrect Password.");
					$(".retrievePassword").focus();
					
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

<div id="wrapper"><br>

<div align="center"><table align="center" style="width:900px; ">
	<tr>
		<td width="300" class="smalltext" style="float:left; text-align: left;">Hi  <span class="smalltext_orange"><%= session.getAttribute( "USER" ) %>, </span> you're logged in as 
		
		<core:if test="${user == 'user'}">
		
		<font color="silver"><%= session.getAttribute( "USERTYPE" ) %></font>
		
		</core:if>  
		
		<core:if test="${user == 'manager'}">
		
		<font color="#2b6991"><%= session.getAttribute( "USERTYPE" ) %></font>
	
		</core:if>  
		
		</td>
        <td width="300" style="text-align:center;"><a href="main.jsp"><img title="main" onmouseover="this.src='../css/images/main-icon-on.png';" onmouseout="this.src='../css/images/main-icon-off.png';" src="../css/images/main-icon-off.png" alt="" width="37" height="38" border="0"></a></td>
        <td width="300" class="smalltext" valign="top" style="text-align:right;"><a href="logout.html"><img title="logout" onmouseover="this.src='../css/images/logout-on.png';" onmouseout="this.src='../css/images/logout.png';" src="../css/images/logout.png" alt="" width="32" height="32" border="0"></a></td>
	</tr>
    
</table>

<div class="smalltext" style="margin-top: -26px; margin-left: 63px; float:left;"><%= new java.text.SimpleDateFormat("MMMM dd,yyyy").format(new java.util.Date()) %></div>
<div><img style="margin-top: -35px; margin-left: 33px; float:left;" src="../css/images/user2.png" width="22" height="25" /></div>

</div>

<div style="clear:both"></div>

<div id="container">
<div id="content">


<p class="title">Wallet Transfer</p>

<core:if test="${user == 'user'}">


				
<!--<div class="smalltext">Your Sub-dealer name is, ${details.partnername}</div>-->
<div class="smalltext">Your Personal Wallet is, PHP ${myWallet}</div>



</core:if>


<core:if test="${user == 'manager'}">

<core:forEach var="details" items="${currentwallet}">
				
<!--<div class="smalltext">Your Sub-dealer name is: ${details.partnername}</div>-->
<div class="smalltext" style="margin-bottom:20px;">Your Sub-dealer Wallet is: <span class="smalltext_red">PHP  ${partnerwallet}</span></div>
<!--<div class="smalltext">Your Personal Wallet is: PHP ${details.wallet}</div>-->
</core:forEach>	
 
</core:if>

		<core:if test="${user == 'user'}">
		
		<script src="../js2/creditsuser.js"></script>
		
		<ul id="tablist">
		<li><a href="#" class="current" >To Web Retailer</a></li>
		<li><a href="#" onClick="return expandcontent('sc2', this)">To Retailer SIM</a></li>
		<li><a href="#" >Retrieve</a></li>
		</ul>

		</core:if>  
		
		<core:if test="${user == 'manager'}">
		
		<core:if test="${retailer != 'null'}">
		
		<script src="../js2/creditsmanager.js"></script>

		<ul id="tablist">
		<li><a href="#" class="current" onClick="return expandcontent('sc1', this)">To Web Retailer</a></li>
		<li><a href="#" onClick="return expandcontent('sc2', this)">To Retailer SIM</a></li>
		<li><a href="#" onClick="return expandcontent('sc3', this)">Retrieve</a></li>
		</ul>

		</core:if>

		
		<core:if test="${retailer == 'null'}">
		
		<script src="../js2/creditsuser.js"></script>

		<ul id="tablist">
		<li><a href="#" class="current" >To Web Retailer</a></li>
		<li><a href="#" onClick="return expandcontent('sc2', this)">To Retailer SIM</a></li>
		<li><a href="#" >Retrieve</a></li>
		</ul>

		</core:if>
	
		</core:if>  
		
<div id="tabcontentcontainer">
<div id="sc1" class="tabcontent">
 <div id="basic-modal-1" class="basic-modal">
 
         <form id="topupform-1" action="" onSubmit="return false" id="topupform-b1" >
           
   	 	 <div class="branch_class" style="margin-left:10px;">Branch Name</div>
 		 <div class="branchid_class" style="margin-left:0px;">Branch ID</div>
  		 <div class="balance_class" style="margin-left:20px;">Balance </div>
  		 <div class="amount_class" style="margin-left:13px;">Amount</div>

     	
     	 <div style="clear:both;"></div>
     	 
     	 <core:forEach var="data" items="${partners}"  varStatus="status" >
     	 
		 <div id="divamount" >
      	 
        	 <div id="branchname" class="branch_class" style="margin-left:7px;">${data.branchname}</div>
        	 <div id="branchid" class="branchid_class" style="margin-left:2px;">${data.branchid}</div>
        	 <div id="balance" class="balance_class" style="margin-left:19px;">${data.branchwallet}</div>
         	
         	
         	 
       		 <input type="text" name="amount[${status.index}]" id="amount" style="margin-left:15px; background: #fff;"/>
       		 <input type="hidden" name="branchid[${status.index}]" id="branchid" value= "${data.branchid}"/>
       
       	<div style="clear:both;"></div>
       
       	 </div>
       	       	 
       	   </core:forEach>
       	         
            <div style="clear:both;"></div>

       		 <input type="image"  src="../css/images/button.png"  name="basic"  id="basic-1" class="basic" value="Confirm"/> 
     	  
     	  </form>
     	  
    
</div><!-- Basic modal -->

<!-- modal content -->


<div id="basic-1-content" class="basic-content">

<div class="modalclose" style="width:25px; height:29px; display:inline; z-index:3200; position:absolute; top:-15px; right:-16px; cursor:pointer;"><img src="../css/images/x.png" alt="" width="25" height="29" border="0"></div>

<table class="icon1" align="center" style="width:50%; display:none; ">
	<tr>
        <td width="300" style="text-align:center;"><a href="main.jsp"><img title="main" onmouseover="this.src='../css/images/main-icon-on.png';" onmouseout="this.src='../css/images/main-icon-off.png';" src="../css/images/main-icon-off.png" alt="" width="37" height="38" border="0"></a></td>
	</tr>

</table>


	<h3>You're transferring the following:</h3>
	
	
	
		 <form id="topupform-1" action="" onSubmit="return false" >
		 
 		 <div class="branch_class">Branch Name</div>
 		 <div class="branchid_class" style="margin-left:-56px;" >Branch ID</div>
  		 <div class="balance_class" style="margin-left:46px;">Balance </div>
  		 <div class="amount_class" style="margin-left:33px;">Amount</div>
         <div class="status" style="margin-top:10px; margin-left:-15px;">Result</div>

         <div style="clear:both;"></div>
         
 		 <div id="data"></div>
 		 	 
 		 <div id="data2"></div>
         
         <div style="clear:both"></div>
         
         <div align="center" style="margin-top:50px; margin-bottom:20px;">
         
         <div id="loader1" style='display:none'>
		 <img src="../css/images/loader.gif" width="37" height="38" border="0"/>
		 </div>
     
         <p class="text">Please enter your password:</p>
         		
		 <input type="password" name="password" class="password"  size="20" style="background:#FFF;">
		
		  <div class = "text" id="error1"></div>	
			
  	     </div>
  <!--	
	<div class='no simplemodal-close' id="closeModal1" style="float:left; margin-left:260px; margin-top:30px;">
		<input type="image" src="../css/images/cancel.png" value="cancel">
	</div>
	
	-->

	<div class="yes" style="float:right; margin-right:370px; margin-top:30px;" >
		<input type="image" src="../css/images/ok.png" value="ok">
	</div>
	
	<p>&nbsp;</p>


	<!-- preload the images -->
				<div style='display:none'>
					<img src='../css/images/x.png' alt='' />
				</div>
                
        </form>
</div><!-- basic modal content -->
</div><!--sc1-->



<!-- start of sc2-->

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
      	 
         <div id="branchname" class="branch_class" style="margin-left:7px;">${data.branchname}</div>
         <div id="branchid" class="branchid_class" style="margin-left:2px;">${data.branchid}</div>
		 <div id="mobile" class="mobile_no" style="margin-left:61px;">${data.msisdn}</div>
		 
       	 <input type="text" name="RetailerAmount[${status.index}]" id="amount" style="margin-left:57px; background: #fff;"/>
          <input type="hidden" name="RetailerMobNo[${status.index}]" id="mobno" value= "${data.msisdn}"/>
         
         
         <div style="clear:both;"></div>
         
         </div>
         
       
</core:forEach>


 <input type="image"  src="../css/images/button.png"  name="basic"  id="basic-2" class="basic" value="Confirm"  /> 
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
		 
 		 <div class="branch_class">Branch Name</div>
 		 <div class="branchid_class">Branch ID</div>
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
 
		<div class="retailerYes" style="float:right; margin-right:370px; margin-top:30px;">
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

<!--start of sc3-->


<div id="sc3" class="tabcontent">
<div id="basic-modal-3"  class="basic-modal">
   	
     <form id="topupform-b3" action="" onSubmit="return false">
     
          <div class="branch_class" style="margin-left:10px;">Branch Name</div>
 		 <div class="branchid_class" style="margin-left:0px;">Branch ID</div>
  		 <div class="balance_class" style="margin-left:20px;">Balance </div>
  		 <div class="amount_class" style="margin-left:13px;">Amount</div>
    
		 <div style="clear:both;"></div>
     	 
     	 <core:forEach var="data" items="${partners}"  varStatus="status" >
     	 
		 <div id="divamount" >
      	 
        	 <div id="branchname" class="branch_class" style="margin-left:7px;">${data.branchname}</div>
        	 <div id="branchid" class="branchid_class" style="margin-left:2px;">${data.branchid}</div>
        	 <div id="balance" class="balance_class" style="margin-left:19px;">${data.branchwallet}</div>
         	
         	
         	 
       		 <input type="text" name="RetrieveAmount[${status.index}]" id="amount" style="margin-left:15px; background: #fff;"/>
       		 <input type="hidden" name="RetrieveBranchid[${status.index}]" id="branchid" value= "${data.branchid}"/>
       
       		<div style="clear:both;"></div>
       
       	 </div>
       	       	 
       	   </core:forEach>
       	         
            <div style="clear:both;"></div>


		<input type="image"  src="../css/images/button.png"  name="basic"  id="basic-3" class="basic" value="Confirm" /> 

</form>
</div><!--basic modal-->

<!-- modal content -->

<div id="basic-3-content" class="basic-content">

<div class="modalclose2" style="width:25px; height:29px; display:inline; z-index:3200; position:absolute; top:-15px; right:-16px; cursor:pointer;"><img src="../css/images/x.png" alt="" width="25" height="29" border="0"></div>

<table class="icon3" align="center" style="width:50%; display:none; ">
	<tr>
        <td width="300" style="text-align:center;"><a href="main.jsp"><img title="main" onmouseover="this.src='../css/images/main-icon-on.png';" onmouseout="this.src='../css/images/main-icon-off.png';" src="../css/images/main-icon-off.png" alt="" width="37" height="38" border="0"></a></td>
	</tr>

</table>
			<h3>You're transferring the following:</h3>
		 <form id="topupform-3" action="" onSubmit="return false">
  		 <div class="branch_class">Branch Name</div>
 		 <div class="branchid_class" style="margin-left:-56px;" >Branch ID</div>
  		 <div class="balance_class" style="margin-left:47px;">Balance </div>
  		 <div class="amount_class" style="margin-left:30px;">Amount</div>
         <div class="status" style="margin-top:10px; margin-left:14px;">Result</div>

         <div style="clear:both;"></div>
         
 		 <div id="data"></div>
 		 
 		 <div id="data2"></div>
 		 
 		 <div style="clear:both;"></div>
        
         <div align="center" style="margin-top:50px; margin-bottom:20px;">
         
         <div id="loader3" style='display:none'>
		 <img src="../css/images/loader.gif" width="37" height="38" border="0"/>
		 </div>
         
         <p class="text">Please enter your password:</p>
         
		 <input type="password" name="password"  size="20" class="retrievePassword" style="background:#FFF;">

		 <div class = "text" id="error3"></div>	
		  
  		</div>
<!--
		<div class='no simplemodal-close' id="closeModal3" style="float:left; margin-left:260px;  margin-top:30px; margin-bottom:20px;">
		<input type="image" src="../css/images/cancel.png" value="cancel">
		</div>	
		
		-->
		
		<div class="retrieveYes" style="float:right; margin-right:370px;  margin-top:30px; margin-bottom:20px;" >
		<input type="image" src="../css/images/ok.png" value="ok">
		</div>
		
		<!-- preload the images -->
				<div style='display:none'>
					<img src='../images/x.png' alt='' />
				</div>
                </form>
</div><!--basic-modal-content-->
</div><!--sc3-->


</div><!--tabcontentcontainer-->

</div>
</div>

<div id="footer"></div>
</div><!--wrapper-->
</div><!--page-->

</body>
</html>