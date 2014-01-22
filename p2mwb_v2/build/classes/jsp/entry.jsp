<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	
ul	li {font: 80.5%/1.4em Arial, Helvetica, sans-serif;color:#333333;margin-left: -15px;
}
</style>
<title>Insert title here</title>
</head>
<body>

<!--
<core:forEach var="data" items="${pomlist}">



</ul>

</core:forEach>
-->

<core:forEach var="data" items="${pomlist}">

<ul>

<ul>
<li><label class="text12_tungsten_bold">Payment Date:</label> <label class="text10_tungsten">${data.date_created }</label></li>
<li><label class="text12_tungsten_bold">Status:</label> <label class="text10_tungsten">${data.payment_status }</label></li>
<li><label class="text12_tungsten_bold">Total Payment Amount:</label> <label class="text10_tungsten">${data.total_order}</label></li>
<li><label class="text12_tungsten_bold">Total Order Amount:</label> <label class="text10_tungsten">${data.total_amount }</label></li>
<li><label class="text12_tungsten_bold">Total Fee:</label> <label class="text10_tungsten">${data.total_fee }</label></li>

</ul>

</core:forEach>

</body>
</html>