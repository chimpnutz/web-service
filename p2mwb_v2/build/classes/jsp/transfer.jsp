<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="../css/images/favicon.ico" >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="refresh" content="<%= session.getMaxInactiveInterval() %>;URL='logout.html?expire=yes'">
<title>Pay PhilExchange</title>


<LINK rel="stylesheet" href="../css/master.css" type="text/css">
<LINK rel="stylesheet" href="../css/font.css" type="text/css">
<!-- Tab contents CSS files -->
<link href="../css/tabcontent.css" rel="stylesheet" type="text/css" />
<script src="../js/tabcontent.js" type="text/javascript"></script>

<script src="../js2/jquery-1.8.2.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>


<link href="../css/unibar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/header.js"></script>
<script type="text/javascript" src="../js/dropdown.js"></script>

<style type="text/css">
.text_10_red {	font-family:"Lucida Sans Unicode", "Lucida Grande", sans-serif;
	font-size:12px;
	color:#ff0000;
	text-decoration:none;
	text-align: center;
}
.text_12_tungsten {
	font-family:"Lucida Sans Unicode", "Lucida Grande", sans-serif;
	font-size:12px;
	color:#333;
	text-align: center;
}
</style>

</head>

<body>
<div id="page"><!---------------------start-page---------------->
	<div id="header"><img src="images/payex-logo.png" style="margin-top:13px;"/></div>
     <div id="profile">
   <div id="profile" class="left"><img src="images/user2.png" width="25" height="35" align="absmiddle" /><span class="text10_tungsten">Hi </span><span class="text10_lightblue_bold">Kiyeme123456789</span><span class="text10_tungsten">, you're logging as super administrator</span><br>
      <div class="text10_tungsten" style="margin-left:27px; margin-top:-10px;">September 20, 2012</div> </div>
 <div id="profile" class="right"><a href="#"><img title="logout" onmouseover="this.src='images/logout-on.png';" onmouseout="this.src='images/logout.png';" src="images/logout.png" alt="" width="32" height="32" border="0"></a></div>
  </div>
  
  <div style="clear:both;"></div>
    <div id="wrapper"><!---------------------start-wrapper------------>
    <div id="container3"><!---------------------start-container------------>
         
         <div id="hr">  	 	 
   	 	   <table width="800" border="0" cellspacing="0" cellpadding="1">
   	 	     <tr class="text12_tungsten">
   	 	       <td width="300" align="left">Transfer credits</td>
   	 	       
   	 	       <td width="300" align="center">&nbsp;</td>
               <td width="330" align="right">&nbsp;</td>
 	         </tr>
 	       </table>
   	 	 </div>
         
         
      <ul class="tabs" persist="true">
            <li><a href="transfer.html#view1" rel="view1">Transfer</a></li>
            <li><a href="revoke.html#view2" rel="view2">Revoke</a></li>
           
           
      </ul>
        
     <div class="tabcontents">
           
        <div id="view1" class="tabcontent">
          <table width="500" border="0" align="center" cellpadding="5" cellspacing="0">
            <tr>
              <td width="200" class="text12_tungsten">Branch ID</td>
              <td width="300"><form id="form1" name="form1" method="post" action="">
                <label for="textfield"></label>
                <input type="text" name="textfield" id="textfield" />
              </form></td>
            </tr>
            <tr>
              <td class="text12_tungsten">Amount</td>
              <td><form id="form2" name="form1" method="post" action="">
                <label for="textfield2"></label>
                <input type="text" name="textfield2" id="textfield2" />
              </form></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td><input type="image" src="../css/images/submit_orange.png" style="margin:0 40px;"  /></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </table>
        </div>
        </div>
		 
	     </span>
    </div><!---------------------end-container2------------>
  	</div><!---------------------end-wrapper-------------->
    <div id="footer"></div>
</div><!---------------------end-page--------------------->
</body>
</html>