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
			width:100%;
			position: absolute;
			margin-top: 100px;
			width: 1360px;
		}


		.clearme
		{
			height: 30px;
			padding-top: 5px;
			
		}

.nov
{
	width: auto;
	height: auto;
	position: absolute;
	margin-top: 487px;
	margin-left: 93px;
}



	</style>		<style>
			canvas{
			}
		</style>



			<link rel="stylesheet" href="resources/css/istyle.css" />

	





		
			<script type="text/javascript" src="resources/js/Chart.js"></script> 
	    <link rel="stylesheet" type="text/css" href="resources/css/style.css" />
       	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700' rel='stylesheet' type='text/css' />
		<script type="text/javascript" src="resources/js/modernizr.custom.79639.js"></script> 

	<link rel="stylesheet" href="resources/css/font-awesome.css" />
	<link rel="stylesheet" href="resources/css/font-awesome.min.css" />
	<link rel="stylesheet" href="resources/css/nav.css" />
	<title>Circles</title>
</head>
<body>
	<div class="container">
<div class="boxes"></div>
		<div class="header clearfix">
			
			
			<div class="nav">
				
				<ul class="menu">
					<li><a href="salesmtd" class="selected"><i class="fa fa-usd"></i>&nbsp;SALES</a></li>
					<li><a href="agent"><i class="icon-agent" style="font-size:20px;"></i>&nbsp;AGENTS</a></li>		
					<li><a href="recent"><i class="fa fa-clock-o"></i>&nbsp;RECENT</a></li>
					<li><a href="viewproduct"><i class="fa fa-cubes"></i>&nbsp;PRODUCTS</a></li>			
					<li><a href="logout"><i class="fa fa-laptop"></i>&nbsp;LOG OUT</a></li>
					
				</ul>\
			</div><!-- End of Navigation -->
			

			
		</div><!-- End of Header -->
		




<div class="legend">
<img src="resources/images/legend2.png" width="210px" height="30px"/>

</div>

<div class="salesgraph">
			<!--<img src="resources/images/monthly.png" width="850px" height="450px"  />-->
		

			<canvas id="canvas" height="400" width="1200"></canvas>
<script>

		var barChartData = {
			labels : ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov"],
			datasets : [
				{
					fillColor : "#8ecc7e",
					
					data : [50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150]
				},
				{
						fillColor : "#ed5c57",
						
					data : [42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8]
				}
			]
			
		}

	var myLine = new Chart(document.getElementById("canvas").getContext("2d")).Bar(barChartData);
	
	</script>

		</div>


	


					<div class="wrapper-demo">
					<div id="dd" class="wrapper-dropdown-2" tabindex="1">Monthly
						<ul class="dropdown">
							<li><a href="salesmtd"><i class="icon-github icon-large"></i>Month to Date</a></li>
							<li><a href="salesmonthly"><i class="icon-twitter icon-large"></i>Monthly</a></li>
							<li><a href="salesytd"><i class="icon-github icon-large"></i>Year to Date</a></li>
							<li><a href="salesyearly"><i class="icon-facebook icon-large"></i>Yearly</a></li>
							
							
						</ul>
					</div>
				</div>
	</div><!-- End of Container -->
	







	<div class="footer">
		<p>&copy; 2013 Circles</p>
	</div>
		
			
		</div>
		<!-- jQuery if needed -->
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<script type="text/javascript" src="resources/js/jquery.min.js"></script> 
		<script type="text/javascript">

			function DropDown(el) {
				this.dd = el;
				this.initEvents();
			}
			DropDown.prototype = {
				initEvents : function() {
					var obj = this;

					obj.dd.on('click', function(event){
						$(this).toggleClass('active');
						event.stopPropagation();
					});	
				}
			}

			$(function() {

				var dd = new DropDown( $('#dd') );

				$(document).click(function() {
					// all dropdowns
					$('.wrapper-dropdown-2').removeClass('active');
				});

			});

		</script>







</body>
</html>