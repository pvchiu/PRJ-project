<%-- 
    Document   : create
    Created on : Oct 31, 2024, 12:02:53 PM
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
        body {
            font-family: Arial, sans-serif; /* Font for the body */
            background-color: #f4f4f4; /* Light background color */
            margin: 0; /* Remove default margin */
            padding: 20px; /* Add padding to body */
        }
        
        h2 {
            text-align: center; /* Center the heading */
            color: #333; /* Darker text color */
        }

        form {
            background-color: #fff; /* White background for the form */
            padding: 20px; /* Padding inside the form */
            border-radius: 5px; /* Rounded corners */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Subtle shadow */
            max-width: 600px; /* Maximum width of the form */
            margin: auto; /* Center the form */
        }

        label {
            display: block; /* Make labels block elements */
            margin: 10px 0 5px; /* Spacing above and below labels */
            color: #555; /* Lighter text color */
        }

        input[type="text"],
        input[type="date"],
        select {
            width: 100%; /* Full width inputs */
            padding: 10px; /* Padding inside inputs */
            border: 1px solid #ccc; /* Light border */
            border-radius: 4px; /* Rounded corners */
            box-sizing: border-box; /* Include padding in width */
            margin-bottom: 15px; /* Space below inputs */
        }

        input[type="radio"] {
            margin-right: 5px; /* Space between radio and label */
        }

        input[type="submit"] {
            background-color: #4CAF50; /* Green background for button */
            color: white; /* White text */
            border: none; /* No border */
            border-radius: 4px; /* Rounded corners */
            padding: 10px; /* Padding for button */
            cursor: pointer; /* Pointer cursor on hover */
            width: 100%; /* Full width button */
            font-size: 16px; /* Larger font size */
        }

        input[type="submit"]:hover {
            background-color: #45a049; /* Darker green on hover */
        }
    </style>
    </head>
    <body>
        <jsp:include page="../master/shortprofile.jsp"></jsp:include>
        
    <h2>Create New Employee</h2>
    <form action="create" method="POST">
        Name: <input type="text" name="name" required/> <br/>
        Gender: 
        <input type="radio" name="gender" value="male" checked="checked"/> Male
        <input type="radio" name="gender" value="female"/> Female <br/>
        Dob: <input type="date" name="dob" required/> <br/>
        Address: <input type="text" name="address" required/> <br/>
        Phone Number: <input type="text" name="phonenumber" required/> <br/>

        Department: 
        <select name="did" required>
            <c:forEach items="${departments}" var="d"> <!-- Change 'requestScope.depts' to 'departments' -->
                <option value="${d.id}">${d.name}</option>
            </c:forEach>
        </select> <br/>

        Salary: 
        <select name="sid" required>
            <c:forEach items="${salaries}" var="s"> <!-- Change 'requestScope.salaries' to 'salaries' -->
                <option value="${s.id}">${s.salary}</option>
            </c:forEach>
        </select> <br/>

        <input type="submit" value="Save"/>
    </form>

    </body>
</html>
