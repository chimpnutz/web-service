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


<core:forEach var="data" items="${polist }">
<ul>
<li>1) ${data.podate }</li>
<li>2) ${data.payment_status }</li>
<li>3) ${data.amount_paid}</li>
<li>4) ${data.total_amount }</li>
<li>5) data here</li>

</ul>
</core:forEach>
</body>
</html>