$(function() {
  
    // Setup form validation on the #register-form element
    $("#purchaseorderForm").validate({
    
        // Specify the validation rules
        rules: {
            qty: {
            	required: true,
                digits: true,
            }
            
            
        },
        
        
        
        // Specify the validation error messages
        messages: {
        	
       
            qty: {
            	required: "Please provide a quantity",
            	digits: "Please enter numbers only",
            	
            }
            
            
            
        },
        
        
        submitHandler: function(form) {
            form.submit();
        }
        
    });
    
  });