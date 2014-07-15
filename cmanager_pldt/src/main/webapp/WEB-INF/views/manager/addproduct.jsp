<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html>

<html lang="en">
<head>
	<meta charset="utf-8" />
	 <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta content="text/html; charset=UTF-8" http-equiv="content-type">
	<meta http-equiv="X-UA-Compatible" content="IE=9" />
	<style type="text/css">
		* {
			margin: 0;
			padding: 0;
			outline: 0;

  
		}
		
		html, body{
			height: 100%;
			background: #F2F2F2;
			width:100%;
		}

		body, a{
			font: normal 16px Helvetica, Verdana, Geneva, sans-serif;
			color: #3F3F3F;		
		}
		
		.container{
			min-height: 100%;
			height: auto !important;
			height: 100%;
			margin: 0 auto -30px;
			width:1360px;
			background-color: #f1f1f1;
		}
		
		.container:after{
			content: '';
			display: block;
			height: 30px;
			clear: both;
		}
		
		.footer{
			height: 12px;
			padding: 8px 0;
			background: #FFF;
			border-top: 1px solid #51C1F1;
			font-size: 12px;
			text-align: center;
			width:1360px;
		}
.nla{

width:500px;
}

.tatatext{
	  line-height: 1.75em;
	font-family: helvetica !important;
	font-weight: lighter !important;
	font-size: 14px;

}


 .leftside{

  min-height:300px;
  width: 200px;
  display: inline-block; 
  margin-top: 15px;
  margin-left: 20px;
  padding-top: 30px;

 }



  .centerside{
 border-left: 1px solid #a3a3a3;

 margin-left: 5px;
  min-height:300px;
  width: 350px;
  display: inline-block; 
  margin-top: 15px;

  padding-left: 30px;
  padding-top: 30px;
 }


   .rightside{
   border-left: 1px solid #a3a3a3;
 padding-left:10px;
     margin-top: 15px;
  min-height:300px;
  width: 480px;
  display: inline-block; 
  vertical-align: top;
   padding-top: 30px;
 }

 .newbox{
 	width: 750px !important;
 }

 .addbot{
  margin-bottom: 20px;
 }

 .amiddle{
 	text-align: center;
 	vertical-align: middle;
 }
/*xbox*/


	</style>
	

       	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700' rel='stylesheet' type='text/css' />
		
	 <link rel="stylesheet" type="text/css" href="resources/css/tata.css">
	 
	
	<link rel="stylesheet" href="resources/css/istyle.css" />

	<link rel="stylesheet" href="resources/css/structure2.css" />

	<link rel="stylesheet" href="resources/css/xbox.css" />
	
	<link rel="stylesheet" href="resources/css/reveal2.css" />
	
	<link rel="stylesheet" href="resources/css/font-awesome.css" />
<link rel="stylesheet" href="resources/css/font-awesome.min.css" />

	<link rel="stylesheet" href="resources/css/nav.css" />
	
	<title>Circles</title>
</head>
<body>
	<div class="container">
<div class="boxes"> </div>
		<div class="header clearfix">
			
			
			<div class="nav">
				
				<ul class="menu">
					<li><a href="salesmtd"><i class="fa fa-usd"></i>&nbsp;SALES</a></li>
					<li><a href="agent"><i  class="icon-agent" style="font-size:20px;"></i>&nbsp;AGENTS</a></li>		
					<li><a href="recent" ><i class="fa fa-clock-o"></i>&nbsp;RECENT</a></li>	
							<li><a href="viewproduct"  class="selected"><i class="fa fa-cubes"></i>&nbsp;PRODUCTS</a></li>		
					<li><a href="logout"><i class="fa fa-laptop"></i>&nbsp;LOG OUT</a></li>
					
				</ul>
			</div><!-- End of Navigation -->
			



		</div><!-- End of Header -->
	
			

<div  class="box white newbox">
	  



