<%-- 
    Document   : home
    Created on : Nov 3, 2024, 10:21:25 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <title>Trang chủ</title>
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
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        header {
            background-color: #4b0082;
            color: white;
            padding: 20px;
            text-align: center;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        nav {
            background-color: #343a40;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        nav ul {
            list-style-type: none;
            display: flex;
            justify-content: space-around; /* Change to space-around or space-between */
            padding: 10px 0;
            max-width: 800px; /* Set a maximum width for the navigation */
            margin: 0 auto; /* Center the ul within the nav */
        }

        nav li {
            margin: 0 10px; /* Reduce margin for less space */
        }

        nav a {
            color: #fff;
            text-decoration: none;
            padding: 10px 15px;
            border-radius: 4px;
            transition: background-color 0.3s;
            background-color: #4b0082;
        }

        nav a:hover {
            background-color: #6a0dad;
        }

        main {
            flex: 1; /* Allow main to grow and fill available space */
            display: flex; /* Use flexbox for centering */
            flex-direction: column; /* Arrange children vertically */
            justify-content: center; /* Center content vertically */
            align-items: center; /* Center content horizontally */
            padding: 20px; /* Add padding for space */
            text-align: center; /* Center text */
        }

        footer {
            background-color: #4b0082;
            color: white;
            text-align: center;
            padding: 10px 0;
            margin-top: auto;
        }

        h1 {
            font-size: 2.5rem;
            margin-bottom: 10px;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            nav ul {
                flex-direction: column;
                padding: 0;
            }

            nav li {
                margin: 5px 0;
            }
        }
        </style>
    </head>
    <body>

        <header>
            <h1>Trang chủ</h1>
        </header>

        <!-- Thanh menu với các mục ProductionPlan, Employee, Attendant -->
        <nav>
            <ul>
                <li><a href="/Project/productionplan/list">ProductionPlan</a></li>
                <li><a href="/Project/employee/list">Employee</a></li>
                <li><a href="#">Attendant</a></li>
            </ul>
        </nav>

        <main>
            <h2>Welcome to Our Management System</h2>
            <p>Explore the features using the menu above.</p>
            
        </main>

        <footer>
            &copy; 2024 Your Company
        </footer>

    </body>
</html>