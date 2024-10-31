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