<form action="saveProduct"  method="POST" ENCTYPE="multipart/form-data">
	<fieldset class="boxBody" style="border:0">
	 <table style="margin-left:auto; margin-top:35px; margin-right:auto;" width="600px" border="0" cellpadding="5" cellspacing="0" bgcolor="transparent" style="text-align:left;">
    
    <tr>
	   <td align="center" colspan="3">
		  <c:if test="${check=='success'}">
				<label style="margin-top:-10px;background-color:green;font-size:12px;"><font color="#FFFFFF">${valid}</font></label>
			</c:if>	
			<c:if test="${check=='fail'}">
				<label style="margin-top:-10px;background-color:red;font-size:12px;"><font color="#FFFFFF">${valid}</font></label>
			</c:if>			
		    <div style="padding-bottom:12px;color:#666666;"></div>
	   </td>		
    </tr>
    
    <tr>
    	<td colspan="3" align="right">
    		 <span style="color:red;"><small>*</small><font face="Helvetica" size="1" color="#666666">Indicates required field</font></span>
		    <div style="padding-bottom:12px;color:#666666;"></div>
    	</td>
    </tr>
    
    <tr>
    	<c:forEach var="list" items="${category}">
		   <td align="center" colspan="3">
		   <font face="Helvetica" size="4" color="#666666">Add Product for: <b>${list.category_name}</b></font>	
		   <div style="padding-bottom:12px;color:#666666;"></div>
		   <div style="padding-bottom:12px;color:#666666;"></div>	  
		   </td>
		  </c:forEach> 
    </tr>

<tr>

      <td width="200px !important">
        <font face="Helvetica" size="2" color="#666666"><b>Name of Product</b></font> <span style="color:red;"><small>*</small></span>
      </td>
      <td colspan="2" style="">
        <input id="field" class="search-box" value="" size="52" name="prod_name" type="text" required/>
        <a class="close-icon" id="reset"></a>
        <div style="padding-bottom:8px;color:#666666;"></div>
      </td>
</tr>



<tr>
      <td >      
      </td>
      <td colspan="2" style="">
      <input id="toggle1" type="checkbox" name="check1" value="check_device">
       <font face="Helvetica" size="2" color="#666666"><b>With Device / Hardware</b></font> <span style="color:red;"></span>
       
        <div style="padding-bottom:8px;color:#666666;"></div>
      </td>
</tr>

 <tr >
      <td>     
      <div id="tago">
      	
      </div>
      </td>
      <td>
      <select id="selectbox" name="device" class="toggle1" style="display:none;height:30px; width:150px; padding:5px">
          <option value=""></option>
          <c:forEach var="list" items="${devices}">
		  <option value="${list.device_name}">${list.device_name}</option>
		  </c:forEach>
	  </select> <a href="savedevice" style="display:none;" class="toggle1"><i class="fa fa-plus-circle" style="color:#4ba6ed;"></i></a>
	  
        <div style="padding-bottom:8px;color:#666666;"></div>
      </td>
     
</tr>

<tr >
     
      <td colspan="2" >
      

        <div style="padding-bottom:8px;color:#666666;"></div>
      </td>
</tr>   
    
<tr >
 	<td style="text-align:left;display:none;" class="toggle1">
 	<font face="Helvetica" size="2" color="#666666"><b>Charges</b></font>
 	</td>
 	 <td colspan="2" style="display:none;" class="toggle1">
 	 <input id="field2" class="search-box2" placeholder="One Time" value="" size="52" name="one_time_price" type="text" />
        <a class="close-icon2" id="reset2"></a>
 	  <div style="padding-bottom:8px;color:#666666;"></div>
 	 </td>
 	
</tr>

<tr >
 	<td style="text-align:left;display:none;" class="toggle1">
 	
 	</td>
 	 <td colspan="2" style="display:none;" class="toggle1">
 	<input id="field3" class="search-box3" placeholder="Monthly" value="" size="52" name="monthly_price" type="text" />
        <a class="close-icon3" id="reset3"></a>
 	  <div style="padding-bottom:8px;color:#666666;"></div>
 	 </td>
 	
</tr>


<tr>
 	<td class="tdspace"></td>
</tr>
   
<tr valign="top">
      <td align="left">
        <font face="Helvetica" size="2" color="#666666"><b>Description</b></font> <span style="color:red;"><small>*</small></span>
      </td>
      <td colspan="2">
        <textarea style="resize:none"  name="product_desc"   cols="50" rows="10" required></textarea>
        <div style="padding-bottom:8px;color:#666666;"></div>
      </td>
</tr>






