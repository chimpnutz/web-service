<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	
ul	li {font: 80.5%/1.4em Arial, Helvetica, sans-serif;color:#333333;margin-left: -25px;
}
</style>
<title>Insert title here</title>
</head>
<body>


<core:forEach var="data" items="${polist }">
 <core:if test="${data.item == 'LOP'}">
<ul>
<li><label  class ="text10_tungsten_bold">PO Date:</label> <label  class ="text10_tungsten">${data.podate }</label></li>
<li><label  class ="text10_tungsten_bold">Partner Name:</label> <label  class ="text10_tungsten">${data.partner_name}</label></li>
<li><label  class ="text10_tungsten_bold">Payment Status:</label> <label  class ="text10_tungsten">${data.payment_status}</label></li>
<li><label  class ="text10_tungsten_bold">Delivery Status:</label> <label  class ="text10_tungsten">${data.delivery_status }</label></li>
<li><label  class ="text10_tungsten_bold">PO Status:</label> <label  class ="text10_tungsten">${data.po_status }</label></li>
<li><label  class ="text10_tungsten_bold">Total Order Amount:</label> <label  class ="text10_tungsten">${data.total_amount }</label></li>
<li><label  class ="text10_tungsten_bold">Amount Paid:</label> <label  class ="text10_tungsten">${data.amount_paid }</label></li>
<li><label  class ="text10_tungsten_bold">Cancel Date:</label> <label  class ="text10_tungsten">${data.cancel_date }</label></li>
</ul>
 </core:if>
</core:forEach>

<core:forEach var="data" items="${otlist }">
 <core:if test="${data.item == 'LOP'}">
<ul>
<li><label  class ="text10_tungsten_bold">PO Date:</label> <label  class ="text10_tungsten">${data.podate }</label></li>
<li><label  class ="text10_tungsten_bold">Partner Name:</label> <label  class ="text10_tungsten">${data.partner_name}</label></li>
<li><label  class ="text10_tungsten_bold">Payment Status:</label> <label  class ="text10_tungsten">${data.payment_status}</label></li>
<li><label  class ="text10_tungsten_bold">Delivery Status:</label> <label  class ="text10_tungsten">${data.delivery_status }</label></li>
<li><label  class ="text10_tungsten_bold">PO Status:</label> <label  class ="text10_tungsten">${data.po_status }</label></li>
<li><label  class ="text10_tungsten_bold">Total Order Amount:</label> <label  class ="text10_tungsten">${data.total_amount }</label></li>
<li><label  class ="text10_tungsten_bold">Amount Paid:</label> <label  class ="text10_tungsten">${data.amount_paid }</label></li>
<li><label  class ="text10_tungsten_bold">Cancel Date:</label> <label  class ="text10_tungsten">${data.cancel_date }</label></li>
</ul>
 </core:if>
</core:forEach>

<core:forEach var="data" items="${polist3 }">
 <core:if test="${data.item == data.item}">
 
<ul>
<li><label  class ="text10_tungsten_bold">PO Date:</label> <label  class ="text10_tungsten">${data.podate }</label></li>
<li><label  class ="text10_tungsten_bold">Partner Name:</label> <label  class ="text10_tungsten">${data.partner_name}</label></li>
<li><label  class ="text10_tungsten_bold">Payment Status:</label> <label  class ="text10_tungsten">${data.payment_status}</label></li>
<li><label  class ="text10_tungsten_bold">Delivery Status:</label> <label  class ="text10_tungsten">${data.delivery_status }</label></li>
<li><label  class ="text10_tungsten_bold">PO Status:</label> <label  class ="text10_tungsten">${data.po_status }</label></li>
<li><label  class ="text10_tungsten_bold">Total Order Amount:</label> <label  class ="text10_tungsten">${data.total_amount }</label></li>
<li><label  class ="text10_tungsten_bold">Amount Paid:</label> <label  class ="text10_tungsten">${data.amount_paid }</label></li>
<li><label  class ="text10_tungsten_bold">Cancel Date:</label> <label  class ="text10_tungsten">${data.cancel_date }</label></li>
</ul>

 </core:if>
</core:forEach>

</body>
</html>