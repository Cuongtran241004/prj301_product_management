<%-- 
    Document   : AddProduct
    Created on : Jun 19, 2024, 3:19:45 PM
    Author     : ACER
    Usage      : Add a product file
--%>


<%@page import="controller.Navigation"%>
<%@page import="java.util.List"%>
<%@page import="model.Category"%>
<%@page import="model.dao.CategoryDAO"%>
<%@page import="controller.Action"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Product</title>
    </head>

    <body>
        <c:if test="${login == null}">
            <jsp:forward page = "Login.jsp"></jsp:forward>
        </c:if>

        <jsp:include page="Dashboard.jsp"></jsp:include>

            <div class="container" style="width: 60%" >
                <h2 class="my-4 text-center">Add new product</h2>
                <form  action="MainController" method="POST" enctype="multipart/form-data" >
                <div class="row mb-3">
                    <label class="col-form-label col-sm-2 fw-bold" for="id">Product ID</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" minlength="2" maxlength="30" placeholder="Enter product id" name="id" required="">
                         <c:if test="${productMsg != null}">
                            <small style="color: red">* ${productMsg}</small>
                        </c:if>
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-form-label col-sm-2 fw-bold" for="name">Product name </label>
                    <div class="col-sm-10">          
                        <input type="text" class="form-control" placeholder="Enter product name" minlength="2" maxlength="30" name="name" required="">
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-form-label col-sm-2 fw-bold" for="file">Product image </label>
                    <div class="col-sm-10">          
                        <input type="file" class="form-control"  accept=".jpg, .png"  aria-label="Product image" required=""  name="file">
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-form-label col-sm-2 fw-bold" for="brief"> Brief </label>
                    <div class="col-sm-10">          
                        <textarea class="form-control" aria-label="With textarea" rows="7" name="brief" required=""></textarea>
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-form-label col-sm-2 fw-bold" for="unit">Unit </label>
                    <div class="col-sm-10">          
                        <input type="text" class="form-control"  placeholder="Enter unit" name="unit"  required="">
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-form-label col-sm-2 fw-bold" for="price">Price </label>
                    <div class="col-sm-10">          
                        <input type="number" class="form-control"  placeholder="Enter price" name="price" value="0">
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-form-label col-sm-2 fw-bold" for="discount">Discount </label>
                    <div class="col-sm-10">          
                        <input type="number" class="form-control"  placeholder="Enter discount" name="discount" value="0">
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-form-label col-sm-2 fw-bold" for="type">Type name </label>
                    <div class="col-sm-10">          
                        <select class="form-control" name="type" id="type">
                            <%
                                CategoryDAO dao = new CategoryDAO(getServletContext());
                                List<Category> list = dao.listAll();
                                for (Category c : list) {
                            %>
                            <option value="<%= c.getTypeId()%>" > <%= c.getCategoryName()%> </option>
                            <%
                                }
                            %>   
                        </select>
                    </div>
                </div>

                <div class="row mb-3">        
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-outline-secondary" name="action" value="<%= Action.ADD_PRODUCT %>">Submit</button>
                    </div>
                    
                     <div class="col-sm-offset-2 col-sm-2" >
                        <a href="<%= Navigation.LIST_PRODUCT %>" class="btn btn-warning" style="margin-left: 60px">Cancel</a>
                    </div>
                </div>
            </form>

        </div>


    </body>
</html>