<tr>
   <td>
     <font face="Helvetica" size="2" color="#666666"><b>Image</b></font> <span style="color:red;"><small></small></span>
   </td>
   
   <td colspan="2" style="">
     <input name="prod_image" type="file" id="file"/>   
     <div style="padding-bottom:8px;color:#666666;"></div>
   </td>
</tr>
   

<tr>
   <td class="tdspace"></td>
</tr>
   
   
<tr valign="top">
      <td align="left">
        <font face="Helvetica" size="2" color="#666666"><b>Conditions & Instructions </b></font> <span style="color:red;"><small></small></span>
      </td>
      <td colspan="2">
        <textarea style="resize:none"  name="condition"   cols="50" rows="10"></textarea>
        <div style="padding-bottom:8px;color:#666666;"></div>
      </td>
</tr>




   <tr>
   <td class="tdspace"></td>
   </tr>
    <tr>
      <td colspan="2" align="center">
      <input type="hidden" name="id" value="${prodid}"/>
   		<div style="margin-bottom:30px;margin-top:10px;margin-left:30px">
        <button class="btn btn-blue" type="submit" name="addprod">Add</button>
        <a class="btn btn-red" href="viewproduct">Cancel</a>
        </div>
      </td>
    </tr>
  </table>
	</fieldset>
	
</form>

<script src="resources/js/jquery-1.7.2.js"></script>
<script src="resources/js/jquery.reveal.js"></script>

<script>
$(document).ready(function(){
	var x = 0;
  $("#toggle1").click(function(){
	if(x==0){
		
		$('#field3:text').attr('required',true);
		 $('#field2:text').attr('required',true);
	x=1;
	
	}
	else{
		
		$('#field3:text').attr('required',false);
		$('#field2:text').attr('required',false);
	x=0;
	} 
	$(".toggle1").toggle();  
    
  });
});
</script>

<script type="text/javascript">
    document.getElementById('reset').onclick= function() {
        var field= document.getElementById('field');
        field.value= field.defaultValue;
    };
    document.getElementById('reset2').onclick= function() {
        var field= document.getElementById('field2');
        field.value= field.defaultValue;
    };
    document.getElementById('reset3').onclick= function() {
        var field= document.getElementById('field3');
        field.value= field.defaultValue;
    };
</script>

	<script type="text/javascript">
    $(document).ready(function(){
        $("select").change(function(){
            $( "select option:selected").each(function(){
            	
               var val = $(this).attr("value");
                	
               $.ajax(
            			
            	        {
            	      
            	        type: "POST",
            	        url: "getValue?getValue",
            	        data: "val=" + val,
            	        async: false,
            	        
            	        success: function(response){
            	        	
            	        	$('div#tago').empty();
            	        	
            	        	for(var i = 0;i<response.length;i++){
            	    			
            	    			$('div#tago').append('<a href="#" class="big-link" data-reveal-id="myModal" data-animation="none" style="color:#4ba6ed;margin-left:150px"><i class="fa fa-info-circle toggle1" style="margin-top:-500px; font-size:25px;"></i></a>');
            	    			$('div#tago').append('<div id="myModal" class="reveal-modal"><div><div style="padding-bottom:30px;color:#666666;"></div><center><font face="Helvetica" size="4" color="#666666"><b>'+response[i].device_name+'</b></font><div style="padding-bottom:30px;color:#666666;"></div><img src="resources/uploaded/'+response[i].device_image+'" width="250px" height="250px"></center><div style="padding-bottom:30px;color:#666666;"></div><font face="Helvetica" size="4" color="#666666"><b>Description:</b></font><div style="padding-bottom:30px;color:#666666;"></div><div class="blocking">'+response[i].description+'</div></div><a class="close-reveal-modal">&#215;</a></div> ');
            	    			
            	    		}
            	        },
            	        
            	        
            	        error: function(e){
            	        alert('Error: ' + e);
            	        }
            	        
            	        
            	        });
             
            });
        }).change();
    });
</script>

	</div><!-- End of Container -->
	
	<div class="footer">
		<p>&copy; 2013 Circles</p>
	</div>

	
 <!-- / jquery [required] -->
    <script src="resources/js/jquery.js" type="text/javascript"></script>

    <!-- / bootstrap [required] -->
    <script src="resources/js/bootstrap.js" type="text/javascript"></script>
    <!-- / modernizr -->

</body>
</html>