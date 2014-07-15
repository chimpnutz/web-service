<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>

<html lang="en">
<head>
	<meta charset="utf-8" />

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


 margin-left: 5px;
  min-height:300px;
  width: 260px;
  display: inline-block; 
  margin-top: 15px;
vertical-align: top;
  padding-left: 30px;
  padding-top: 30px;
 }



  .centerside{
 border-left: 1px solid #a3a3a3;
vertical-align: top;
 margin-left: 5px;
  min-height:300px;
  width: 350px;
  display: inline-block; 
  margin-top: 15px;

  padding-left: 30px;
  padding-top: 30px;
 }


   .rightside{
   	vertical-align: top;
   border-left: 1px solid #a3a3a3;
 padding-left:10px;
     margin-top: 15px;
  min-height:300px;
  width: 450px;
  display: inline-block; 
  vertical-align: top;
   padding-top: 30px;
 }

 .addbot{
  margin-bottom: 20px;
 }

 .amiddle{
 	text-align: center;
 	vertical-align: middle;
 }


.tata {
   color:#b6b6b6;
}
.tata:hover{
 
  cursor: pointer;
}
.select {
    color:#4ba6ed;
}

a {
	text-decoration: none;
}

.tata2 {
   color:#b6b6b6;
}
.tata2:hover{
 
  cursor: pointer;
}
.select2 {
    color:#4ba6ed;
}
.scrollable{
		height:280px;
		width:350px;
        overflow-y:auto;
        }
    
.scrollleft{
		height:280px;
		width:270px;
        overflow-y:auto;
        }

.scrollright{
		height:280px;
		width: 500px;
        overflow-y:auto;
        }	
	</style>
	
	

       	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700' rel='stylesheet' type='text/css' />
		


	<link rel="stylesheet" href="resources/css/istyle.css" />

	<link rel="stylesheet" href="resources/css/structure2.css" />

	
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
	
			

<div  class="box white">
	  <div class="leftside tatabody">
             
          <text style="color:#4ba6ed;font-weight:bold;font-size: 16px;">Categories <a style="color:#4ba6ed; margin-top:0px;font-size: 18px;" href="addcategory.html" class="fa fa-plus-circle amiddle"></a></text>
          <br>        
          <div class="scrollleft">
	          <c:forEach var="list" items="${category}">
	             <a id="panes[${list.groupid}]" style="font-weight:bold;" class="tata tatatext" >${list.category_name}</a><br>      
	          </c:forEach>
	      </div>
      </div>



            <div class="centerside tatabody2">
            
        		<div id="form"  class="panes">
									<div id="trix">
									</div>
									<div class="scrollable"  id="tata1">
									</div>				
         		
         	 </div>	
         	  
              </div>
              



              <div class="rightside tatatext">
              <div id="form2" class="planes" >
              <div id="trix2"></div>
              <div class="scrollright" id="tata2"></div>		
    		  </div>
              </div>
	
</div>

	</div><!-- End of Container -->
	
	<div class="footer">
		<p>&copy; 2013 Circles</p>
	</div>

	
<script src="resources/js/jquery-1.7.2.js"></script>
<script>

$('.panes').hide();

$('.tata').click(function() {

$('.tata').removeClass('select');
$(this).addClass('select');	

$('.panes').hide();

$('.panes').show();

	
var id = $(this).attr('id');

var res = id.substring(id.lastIndexOf("[")+1,id.lastIndexOf("]"));

$.ajax(
		
        {
        	
        type: "POST",
        url: "getGroup?getGroup",
        data: "id=" + res,
        async: false,
        
        success: function(response){
 		
        	$('div#tata1').empty();
        	$('div#trix').empty();
        	$('div#trix').append('<text style="color:#4ba6ed;font-weight:bold;font-size: 16px;">Products <a href="addproduct.html?id='+res+'"  style="color:#4ba6ed; margin-top:0px;font-size: 18px;" class="fa fa-plus-circle amiddle"></a></text></br>');
        	
		for(var i = 0;i<response.length;i++){
			
			$('div#tata1').append('<a id='+response[i].product_id+' style="font-weight:bold;" class="tata2 tatatext" >'+response[i].product_name+'</a></br>');
			
		}
		
		
		
					        	             
        },
    
        
        error: function(e){
        alert('Error: ' + e);
        }
        
        
        });

});


</script>
<script>
$('.planes').hide();

$('.tata2').live("click", function(event) {

$('.tata2').removeClass('select2');
$(this).addClass('select2');	

$('.planes').hide();

$('.planes').show();

	
var id = $(this).attr('id');

$.ajax(
		
        {
        	
        type: "POST",
        url: "prodid?prodid",
        data: "id=" + id,
        async: false,
        
        success: function(response){
 		
        	$('div#tata2').empty();
        	$('div#trix2').empty();
        	$('div#trix2').append('<text style="color:#4ba6ed;font-weight:bold;font-size: 16px;">Description:<a href="editproduct.html?id='+id+'"  style="font-weight:bold;color:#4ba6ed; margin-left:350px; margin-top:0px;font-size: 16px;" class="amiddle">Edit</a></text></br>');
        	
		for(var i = 0;i<response.length;i++){
			
			$('div#tata2').append('<p style="font-weight:bold;" class="tatatext" >'+response[i].product_desc+'</p></br>');
	
			if(response[i].monthly_price==null || response[i].monthly_price==0){
				$('div#tata2').append('<text style="margin-top:150px;color:#4ba6ed;font-weight:bold;" class="tatatext" >Monthly:</text> Not Available<br>');
			}
			else{
				$('div#tata2').append('<text style="margin-top:150px;color:#4ba6ed;font-weight:bold;" class="tatatext" >Monthly:</text>'+response[i].monthly_price+'<br>');
			}		
			if(response[i].one_time_price==null || response[i].one_time_price==0){
				$('div#tata2').append('<text style="margin-top:150px;color:#4ba6ed;font-weight:bold;" class="tatatext" >One Time:</text> Not Available');
			}
			else{
				$('div#tata2').append('<text style="margin-top:150px;color:#4ba6ed;font-weight:bold;" class="tatatext" >One Time:</text>'+response[i].one_time_price);
			}
		}
		
		
		
					        	             
        },
    
        
        error: function(e){
        alert('Error: ' + e);
        }
        
        
        });

});
</script>


</body>
</html>
