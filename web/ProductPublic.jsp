<%-- 
    Document   : ProductPublic
    Created on : Jun 21, 2024, 2:34:54 PM
    Author     : ACER
    Usage      : Public product file, includes carts rendering all product information
--%>

<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@page import="model.dao.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product View Page</title>
    </head>
    <body >
        <h1 class="mt-5 text-center" style="color: #4682B4" id="productView">Product View</h1>
        <div class="container" >
            <div class="row" style="margin: 0 auto">
                <%
                    ProductDAO dao = new ProductDAO(getServletContext());
                    List<Product> list = dao.listAll();
                    // Using this library to get currency of Vietnam
                    Locale localeVN = new Locale("vi", "VN");
                    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
                    
                    for (Product product : list) {
                        double value = product.getDiscount();
                        double per = value / 100;
                        double sale = product.getPrice() * (1- per);
                        String price = currencyVN.format(product.getPrice());
                        String sprice = currencyVN.format(sale);
                %>

                <div class="card col-md-4 mt-3 mx-3" style="width: 18rem; height: 32rem" id="<%= product.getProductId()%>">
                    <div>
                        <img src="<%= "." + product.getProductImage()%>" class="card-img-top" alt="productImg" height="300px">
                    </div>
                    <div class="card-body text-center">
                        <div>
                            <h5 class="card-title" style="color: #008080; height: 4rem"><%= product.getProductName()%></h5>
                        </div>
                        <div>
                            <% if (product.getDiscount() != 0) {%>
                            <h5 style="color: green">Price: <%= sprice %></h5>
                            <h6 style="color: red; text-decoration: line-through; font-weight: 300">Before <%= price%></h6>
                            <%} else {%>
                            <h5 style="color: green">Price: <%= price %></h5>
                            <%}%>
                        </div>
                    </div>

                    <button type="button" class="btn btn-primary" data-bs-toggle="popover" data-bs-placement="top" data-bs-trigger="hover" style="background-color: #48D1CC; border: none; margin-bottom: 10px" title="<%= product.getProductName()%>" data-bs-content="<%= product.getBrief()%>">
                        Details
                    </button>

                </div>


                <%                    }
                %>
            </div>
        </div>


    </body>
</html>
