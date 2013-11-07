/*
 * SimpleModal Basic Modal Dialog
 * http://www.ericmmartin.com/projects/simplemodal/
 * http://code.google.com/p/simplemodal/
 *
 * Copyright (c) 2010 Eric Martin - http://ericmmartin.com
 *
 * Licensed under the MIT license:
 *   http://www.opensource.org/licenses/mit-license.php
 *
 * Revision: $Id: basic.js 254 2010-07-23 05:14:44Z emartin24 $
 */


	// Load dialog on page load
	//$('#basic-modal-content').modal();

	// Load dialog on click
	$(document).ready(function() {
		
        $("#basic-modal .basic").click(function() 
        {
	
        	var myClass = $(this).attr("name");
        	
        	var first = myClass.indexOf("["); 
        	var last = myClass.indexOf("]"); 
        	
        	var place = myClass.substring(first+1,last);
        	
        	var bid = $("input[name='partnerid["+place+"]']").val();

        	var editbranch = "";
        	
        	$('#basic-modal-content').html('<div class="modalclose" style="width:25px; height:29px; display:inline; z-index:3200; position:absolute; top:-15px; right:-16px; cursor:pointer;"><img src="../css/images/x.png" alt="" width="25" height="29" border="0"></div><div  id="msg" style="z-index: -1; position:absolute; font-size:10px; color:#2b6991"></div>	</br><table width="350px" align="center" ><tr><td>Partner ID:</td><td><input type="text" name="partnerid" class="partnerid"  size="20" style="background:#FFF;" value='+bid+' disabled></td></tr><tr><td>Partner Name:</td><td><input type="text" name="partnername" class="partnername"  size="20" style="background:#FFF;"></td></tr><tr><td>Custom Message:</td><td><textarea rows="4" cols="30" name="message" id="message" class="message" ></textarea></td></tr><tr><td>Email Address:</td><td><input type="text" name="email" class="email"  size="20" style="background:#FFF;" ></td></tr><tr><td>&nbsp;</td><td><input class="yes" type="image" src="../css/images/ok.png" value="ok" style="float:right;"></td></tr>')
        	
        	$('#basic-modal-content').modal();
        	
        	return false;

        });
        
        $(".yes").live("click", function()  {
        	
        	var bid = $("input[name='partnerid']").val();
        	var bname = $("input[name='partnername']").val();
        	
        	var message = $("#message").val(); 
        	var email = $("input[name='email']").val()
        	
            if(bname == '')
            {
            	$("div#msg").html("Please input your partner name.");
            	
            	return false;
            }
     	    
            
            $.ajax(
            		
                    {
                    	
                    type: "POST",
                    url: "editbranch.html",
                    data: "partnerId=" + bid + "&partnerName=" + bname + "&message=" +message+ "&email=" +email,
                 	async: false,

                    success: 
                    function(response){

                    		$("div#msg").html(response);
                    		$("input[name='partnername']").val('');
                    		$("#message").val('');
                    		$('.yes').hide();
//            						if(response != ""){
//            							
//            							$('<div class="statusmsg-'+i+'" style="padding-bottom: 10px; margin-left: -29px" > </div>').appendTo('div#data2').append(response);
//            													
//            						}
//            						
//            						$('#loader1').hide();

                    },
                
                    
                    error: function(e){
                    alert('Error: ' + e);
                    }
                    
                    
                    });
        });
        
	     $('.modalclose').live("click", function()  {
	   	    	var url = "managebranches.html";    
	   	    	
	 			$.modal.close();
	 			
	 			$(location).attr('href',url);
	 			
	   	    	});

	     
	     $("#yes").live("click", function()  {
	        	
	        	var bid = $("input[name='partnerid']").val();
	        	var bname = $("input[name='partnername']").val();
	        	
	            if(bname == '')
	            {
	            	$("div#msg").html("Please input your partner name.");
	            	
	            	return false;
	            }
	     	    
	            $.ajax(
	            		
	                    {
	                    	
	                    type: "POST",
	                    url: "editbranch.html",
	                    data: "partnerId=" + bid + "&partnerName=" + bname,
	                 	async: false,

	                    success: 
	                    function(response){

	                    		$("div#msg").html(response);
	                    		$("input[name='partnername']").val('');
	                    		$('.yes').hide();
//	            						if(response != ""){
//	            							
//	            							$('<div class="statusmsg-'+i+'" style="padding-bottom: 10px; margin-left: -29px" > </div>').appendTo('div#data2').append(response);
//	            													
//	            						}
//	            						
//	            						$('#loader1').hide();

	                    },
	                
	                    
	                    error: function(e){
	                    alert('Error: ' + e);
	                    }
	                    
	                    
	                    });
	        });
        
        
        
        $("#simplemodal-overlay").live("click", function(){
        	$.modal.close();
        });

	});
	



		
		
	
	
	

