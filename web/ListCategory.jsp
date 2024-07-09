<%-- 
    Document   : ListAccount
    Created on : Jun 12, 2024, 8:08:32 AM
    Author     : ACER
    Usage      : List categories file
--%>

<%@page import="context.Navigation"%>
<%@page import="context.Action"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>List Categories Page</title>
        <link rel="shortcut icon" href="images/web_logo.png">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" 
              integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" 
              crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>

    <body >

        <jsp:include page="Dashboard.jsp" ></jsp:include>

            <h2 class="text-center mt-4">List All Categories</h2>
            <table class="table  container mt-3">
                <thead class="table-info">
                    <tr>
                        <th scope="col">Type ID</th>
                        <th scope="col">Category name</th>
                        <th scope="col">Memo</th>                   
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>               
                <c:forEach items="${listCategory}" var="a">
                    <tr id="${a.typeId}">
                        <td>${a.typeId}</td>
                        <td>${a.categoryName}</td>
                        <td>
                            <c:choose>
                                <c:when test="${a.memo != null}">${a.memo}</c:when>
                                <c:otherwise></c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <c:url scope="request" var="updateUrl" value="<%= Navigation.UPDATE_CATEGORY%>">
                                <c:param name="category" value="${a.typeId}"></c:param>
                            </c:url>

                            <c:url scope="request" var="deleteUrl" value="CategoryController">
                                <c:param name="action" value="<%= Action.DELETE_CATEGORY %>"></c:param>
                                <c:param name="category" value="${a.typeId}"></c:param>
                            </c:url>
                            <a href= "${updateUrl}" style="color: black; margin: 10px"><i class="fa-solid fa-pen"></i></a>
                            <a href= "${deleteUrl}" onclick="return confirm('Do you want to delete this category?')" style="color: black; margin: 10px"><i class="fa-solid fa-trash"></i></a>
                        </td>
                    </tr>  

                </c:forEach> 
            </tbody>
        </table>


    </body>
</html>
