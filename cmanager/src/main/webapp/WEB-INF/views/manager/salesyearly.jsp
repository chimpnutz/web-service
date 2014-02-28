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
			width:100%;
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
		}

.clearme
		{
			height: 30px;
			padding-top: 5px;
			
		}

		

	</style>

		<style>
			canvas{
			}
		</style>


	<link rel="stylesheet" href="css/istyle.css" />

	



		
			<script type="text/javascript" src="js/Chart.js"></script> 
	    <link rel="stylesheet" type="text/css" href="css/style.css" />
       	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700' rel='stylesheet' type='text/css' />
		<script type="text/javascript" src="js/modernizr.custom.79639.js"></script> 

	<link rel="stylesheet" href="css/font-awesome.css" />
	<link rel="stylesheet" href="css/font-awesome.min.css" />
	<link rel="stylesheet" href="css/nav.css" />
	<title>Circles</title>
</head>
<body>
	<div class="container">
		<div class="header clearfix">
			
			
			<div class="nav">
				
				<ul class="menu">
					<li><a href="salesmtd.html" class="selected"><i class="fa fa-usd"></i>&nbsp;SALES</a></li>
					<li><a href="agent.html"><i class="icon-agent" style="font-size:20px;"></i>&nbsp;AGENTS</a></li>		
					<li><a href="recent.html"><i class="fa fa-clock-o"></i>&nbsp;RECENT</a></li>
							<li><a href="settings.html"><i class="fa fa-mobile-phone"></i>&nbsp;MOBILE</a></li>	
					<li><a href="index.html"><i class="fa fa-laptop"></i>&nbsp;Log out</a></li>	
					
				</ul>
			</div><!-- End of Navigation -->
			

			
		</div><!-- End of Header -->
		


<div class="classification">
<p>  </p>
</div>


<div class="legend">
<img src="images/legend2.png" width="210px" height="30px"/>

</div>


<div class="salesgraph">
			<!--<img src="images/monthly.png" width="850px" height="450px"  />-->
		

			<canvas id="canvas" height="400" width="1200"></canvas>
<script>

		var barChartData = {
			labels : ["","","2013","",""],
			datasets : [
				{
					fillColor : "#8ecc7e",
					data : [,,39.5,,]
				},
				{
					fillColor : "#ed5c57",
					data : [,,38.5,,]
				}
			]
			
		}

	var myLine = new Chart(document.getElementById("canvas").getContext("2d")).Bar(barChartData);
	
	</script>

		</div>


	


					<div class="wrapper-demo">
					<div id="dd" class="wrapper-dropdown-2" tabindex="1">Yearly
						<ul class="dropdown">
							<li><a href="salesmtd.html"><i class="icon-github icon-large"></i>Month to Date</a></li>
							<li><a href="salesmonthly.html"><i class="icon-twitter icon-large"></i>Monthly</a></li>
							<li><a href="salesytd.html"><i class="icon-github icon-large"></i>Year to Date</a></li>
							<li><a href="salesyearly.html"><i class="icon-facebook icon-large"></i>Yearly</a></li>
						</ul>
					</div>
				</div>

		
			
		</div>
		<!-- jQuery if needed -->
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.min.js"></script> 
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






	</div><!-- End of Container -->
	






<div class="clearme">
</div>

	<div class="footer">
		<p>&copy; 2013 Circles</p>
	</div>
</body>
</html>