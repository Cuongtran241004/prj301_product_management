<%-- 
    Document   : ListAccount
    Created on : Jun 12, 2024, 8:08:32 AM
    Author     : ACER
--%>

<%@page import="controller.Navigation"%>
<%@page import="controller.Action"%>
<%@page import="java.util.List"%>
<%@page import="model.Category"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>List Categories Page</title>

    </head>

    <body >
        <c:if test="${login == null}">
            <jsp:forward page = "Login.jsp"></jsp:forward>
        </c:if>

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

                            <c:url scope="request" var="deleteUrl" value="<%= Action.DELETE_CATEGORY%>">
                                <c:param name="category" value="${a.typeId}"></c:param>
                            </c:url>
                            <a href= "${updateUrl}" class="btn btn-primary">Update</a>
                            <a href= "${deleteUrl}" class="btn btn-danger">Delete</a>
                        </td>
                    </tr>  

                </c:forEach> 
            </tbody>
        </table>


    </body>
</html>
