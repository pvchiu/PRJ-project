<%-- 
    Document   : list
    Created on : Oct 15, 2024, 11:56:30 PM
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
                <td>Product ID</td>
                <td>Product Name</td>
                <td>Estimated Effort</td>
                
                
            </tr>
            <c:forEach items="${requestScope.pts}" var="p">
            <tr>
                <td>${p.pid}</td>
                <td>${p.pname}</td>
                <td>${p.estimation}</td>
                
                
            </tr>
        </c:forEach>
            </table>
    </body>
</html>
