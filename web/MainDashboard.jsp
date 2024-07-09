<%-- 
    Document   : Home
    Created on : Jun 19, 2024, 2:50:27 PM
    Author     : ACER
    Usage      : include all information about Cường's Store
--%>

<%@page import="entities.Accounts"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="shortcut icon" href="images/web_logo.png">
    </head>
    <body>

        <jsp:include page="Dashboard.jsp"></jsp:include>
        <%
            boolean check = false;
            Accounts a = (Accounts) session.getAttribute("login");
            if (a.getRoleInSystem().equals("administrator")) {
                check = true;
            }
        %>
        <h2 class="text-center mt-5">Available To Access</h2>
        <%            if (check) {
        %>

        <table class="table table-striped mt-4 text-center" style="width: 50%; margin: auto">
            <thead>
                <tr>
                    <th>Accounts</th>
                    <th>Categories</th>
                    <th>Products</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Add new account</td>
                    <td>Add new category</td>
                    <td>Add new product</td>
                </tr>

                <tr>
                    <td>Get list accounts</td>
                    <td>Get list categories</td>
                    <td>Get list products</td>
                </tr>

            </tbody>
        </table>
        <%            } else {

        %>
        <table class="table table-striped mt-4 text-center" style="width: 40%; margin: auto">
            <thead>
                <tr>
                    <th>Categories</th>
                    <th>Products</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Add new category</td>
                    <td>Add new product</td>
                </tr>
                <tr>
                    <td>Get list categories</td>
                    <td>Get list products</td>
                </tr>
            </tbody>
        </table>
        <%            }
        %>    
    </body>
</html>
