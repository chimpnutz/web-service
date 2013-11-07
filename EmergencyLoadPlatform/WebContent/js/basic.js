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

$(document).ready(function() {
	// Load dialog on page load
	//$('#basic-modal-content').modal();

	// Load dialog on click
	

	
	$('#apply').click(function (e) {

		$('#basic-modal-content').html('<div class="modalclose" style="width:25px; height:29px; display:inline; z-index:3200; position:absolute; top:-15px; right:-16px; cursor:pointer;"><img src="../css/images/x.png" alt="" width="25" height="29" border="0"></div><div  id="msg" style="z-index: -1; position:absolute;  left:100px; font-size:10px; color:#2b6991"></div><br/><div class="text12_tungsten_bold">Amount: &nbsp;&nbsp;<input type="text" name="eAmount" id="eAmount" class="eAmount" size="30px"/></div> <div style="float:right;"><input type="image" src="../css/images/submit.png" width="60" height="32" value="submit" id="eApply" class="eApply"/></div>');
		
    	$('#basic-modal-content').modal();
    	
		return false;
	});
	
	$('#modality').click(function (e) {


    	$('#basic-modal-content-credit-limit').modal();
    	
		return false;
	});

	
	
    $("#loadtoretailer").click(function () {
    	
    	$('#basic-modal-content2').modal();    
        
		return false;

        });
	
	     $('.modalclose').live("click", function()  
		         	{
	  	    	var url = "emergencyloadmanagement.html?unpaid";    
	  	    	
				$.modal.close();
				
				$(location).attr('href',url);
				
	  	    	});
	     
	     $('.modalclose2').live("click", function()  
		         	{
	  	    	var url = "emergencyloadmanagement.html?unpaid";    
	  	    	
				$.modal.close();
				
				$(location).attr('href');
				
	  	    	});
	

	     $('.eApply').live("click", function()  
	         	{
	     	
	     	var amount =  $("#eAmount").val();

	         $.ajax(
	         		
	                 {
	                 	
	                 type: "POST",
	                 url: "emergencyloadmanagement.html",
	                 data: "amount=" + amount,
	              	async: false,
	 
	                 success: 
	                 function(response){
	 
	                	 if(response != null){
	                		 $("div#msg").html(response);
	                		 $(".eAmount").val('');
	                		 $(".eApply").empty();
	                		 $("#eApply").empty();
	                		 $(".eApply").hide();
	                	 }
	                 	
	                 	
	                 },
	             
	                 
	                 error: function(e){
	                 $("div#msg").html("Fatal Error. Contact Administrator");
	                 }
	                 
	                 
	                 });
	     
	     	
	         



	   	});



});
