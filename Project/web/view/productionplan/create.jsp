<%-- 
    Document   : create
    Created on : Oct 16, 2024, 11:19:35 AM
    Author     : pvchiu
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
            max-width: 600px; /* Maximum width for the form */
            margin: auto; /* Center the form */
            background-color: white; /* White background for the form */
            padding: 20px; /* Padding inside the form */
            border-radius: 8px; /* Rounded corners */
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Subtle shadow */
        }

        label {
            margin-top: 10px;
            font-weight: bold;
            display: block; /* Stack labels on top */
        }

        input[type="text"], input[type="date"], select {
            width: calc(100% - 20px); /* Full width with padding */
            padding: 10px; /* Padding inside input fields */
            margin-top: 5px; /* Space above inputs */
            margin-bottom: 15px; /* Space below inputs */
            border: 1px solid #ddd; /* Border for inputs */
            border-radius: 4px; /* Rounded corners */
            font-size: 1rem; /* Font size for inputs */
        }

        table {
            width: 100%; /* Full width for the table */
            border-collapse: collapse; /* Remove spacing between borders */
            margin-top: 20px; /* Space above the table */
        }

        th, td {
            padding: 12px; /* Padding in table cells */
            border: 1px solid #ddd; /* Border for table cells */
            text-align: left; /* Align text to the left */
        }

        th {
            background-color: #4b0082; /* Header background color */
            color: white; /* Header text color */
        }

        input[type="submit"] {
            background-color: #4b0082; /* Button background color */
            color: white; /* Button text color */
            padding: 10px 20px; /* Padding for button */
            border: none; /* No border */
            border-radius: 4px; /* Rounded corners */
            cursor: pointer; /* Pointer cursor on hover */
            font-size: 1rem; /* Font size for button */
            margin-top: 20px; /* Space above the button */
            display: block; /* Center the button */
            width: 100%; /* Full width for the button */
            transition: background-color 0.3s; /* Transition for hover effect */
        }

        input[type="submit"]:hover {
            background-color: #6a0dad; /* Change button color on hover */
        }
    </style>
    </head>
    <body>
        <form action="create" method="POST">
            Plan Name: <input type="text" name="name"/> <br/>
            From: <input type="date" name="from"/> To: <input type="date" name="to"/> <br/>
            Workshop: <select name="did">
                <c:forEach items="${requestScope.depts}" var="d">
                    <option value="${d.id}">${d.name}</option>
                </c:forEach>
            </select>
            <br/>
            <table border="1px">
                <tr>
                    <td>Product</td>
                    <td>Quantity</td>
                    <td>Estimated Effort</td>
                </tr>
                <c:forEach items="${requestScope.products}" var="p">
                <tr>
                    <td>${p.name}<input type="hidden" name="pid" value="${p.id}"></td>
                    <td><input type="text" name="quantity${p.id}"/></td>
                    <td><input type="text" name="effort${p.id}"/></td>
                </tr>    
                </c:forEach>
            </table>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
