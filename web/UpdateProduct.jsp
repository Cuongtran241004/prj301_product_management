<%-- 
    Document   : UpdateProduct
    Created on : Jun 22, 2024, 8:41:14 AM
    Author     : ACER
    Usage      : Update a product file, include form to update a product
--%>
<%@page import="controller.Navigation"%>
<%@page import="model.dao.ProductDAO"%>
<%@page import="controller.Action"%>
<%@page import="model.Product"%>
<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@page import="model.dao.CategoryDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product</title>
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

            <h2 class="my-4 text-center">Update product</h2>
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
                    <label class="col-form-label col-sm-2 fw-bold" for="brief"> Brief </label>
                    <div class="col-sm-10">          
                        <textarea type="text" class="form-control" rows="7"  name="brief" required=""><%= product.getBrief()%></textarea>
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-form-label col-sm-2 fw-bold" for="unit">Unit </label>
                    <div class="col-sm-10">          
                        <input type="text" class="form-control"  placeholder="Enter unit" value="<%= product.getUnit()%>" name="unit" required="">
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-form-label col-sm-2 fw-bold" for="price">Price </label>
                    <div class="col-sm-10">          
                        <input type="number" class="form-control"  placeholder="Enter price" name="price" value="<%= product.getPrice()%>">
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-form-label col-sm-2 fw-bold" for="discount">Discount </label>
                    <div class="col-sm-10">          
                        <input type="number" class="form-control"  placeholder="Enter discount" name="discount" value="<%= product.getDiscount()%>">
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-form-label col-sm-2 fw-bold" for="type">Type name </label>
                    <div class="col-sm-10">          

                        <select class="form-select"  name="type" >
                            <%
                                CategoryDAO dao = new CategoryDAO(getServletContext());
                                List<Category> list = dao.listAll();
                                for (Category c : list) {
                                    if (c.getTypeId() == type) {
                            %>

                            <option value="<%= c.getTypeId()%>" selected=""><%= c.getCategoryName()%></option>

                            <%} else {
                            %>
                            <option value="<%= c.getTypeId()%>" ><%= c.getCategoryName()%></option>
                            <%}}%>
                        </select>
                    </div>
                </div>

                <div class="row mb-3">        
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-outline-secondary" name="action" value="<%= Action.UPDATE_PRODUCT %>">Submit</button>
                    </div>
                    
                     <div class="col-sm-offset-2 col-sm-2" >
                        <a href="<%= Navigation.LIST_PRODUCT %>" class="btn btn-warning" style="margin-left: 60px">Cancel</a>
                    </div>
                </div>
            </form>

        </div>

        <script>

        </script>
    </body>
</html>

