<%-- 
    Document   : UpdateProduct
    Created on : Jun 22, 2024, 8:41:14 AM
    Author     : ACER
    Usage      : Change image product file
--%>
<%@page import="controller.Navigation"%>
<%@page import="model.dao.ProductDAO"%>
<%@page import="controller.Action"%>
<%@page import="model.Product"%>
<%@page import="java.util.List"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Image Product</title>
    </head>
    <body>
        <c:if test="${login == null}">
            <jsp:forward page = "Login.jsp"></jsp:forward>
        </c:if>

        <jsp:include page="Dashboard.jsp"></jsp:include>

        <%
            String updateProduct = request.getParameter("product");
            Product product = new ProductDAO(getServletContext()).getObjectById(updateProduct);
            int type = product.getType().getTypeId();
        %>
        <div class="container" style="width: 60%" >

            <h2 class="my-4 text-center">Change image product</h2>
            <form  action="MainController" method="POST" enctype="multipart/form-data">
                <div class="row mb-3">
                    <label class="col-form-label col-sm-2 fw-bold" for="id">Product ID</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control"  placeholder="Enter product id" minlength="2" maxlength="30" name="id" value="<%= product.getProductId()%>" readonly="">
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-form-label col-sm-2 fw-bold" for="name">Product name </label>
                    <div class="col-sm-10">          
                        <input type="text" class="form-control" placeholder="Enter product name" minlength="2" maxlength="30" name="name" value="<%= product.getProductName()%>" required="">
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-form-label col-sm-2 fw-bold" for="file">Product image </label>
                    <div class="col-sm-10">          
                        <input type="file" class="form-control"  accept=".jpg, .png"  aria-label="Product image" required=""  name="file">
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-sm-10">
                        <img src="<%= "." + product.getProductImage()%>"  alt="productImg"  width="500px" height="300px">
                    </div>
                </div>

                <div class="row mb-3">        
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-outline-secondary" name="action" value="<%= Action.CHANGE_IMG_PRODUCT%>">Submit</button>
                    </div>

                    <div class="col-sm-offset-2 col-sm-2" >
                        <a href="<%= Navigation.LIST_PRODUCT%>" class="btn btn-warning" style="margin-left: 60px">Return</a>
                    </div>
                </div>
            </form>

        </div>

        <script>

        </script>
    </body>
</html>

