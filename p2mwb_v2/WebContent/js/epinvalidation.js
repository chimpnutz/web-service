

$(function() {
  
    // Setup form validation on the #register-form element
    $("#epinForm").validate({
    
        // Specify the validation rules
        rules: {
        	mobnum: {
        		required: true,
        		digits: true
        	},
            target: {
                required: true,
                email: true
            },
            quantity: {
                required: true,
                digits: true,
                min: 2
            },
            password: {
                required: true,               
            },
            password2: {
                required: true,
                equalTo: "#password"
            },
            errorElement: "span",
            errorPlacement: function(text12_tungsten, element) {
                element.siblings("label").append(text12_tungsten);
            },
            highlight: function(element) {
                $(element).siblings("label").addClass("text12_tungsten");
            },
            unhighlight: function(element) {
                $(element).siblings("label").removeClass("text12_tungsten");
            }
        },
        
        // Specify the validation error messages
        messages: {
        	mobnum: "Please provide a mobile number",
            target: {
            	required:"Please enter a valid email address",
            	email:"Please provide a proper email address"
            },
            quantity: {
            	required: "Please provide a quantity",
            	digits: "Please enter numbers only",
            	min: "The quantiy must be 2 or more"
            },
            password2: {
            	required: "Please provide a password",
            	equalTo: "Please enter the same password as above "
            },
            password: {
                required: "Please provide a password",
            }
            
            
        },
        
        submitHandler: function(form) {
            form.submit();
        }
    });

  });