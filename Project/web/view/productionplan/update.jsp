<%-- 
    Document   : update
    Created on : Nov 3, 2024, 9:40:04 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <jsp:include page="../master/shortprofile.jsp"></jsp:include>
    
    <h1>Update Production Plan</h1>
    
    <form action="update" method="post">
    <input type="hidden" name="id" value="${plan.id}"/>
    
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${plan.name}" required/><br/>

    <label for="from">Start Date:</label>
    <input type="date" id="from" name="from" value="${fn:substring(plan.start, 0, 10)}" required/><br/>

    <label for="to">End Date:</label>
    <input type="date" id="to" name="to" value="${fn:substring(plan.end, 0, 10)}" required/><br/>

    <label for="did">Department ID:</label>
    <input type="number" id="did" name="did" value="${plan.dept.id}" required/><br/>
    
    <h3>Production Plan Headers</h3>
    <c:forEach var="header" items="${plan.header}">
        <div>
            <label>Product ID:</label>
            <input type="number" name="pid${header.product.id}" value="${header.product.id}" readonly/>
            <label>Quantity:</label>
            <input type="number" name="quantity${header.product.id}" value="${header.quantity}" required/>
            <label>Estimated Effort:</label>
            <input type="number" step="0.1" name="effort${header.product.id}" value="${header.estimatedeffort}" required/>
        </div>
    </c:forEach>

    <input type="submit" value="Update Production Plan"/>
</form>
    
</body>
</html>
