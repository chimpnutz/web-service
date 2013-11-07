$(document).ready(function(){
	  
	$("#amount").attr("readonly", false);
	
    $("#topupForm").validate({
  	  rules: {
      mobnum: {
      required: true,
      number: true,
      maxlength: 7
      }
  },  messages: {
      mobnum: 
      {
    	  number: "Enter a Valid Mobile No.",
    	  required: "Please Input Mobile No. ",	 
          maxlength: "Please input the remaining mobile no."	
      }    	 
  }
});
    $('#mobnum').val('');
    $('#amount').val('');
    $('#message').val('');
    
    
    
    
    $('#producttype').change(function() {
      
      	var producttype = $("#producttype").val();
      	
    	 if(producttype == 'LOAD') {
    		
    		 $('#amount').removeAttr("readonly"); 

    		
    	}else{
    		
    		$("#amount").attr("readonly", true);
    	}
          
          
      	});
    
    
	$("#mobnum").focusout(function(){
		
	  	var prefix = $("#prefix").val();
	  	var number = $("#mobnum").val();
	  	
	  	var mobnum = prefix+number;
	  	
	  	if(number.length == 7){
	  	
		
        $.ajax(
        		
                {
                	
                type: "POST",
                url: "checkbrand.html",
                data: "mobnum=" + mobnum,
                dataType: "json",
                success: 
                function(response){
                	
          		  $("#producttype").append("<option value='load-350'>Regular load - 350</option>");
        		  $("#producttype").append("<option value='load-450'>Regular load - 450</option>");
        		  $("#producttype").append("<option value='load-550'>Regular load - 550</option>");
        		  $("#producttype").append("<option value='load-600'>Regular load - 600</option>");
        		  $("#producttype").append("<option value='load-700'>Regular load - 700</option>");
        		  $("#producttype").append("<option value='load-900'>Regular load - 900</option>");

                	  for (var i = 0; i < response.length; i++) {
                
                		  $("#producttype").append("<option value="+response[i].keyword+">"+response[i].keyword+"</option>");
                
         		 
                	    }
                	  

                  	   
     

                },
            
                
                error: function(e){
                alert('Error: ' + e);
                }
                
                
                });
		
	  	}
		
		}); 
	
	$("#mobnum").focusin(function(){

		  $("#producttype").html("<option value='load'>Regular load</option>");

          

		}); 



});