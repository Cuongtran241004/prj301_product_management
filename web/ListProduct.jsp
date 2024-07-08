<%-- 
    Document   : ListProduct
    Created on : Jun 21, 2024, 12:05:17 PM
    Author     : ACER
    Usage      : List products file
--%>

<%@page import="entities.Categories"%>
<%@page import="entities.CategoriesBLO"%>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" 
              integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" 
              crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>



        <jsp:include page="Dashboard.jsp" ></jsp:include>

            <h2 class="text-center mt-4">List All Products</h2>
            <div class="dropdown">
                <button type="button" class="btn btn-info dropdown-toggle" data-bs-toggle="dropdown" style="margin-left:85%">
                    List by Category
                </button>
                <ul class="dropdown-menu">
                <c:set var="list" value="<%= new CategoriesBLO().listAll()%>"></c:set>
                <c:forEach var="i" items="${list}">
                    <li><a class="dropdown-item" href="MainController?action=<%= Action.LIST_PRODUCT%>&cate=${i.typeId}" id="${i.typeId}" > ${i.categoryName} </a></li>
                    </c:forEach>

                <li><a class="dropdown-item" href="MainController?action=<%= Action.LIST_PRODUCT%>&cate=0" id="0" > Tất cả </a></li>
            </ul>
        </div>
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

                <c:if test="${listProduct != null}">
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
                                <a href="${updateUrl}" style="color: black; margin: 10px"><i class="fa-solid fa-pen"></i></a>
                                <a href="${deleteUrl}" onclick="return confirm('Do you want to delete this product?')" style="color: black; margin: 10px"><i class="fa-solid fa-trash"></i></a>
                                <a href="${changeUrl}" style="color: black; margin: 10px"><i class="fa-solid fa-image"></i></a>
                            </td>
                        </tr>  
                    </c:forEach>  
                </c:if>
            </tbody>
        </table>


    </body>

</html>
