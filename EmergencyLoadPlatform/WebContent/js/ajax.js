$(document).ready(function()
{
	var counter = 1;
	
	$("#rolename").append("<option value='Select Rolename'>Select Rolename</option>");

    	
    $("#addrole").click(function () {
    	
    	if(counter>1){
                alert("Only 1 textbox allow");
                return false;
    	}   
    	counter++;
    	
    	$('#roletable tr:last').after('<tr><td class="text12_tungsten"><input type="text" name="role" id="role" /></td> <td class="text10_red"><input type="image" src="../css/images/submit.png" width="60" height="32" value="submit" id="btnsubmitrole" id="btnsubmitrole"/></td><td class="text10_red"><input type="image" src="../css/images/x.png".png"  value="submit" id="btncancelrole" id="btncancelrole"/></td></tr>');
    	
    	

        });
    
    $("#btncancelrole").live("click", function()  {

    	$('#roletable tr:last').remove();

    	counter = 1;
     
         });
    
    $("#btnsubmitrole").live("click", function()  
    	{

    	var role = $("input[name='role']").val();
    	
    	if(role == ""){
    		
    		alert("Please input valid role.");
    		return false;
    	}

        $.ajax(
        		
                {
                	
                type: "POST",
                url: "rolemanagement.html",
                data: "role=" + role,
             	async: false,

                success: 
                function(response){

                	if(response != null){
                		alert("New Role Added");
                		$(location).attr('href',"rolemanagement.html");
                	}
                	
                	alert("Adding of New Role Failed");
            		$(location).attr('href',"rolemanagement.html");
                	
                },
            
                
                error: function(e){
                alert('Error: ' + e);
                }
                
                
                });
     
         });
    
    
    $("#addpriv").click(function () {
    	
    	if(counter>1){
                alert("Only 1 textbox allow");
                return false;
    	}   
    	counter++;
    	
    	$('#privtable tr:last').after('<tr><td class="text12_tungsten"><input type="text" name="priv" id="priv" /></td> <td class="text10_red"><input type="image" src="../css/images/submit.png" width="60" height="32" value="submit" id="btnsubmitpriv" id="btnsubmitpriv"/></td><td class="text10_red"><input type="image" src="../css/images/x.png" value="submit" id="btncancelpriv" id="btncancelpriv"/></td></tr>');
    	
    	

        });
    
    $("#btncancelpriv").live("click", function()  {

    	$('#privtable tr:last').remove();

    	counter = 1;
     
         });
    
    
    $("#btnsubmitpriv").live("click", function()  
        	{

        	var priv = $("input[name='priv']").val();
        	
        	if(priv == ""){
        		
        		alert("Please input valid privilege.");
        		return false;
        	}

            $.ajax(
            		
                    {
                    	
                    type: "POST",
                    url: "privilegesmanagement.html",
                    data: "priv=" + priv,
                 	async: false,

                    success: 
                    function(response){

                    	if(response == "success"){
                    		alert("New Privilege Added");
                    		$(location).attr('href',"privilegesmanagement.html");
                    	}else{
                        	alert("Adding of New Privilege Failed");
                    		$(location).attr('href',"privilegesmanagement.html");
                    	}
                    	
          
                    	
                    },
                
                    
                    error: function(e){
                    alert('Error: ' + e);
                    }
                    
                    
                    });
         
             });
    
    
    
    $('.deluser').click(function() {
    	
    	var uid =  $(this).val();
    	
 	   if(window.confirm("Are You Sure You Want to Delete this item?")){
    	
        $.ajax(
        		
                {
                	
                type: "POST",
                url: "usermanagement-delete.html",
                data: "userid=" + uid,
             	async: false,

                success: 
                function(response){

  
                		alert("User deleted!");
                    	$(location).attr('href',"usermanagement.html");
                
                },
            
                
                error: function(e){
                alert('Error: ' + e);
                }
                
                
                });
        
       } else {
           return false;
       }
   	
    



  	});
    
    $('.delpriv').click(function() {
    	
    	
    	var id = $(this).val();
    	
    	   if(window.confirm("Are You Sure You Want to Delete this item?")){

        $.ajax(
        		
                {
                	
                type: "POST",
                url: "privilegesmanagement-delete.html",
                data: "id=" + id,
             	async: false,

                success: 
                function(response){

                	alert("Privilege deleted!");
            		$(location).attr('href',"privilegesmanagement.html");
                },
            
                
                error: function(e){
                alert('Error: ' + e);
                }
                
                
                });
        
           } else {
               return false;
           }
       	
    	
    
    
    



  	});
    
    $('.delhol').click(function() {
    	
    	
    	var id = $(this).val();
    	
        if(window.confirm("Are You Sure You Want to Delete this item?")){

        $.ajax(
        		
                {
                	
                type: "POST",
                url: "holidaymanagement-delete.html",
                data: "id=" + id,
             	async: false,

                success: 
                function(response){

                	alert("Holiday deleted!");
            		$(location).attr('href',"holidaymanagement.html");
                },
            
                
                error: function(e){
                alert('Error: ' + e);
                }
                
                
                });
        
        } else {
            return false;
        }
    	
    
    
    



  	});
    


    $('.delrole').click(function() {
    	
    	var id = $(this).val();

        if(window.confirm("Are You Sure You Want to Delete this item?")){
           
        $.ajax(
        		
                {
                	
                type: "POST",
                url: "rolemanagement-delete.html",
                data: "id=" + id,
             	async: false,

                success: 
                function(response){

                	alert("Role deleted!");
                	$(location).attr('href',"rolemanagement.html");
                },
            
                
                error: function(e){
                alert('Error: ' + e);
                }
                
                
                });
    
   
        } else {
            return false;
        }
        

  	});
    
    $('.delcomp').click(function() {
    	
  
    	var id = $(this).val();
    	
    	 if(window.confirm("Are You Sure You Want to Delete this item?")){

        $.ajax(
        		
                {
                	
                type: "POST",
                url: "networkmanagement-delete.html",
                data: "id=" + id,
             	async: false,

                success: 
                function(response){

                	alert("Company deleted!");
                	$(location).attr('href',"networkmanagement.html");
                },
            
                
                error: function(e){
                alert('Error: ' + e);
                }
                
                
                });
        
         } else {
             return false;
         }
    
    

  	});
    
    
    $('#level').change(function() {
    	
    	var dropdown = $("#level").val();


        $.ajax(
        		
                {
                	
                type: "POST",
                url: "networkmanagement-get.html",
                data: "level=" + dropdown,
                dataType: "json",
                success: 
                function(response){

                	  for (var i = 0; i < response.length; i++) {
                
                		  $("#parentcompany").append("<option value="+response[i].companyname+">"+response[i].companyname+"</option>");
                
                	    }
                	  
                
                	  
                },
            
                
                error: function(e){
                alert('Error: ' + e);
                }
                
                
                });
        
        
    	
    	
    	
    	
    	});
    
    $('#dsp').change(function() {
    	
    	var dropdown = $("#dsp").val();

	$("#retailers").html("<option value='Select Rolename'>Select Retailers</option>");
    	
    	if(dropdown == 'Select DSP'){
    		$("#retailers").html("<option value='Select RolenameRetailers>Select Retailers</option>");
    		return false;
    	}


        $.ajax(
        		
                {
                	
                type: "POST",
                url: "creditlimitmanagement-get.html",
                data: "dsp=" + dropdown,
                dataType: "json",
                success: 
                function(response){
                	
               

                	  for (var i = 0; i < response.length; i++) {
                
                		  $("#retailers").append("<option value="+response[i].companyid+">"+response[i].companyname+"</option>");
                
                	    }
                	  
                
                	  
                },
            
                
                error: function(e){
                alert('Error: ' + e);
                }
                
                
                });
    	
    	
    	
    	
    	});
    
    
    $('#companyname').change(function() {
    	
    	var dropdown = $("#companyname").val();
    	
    	$("#rolename").html("<option value='Select Rolename'>Select Rolename</option>");
    	
    	if(dropdown == 'Select Company'){
        	$("#rolename").html("<option value='Select Rolename'>Select Rolename</option>");
    		return false;
    	}


        $.ajax(
        		
                {
                	
                type: "POST",
                url: "usermanagement-get.html",
                data: "cid=" + dropdown,
                dataType: "json",
                success: 
                function(response){
                	
            		

                	  for (var i = 0; i < response.length; i++) {
                		     		
                
                		  $("#rolename").append("<option value="+response[i].roleid+">"+response[i].rolename+"</option>");
                		  
                
                	    }
                	  
                
                	  
                },
            
                
                error: function(e){
                alert('Error: ' + e);
                }
                
                
                });
    	
    	
    	
    	
    	});
    

    $(".btnUpdate").click(function() {
    
    	var compid = $('.hdnCompid').val();
    	var credid = $('.hdnCredid').val();
    	var amt = $('.hdnAmt').val();
    	var stat = $('.hdnstatus').val();
    	var oldstat = $('.hdnOldstatus').val();
    	


        $.ajax(
        		
                {
                	
                type: "POST",
                url: "emergencyloadmanagement-edit.html",
                data: "compid=" + compid + "&creditid=" +credid+ "&amount=" +amt+ "&status=" +stat+ "&oldstatus=" +oldstat,
             	async: false,

                success: 
                function(response){

                
                	$('div#eMsg').html(response);

                },
            
                
                error: function(e){
                alert('Error: ' + e);
                }
                
                
                });
    	
    
  	});
    
    $(".btnTransfer").click(function() {
        

    	var cid = $('.hdnCredid').val();

    	


        $.ajax(
        		
                {
                	
                type: "POST",
                url: "emergencyloadmanagement-transfer.html",
                data: "cid=" + cid ,
             	async: false,

                success: 
                function(response){

                if(response != null){
                	$('div#eMsg').html(response);
                }
                	

                },
            
                
                error: function(e){
                alert('Error: ' + e);
                }
                
                
                });
    	
    
    
    



  	});
    

    
  
  $('#username').change(function() {
	  
  	var dropdown = $("#username").val();
	
	if(dropdown == 'Select Username'){
		
		alert(dropdown);

		return false;
	}

	
  	
      $.ajax(
      		
              {
              	
              type: "POST",
              url: "usermanagement.html?result",
              data: "uname=" + dropdown,
              dataType: "json",
              success: 
              function(response){
            	  
            	  $(".data").remove();
              	
            	  for (var i = 0; i < response.length; i++) {
            		  
            		  $('#usertable tr:last').after("<tr class ='data'><td class='text10_red'><input type='image' src='../css/images/x.png'  id = 'deluser' class='deluser' value ='${data.userid}'/><input type='hidden'  id = 'hiddenuserid' class='hiddenuserid' name='hiddenuserid' value ='${data.userid}'/></td><td class='lucida_12_tungsten'>"+response[i].username+"</td><td class='lucida_12_tungsten'>"+response[i].companyname+"</td><td class='lucida_12_tungsten'>"+response[i].rolename+"</td> <td align='center' class='text10_red'><a href='usermanagement-edit.html?userid="+response[i].userid+"' class='text10_red'>edit</a> |<a href='usermanagement-view.html?userid="+response[i].userid+"' class='text10_red'> view</a></td></tr>");
            		 
            		  response[i].username;
            		  
            		  
            	  }
            
              
              	  
              },
          
              
              error: function(e){
              alert('Error: ' + e);
              }
              
              
              });
  	
    	
    	
    	});
  
  
  $('#status').change(function() {
	  
	  	var dropdown = $("#status").val();
		
		if(dropdown == 'Cancel'){
			
	  		$("#amount").attr("disabled", "disabled"); 

			return false;
		}
		else
			if(dropdown == 'Approved'){
				
		  		$("#amount").removeAttr("disabled"); 

				return false;
			}

		
	  	

	    	
	    	
	    	});
  

  $('#opensim').change(function() {
  	
  	var dropdown = $("#opensim").val();
  	
  
  	$("#sim").html("<option value='Select Opensim Number'>Select Opensim Number</option>");
  	
  	if(dropdown == 'Select Open Sim Number'){
      	$("#sim").html("<option value='Select Select Opensim Number'>Select Opensim Number</option>");
      	$(".testdiv").html('');
      	$(".test").show();
      	//return false;
  	}


      $.ajax(
      		
              {
              	
              type: "POST",
              url: "opensimtransactions.html",
              data: "opensim=" + dropdown,
              dataType: "json",
              success: 
              function(response){
              	
          		

              	  for (var i = 0; i < response.length; i++) {
              		     		
              		  $(".test").hide();
              		  $(".testdiv").append('<table width=740  border=0 align=center cellpadding=3 cellspacing=0 id=test class=test><tr id="header" class ="header"><td class="text12_tungsten_bold">Time</td><td class="text12_tungsten_bold">Open Sim</td><td class="text12_tungsten_bold">Balance</td></tr><tr><td class="text12_tungsten">'
              				  +response[i].date+'</td><td class="text12_tungsten">'+response[i].opensim+'</td><td class="text12_tungsten">'+response[i].balance+'</td></tr></table>');
//              		  $(".testdiv").append(response[i].date);
//              		  $(".testdiv").append(response[i].opensim);
//              		  $(".testdiv").append(response[i].balance);
              
              	    }
              	  
              
              	  
              },
          
              
              error: function(e){
          	  alert('Error: ' + e);
              }
              
              
              });
  	
  	
  	
  	
  	});
  
	

});
