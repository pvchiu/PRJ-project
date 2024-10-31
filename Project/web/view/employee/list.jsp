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
        
        <form action="list" method="GET"> 
            Id: <input type="text" name="id" value="${param.id}"/> <br/>
            Name: <input type="text" name="name" value="${param.name}"/> <br/>
            Gender: 
            <input type="radio" ${param.gender eq "male" ? "checked='checked'" : ""} 
                   name="gender" value="male"/> Male
            <input type="radio" ${param.gender eq "female" ? "checked='checked'" : ""}
                   name="gender" value="female"/> Female
            <input type="radio" ${param.gender eq null or param.gender eq "both" ? "checked='checked'" : ""} 
                   name="gender" value="both"/> Both
            <br/>
            Dob - From: <input type="date" name="dobFrom" value="${param.dobFrom}"/> 
            - To: <input type="date" name="dobTo" value="${param.dobTo}"/> <br/>
            Phone Number: <input type="text" name="phonenumber" value="${param.phonenumber}"/> <br/>
            Address: <input type="text" name="address" value="${param.address}"/> <br/>
            Department: 
            <select name="did">
                <option value="-1">--------------ALL--------------</option>
                <c:forEach items="${requestScope.depts}" var="d">
                    <option 
                        ${param.did ne null and param.did eq d.id ? "selected='selected'" : ""}
                        value="${d.id}">${d.name}</option>
                </c:forEach>
            </select> 
            <br/>
            <input type="submit" value="Search"/>
        </form>

        
        <table border="1px">
            <tr>
                <td>Employee ID</td>
                <td>Employee Name</td>
                <td>Address</td>
                <td>Department</td>
                <td>Salary</td>
                <td>Phone number</td>
                <td>Gender</td>
                <td>Dob</td>
                
                
                
            </tr>
            <c:forEach items="${requestScope.emps}" var="e">
            <tr>
                <td>${e.id}</td>
                <td>${e.name}</td>
                <td>${e.address}</td>
                <td>${e.did.name}</td>
                <td>${e.sid.salary}</td>
                <td>${e.phonenumber}</td>
                <td>${e.gender?"male":"female"}</td>
                <td>${e.dob}</td>
                
            </tr>
        </c:forEach>
            </table>
    </body>
</html>
