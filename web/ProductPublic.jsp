<%-- 
    Document   : ProductPublic
    Created on : Jun 21, 2024, 2:34:54 PM
    Author     : ACER
    Usage      : Public product file, includes carts rendering all product information
--%>
<%@page import="context.Action"%>
<%@page import="context.Navigation"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="format" uri="/WEB-INF/tlds/myTag.tld" %>
<%@page import="entities.CategoriesBLO"%>
<%@page import="entities.Products"%>
<%@page import="entities.ProductsBLO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product View Page</title>
        <link rel="shortcut icon" href="images/web_logo.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body >

        <h1 class="mt-5 text-center" style="color: #4682B4" id="productView">Product View</h1>
        <div class="dropdown">
            <button type="button" class="btn btn-info dropdown-toggle" data-bs-toggle="dropdown" style="margin-left:80%">
                List by Category
            </button>
            <ul class="dropdown-menu">
                <c:set var="list" value="<%= new CategoriesBLO().listAll()%>"></c:set>
                <c:forEach var="i" items="${list}">
                    <li><a class="dropdown-item" href="GuestController?action=<%= Action.LIST_PRODUCT_BY_CATE_PUBLIC%>&cate=${i.typeId}#productView" id="${i.typeId}" > ${i.categoryName} </a></li>
                    </c:forEach>
                <li><a class="dropdown-item" href="GuestController?action=<%= Action.LIST_PRODUCT_BY_CATE_PUBLIC%>&cate=0#productView" id="0" > Tất cả </a></li>
            </ul>
        </div>
        <div class="container" >
            <div class="row" style="margin: 0 auto">
                <c:choose>
                    <c:when test="${listProductPublic != null}">
                        <c:set var="list" value="${listProductPublic}"></c:set>
                    </c:when>
                    <c:otherwise>
                        <c:set var="list" value="<%= new ProductsBLO().listAll() %>"></c:set>
                    </c:otherwise>
                </c:choose>

                <c:forEach var="p" items="${list}" >
                    <div class="card col-md-4 mt-3 mx-3" style="width: 18rem; height: 32rem" id="${p.productId}">
                        <div>
                            <img src=".${p.productImage}" class="card-img-top" alt="productImg" height="300px">
                        </div>
                        <div class="card-body text-center">
                            <div>
                                <h5 class="card-title" style="color: #008080; height: 4rem">${p.productName}</h5>
                            </div>
                            <div>
                                <c:set var="discount" value="${p.discount}" />                            
                                <c:choose>
                                    <c:when test="${discount != 0}">
                                        <h5 style="color: green">Price: <format:style price="${p.price}" sale="${p.discount}" /></h5>
                                        <h6 style="color: red; text-decoration: line-through; font-weight: 300">Before <format:style price="${p.price}" sale="${0}"></format:style></h6>
                                    </c:when>
                                    <c:otherwise>
                                        <h5 style="color: green">Price: <format:style price="${p.price}" sale="0" /></h5>

                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <c:url scope="request" var="productDetailUrl" value="GuestController">
                            <c:param name="action" value="<%= Navigation.PRODUCT_DETAIL%>"/>
                            <c:param name="product" value="${p.productId}"></c:param>
                        </c:url>
                        <a href="${productDetailUrl}" class="btn btn-primary" style="margin-bottom: 10px; background-color: #48D1CC; border: none">Detail</a>
                    </div>
                </c:forEach>
            </div>
        </div>

    </body>
</html>
