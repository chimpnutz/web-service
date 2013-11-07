$(document).ready(function(){
	
	var pattern = "";
  	var allFieldsComplete = true;
	  
    $.ajax(
    		
            {
            	
            type: "POST",
            url: "billerlist.html",
            dataType: "json",
            success: 
            function(response){

            	  for (var i = 0; i < response.length; i++) {
            
            
            		  $("#biller").append("<option value="+response[i].billercode+">"+response[i].biller+"</option>");
                      
     		 
            	    }
            	  

            },
        
            
            error: function(e){
            alert('Error: ' + e);
            }
            
            
            });
    
    
       
    $('#biller').change(function() {
		
    	var biller = $('#biller').val();
    	$("#billerfield").html('')
	  	
		
        $.ajax(
        		
                {
                	
                type: "POST",
                url: "billerfield.html",
                data: "biller=" + biller,
                dataType: "json",
                success: 
                function(response){

                	  for (var i = 0; i < response.length; i++)
                	  {
                
                		  $("#billerfield").append("<div class=text12_tungsten style='float left; margin-top: 27px;'>"+response[i].description+"</div> <div style = 'float: left; margin-top:-17px; margin-left:191px;'><input type=text name="+response[i].parameter_name+" size=30 style=background-color:white; id="+response[i].parameter_name+" class="+response[i].parameter_type+" /><label class=text10_tungsten  >"+response[i].label+"</label></div>");
                		  
                		  pattern = response[i].billerfield;
                		  
                		  if(response[i].parameter_type == 'date-pick')
                		  {
                		
                				Date.firstDayOfWeek = 0;
        	    				Date.format = response[i].format;
        	    				var dateSelected = false;
        	    				$('.date-pick').datePicker({				
        	    					
        	    					onSelect: function(dateText, inst) 
        	    					{
        	    				
        	    					dateSelected = true;
        	    					},
        	
        	    					startDate:'01/01/2000'
        	    					
        	    				
        	    				});  
                		  }
                		  
                		  
                	   }
                	  
                	  $("#billerfield").append("<div class=text12_tungsten style='float left; margin-top: 27px;'>Bill Received</div> <div style = 'float: left; margin-top:-17px; margin-left:191px;'><input type=text name=billreceived size=30 style=background-color:white; id=billreceived class=billreceived /></div>");
                	  
                	  $("#billerfield").append("<div class=text12_tungsten style='float left; margin-top: 27px;'>&nbsp;</div> <div style = 'float: left; margin-top:-17px; margin-left:191px;'><input type=image value=Submit src=../css/images/submit2_button.png style=margin-left:140px; class=btnbillssubmit id=btnbillssubmit/></div>");
                },

                
                error: function(e){
                alert('Error: ' + e);
                }
                
                
                });

		
		}); 
	


    $(".btnbillssubmit").live("click", function()
    {	
  
    	$('input[type=text]').each(function(index)
    	{ 

    		 if ($(this).val().trim().length === 0 )  
    		 {

    			 allFieldsComplete = false;
    		 }
    	

    	    
    	});
    	
    	if(!allFieldsComplete){
    	    alert('Please complete all required fields');
    	}else
    	{
    		$('#loader2').show();
        	
        	
        	var acctno = "";
        	var billerfield="";
        	var field;
        	var payfield = "";
        	var biller = $('#biller').val();
        	//var pattern = "ACCNTNO|{ACCNTNO}~NAME|{NAME}~TELNO|{TELNO}~TODATE|{TODATE}~BILLAMT|{BILLAMT}";
        	
        	//alert(pattern);
        	
        	var test = new Array()
        	
        	$('input[type=text]').each(function(index)
        	{ 
        		test[index] = pattern.replace("{"+$(this).attr('name').toUpperCase()+"}",$(this).val()); 
        		pattern = test[index];
        		billerfield = pattern;
        	});

        	var billamt = $("#billamt").val();
        	
        	var billreceived = $("#billreceived").val();
        	

        	payfield ="AMT|"+billamt+"~REC|"+billreceived+"";
        	
        	$('#basic-modal-content').modal();

        	
            $.ajax(
            		
                    {
                    	
                    type: "POST",
                    url: "billspayment.html",
                    data: "biller=" + biller+ "&billerfield=" + billerfield+ "&payfield=" + payfield,
                    async: false,
                    success: 
                    function(response){

                    
                    	
                		if(response != ""){
                			
                			$.modal.close();
                			$('input[type="text"]').val('');
                			//$("#result").html(response);
                			window.location.replace("billspayment.jsp?result="+response);
                			
                		 	acctno = "";
                        	billerfield="";
                        	field;
                        	payfield = "";
                        	biller = "";
        						
        				}

                    	  
                    },

                    
                    error: function(e){
                    alert('Error: ' + e);
                    }
                    
                    
                    });
    	}
    	
    	
 
			
    });
    


	
	
	});


	function doBillsPayment() {
		
		$('#loader2').show();
    	
    	
    	var acctno = "";
    	var billerfield="";
    	var field;
    	var payfield = "";
    	var biller = $('#biller').val();
    	//var pattern = "ACCNTNO|{ACCNTNO}~NAME|{NAME}~TELNO|{TELNO}~TODATE|{TODATE}~BILLAMT|{BILLAMT}";
    	
    	//alert(pattern);
    	
    	var test = new Array()
    	
    	$('input[type=text]').each(function(index)
    	{ 
    		test[index] = pattern.replace("{"+$(this).attr('name').toUpperCase()+"}",$(this).val()); 
    		pattern = test[index];
    		billerfield = pattern;
    	});
//    	alert(billerfield);

    	var billamt = $("#billamt").val();
    	
    	var billreceived = $("#billreceived").val();
    	

    	payfield ="AMT|"+billreceived+"~REC|"+billreceived+"";
    	
    	$('#basic-modal-content').modal();

    	
        $.ajax(
        		
                {
                	
                type: "POST",
                url: "billspayment.html",
                data: "biller=" + biller+ "&billerfield=" + billerfield+ "&payfield=" + payfield,
                async: false,
                success: 
                function(response){

                
                	
            		if(response != ""){
            			
            			$.modal.close();
            			$('input[type="text"]').val('');
            			$("#result").html(response);
    						
    				}

                	  
                },

                
                error: function(e){
                alert('Error: ' + e);
                }
                
                
                });
    	
		
	}
