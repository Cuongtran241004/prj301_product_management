<%-- 
    Document   : Home
    Created on : Jun 19, 2024, 2:50:27 PM
    Author     : ACER
--%>

<%@page import="model.Account"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        
    </head>
    <body>
    <c:if test="${login == null}">
        <jsp:forward page = "Login.jsp"></jsp:forward>
        </c:if>
    <jsp:include page="Dashboard.jsp"></jsp:include>
    <%
        boolean check = false;

        Account a = (Account) session.getAttribute("login");
        if (a.getRoleInSystem() == 1) {
            check = true;
        }

    %>
    <h2 class="text-center mt-5">Các tính năng có thể sử dụng</h2>
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
