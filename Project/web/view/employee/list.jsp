<%-- 
    Document   : list
    Created on : Oct 28, 2024, 12:02:09 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="../master/shortprofile.jsp"></jsp:include>
        <table border="1px">
            <tr>
                <td>Employee ID</td>
                <td>Employee Name</td>
                <td>Address</td>
                <td>Department</td>
                <td>Salary</td>
                <td>Phone number</td>
                
                
            </tr>
            <c:forEach items="${requestScope.emps}" var="e">
            <tr>
                <td>${e.id}</td>
                <td>${e.name}</td>
                <td>${e.address}</td>
                <td>${e.did.name}</td>
                <td>${e.sid.salary}</td>
                <td>${e.phonenumber}</td>
                
                
            </tr>
        </c:forEach>
            </table>
    </body>
</html>
