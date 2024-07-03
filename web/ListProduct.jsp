<%-- 
    Document   : ListProduct
    Created on : Jun 21, 2024, 12:05:17 PM
    Author     : ACER
    Usage      : List products file
--%>

<%@page import="controller.Action"%>
<%@page import="controller.Navigation"%>
<%@page import="entities.Products"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Product Page</title>
        <link rel="shortcut icon" href="images/web_logo.png">
    </head>
    <body>
        <c:if test="${login == null}">
            <jsp:forward page = "Login.jsp"></jsp:forward>
        </c:if>

        <jsp:include page="Dashboard.jsp" ></jsp:include>

            <h2 class="text-center mt-4">List All Products</h2>
            <table class="table table-bordered container mt-3" >
                <thead class="table-info" style="vertical-align: middle; text-align: center">
                    <tr>
                        <th scope="col">Product name</th>
                        <th scope="col" >Product brief</th>
                        <th scope="col">Type Id</th>
                        <th scope="col">Posted date</th>
                        <th scope="col">Unit</th>
                        <th scope="col">Price</th>
                        <th scope="col">Discount</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>                 

                <c:forEach  items="${listProduct}" var="i">

                    <tr id="${i.productId}" style="vertical-align: middle; text-align: center">
                        <td>${i.productName}</td>
                        <td>${i.brief}</td>
                        <td>${i.typeId.typeId}</td>
                        <td> <fmt:formatDate value="${i.postedDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>${i.unit}</td>
                        <td>${i.price}</td>
                        <td>${i.discount}</td>
                        <td>
                            <c:url scope="request" var="updateUrl" value="<%= Navigation.UPDATE_PRODUCT%>">
                                <c:param name="product" value="${i.productId}"></c:param>
                            </c:url>

                            <c:url scope="request" var="deleteUrl" value="<%= Action.DELETE_PRODUCT%>">
                                <c:param name="product" value="${i.productId}"></c:param>
                            </c:url>
                            <c:url scope="request" var="changeUrl" value="<%= Navigation.CHANGE_IMG_PRODUCT%>">
                                <c:param name="product" value="${i.productId}"></c:param>
                            </c:url>
                            <a href="${updateUrl}" class="btn btn-warning ">Update</a>
                            <a href="${deleteUrl}" class="btn btn-danger mt-2">Delete</a>
                            <a href="${changeUrl}" class="btn btn-info mt-2">Photo</a>
                        </td>
                    </tr>  

                </c:forEach>  
            </tbody>
        </table>
    </body>
</html>
