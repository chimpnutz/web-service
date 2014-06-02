<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />

	 <link href="css2/modal.css" rel="stylesheet" type="text/css" />
	
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
		
		.container2{
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
			padding-top: 140px;
			height: 20px;
			
		}
	</style>

	<style>
			canvas{
			}
		</style>


	<script type="text/javascript" src="js/Chart.js"></script> 
	

		<link rel="stylesheet" href="css/istyle.css" />

	


	<link rel="stylesheet" href="css/cssmsg.css" />
	<link rel="stylesheet" href="css/font-awesome.css" />
	<link rel="stylesheet" href="css/font-awesome.min.css" />
	<link rel="stylesheet" href="css/nav.css" />
	<link rel="stylesheet" href="css/agentview.css" />
	<title>Circles</title>
</head>
<body>
	<div class="container2">
		<div class="header clearfix">
			
			
			<div class="nav">
				
				<ul class="menu">
					<li><a href="salesmtd.html"><i class="fa fa-usd"></i>&nbsp;SALES</a></li>
					<li><a href="agent.html" class="selected"><i class="icon-agent" style="font-size:20px;"></i>&nbsp;AGENTS</a></li>		
					<li><a href="recent.html"><i class="fa fa-clock-o"></i>&nbsp;RECENT</a></li>
							<li><a href="settings.html"><i class="fa fa-mobile-phone"></i>&nbsp;MOBILE</a></li>			
				<li><a href="logout.html"><i class="fa fa-laptop"></i>&nbsp;LOG OUT</a></li>
					
				</ul>
			</div><!-- End of Navigation -->
			
		</div><!-- End of Header -->
		

		
<div class="agentgraph1">
			<!--<img src="images/monthly.png" width="850px" height="450px"  />-->
			


			<canvas id="canvas" height="400" width="1200"></canvas>


	<script>

		var barChartData = {
			labels : ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"],
			datasets : [
				{
					fillColor : "rgba(255, 0, 0, 0.5)",
					strokeColor : "rgba(255, 0, 0, 0.8)",
					data : [49.9, 71.5, 106.4, 29.2, 44.0, 176.0, 135.6, 48.5, 16.4, 24.1, 95.6, 54.4]
				},
				{
					fillColor : "rgba(151,187,205,0.5)",
					strokeColor : "rgba(151,187,205,1)",
					data : [42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1]
				}
			]
			
		}

	var myLine = new Chart(document.getElementById("canvas").getContext("2d")).Bar(barChartData);
	
	</script>
		</div>













			<div id="frame">
			<div class="frame-menu">
			<div id="profile">
			

			<div class="circletag">
			<div class="proname">
			<p> Sheldon Cooper</p>
			<div class="cleardistance"> </div>
			<span>
				<i class="fa fa-map-marker"/></i> &nbsp; New York City
			</span>
			</div>
			<img src="images/sheldon.jpg">
			</div>

			<div class="email">
			<a href="#login_form" id="login_pop">
			<img src="images/email.png" class="button"  data-type="zoomout" width="35px" height="25px"/>
			</a>



<a href="#x" class="overlay" id="login_form"></a>
        <div class="popup">
           <div>
               <textarea  class="fontchange" cols="60" name="TextArea1" rows="10" style="resize: none;" placeholder="Leave a message..." required></textarea>

            </div>
         
            <input type="button" class="ok" value="Send" />
             <input type="button" class="cancel"  value="Cancel" />
     
        </div>











			</div>





			</div>


				<ul class="agentmenu">
				<li class="recent"><a href="agentrecent.html" class="selected2"  title="monthly"><span class="displace2">recent</span></a></li>
				<li class="mtd"><a href="agentview.html" title="ytd"><span class="displace2">monthly</span></a></li>
				<li class="monthly"><a href="agentmtd.html" title="ytd"><span class="displace2">mtd</span></a></li>
	
	<li class="ytd"><a href="agentviewytd.html" title="ytd"><span class="displace2">ytd</span></a></li>
	<li class="yearly"><a href="agentviewyearly.html" title="yearly"><span class="displace2">yearly</span></a></li>			
				</ul>
			


			</div>
			 </div>



	</div><!-- End of Container -->
	

<div class="clearme">
</div>
	<div class="footer">
		<p>&copy; 2013 Circles</p>
	</div>







</body>
</html>