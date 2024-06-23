<%-- 
    Document   : login
    Created on : Jun 11, 2024, 12:25:23 PM
    Author     : ACER
    Usage      : Login file, include form to login to access private page
--%>

<%@page import="controller.Navigation"%>
<%@page import="controller.Action"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" type="text/css" href="css/login.css">
        <!-- Latest compiled and minified CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="container" >
        <h1 class="my-4">Welcome to Cường Store</h1>

        <c:if test="${login != null}">
            <jsp:forward page="<%= Navigation.MAIN_DASHBOARD %>"></jsp:forward>
        </c:if>

        <!--<p style="color: red; font-weight: bold">
        <%=   (request.getAttribute("error") != null) ? request.getAttribute("error") : "" %>
        </p>-->
        <form action="MainController" method="POST" class="row" style="width: 70%; margin: 0 auto">
            <div class="imgcontainer col-md-6" style="margin-top: 30px">
                <img src="images/login.jpg" alt="Avatar" class="avatar">
            </div>

            <div class="col-md-6">
                <label for="username"><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="account" style="border-radius: 20px" required>

                <label for="password"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="pass" style="border-radius: 20px" required>


                <button type="submit" name="action" value="<%= Action.LOGIN %>" style="border-radius: 20px">Login</button>
                <label>
                    <input type="checkbox" checked="checked" name="remember"> Remember me
                </label>

                <%
                    if (request.getAttribute("ERROR") != null) {
                        out.println("<h6 style=\"color:red;\" > " + request.getAttribute("ERROR") + "</h6>");
                    }

                %>

            </div>

            <div class="container" style="background-color:#f1f1f1">
                <a href="Welcome.jsp"><button type="button" class="cancelbtn" style="border-radius: 20px">Cancel</button></a>
                <span class="psw"> <a href="#">Forgot password?</a></span>

            </div>




        </form>
        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
