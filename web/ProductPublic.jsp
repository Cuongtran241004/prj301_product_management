<%-- 
    Document   : ProductPublic
    Created on : Jun 21, 2024, 2:34:54 PM
    Author     : ACER
    Usage      : Public product file, includes carts rendering all product information
--%>

<%@page import="controller.Navigation"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="format" uri="/WEB-INF/tlds/myTag.tld" %>
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
    </head>
    <body >
        <h1 class="mt-5 text-center" style="color: #4682B4" id="productView">Product View</h1>
        <div class="container" >
            <div class="row" style="margin: 0 auto">

                <c:forEach var="p" items="<%= new ProductsBLO().listAll()%>" >
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

                        <c:url scope="request" var="productDetailUrl" value="<%= Navigation.PRODUCT_DETAIL%>">
                            <c:param name="product" value="${p.productId}"></c:param>
                        </c:url>
                        <a href="${productDetailUrl}" class="btn btn-primary" style="margin-bottom: 10px; background-color: #48D1CC; border: none">Detail</a>

                    </div>
                </c:forEach>
            </div>
        </div>

    </body>
</html>
