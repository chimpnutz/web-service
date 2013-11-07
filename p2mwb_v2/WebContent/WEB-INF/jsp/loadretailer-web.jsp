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
  	    	var url = "loadretailer-web.html";    
  	    	
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
        url: "loadretailer-web.html",
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
            url: "loadretailer-web.html?mode=validUser",
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
        
   
     
        
        </script>

</head>

<!-- Load jQuery, SimpleModal and Basic JS files -->

<script type='text/javascript' src='../js2/jquery.simplemodal.js'></script>
<script type='text/javascript' src='../js2/basic.js'></script>
<link href="../css/unibar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/header.js"></script>
<script type="text/javascript" src="../js/dropdown.js"></script>

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
				
		<br/>
		
		<center>Cannot Access Page</center>
		
		<br/><br/>

  		</core:if>  
		
		<core:if test="${user == 'manager'}">
		
		
		
    
	    <ol id="toc">
			<li class="current"><a href="loadretailer-web.html">To web retailer</a></li>
			<li><a href="loadretailer-sim.html">To retailer sim</a></li>
	        <li><a href="loadretailer-retrieve.html">Retrieve</a></li>
		</ol>
   
	
		</core:if> 
    
    
<div class="content">

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
      	 
        	 <div id="branchname" class="branch_class" style="margin-left:7px;">${data.partnername}</div>
        	 <div id="branchid" class="branchid_class" style="margin-left:2px;">${data.partnerid}</div>
        	 <div id="balance" class="balance_class" style="margin-left:19px;">${data.partnerwallet}</div>
         	
         	
         	 
       		 <input type="text" name="amount[${status.index}]" id="amount" style="margin-left:15px; background: #fff;"/>
       		 <input type="hidden" name="branchid[${status.index}]" id="branchid" value= "${data.partnerid}"/>
       
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

	<div class="yes" style="float:right; margin-right:380px; margin-top:30px;" >
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
</div>

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
