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
            line-height: 1.6;
            padding: 20px; /* Add padding around the body */
        }

        h1 {
            font-size: 2rem;
            margin-bottom: 20px;
            color: #4b0082; /* Title color */
            text-align: center; /* Center align title */
        }

        table {
            width: 100%; /* Make the table take full width */
            border-collapse: collapse; /* Merge borders for a cleaner look */
            margin-top: 20px; /* Add space above the table */
            background-color: white; /* Table background color */
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Add shadow for depth */
        }

        th, td {
            padding: 12px; /* Padding inside cells */
            text-align: left; /* Align text to the left */
            border: 1px solid #ddd; /* Light border around cells */
        }

        th {
            background-color: #4b0082; /* Header background color */
            color: white; /* Header text color */
            font-weight: bold; /* Bold header text */
        }

        tr:nth-child(even) {
            background-color: #f9f9f9; /* Zebra striping for rows */
        }

        tr:hover {
            background-color: #f1f1f1; /* Highlight row on hover */
        }

        a {
            color: #4b0082; /* Link color */
            text-decoration: none; /* Remove underline */
            transition: color 0.3s; /* Smooth color transition */
        }

        a:hover {
            color: #6a0dad; /* Change link color on hover */
        }

        /* Button styles */
        .create-button {
            display: inline-block; /* Make the links behave like buttons */
            padding: 10px 20px; /* Add padding for size */
            margin: 5px; /* Space between buttons */
            background-color: #4CAF50; /* Green background for Home button */
            color: white; /* White text color */
            border: none; /* Remove default border */
            border-radius: 5px; /* Rounded corners */
            text-align: center; /* Center the text */
            text-decoration: none; /* Remove underline */
            font-size: 16px; /* Increase font size */
            transition: background-color 0.3s, transform 0.2s; /* Smooth transition for hover effect */
        }

        .create-button:hover {
            background-color: #45a049; /* Darker green on hover for Home button */
            transform: translateY(-2px); /* Lift effect on hover */
        }

        /* Additional style for Create Plan button */
        .create-button:nth-of-type(2) {
            background-color: #2196F3; /* Blue background for Create Plan button */
        }

        .create-button:nth-of-type(2):hover {
            background-color: #1e88e5; /* Darker blue on hover for Create Plan button */
        }
    </style>
</head>
<body>
    <jsp:include page="../master/shortprofile.jsp"></jsp:include>
    <h1>Production Plan List</h1>
    <div style="margin-bottom: 20px;">
        <a href="/Project/view/home/home.jsp" class="create-button">Home</a>
        <a href="/Project/productionplan/create" class="create-button">Create Plan</a>
    </div>
    <table border="1px">
        <tr>
            <td style="font-weight: bold">ID</td>
            <td style="font-weight: bold">Name</td>
            <td style="font-weight: bold">StartDate</td>
            <td style="font-weight: bold">EndDate</td>
            <td style="font-weight: bold">Quantity</td>
            <td style="font-weight: bold">Product</td>
            <td style="font-weight: bold">Estimation</td>
        </tr>

        <c:forEach items="${requestScope.pl}" var="p">
            <tr class="even">
                <td rowspan="${p.header.size()}">${p.id}</td>
                <td rowspan="${p.header.size()}"><a href="detail?plid=${p.id}">${p.name}</a> </td>
                <td rowspan="${p.header.size()}">${p.start}</td>
                <td rowspan="${p.header.size()}">${p.end}</td>
                <td>
                    ${p.header[0].quantity}<br/>
                </td>
                <td>
                    ${p.header[0].product.name}<br/>
                </td>
                <td>
                    ${p.header[0].estimatedeffort}<br/>
                </td>
            </tr>
            <c:forEach var="i" begin="1" end="${p.header.size()-1}">
                <tr>
                    <td>${p.header[i].quantity}<br/></td>
                    <td>${p.header[i].product.name}<br/></td>
                    <td>${p.header[i].estimatedeffort}<br/></td>
                </tr>
            </c:forEach>
        </c:forEach>
    </table>
</body>
</html>
