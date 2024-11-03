<%-- 
    Document   : detail.jsp
    Created on : Nov 3, 2024, 11:38:09 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
        /* Reset some default styles */
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f2f5;
            color: #333;
            padding: 20px;
        }

        h1 {
            font-size: 2rem;
            margin-bottom: 20px;
            color: #4b0082;
            text-align: center;
        }

        form {
            margin-top: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
            background-color: white;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #4b0082;
            color: white;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        input[type="text"] {
            width: 80%; /* Width of text inputs */
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1rem; /* Font size for input */
        }

        input[type="submit"] {
            background-color: #4b0082; /* Button color */
            color: white; /* Button text color */
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1rem; /* Font size for button */
            margin-top: 20px;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #6a0dad; /* Change button color on hover */
        }
    </style>
    </head>
    <body>
        <h1>${requestScope.plan.name} Detail</h1>
        
        
        
        <form action="detail" method="POST">
            <table border="1px">
                <thead>
                    <tr>
                        <td colspan="2">Product</td>
                        <c:forEach items="${requestScope.plan.header}" var="h">
                            <td>${h.product.name}</td>
                        </c:forEach>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.datePlan}" var="d">
                    <input type="hidden" name="date" value="${d}">
                        <tr>
                            
                            <td rowspan="3">${d}</td>
                            <td>K1<input type="hidden" name="sid${d}" value="1"></td>
                            <c:forEach items="${requestScope.plan.header}" var="h">
                            <input type="hidden" name="hid${d}" value="${h.id}">
                            <td><input type="text" name="quantity${h.id}1${d}"
                                       <c:forEach items="${requestScope.details}" var="detail">
                                           <c:if test="${(detail.header.id eq h.id) and (detail.date eq d)  and (detail.sid eq 1)}"> value="${detail.quantity}"</c:if>
                                       </c:forEach>></td>
                            </c:forEach>
                            
                        </tr>
                        <tr>
                            <td>K2<input type="hidden" name="sid${d}" value="2"></td>
                            <c:forEach items="${requestScope.plan.header}" var="h">
                           
                                
                                           <td><input type="text" name="quantity${h.id}2${d}"
                                       <c:forEach items="${requestScope.details}" var="detail">
                                           <c:if test="${(detail.header.id eq h.id) and (detail.date eq d)  and (detail.sid eq 2)}"> value="${detail.quantity}"</c:if>
                                       </c:forEach>></td>
                            </c:forEach>      
                        </tr>
                        <tr>
                            <td>K3<input type="hidden" name="sid${d}" value="3"></td>
                            <c:forEach items="${requestScope.plan.header}" var="h">
                           
                                <td><input type="text" name="quantity${h.id}3${d}"
                                       <c:forEach items="${requestScope.details}" var="detail">
                                           <c:if test="${(detail.header.id eq h.id) and (detail.date eq d)  and (detail.sid eq 3)}"> value="${detail.quantity}"</c:if>
                                       </c:forEach>></td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
        </table>
            <input type="submit" value="Save">
        </form>
        
       
        
        
    </body>
</html>
