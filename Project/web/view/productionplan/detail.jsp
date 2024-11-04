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
        /* Reset and base styling */
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        body {
            background-color: #f0f2f5;
            padding: 20px;
        }

        h1 {
            font-size: 2rem;
            color: #4b0082;
            text-align: center;
            margin-bottom: 20px;
        }

        /* Form styling */
        form {
            margin-top: 20px;
        }

        /* Table styling */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
            background-color: white;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #4b0082;
            color: white;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        /* Input fields */
        input[type="text"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1rem;
            text-align: center;
        }

        /* Button styling */
        .submit-container {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        input[type="submit"] {
            background-color: #4b0082;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1rem;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #6a0dad;
        }
    </style>
</head>
<body>
    <h1>${requestScope.plan.name} Detail</h1>
    
    <form action="detail" method="POST">
        <table>
            <thead>
                <tr>
                    <th rowspan="2">Shift</th>
                    <c:forEach items="${requestScope.plan.header}" var="h">
                        <th colspan="3">${h.product.name}</th>
                    </c:forEach>
                </tr>
                <tr>
                    <c:forEach items="${requestScope.plan.header}" var="h">
                        <th>K1</th>
                        <th>K2</th>
                        <th>K3</th>
                    </c:forEach>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.datePlan}" var="d">
                    <input type="hidden" name="date" value="${d}">
                    <tr>
                        <td>${d}</td>
                        <c:forEach items="${requestScope.plan.header}" var="h">
                            <td><input type="text" name="quantity${h.id}1${d}"
                                <c:forEach items="${requestScope.details}" var="detail">
                                    <c:if test="${(detail.header.id eq h.id) and (detail.date eq d)  and (detail.sid eq 1)}"> value="${detail.quantity}"</c:if>
                                </c:forEach>
                                ></td>
                            <td><input type="text" name="quantity${h.id}2${d}"
                                <c:forEach items="${requestScope.details}" var="detail">
                                    <c:if test="${(detail.header.id eq h.id) and (detail.date eq d)  and (detail.sid eq 2)}"> value="${detail.quantity}"</c:if>
                                </c:forEach>
                                ></td>
                            <td><input type="text" name="quantity${h.id}3${d}"
                                <c:forEach items="${requestScope.details}" var="detail">
                                    <c:if test="${(detail.header.id eq h.id) and (detail.date eq d)  and (detail.sid eq 3)}"> value="${detail.quantity}"</c:if>
                                </c:forEach>
                                ></td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <div class="submit-container">
            <input type="submit" value="Save">
        </div>
    </form>
</body>
</html>
